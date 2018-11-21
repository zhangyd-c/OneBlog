package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.entity.Config;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.framework.property.AppProperties;
import com.zyd.blog.persistence.beans.SysConfig;
import com.zyd.blog.persistence.mapper.SysConfigMapper;
import com.zyd.blog.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * 系统配置
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
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
    @RedisCache
    public Config get() {
        SysConfig config = sysConfigMapper.get();
        return null == config ? null : new Config(config);
    }

    /**
     * 添加系统配置
     *
     * @param config
     * @return
     */
    @Override
    @RedisCache(flush = true)
    public Config insert(Config config) {
        config.setCreateTime(new Date());
        config.setUpdateTime(new Date());
        sysConfigMapper.insert(config.getSysConfig());
        return config;
    }

    /**
     * 删除系统配置记录
     *
     * @param id
     */
    @Override
    @RedisCache(flush = true)
    public void remove(Long id) {
        sysConfigMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改系统配置记录
     *
     * @param config
     */
    @Override
    @RedisCache(flush = true)
    public void update(Config config) {
        config.setUpdateTime(new Date());
        sysConfigMapper.updateByPrimaryKeySelective(config.getSysConfig());
    }

    /**
     * 获取网站详情
     *
     * @return
     */
    @Override
    public Map<String, Object> getSiteInfo() {
        Map<String, Object> map = sysConfigMapper.getSiteInfo();
        if (!CollectionUtils.isEmpty(map)) {
            Date lastUpdateTime = (Date) map.get("lastUpdateTime");
            if (!StringUtils.isEmpty(lastUpdateTime)) {
                map.put("lastUpdateTime", DateUtil.date2Str(lastUpdateTime, "yyyy年MM月dd日HH点"));
            }
            // 获取建站天数
            map.put("buildSiteDate", DateUtil.getGapDay(properties.getBuildWebsiteDate(), new Date()));
        }
        return map;
    }
}
