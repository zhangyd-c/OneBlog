package com.zyd.blog.file.util;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Arrays;

/**
 * 文件操作工具类
 *
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/01/09 17:40
 * @since 1.0
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {
    private static final String[] PICTURE_SUFFIXS = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg"};

    /**
     * 删除目录，返回删除的文件数
     *
     * @param rootPath 待删除的目录
     * @param fileNum  已删除的文件个数
     * @return 已删除的文件个数
     */
    public static int deleteFiles(String rootPath, int fileNum) {
        File file = new File(rootPath);
        if (!file.exists()) {
            return -1;
        }
        if (file.isDirectory()) {
            File[] sonFiles = file.listFiles();
            if (sonFiles != null && sonFiles.length > 0) {
                for (File sonFile : sonFiles) {
                    if (sonFile.isDirectory()) {
                        fileNum = deleteFiles(sonFile.getAbsolutePath(), fileNum);
                    } else {
                        sonFile.delete();
                        fileNum++;
                    }
                }
            }
        } else {
            file.delete();
        }
        return fileNum;
    }


    public static String getPrefix(File file) {
        return getPrefix(file.getName());
    }

    public static String getPrefix(String fileName) {
        int idx = fileName.lastIndexOf(".");
        int xie = fileName.lastIndexOf("/");
        idx = idx == -1 ? fileName.length() : idx;
        xie = xie == -1 ? 0 : xie + 1;
        return fileName.substring(xie, idx);
    }

    public static String getSuffix(File file) {
        return getSuffix(file.getName());
    }

    public static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        index = -1 == index ? fileName.length() : index;
        return fileName.substring(index);
    }

    public static String getSuffixByUrl(String imgUrl) {
        String defaultSuffix = ".png";
        if (StringUtils.isEmpty(imgUrl)) {
            return defaultSuffix;
        }
        String fileName = imgUrl;
        if(imgUrl.contains("/")) {
            fileName = imgUrl.substring(imgUrl.lastIndexOf("/"));
        }
        String fileSuffix = getSuffix(fileName);
        return StringUtils.isEmpty(fileSuffix) ? defaultSuffix : fileSuffix;
    }

    public static String generateTempFileName(String imgUrl) {
        return "temp" + getSuffixByUrl(imgUrl);
    }

    public static boolean isPicture(String suffix) {
        return !StringUtils.isEmpty(suffix) && Arrays.asList(PICTURE_SUFFIXS).contains(suffix.toLowerCase());
    }

    public static void mkdirs(String filePath) {
        File file = new File(filePath);
        mkdirs(file);
    }

    public static void mkdirs(File file) {
        if (!file.exists()) {
            if (file.isDirectory()) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs();
            }
        }
    }

    public static void checkFilePath(String realFilePath) {
        if (StringUtils.isEmpty(realFilePath)) {
            return;
        }
        File parentDir = new File(realFilePath).getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }
}
