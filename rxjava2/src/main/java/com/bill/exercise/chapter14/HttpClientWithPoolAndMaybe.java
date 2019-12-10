package com.bill.exercise.chapter14;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientWithPoolAndMaybe {

    // 全局连接池对象
    private static final PoolingHttpClientConnectionManager connManger
            = new PoolingHttpClientConnectionManager();

    static {
        // 设置最大连接数
        connManger.setMaxTotal(200);
        // 设置每个连接的路由数
        connManger.setDefaultMaxPerRoute(20);
    }

    /**
     * 获取Http客户端连接对象
     */
    public static CloseableHttpClient getHttpClient(int timeOut) {
        RequestConfig requestConfig = RequestConfig.custom()
                // 获取连接超时时间
                .setConnectionRequestTimeout(timeOut)
                // 请求超时时间
                .setConnectTimeout(timeOut)
                // 响应超时时间
                .setSocketTimeout(timeOut).build();

        return HttpClients.custom()
                // 把请求相关的超时信息设置到连接客户端
                .setDefaultRequestConfig(requestConfig)
                // 把请求重试设置到连接客户端
                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                // 配置连接池管理对象
                .setConnectionManager(connManger).build();
    }

    public static Maybe<String> httpGet(final String url, final int timeOut) {
        return Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onSuccess(url);
            }
        }).map(urlStr -> {
           // 获取客户端连接对象
           CloseableHttpClient httpClient = getHttpClient(timeOut);
           // 创建GET请求对象
            HttpGet httpGet = new HttpGet(urlStr);

            CloseableHttpResponse response = null;
            String msg = null;

            try {
                // 执行请求
                response = httpClient.execute(httpGet);
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 获取响应信息
                msg = EntityUtils.toString(entity, "UTF-8");
            } catch (Exception e){

            } finally {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                }
                return msg;
            }

        });
    }

    public static void main(String[] args) {

    }

}
