package com.study.educenter.utils;

import io.netty.channel.ConnectTimeoutException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.net.SocketTimeoutException;
import java.util.Map;

/**
 * @ClassName:HttpClientUtils
 * @Auther: yao
 * @Description: httpclient工具类
 * @Date: 2023/11/6 22:17
 * @Version: v1.0
 */

public class HttpClientUtils {
    public static final int connTimeout=10000;
    public static final int readTimeout=10000;
    public static final String charset="UTF-8";
    private static HttpClient client=null;
    static{
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client= HttpClients.custom().setConnectionManager(cm).build();
    }
//    public static String postParameters(String url,String parameterStr)throws ConnectTimeoutException, SocketTimeoutException,Exception{
//        return post(url,parameterStr,"application/x-www-form-urlencoded",charset,connTimeout,readTimeout);
//    }
//    public static String postParameters(String url,String parameterStr,String charset,Integer connTimeout,Integer readTimeout)throws ConnectTimeoutException, SocketTimeoutException,Exception{
//        return post(url,parameterStr,"application/x-www-form-urlencoded",charset,connTimeout,readTimeout);
//    }
//    public static String postParameters(String url, Map<String,String> params)throws ConnectTimeoutException,SocketTimeoutException,Exception{
//        return postForm(url,params,null,connTimeout,readTimeout);
//    }
//    public static String get(String url)throws Exception{
//        return get(url,charset,null,null);
//    }
//    public static String get(String url,String charset)throws Exception{
//        return get(url,charset,connTimeout,readTimeout);
//    }
    // 发送post请求，使用指定的字符集编码

}
