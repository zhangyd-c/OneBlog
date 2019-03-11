package com.zyd.blog.business.util;

import com.zyd.blog.business.enums.FileUploadType;
import com.zyd.blog.file.FileUploader;
import com.zyd.blog.file.entity.VirtualFile;
import com.zyd.blog.file.exception.GlobalFileException;
import com.zyd.blog.file.util.FileUtil;
import com.zyd.blog.plugin.file.GlobalFileUploader;
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
     * 将网络图片转存到云存储中
     *
     * @param imgUrl  网络图片地址
     * @param referer 为了预防某些网站做了权限验证，不加referer可能会403
     */
    public static String saveToCloudStorage(String imgUrl, String referer) {
        log.debug("download img >> %s", imgUrl);
        String res = null;
        try (InputStream is = getInputStreamByUrl(imgUrl, referer)) {
            FileUploader uploader = new GlobalFileUploader();
            VirtualFile file = uploader.upload(is, FileUploadType.SIMPLE.getPath(), imgUrl, false);
            res = file.getFullFilePath();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GlobalFileException(e.getMessage());
        }
        return res;
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

        String fileName = localPath + File.separator + UUID.randomUUID().toString() + FileUtil.getSuffixByUrl(imgUrl);
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
