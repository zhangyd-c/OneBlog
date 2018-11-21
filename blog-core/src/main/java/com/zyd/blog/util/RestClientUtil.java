package com.zyd.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Http接口请求工具
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
public class RestClientUtil {
    protected static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.10 Safari/537.36";
    private static final String DEFAULT_ENCODE = "UTF-8";

    public static String post(String urlString, Map<String, Object> params, Map<String, String> requestHeader) {
        return request("POST", urlString, params, DEFAULT_ENCODE, requestHeader);
    }

    public static String get(String urlString) {
        return get(urlString, DEFAULT_ENCODE, null);
    }

    public static String get(String urlString, Map<String, String> requestHeader) {
        return get(urlString, DEFAULT_ENCODE, requestHeader);
    }

    public static String get(String urlString, String encode) {
        return get(urlString, encode, null);
    }

    public static String get(String urlString, String encode, Map<String, String> requestHeader) {
        return request("GET", urlString, null, encode, requestHeader);
    }

    /**
     * @param method:
     *         GET/PUT/POST default GET
     * @param urlString:
     *         requried
     * @param params:
     *         default null
     */
    public static String request(String method, String urlString, Map<String, Object> params, String encode, Map<String, String> requestHeader) {
        // 解决因jdk版本问题造成的SSL请求失败的问题
        java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        final HttpURLConnection connection;
        try {
            connection = openConnection(urlString);
            connection.setRequestMethod(method);
            if (null != requestHeader) {
                Set<Map.Entry<String, String>> entrySet = requestHeader.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            } else {
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.setRequestProperty("Accept-Charset", "utf-8");
                connection.setRequestProperty("User-Agent", USER_AGENT);
            }
            connection.setDoOutput(true);

            if (!CollectionUtils.isEmpty(params)) {
                final OutputStream outputStream = connection.getOutputStream();
                StringBuilder paramsStr = new StringBuilder();
                Set<Map.Entry<String, Object>> set = params.entrySet();
                for (Map.Entry<String, Object> stringObjectEntry : set) {
                    paramsStr.append(stringObjectEntry.getKey()).append("=").append(stringObjectEntry.getValue()).append("&");
                }
                paramsStr.setLength(paramsStr.length() - 1);
                writeOutput(outputStream, paramsStr.toString());
                outputStream.close();
            }

            log.info("RestClientUtil url: {}, response: {} : {}", urlString, connection.getResponseCode(), connection.getResponseMessage());

            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                return readInput(connection.getInputStream(), encode);
            } else {
                return readInput(connection.getErrorStream(), encode);
            }
        } catch (Exception e) {
            log.error("Http请求失败{}", urlString, e);
        }
        return null;
    }

    protected static HttpURLConnection openConnection(final String urlString) throws Exception {
        final URL url = new URL(urlString);
        return (HttpURLConnection) url.openConnection();
    }

    protected static void writeOutput(final OutputStream outputStream, final String params) throws Exception {
        ByteArrayInputStream inputStram = new ByteArrayInputStream(params.getBytes("UTF-8"));

        final byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStram.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
    }

    protected static String readInput(final InputStream is, String encode) {
        if (null == is) {
            return null;
        }
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, encode));) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            log.error("数据读取失败", e);
        }
        return content.toString();
    }
}
