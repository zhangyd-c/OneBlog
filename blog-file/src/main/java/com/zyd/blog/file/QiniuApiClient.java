package com.zyd.blog.file;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringUtils;
import com.zyd.blog.file.entity.VirtualFile;
import com.zyd.blog.file.exception.QiniuApiException;
import com.zyd.blog.file.util.FileUtil;
import com.zyd.blog.file.util.ImageUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

/**
 * Qiniu云操作文件的api：v1
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class QiniuApiClient implements ApiClient {

    private static final String DEFAULT_PREFIX = "oneblog/";

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String path;
    private String pathPrefix;

    public QiniuApiClient() {
    }

    public QiniuApiClient init(String accessKey, String secretKey, String bucketName, String baseUrl, String uploadType) {
        if (StringUtils.isNullOrEmpty(accessKey) || StringUtils.isNullOrEmpty(secretKey) ||
                StringUtils.isNullOrEmpty(bucketName) || StringUtils.isNullOrEmpty(baseUrl)) {
            throw new QiniuApiException("[七牛云]文件上传失败，系统当前暂不支持七牛云文件上传！");
        }
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucketName;
        this.path = baseUrl;

        if (StringUtils.isNullOrEmpty(uploadType)) {
            this.pathPrefix = DEFAULT_PREFIX;
        } else {
            this.pathPrefix = uploadType.endsWith("/") ? uploadType : uploadType + "/";
        }
        return this;
    }

    /**
     * 上传图片
     *
     * @param file 图片
     * @return 上传后的路径
     */
    public VirtualFile uploadImg(MultipartFile file) {
        if (file == null) {
            throw new QiniuApiException("[七牛云]文件上传失败：文件不可为空");
        }
        try {
            VirtualFile res = this.uploadImg(file.getInputStream(), file.getOriginalFilename());
            VirtualFile imageInfo = ImageUtil.getInfo(file);
            return res.setSize(imageInfo.getSize())
                    .setOriginalFileName(file.getOriginalFilename())
                    .setWidth(imageInfo.getWidth())
                    .setHeight(imageInfo.getHeight());
        } catch (IOException e) {
            throw new QiniuApiException("[七牛云]文件上传失败：" + e.getMessage());
        }
    }

    @Override
    public VirtualFile uploadImg(File file) {
        this.check();
        if (file == null) {
            throw new QiniuApiException("[七牛云]文件上传失败：文件不可为空");
        }
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            VirtualFile res = this.uploadImg(is, "temp." + FileUtil.getSuffix(file));
            VirtualFile imageInfo = ImageUtil.getInfo(file);
            return res.setSize(imageInfo.getSize())
                    .setOriginalFileName(file.getName())
                    .setWidth(imageInfo.getWidth())
                    .setHeight(imageInfo.getHeight());
        } catch (FileNotFoundException e) {
            throw new QiniuApiException("[七牛云]文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 上传图片
     *
     * @param is 图片流
     * @param key  图片文件名（非上传后的文件名）
     * @return 上传后的路径
     */
    @Override
    public VirtualFile uploadImg(InputStream is, String key) {
        this.check();
        String suffix = FileUtil.getSuffix(key);
        if (!FileUtil.isPicture(suffix)) {
            throw new QiniuApiException("[七牛云]只支持图片格式：[jpg, jpeg, png, gif, bmp]");
        }
        Date startTime = new Date();
        String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        String newKey = pathPrefix + (fileName + suffix);
        //Zone.zone0:华东
        //Zone.zone1:华北
        //Zone.zone2:华南
        //Zone.zoneNa0:北美
        Configuration cfg = new Configuration(Zone.autoZone());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(is, newKey, upToken, null, null);

            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);

            return new VirtualFile()
                    .setOriginalFileName(key)
                    .setSuffix(suffix)
                    .setUploadStartTime(startTime)
                    .setUploadEndTime(new Date())
                    .setFilePath(putRet.key)
                    .setFileHash(putRet.hash)
                    .setFullFilePath(path + "/" + putRet.key);
        } catch (QiniuException ex) {
            throw new QiniuApiException("[七牛云]文件上传失败：" + ex.error());
        }
    }

    /**
     * 删除七牛空间图片方法
     *
     * @param key 七牛空间中文件名称
     */
    @Override
    public boolean removeFile(String key) {
        this.check();

        if (StringUtils.isNullOrEmpty(key)) {
            throw new QiniuApiException("[七牛云]删除文件失败：文件key为空");
        }
        Auth auth = Auth.create(accessKey, secretKey);
        Configuration config = new Configuration(Zone.autoZone());
        BucketManager bucketManager = new BucketManager(auth, config);
        try {
            Response re = bucketManager.delete(bucket, key);
            return re.isOK();
        } catch (QiniuException e) {
            Response r = e.response;
            throw new QiniuApiException("[七牛云]删除文件发生异常：" + r.toString());
        }
    }

    private void check() {
        if (StringUtils.isNullOrEmpty(accessKey) || StringUtils.isNullOrEmpty(secretKey) || StringUtils.isNullOrEmpty(bucket)) {
            throw new QiniuApiException("[七牛云]尚未配置七牛云，文件上传功能暂时不可用！");
        }
    }

    public String getPath() {
        return path;
    }
}
