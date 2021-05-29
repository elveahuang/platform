package cn.elvea.platform.commons.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpComponentsUtils
 * 基于HttpComponents封装网络请求相关的方法
 * https://hc.apache.org/
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class HttpComponentsUtils {

    private static final String ENCODING = "UTF-8";
    private static final int CONNECT_TIMEOUT = 3 * 60 * 1000;
    private static final int SOCKET_TIMEOUT = 3 * 60 * 1000;

    /**
     * Get
     */
    public static String get(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(createURIBuilder(url, params).build());
        setRequestConfig(httpGet);
        setRequestHeader(httpGet, headers);
        return getHttpResponse(httpClient, httpGet);
    }

    /**
     * Get
     */
    public static String get(String url, Map<String, String> params) throws Exception {
        return get(url, new HashMap<>(), params);
    }

    /**
     * Get
     */
    public static String get(String url) throws Exception {
        return get(url, new HashMap<>(), new HashMap<>());
    }

    /**
     * Post
     */
    public static String post(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        setRequestConfig(httpPost);
        setRequestHeader(httpPost, headers);
        setRequestParams(httpPost, params);
        return getHttpResponse(httpClient, httpPost);
    }

    /**
     * Post
     */
    public static String post(String url, Map<String, String> params) throws Exception {
        return post(url, Maps.newHashMap(), params);
    }

    /**
     * Post
     */
    public static String post(String url) throws Exception {
        return post(url, Maps.newHashMap());
    }

    /**
     * Post
     */
    public static String postJson(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        setRequestConfig(httpPost);
        setRequestHeader(httpPost, headers);
        setRequestJson(httpPost, params);
        return getHttpResponse(httpClient, httpPost);
    }

    /**
     * Post
     */
    public static String postJson(String url, Map<String, Object> params) throws Exception {
        return postJson(url, Maps.newHashMap(), params);
    }

    /**
     * Post
     */
    public static String postJson(String url) throws Exception {
        return postJson(url, Maps.newHashMap());
    }

    /**
     * Put
     */
    public static String put(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        setRequestConfig(httpPut);
        setRequestHeader(httpPut, headers);
        setRequestParams(httpPut, params);
        return getHttpResponse(httpClient, httpPut);
    }

    /**
     * Put
     */
    public static String put(String url, Map<String, String> params) throws Exception {
        return put(url, Maps.newHashMap(), params);
    }

    /**
     * Put
     */
    public static String put(String url) throws Exception {
        return put(url, Maps.newHashMap());
    }

    /**
     * Delete
     */
    public static String delete(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(createURIBuilder(url, params).build());
        setRequestConfig(httpDelete);
        setRequestHeader(httpDelete, headers);
        return getHttpResponse(httpClient, httpDelete);
    }

    /**
     * Delete
     */
    public static String delete(String url, Map<String, String> params) throws Exception {
        return delete(url, Maps.newHashMap(), params);
    }

    /**
     * Delete
     */
    public static String delete(String url) throws Exception {
        return delete(url, Maps.newHashMap());
    }

    private static URIBuilder createURIBuilder(String url, Map<String, String> params) throws Exception {
        URIBuilder builder = new URIBuilder(url);
        if (!ObjectUtils.isEmpty(params)) {
            params.forEach(builder::setParameter);
        }
        return builder;
    }

    private static void setRequestConfig(HttpRequestBase httpRequest) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpRequest.setConfig(requestConfig);
    }

    private static void setRequestHeader(HttpRequestBase httpRequest, Map<String, String> headers) {
        if (!ObjectUtils.isEmpty(headers)) {
            headers.forEach(httpRequest::setHeader);
        }
    }

    private static void setRequestParams(HttpEntityEnclosingRequestBase httpRequest, Map<String, String> params) throws UnsupportedEncodingException {
        if (!ObjectUtils.isEmpty(params)) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            params.forEach((key, value) -> nameValuePairs.add(new BasicNameValuePair(key, value)));
            httpRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs, ENCODING));
        }
    }

    private static void setRequestJson(HttpEntityEnclosingRequestBase httpRequest, Map<String, Object> params) throws Exception {
        if (!ObjectUtils.isEmpty(params)) {
            String json = JsonUtils.toJson(params);
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpRequest.setEntity(stringEntity);
        }
    }

    private static String getHttpResponse(CloseableHttpClient httpClient, HttpRequestBase httpRequest) throws Exception {
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpRequest);
            String content = "";
            if (!ObjectUtils.isEmpty(httpResponse) && !ObjectUtils.isEmpty(httpResponse.getEntity())) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return content;
        } finally {
            if (!ObjectUtils.isEmpty(httpResponse)) {
                httpResponse.close();
            }
            if (!ObjectUtils.isEmpty(httpClient)) {
                httpClient.close();
            }
        }
    }

}
