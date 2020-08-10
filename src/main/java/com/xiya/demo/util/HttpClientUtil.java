package com.xiya.demo.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
    private static HttpClient client = new HttpClient(httpConnectionManager);

    static {
        //每主机最大连接数和总共最大连接数，通过 hosfConfiguration 设置 host 来区分每个主机
        client.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(8);
        client.getHttpConnectionManager().getParams().setMaxTotalConnections(300);
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        client.getHttpConnectionManager().getParams().setSoTimeout(5000);
        client.getHttpConnectionManager().getParams().setTcpNoDelay(true);
        client.getHttpConnectionManager().getParams().setLinger(1);
        //失败的情况下会进行3次尝试,成功之后不会再尝试
        client.getHttpConnectionManager().getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
    }

    /**
     * 发起 POST 请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String executePostMethod(String url, String json) {
        PostMethod postMethod = new PostMethod(url);
        byte[] responseJson = null;
        try {
            url = postMethod.getURI().toString();
            postMethod.setRequestEntity(new StringRequestEntity(json, "application/json", "UTF-8"));
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                responseJson = postMethod.getResponseBody();
                return new String(responseJson, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.error("HttpClientUtils executePostMethod error, url: {}, error: {}", url, e);
        } finally {
            postMethod.releaseConnection();
        }
        return null;
    }

    /**
     * 发起 GET 请求
     *
     * @param url url请求，包含参数
     */
    public static String executeGetMethod(String url) {
        GetMethod getMethod = new GetMethod(url);
        byte[] responseJson = null;
        try {
            int status = client.executeMethod(getMethod);
            if (status == HttpStatus.SC_OK) {
                responseJson = getMethod.getResponseBody();
                return new String(responseJson, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            logger.error("HttpClientUtils executeGetMethod error, url: {}, error: {}", url, e);
        } finally {
            getMethod.releaseConnection();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(executeGetMethod("https://www.baidu.com/"));
    }

}
