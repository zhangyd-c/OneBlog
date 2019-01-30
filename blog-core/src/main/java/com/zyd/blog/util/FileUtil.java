package com.zyd.blog.util;

import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.framework.exception.ZhydFileException;
import com.zyd.blog.plugin.QiniuApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

/**
 * 文件操作工具类
 *
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/01/09 17:40
 * @since 1.0
 */
@Slf4j
public class FileUtil {
    private static final String[] PICTURE_SUFFIXS = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};

    /**
     * 删除目录，返回删除的文件数
     *
     * @param rootPath
     *         待删除的目录
     * @param fileNum
     *         已删除的文件个数
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
        int idx = fileName.lastIndexOf(".");
        idx = idx == -1 ? fileName.length() : idx;
        return fileName.substring(idx);
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

    public static String uploadToQiniu(MultipartFile file, QiniuUploadType uploadType, boolean canBeNull) {
        // 不可为空并且file为空，抛出异常
        if (!canBeNull && null == file) {
            throw new ZhydFileException("请选择图片");
        }
        // 可为空并且file为空，忽略后边的代码，返回null
        if (canBeNull && null == file) {
            return null;
        }
        try {
            String filePath = "";
            boolean isPicture = FileUtil.isPicture(FileUtil.getSuffix(file.getOriginalFilename()));
            if (isPicture) {
                filePath = QiniuApi.getInstance()
                        .withFileName(file.getOriginalFilename(), uploadType)
                        .upload(file.getBytes());
//                return UrlCodeUtil.encode(filePath);
                return filePath;
            } else {
                throw new ZhydFileException("只支持图片");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZhydFileException("上传图片到七牛云发生异常，请检查七牛配置是否正常", e);
        }
    }

    /**
     * 删除七牛上的文件
     *
     * @param keys
     *         七牛云文件的key（上传成功时返回的文件路径）
     * @return
     */
    public static int removeQiniu(String... keys) {
        if (null == keys || keys.length == 0) {
            return 0;
        }
        int count = 0;
        for (String key : keys) {
            // 不可为空并且file为空，抛出异常
            if (StringUtils.isEmpty(key)) {
                log.error("删除七牛文件失败:文件key为空");
                continue;
            }
            try {
                boolean result = QiniuApi.getInstance().delete(key);
                if (result) {
                    count++;
                }
            } catch (Exception e) {
                log.error("删除七牛云文件发生异常", e);
            }
        }
        return count;
    }
}
