package com.zyd.blog.business.util;

import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.plugin.QiniuApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

@Slf4j
public class ImageDownloadUtil {

    /**
     * 将网络图片转存到七牛云
     *
     * @param imgUrl  网络图片地址
     * @param referer 为了预防某些网站做了权限验证，不加referer可能会403
     */
    public static String convertToQiniu(String imgUrl, String referer) {
        log.debug("download img >> %s", imgUrl);
        String qiniuImgPath = null;
        try (InputStream is = getInputStreamByUrl(imgUrl, referer);
             ByteArrayOutputStream outStream = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            qiniuImgPath = QiniuApi.getInstance()
                    .withFileName("temp." + getSuffixByUrl(imgUrl), QiniuUploadType.SIMPLE)
                    .upload(outStream.toByteArray());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return qiniuImgPath;
    }

    /**
     * 下载网络图片到本地<br>暂时不用，只供测试
     *
     * @param imgUrl    网络图片地址
     * @param referer   为了预防某些网站做了权限验证，不加referer可能会403
     * @param localPath 待保存的本地地址
     */
    @Deprecated
    public static String download(String imgUrl, String referer, String localPath) {

        String fileName = localPath + File.separator + UUID.randomUUID().toString() + "." + getSuffixByUrl(imgUrl);
        try (InputStream is = getInputStreamByUrl(imgUrl, referer);
             FileOutputStream fos = new FileOutputStream(fileName)) {
            if (null == is) {
                return null;
            }
            File file = new File(localPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            int bytesWritten = 0, byteCount = 0;
            byte[] b = new byte[1024];
            while ((byteCount = is.read(b)) != -1) {
                fos.write(b, bytesWritten, byteCount);
            }
        } catch (IOException e) {
            log.error("Error.", e);
            return null;
        }
        return fileName;
    }

    private static String getSuffixByUrl(String imgUrl) {
        String defaultSuffix = "png";
        if (StringUtils.isEmpty(imgUrl)) {
            return defaultSuffix;
        }
        String temStr = imgUrl.substring(imgUrl.lastIndexOf("/"));
        int index = temStr.lastIndexOf(".");
        return -1 == index ? defaultSuffix : temStr.substring(index + 1);
    }

    private static InputStream getInputStreamByUrl(String url, String referer) {
        HttpGet httpGet = new HttpGet(checkUrl(url));
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        if (StringUtils.isNotEmpty(referer)) {
            httpGet.setHeader("referer", referer);
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            response = httpclient.execute(httpGet);
            in = response.getEntity().getContent();
            if (response.getStatusLine().getStatusCode() == 200) {
                return in;
            } else {
                log.error("Error. %s", parseInputStream(in));
                return null;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return in;
    }

    private static String parseInputStream(InputStream in) throws IOException {
        String result = "";
        StringBuffer content = null;
        if (null != in) {
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            content = new StringBuffer();
            String line = "";
            while ((line = r.readLine()) != null) {
                content.append(line);
            }
            result = content.toString();
        }
        return result;
    }

    /**
     * 校验Url，并返回完整的url
     *
     * @param url 待校验的url
     */
    private static String checkUrl(String url) {
        if (!StringUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                return url;
            }
            return url.startsWith("//") ? "https:" + url : "http://" + url;
        }
        return null;
    }

}
