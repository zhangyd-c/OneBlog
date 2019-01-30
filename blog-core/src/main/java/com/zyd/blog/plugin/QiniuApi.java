package com.zyd.blog.plugin;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.zyd.blog.business.consts.CommonConst;
import com.zyd.blog.business.consts.DateConst;
import com.zyd.blog.business.entity.BaseConfig;
import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.framework.holder.SpringContextHolder;
import com.zyd.blog.util.DateUtil;
import com.zyd.blog.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Qiniu云操作文件的api
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
public class QiniuApi {
    private static final Object LOCK = new Object();
    private BaseConfig config;
    private String key;
    private Auth auth;
    private UploadManager uploadManager;

    private QiniuApi() {
        SysConfigService configService = SpringContextHolder.getBean(SysConfigService.class);
        this.config = configService.getBaseConfig();
        auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
        uploadManager = new UploadManager();
    }

    public static QiniuApi getInstance() {
        synchronized (LOCK) {
            return new QiniuApi();
        }
    }

    public QiniuApi withFileName(String key, QiniuUploadType type) {
        return withFileName(key, type.getPath());
    }

    private QiniuApi withFileName(String key, String path) {
        String suffix = FileUtil.getSuffix(key);
        // 不用时间戳命名文件，改为具体的直观的日期命名文件
        String fileName = DateUtil.date2Str(new Date(), DateConst.MILLISECOND);
        this.key = path + (fileName + suffix);
        return this;
    }

    private String getUpToken() {
        return this.auth.uploadToken(config.getQiniuBucketName(), this.key, 3600L, new StringMap().put("insertOnly", 1));
    }

    public String upload(File fileByte) throws IOException {
        Response res = this.uploadManager.put(fileByte, this.key, getUpToken());
        upload(res);
        return key;
    }

    public String upload(byte[] byteArr) throws IOException {
        Response res = this.uploadManager.put(byteArr, this.key, getUpToken());
        upload(res);
        return key;
    }

    private String upload(Response res) throws IOException {
        try {
            int status = res.statusCode;
            if (status == CommonConst.DEFAULT_SUCCESS_CODE) {
                StringMap map = res.jsonToMap();
                return String.valueOf(map.get("key"));
            }
        } catch (QiniuException e) {
            Response r = e.response;
            log.error(r.toString(), e);
        }
        return null;
    }

    public boolean delete(String fileName) {
        BucketManager bucketManager = new BucketManager(this.auth);
        try {
            bucketManager.delete(config.getQiniuBucketName(), fileName);
            return true;
        } catch (QiniuException e) {
            Response r = e.response;
            log.error(r.toString(), e);
        }
        return false;
    }

    public FileInfo getFileInfo(String fileName) {
        BucketManager bucketManager = new BucketManager(this.auth);

        FileInfo info = null;
        try {
            info = bucketManager.stat(config.getQiniuBucketName(), fileName);
            log.info(info.hash);
            log.info(info.key);
        } catch (QiniuException e) {
            Response r = e.response;
            log.error(r.toString(), e);
        }
        return info;
    }

    public String download(String fileUrl) {
        return this.auth.privateDownloadUrl(fileUrl, 3600L);
    }

}
