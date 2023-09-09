package cn.jokang.demos.tools;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {

    public static final String CHARSET = "UTF-8";
    private static CloseableHttpClient httpClient; // 发送请求的客户端单例
    private static PoolingHttpClientConnectionManager manager; //连接池管理类
    private static RequestConfig requestConfig;
    private static int CONNECTION_TIME_OUT = 5000;//客户端和服务器建立连接的超时时间
    private static int CONNECTION_REQUEST_TIME_OUT = 5000; //从连接池中获取连接的超时时间
    private static int READ_TIME_OUT = 7000; //客户端从服务器读取数据的超时时间
    private static int maxTotal = 200; //最大连接数
    private static int maxPerRoute = 50; //最大路由连接数量

    static {
        // 配置请求的超时设置
        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT)//建立链接时间也就是TCP三次握手时间
                .setConnectTimeout(CONNECTION_TIME_OUT)//等待数据传输的时间socketTimeout是等待数据的时间或者两个包之间的间隔时间。
                .setSocketTimeout(READ_TIME_OUT).build(); //数据传输的时间
        //用于创建普通（未加密）套接字的默认类
        ConnectionSocketFactory connectionSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
        //创建加密的套接字
        LayeredConnectionSocketFactory layeredConnectionSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
        //支持HTTP和HTTPS连接
        Registry registry = RegistryBuilder.create()
                .register("http", connectionSocketFactory)
                .register("https", layeredConnectionSocketFactory).build();
        manager = new PoolingHttpClientConnectionManager(registry);

        // 最大连接数
        manager.setMaxTotal(maxTotal);
        //路由最大连接数
        //路由:包含的数据有目标主机、本地地址、代理链、是否tunnulled、是否layered、是否是安全路由
        manager.setDefaultMaxPerRoute(maxPerRoute);

        /* HttpHost httpHost = new HttpHost(hostname, port);
        // 某个站点或者某个ip的最大连接个数
        manager.setMaxPerRoute(new HttpRoute(httpHost),maxRoute);*/

        //重试策略
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            // 重试一次
            if (executionCount > 3) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {// 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {// 目标服务器不可达
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                return false;
            }
            if (exception instanceof SSLException) {// SSL握手异常
                return false;
            }
            return false;
        };
        httpClient = HttpClients.custom().
                setConnectionManager(manager).
                setRetryHandler(httpRequestRetryHandler).
                setDefaultRequestConfig(requestConfig).build();

        IdleConnectionMonitorThread idleConnectionMonitorThread = new IdleConnectionMonitorThread(manager);
        idleConnectionMonitorThread.start();

    }

    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
            result = java.net.URLEncoder.encode(str);
        return result;
    }


    public static String get(String url) {
        if (url == null) {
            throw new RuntimeException("url is not allow at http get");
        }
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
                return result;
            } else {
                throw new RuntimeException("response.getStatusLine() is now allow url:" + url);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String post(String url, String body) {
        if (url == null) {
            throw new RuntimeException("url is not allow at http get");
        }
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {
            StringEntity stringEntity = new StringEntity(body, CHARSET);
            httpPost.setEntity(stringEntity);
            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
                return result;
            } else {
                throw new RuntimeException("response.getStatusLine() is now allow url:" + url);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {

        String param = getURLEncoderString("[{\"type\":\"RankAction\",\"names\":[\"RECSYS_TUAN_SPU_LIST_EASY_MALL.TuanSpuMergeRecaller.process\",\"RECSYS_TUAN_SPU_ORDER_TRACK.TuanSpuModelScorer.process\"]}]");
        String catResult = get("http://cat.vip.sankuai.com/cat/r/q?startDate=202107011700&endDate=202107011701&domain=com.sankuai.tmall.be.recsysrank&ip=10.37.56.41,10.37.64.81&reportName=transaction&params="+param+"&op=queryBatchData&token=06a727876f0d489b8dda7eb7a5fff0b9");
        JSONObject jsonObject = JSONObject.parseObject(catResult);



        System.out.println(jsonObject);
    }


    /**
     * 关闭连接池
     */
    public static void closeConnectionPool() {
        try {
            if (httpClient != null)
                httpClient.close();
            if (manager != null)
                manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 监控有异常的链接
    private static class IdleConnectionMonitorThread extends Thread {

        private final PoolingHttpClientConnectionManager connMgr;

        public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
            this.setDaemon(true);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (this) {
                        wait(5000);
                        // 关闭失效的连接
                        connMgr.closeExpiredConnections();
                        // 可选的, 关闭30秒内不活动的连接
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
