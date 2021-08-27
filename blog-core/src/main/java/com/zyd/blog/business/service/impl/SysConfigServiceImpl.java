package com.zyd.blog.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.consts.DateConst;
import com.zyd.blog.business.enums.ConfigKeyEnum;
import com.zyd.blog.business.enums.FileUploadType;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.file.FileUploader;
import com.zyd.blog.file.entity.VirtualFile;
import com.zyd.blog.framework.property.AppProperties;
import com.zyd.blog.persistence.beans.SysConfig;
import com.zyd.blog.persistence.mapper.SysConfigMapper;
import com.zyd.blog.plugin.file.GlobalFileUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;
    @Autowired
    private AppProperties properties;

    /**
     * 获取系统配置
     *
     * @return
     */
    @Override
    @RedisCache(enable = false)
    public Map<String, Object> getConfigs() {
        List<SysConfig> list = sysConfigMapper.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        String updateTimeKey = ConfigKeyEnum.UPDATE_TIME.getKey();
        Map<String, Object> res = new HashMap<>();
        res.put(updateTimeKey, DateUtil.parse("2019-01-01 00:00:00", DateConst.YYYY_MM_DD_HH_MM_SS_EN));
        list.forEach((sysConfig) -> {
            res.put(String.valueOf(sysConfig.getConfigKey()), sysConfig.getConfigValue());
            if (sysConfig.getUpdateTime().after(((Date) res.get(updateTimeKey)))) {
                res.put(updateTimeKey, sysConfig.getUpdateTime());
            }
        });
        String storageType = (String) res.get(ConfigKeyEnum.STORAGE_TYPE.getKey());
        if ("local".equalsIgnoreCase(storageType)) {
            res.put("fileStoragePath", res.get(ConfigKeyEnum.LOCAL_FILE_URL.getKey()));
        } else if ("qiniu".equalsIgnoreCase(storageType)) {
            res.put("fileStoragePath", res.get(ConfigKeyEnum.QINIU_BASE_PATH.getKey()));
        } else if ("aliyun".equalsIgnoreCase(storageType)) {
            res.put("fileStoragePath", res.get(ConfigKeyEnum.ALIYUN_FILE_URL.getKey()));
        }
        return res;
    }

    @Override
    @RedisCache(flush = true, enable = false)
    public void saveFile(String key, MultipartFile file) {
        if (key == null) {
            return;
        }
        if (file != null) {
            FileUploader uploader = new GlobalFileUploader();
            VirtualFile virtualFile = uploader.upload(file, FileUploadType.QRCODE.getPath(), true);
            this.saveConfig(key, virtualFile.getFullFilePath());
        }
    }

    @Override
    @RedisCache(flush = true, enable = false)
    public void saveConfig(String key, String value) {
        if (!StringUtils.isEmpty(key)) {
            SysConfig config = null;
            if (null == (config = this.getByKey(key))) {
                config = new SysConfig();
                config.setConfigKey(key);
                config.setConfigValue(value);
                config.setCreateTime(new Date());
                config.setUpdateTime(new Date());
                this.sysConfigMapper.insert(config);
            } else {
                config.setConfigKey(key);
                config.setConfigValue(value);
                config.setUpdateTime(new Date());
                this.sysConfigMapper.updateByPrimaryKeySelective(config);
            }
        }
    }

    @Override
    @RedisCache(enable = false)
    public SysConfig getByKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey(key);
        return this.sysConfigMapper.selectOne(sysConfig);
    }

    /**
     * 添加/修改系统配置
     *
     * @param configs 所有的配置项
     */
    @Override
    @RedisCache(flush = true, enable = false)
    public void saveConfig(Map<String, String> configs) {
        if (!CollectionUtils.isEmpty(configs)) {
            configs.forEach(this::saveConfig);
        }
    }

    /**
     * 获取网站详情
     */
    @Override
    public Map<String, Object> getSiteInfo() {
        Map<String, Object> siteInfo = sysConfigMapper.getSiteInfo();
        if (!CollectionUtils.isEmpty(siteInfo)) {
            Date installdate = null;
            SysConfig config = this.getByKey(ConfigKeyEnum.INSTALLDATE.getKey());
            if (null == config || StringUtils.isEmpty(config.getConfigValue())) {
                // 默认建站日期为2019-01-01
                installdate = Date.from(LocalDate.of(2019, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else {
                installdate = DateUtil.parse(config.getConfigValue(), DatePattern.NORM_DATETIME_PATTERN);
            }
            long between = 1;
            if (!installdate.after(new Date())) {
                between = DateUtil.between(installdate, new Date(), DateUnit.DAY);
            }
            siteInfo.put("installdate", between < 1 ? 1 : between);
        }
        return siteInfo;
    }

    @Override
    @RedisCache(enable = false)
    public String getSpiderConfig() {
        SysConfig config = this.getByKey(ConfigKeyEnum.BLOG_HUNTER_CONFIG.getKey());
        if (config == null) {
            return "{}";
        }
        return StringUtils.isEmpty(config.getConfigValue()) ? "{}" : config.getConfigValue();
    }

    @Override
    public List<String> getRandomUserAvatar() {
        SysConfig config = this.getByKey("defaultUserAvatar");
        if (config == null) {
            return null;
        }
        try {
            return JSONArray.parseArray(config.getConfigValue(), String.class);
        } catch (Exception e) {
            log.error("配置项无效！defaultUserAvatar = [" + config.getConfigValue() + "]");
        }
        return null;
    }
}
