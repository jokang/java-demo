package cn.jokang.demos.tools;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://www.baeldung.com/httpclient-connection-management
 *
 * HttpClient close之后，再次发送请求请求时，连接池已经关闭。
 * 确定以后不会再请求的时候，才去调用close方法，把连接池也一起关闭了。
 **/
public class SimpleHttpClientUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleHttpClientUtil.class);
    //    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpClient httpClient = createCustomClient();


    private CloseableHttpClient createCustomClient() {
        PoolingHttpClientConnectionManager poolingConnManager
            = new PoolingHttpClientConnectionManager();
        poolingConnManager.setMaxTotal(20);
        poolingConnManager.setDefaultMaxPerRoute(15);


        final int timeout = 5;
        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(timeout * 1000)
            .setConnectionRequestTimeout(timeout * 1000)
            .setSocketTimeout(timeout * 1000)
            .build();
        return HttpClients.custom()
            .setDefaultRequestConfig(config)
            .setConnectionManager(poolingConnManager)
            .build();
    }

    /**
     * get请求 比如要请求www.meituan.com/index/
     *
     * @param host :   网址:www.meituan.com
     * @param path :   URI:/index/
     * @param params : 参数k-v
     * @param useHttps
     * @return
     */
    public String doGet(String host, String path, Map<String, String> params, boolean useHttps) {
        try {
            // 组装请求体
            String scheme = useHttps ? "https" : "http";
            URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host).setPath(path);
            for (Map.Entry<String, String> param : params.entrySet()) {
                uriBuilder.addParameter(param.getKey(), param.getValue());
            }

            HttpGet getRequest = new HttpGet(uriBuilder.build());
            LOGGER.info("GET请求HTTP链接, 请求体为:{}", JSONObject.toJSONString(getRequest));
            // 发送http请求
            return doHttpRequest(getRequest);
        } catch (URISyntaxException e) {
            LOGGER.error("URI组装异常,异常原因:{}", e.getMessage());
        }

        return "";
    }

    /**
     * post请求 比如要请求www.meituan.com/index/
     *
     * @param host     :   网址:www.meituan.com
     * @param path     :   URI:/index/
     * @param formData : 参数k-v
     * @param headers
     * @param useHttps
     * @return
     */
    public String doPost(String host, String path, Map<String, String> queryParam,
                         Map<String, String> formData, Map<String, String> headers, boolean useHttps) {
        try {
            String scheme = useHttps ? "https" : "http";
            URIBuilder uriBuilder = new URIBuilder()
                .setScheme(scheme).setHost(host).setPath(path);
            for (Map.Entry<String, String> param : queryParam.entrySet()) {
                uriBuilder.addParameter(param.getKey(), param.getValue());
            }
            HttpPost postRequest = new HttpPost(uriBuilder.build());

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            for (String key : formData.keySet()) {
                urlParameters.add(new BasicNameValuePair(key, formData.get(key)));
            }
            postRequest.setEntity(new UrlEncodedFormEntity(urlParameters));

            if (MapUtils.isNotEmpty(headers)) {
                headers.entrySet().stream().forEach(entry -> {
                    if (StringUtils.isEmpty(entry.getKey()) || StringUtils.isEmpty(entry.getValue())) {
                        return;
                    }
                    postRequest.setHeader(entry.getKey(), entry.getValue());
                });
            }
            LOGGER.info("POST请求HTTP链接,请求体为:{},\n 请求参数为:{}",
                JSONObject.toJSONString(postRequest), JSONObject.toJSONString(urlParameters));
            // 发送http请求
            return doHttpRequest(postRequest);

        } catch (URISyntaxException e) {
            LOGGER.error("URI组装异常,异常原因:{}", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("请求体组装异常, 异常原因: {}", e.getMessage());
        }
        return "";
    }


    /**
     * 发送Http请求
     *
     * @param request: 请求体
     * @return
     */
    private String doHttpRequest(HttpRequestBase request) {
        try {
            // 发送http请求
            int triedTimes = 3;
            CloseableHttpResponse httpResponse = null;
            while (triedTimes > 0) {
                httpResponse = httpClient.execute(request);
                if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    break;
                }
                Thread.sleep(500);
                triedTimes--;
            }

            // 读取请求并返回
            return readHttpResponse(httpResponse);
        } catch (Exception e) {
            LOGGER.error("请求出现异常，异常原因: {}", e.getMessage());
        } finally {
            try {
//                httpClient.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return "";
    }

    /**
     * http请求返回值读取
     *
     * @param httpResponse
     * @return
     */
    private String readHttpResponse(CloseableHttpResponse httpResponse) {
        StringBuilder result = new StringBuilder();
        try {
            if (httpResponse == null || httpResponse.getStatusLine() == null) {
                throw new RuntimeException("Failed : CloseableHttpResponse is null");
            }

            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException(
                    "Failed : HTTP error code : " + httpResponse.getStatusLine().getStatusCode());
            }


            BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));

            String line;
            LOGGER.info("Output from Server .... \n");
            while ((line = br.readLine()) != null) {
                LOGGER.info(line);
                result.append(line);
            }
            // TODO 是不是该在消费之后调用?
            // https://www.baeldung.com/httpclient-connection-management
            // Notice the EntityUtils.consume(response.getEntity) call – necessary to consume the entire content of the response (entity) so that the manager can release the connection back to the pool.
            EntityUtils.consume(httpResponse.getEntity());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return result.toString();
    }
}
