package com.study.educenter.utils;

import io.netty.channel.ConnectTimeoutException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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
    public static String postParameters(String url,String parameterStr)throws ConnectTimeoutException, SocketTimeoutException,Exception{
        return post(url,parameterStr,"application/x-www-form-urlencoded",charset,connTimeout,readTimeout);
    }
    public static String postParameters(String url,String parameterStr,String charset,Integer connTimeout,Integer readTimeout)throws ConnectTimeoutException, SocketTimeoutException,Exception{
        return post(url,parameterStr,"application/x-www-form-urlencoded",charset,connTimeout,readTimeout);
    }
    public static String postParameters(String url, Map<String,String> params)throws ConnectTimeoutException,SocketTimeoutException,Exception{
        return postForm(url,params,null,connTimeout,readTimeout);
    }
    public static String get(String url)throws Exception{
        return get(url,charset,null,null);
    }
    public static String get(String url,String charset)throws Exception{
        return get(url,charset,connTimeout,readTimeout);
    }

    /**
     * 发送post请求，使用指定的字符集编码
     * @param url
     * @param body
     * @param mimeType 例如：application/xml"application/x-www-form-urlencoded" a=1&b=2&c=3
     * @param charset 编码
     * @param connTimeout 建立连接超时时间，毫秒
     * @param readTimeout 建立响应超时时间，毫秒
     * @return
     * @throws ConnectTimeoutException 建立连接超时异常
     * @throws SocketTimeoutException 响应超时
     * @throws Exception
     */
    public static String post(String url,String body,String mimeType,String charset,Integer connTimeout,Integer readTimeout)
        throws ConnectTimeoutException,SocketTimeoutException,Exception{
        HttpClient client=null;
        HttpPost post = new HttpPost(url);
        String result="";
        if (StringUtils.isNotBlank(body)){
            new StringEntity(body, ContentType.create(mimeType, charset));
        }
    }
}
