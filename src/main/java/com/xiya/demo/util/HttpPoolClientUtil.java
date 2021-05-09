package com.xiya.demo.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpPoolClientUtil {


    private static final int CONNECT_TIMEOUT = 3000;
    private static final int SOCKET_TIMEOUT = 3000;
    private static final int MAX_CONN = 1000;
    private static final int MAX_PRE_ROUTE = 1000;
    private static CloseableHttpClient httpClient; // 发送请求的客户端单例
    private static PoolingHttpClientConnectionManager manager; //连接池管理类
    private static RequestConfig config;
    private static ConnectionKeepAliveStrategy myStrategy;

    static {
        manager = new PoolingHttpClientConnectionManager();
        manager.setDefaultMaxPerRoute(MAX_PRE_ROUTE);
        manager.setMaxTotal(MAX_CONN);
        myStrategy = (response, context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase
                        ("timeout")) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return 60 * 1000;//如果没有约定，则默认定义时长为60s
        };
        config = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();
        httpClient = HttpClients.custom()
                .setConnectionManager(manager)
                .setKeepAliveStrategy(myStrategy)
                .setDefaultRequestConfig(config)
                .build();
    }

    public static String doGet(String url, Map<String, String> param) {
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, Object> param) {
        // 创建Httpclient对象
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, (String) param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        CloseableHttpResponse response = null;
        String resultString = "";
        HttpPost httpPost;
        try {
            // 创建Http Post请求
            httpPost = new HttpPost(url);
            // 创建请求内容
            httpPost.setHeader("HTTP Method", "POST");
            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            // 执行http请求
            response = httpClient.execute(httpPost);
            log.info("doPostJson execute httpPost:{},response:{}", httpPost, response);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.error("doPostJson error url:{},json:{}", url, json, e);

        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("doPostJson error ", e);
            }
        }

        return resultString;
    }


}

