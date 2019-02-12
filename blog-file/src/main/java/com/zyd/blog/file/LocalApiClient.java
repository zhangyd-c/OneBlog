package com.zyd.blog.file;

import cn.hutool.core.date.DateUtil;
import com.zyd.blog.file.entity.VirtualFile;
import com.zyd.blog.file.exception.LocalApiException;
import com.zyd.blog.file.util.FileUtil;
import com.zyd.blog.file.util.ImageUtil;
import org.springframework.util.DigestUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/11 15:06
 * @since 1.8
 */
public class LocalApiClient implements ApiClient {
    private static final String DEFAULT_PREFIX = "oneblog/";

    private String url;
    private String rootPath;
    private String pathPrefix;

    public LocalApiClient() {
    }

    public LocalApiClient init(String url, String rootPath, String uploadType) {
        this.url = url;
        this.rootPath = rootPath;

        if (StringUtils.isEmpty(uploadType)) {
            this.pathPrefix = DEFAULT_PREFIX;
        } else {
            this.pathPrefix = uploadType.endsWith("/") ? uploadType : uploadType + "/";
        }
        return this;
    }

    @Override
    public VirtualFile uploadImg(MultipartFile file) {
        this.check();
        if (file == null) {
            throw new LocalApiException("[Nginx文件服务器]文件上传失败：文件不可为空");
        }
        try {
            VirtualFile res = this.uploadImg(file.getInputStream(), file.getOriginalFilename());
            VirtualFile imageInfo = ImageUtil.getInfo(file);
            return res.setSize(imageInfo.getSize())
                    .setWidth(imageInfo.getWidth())
                    .setHeight(imageInfo.getHeight());
        } catch (IOException e) {
            throw new LocalApiException("[Nginx文件服务器]文件上传失败：" + e.getMessage());
        }
    }

    @Override
    public VirtualFile uploadImg(File file) {
        this.check();
        if (file == null) {
            throw new LocalApiException("[Nginx文件服务器]文件上传失败：文件不可为空");
        }
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            VirtualFile res = this.uploadImg(is, "temp." + FileUtil.getSuffix(file));
            VirtualFile imageInfo = ImageUtil.getInfo(file);
            return res.setSize(imageInfo.getSize())
                    .setWidth(imageInfo.getWidth())
                    .setHeight(imageInfo.getHeight());
        } catch (FileNotFoundException e) {
            throw new LocalApiException("[Nginx文件服务器]文件上传失败：" + e.getMessage());
        }
    }

    @Override
    public VirtualFile uploadImg(InputStream is, String key) {
        this.check();
        String suffix = FileUtil.getSuffix(key);
        if (!FileUtil.isPicture(suffix)) {
            throw new LocalApiException("[Nginx文件服务器]只支持图片格式：[jpg, jpeg, png, gif, bmp]");
        }
        Date startTime = new Date();
        String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");

        key = pathPrefix + (fileName + suffix);
        String realFilePath = this.rootPath + key;
        FileUtil.checkFilePath(realFilePath);
        try (InputStream copyIs = is;
             FileOutputStream fos = new FileOutputStream(realFilePath)) {
            StreamUtils.copy(copyIs, fos);
            String fileHash = DigestUtils.md5DigestAsHex(copyIs);
            return new VirtualFile()
                    .setSize(is.available())
                    .setOriginalFileName(FileUtil.getName(key))
                    .setSuffix(suffix)
                    .setUploadStartTime(startTime)
                    .setUploadEndTime(new Date())
                    .setFilePath(key)
                    .setFileHash(fileHash)
                    .setFullFilePath(this.url + key);
        } catch (IOException e) {
            throw new LocalApiException("[Nginx文件服务器]文件上传失败：" + e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean removeFile(String key) {
        this.check();
        if (StringUtils.isEmpty(key)) {
            throw new LocalApiException("[Nginx文件服务器]删除文件失败：文件key为空");
        }
        File file = new File(this.rootPath + key);
        if (!file.exists()) {
            throw new LocalApiException("[Nginx文件服务器]删除文件失败：文件不存在[" + this.rootPath + key + "]");
        }
        try {
            return file.delete();
        } catch (Exception e) {
            throw new LocalApiException("[Nginx文件服务器]删除文件失败：" + e.getMessage());
        }
    }

    private void check() {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(rootPath)) {
            throw new LocalApiException("[Nginx文件服务器]尚未配置Nginx文件服务器，文件上传功能暂时不可用！");
        }
    }
}
