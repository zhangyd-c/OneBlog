/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
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
public class RestClientUtil {
    protected static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.10 Safari/537.36";
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientUtil.class);

    public static String post(String urlString, Map<String, Object> params, Map<String, String> requestHeader) {
        return post(urlString, null, null, params, requestHeader);
    }

    public static String post(String urlString, String certPath, String certPassword, Map<String, Object> params, Map<String, String> requestHeader) {
        return request("POST", urlString, certPath, certPassword, params, "UTF-8", requestHeader);
    }

    public static String get(String urlString) {
        return get(urlString, null, null, "UTF-8", null);
    }

    public static String get(String urlString, Map<String, String> requestHeader) {
        return get(urlString, null, null, "UTF-8", requestHeader);
    }

    public static String get(String urlString, String encode) {
        return get(urlString, null, null, encode, null);
    }

    public static String get(String urlString, String certPath, String certPassword, String encode, Map<String, String> requestHeader) {
        return request("GET", urlString, certPath, certPassword, null, encode, null);
    }

    public static String put(String urlString) {
        return get(urlString, null, null, "UTF-8", null);
    }

    public static String put(String urlString, String encode) {
        return get(urlString, null, null, "UTF-8", null);
    }

    public static String put(String urlString, String certPath, String certPassword) {
        return request("PUT", urlString, certPath, certPassword, null, "UTF-8", null);
    }

    /**
     * @param method:
     *         GET/PUT/POST default GET
     * @param urlString:
     *         requried
     * @param certPath:
     *         if not null secure request
     * @param certPassword:
     * @param params:
     *         default null
     */
    public static String request(String method, String urlString, String certPath, String certPassword, Map<String, Object> params, String encode, Map<String, String> requestHeader) {
        // 解决因jdk版本问题造成的SSL请求失败的问题
        java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        final HttpURLConnection connection;
        try {
            if (certPath != null && certPath.length() > 0) {
                System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
                connection = openSecureConnection(urlString, certPath, certPassword);
            } else {
                connection = openConnection(urlString);
            }

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

            LOGGER.info("RestClientUtil url: {}, response: {} : {}", urlString, connection.getResponseCode(), connection.getResponseMessage());

            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                return readInput(connection.getInputStream(), encode);
            } else {
                return readInput(connection.getErrorStream(), encode);
            }
        } catch (Exception e) {
            LOGGER.error("Http请求失败{}", urlString, e);
        }
        return null;
    }


    private static HttpsURLConnection openSecureConnection(final String urlString, final String certPath, final String certPassword) throws Exception {

        final URL url = new URL(urlString);

        final KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        final KeyStore keyStore = KeyStore.getInstance("PKCS12");

        final InputStream keyInput = new FileInputStream(certPath);
        keyStore.load(keyInput, certPassword.toCharArray());
        keyInput.close();

        keyManagerFactory.init(keyStore, certPassword.toCharArray());
        final SSLContext context = SSLContext.getInstance("TLS");
        context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
        final SSLSocketFactory socketFactory = context.getSocketFactory();

        final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(socketFactory);
        return connection;
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
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, encode));) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
