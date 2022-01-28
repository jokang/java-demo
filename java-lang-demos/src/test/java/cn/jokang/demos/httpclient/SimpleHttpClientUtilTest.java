package cn.jokang.demos.httpclient;

import cn.jokang.demos.tools.SimpleHttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class SimpleHttpClientUtilTest {
    private SimpleHttpClientUtil simpleHttpClientUtil = new SimpleHttpClientUtil();

    @Test
    public void doPostTwice() throws InterruptedException {
        Map<String, String> param = Maps.newHashMap();
        param.put("k", "v");
        String resp = simpleHttpClientUtil.doPost("localhost", "/post", param, ImmutableMap.of("k", "v"), null, false);
        Map<String, Object> r = JSON.parseObject(resp, new TypeReference<Map<String, Object>>() {
        });
        Assert.assertEquals(r.get("form"), param);

//        Thread.sleep(10000);
        resp = simpleHttpClientUtil.doPost("localhost", "/post", param, ImmutableMap.of("k", "v"), null, false);
        r = JSON.parseObject(resp, new TypeReference<Map<String, Object>>() {
        });
        Assert.assertEquals(r.get("form"), param);
    }

    @Test
    public void testSimpleGet() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://httpbin.org/");

            log.info("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            log.info("----------------------------------------");
            log.info(responseBody);
            httpclient.close();
        } finally {
            httpclient.close();
        }
    }
}