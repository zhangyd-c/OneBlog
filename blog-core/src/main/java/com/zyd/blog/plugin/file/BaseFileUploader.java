package com.zyd.blog.plugin.file;

import com.alibaba.fastjson.JSONObject;
import com.zyd.blog.business.entity.File;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.service.BizFileService;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.file.ApiClient;
import com.zyd.blog.file.LocalApiClient;
import com.zyd.blog.file.QiniuApiClient;
import com.zyd.blog.file.entity.VirtualFile;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.framework.holder.SpringContextHolder;
import com.zyd.blog.persistence.beans.BizFile;
import com.zyd.blog.util.BeanConvertUtil;
import com.zyd.blog.util.SessionUtil;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/11 14:26
 * @since 1.8
 */
public class BaseFileUploader {

    protected ApiClient getApiClient(String uploadType) {
        SysConfigService configService = SpringContextHolder.getBean(SysConfigService.class);
        Map<String, Object> config = configService.getConfigs();
        String storageType = null;
        if (null == config || StringUtils.isEmpty((storageType = (String) config.get("storageType")))) {
            throw new ZhydException("[文件服务]当前系统暂未配置文件服务相关的内容！");
        }

        ApiClient res = null;
        switch (storageType) {
            case "local":
                String localFileUrl = (String) config.get("localFileUrl"),
                        localFilePath = (String) config.get("localFilePath");
                res = new LocalApiClient().init(localFileUrl, localFilePath, uploadType);
                break;
            case "qiniu":
                String accessKey = (String) config.get("qiniuAccessKey"),
                        secretKey = (String) config.get("qiniuSecretKey"),
                        bucketName = (String) config.get("qiniuBucketName"),
                        baseUrl = (String) config.get("qiniuBasePath");
                res = new QiniuApiClient().init(accessKey, secretKey, bucketName, baseUrl, uploadType);
                break;
            case "aliyun":
                break;
            case "youpaiyun":
                break;
            default:
                break;
        }
        return res;
    }

    protected VirtualFile saveFile(VirtualFile virtualFile, boolean save, String uploadType) {
        System.out.println(JSONObject.toJSONString(virtualFile));
        if (save) {
            BizFileService fileService = SpringContextHolder.getBean(BizFileService.class);
            try {
                SysConfigService configService = SpringContextHolder.getBean(SysConfigService.class);
                Map<String, Object> config = configService.getConfigs();
                String storageType = (String) config.get("storageType");

                BizFile fileInfo = BeanConvertUtil.doConvert(virtualFile, BizFile.class);
                User sessionUser = SessionUtil.getUser();
                fileInfo.setUserId(null == sessionUser ? null : sessionUser.getId());
                fileInfo.setUploadType(uploadType);
                fileInfo.setStorageType(storageType);
                fileService.insert(new File(fileInfo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return virtualFile;
    }

    protected boolean removeFile(ApiClient apiClient, String filePath, String uploadType) {
        BizFileService fileService = SpringContextHolder.getBean(BizFileService.class);
        File file = fileService.selectFileByPathAndUploadType(filePath, uploadType);
        String fileKey = filePath;
        if (null != file) {
            fileKey = file.getFilePath();
        }
        return apiClient.removeFile(fileKey);
    }

}
