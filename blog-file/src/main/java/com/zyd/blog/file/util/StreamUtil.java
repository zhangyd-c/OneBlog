package com.zyd.blog.file.util;

import com.zyd.blog.file.exception.GlobalFileException;

import java.io.*;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2017/7/12 10:29
 * @since 1.8
 */
public class StreamUtil {

    /**
     * 将InputStream转换为字符串
     *
     * @param is InputStream
     * @return
     */
    public static String toString(InputStream is) {
        return toString(is, "UTF-8");
    }

    /**
     * 将InputStream转换为字符串
     *
     * @param is InputStream
     * @return
     */
    public static String toString(InputStream is, String encoding) {
        if (null == is) {
            return null;
        }
        encoding = encoding == null ? "UTF-8" : encoding;
        StringBuilder fileContent = new StringBuilder();
        try (
                InputStream inputStream = is;
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encoding))
        ) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
                fileContent.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    /**
     * 复制InputStream
     *
     * @param is InputStream
     * @return
     */
    public static InputStream clone(InputStream is) {
        if(null == is){
            throw new GlobalFileException("无法获取文件流，文件不可用！");
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return new ByteArrayInputStream(baos.toByteArray());
        } catch (IOException e) {
            throw new GlobalFileException("无法复制当前文件流！", e);
        }
    }
}
