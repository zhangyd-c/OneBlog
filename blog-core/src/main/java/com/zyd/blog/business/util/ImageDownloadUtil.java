package com.zyd.blog.business.util;

import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.plugin.QiniuApi;
import com.zyd.blog.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.UUID;

@Slf4j
public class ImageDownloadUtil {

    /**
     * 将网络图片转存到七牛云
     *
     * @param imgUrl 网络图片地址
     */
    public static String convertToQiniu(String imgUrl) {
        log.debug("download img >> %s", imgUrl);
        String qiniuImgPath = null;
        try (InputStream is = getInputStreamByUrl(checkUrl(imgUrl));
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
            log.error("Error.", e);
        }
        return qiniuImgPath;
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

    /**
     * @param imgUrl    网络图片地址
     * @param localPath 待保存的本地地址
     */
    public static String download(String imgUrl, String localPath) {
        log.debug("download img >> %s", imgUrl);

        String fileName = localPath + File.separator + UUID.randomUUID().toString() + "." + getSuffixByUrl(imgUrl);
        try (InputStream is = getInputStreamByUrl(checkUrl(imgUrl));
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

    private static InputStream getInputStreamByUrl(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
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
            e.printStackTrace();
            log.error("Error.", e);
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
