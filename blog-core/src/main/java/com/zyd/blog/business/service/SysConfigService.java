package com.zyd.blog.business.service;


import com.zyd.blog.business.entity.Config;

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
public interface SysConfigService {

    /**
     * 获取系统配置
     *
     * @return
     */
    Config get();

    /**
     * 添加系统配置
     *
     * @param config
     * @return
     */
    Config insert(Config config);

    /**
     * 删除系统配置记录
     *
     * @param id
     */
    void remove(Long id);

    /**
     * 修改系统配置记录
     *
     * @param config
     */
    void update(Config config);

    /**
     * 获取网站详情
     *
     * @return
     */
    Map<String, Object> getSiteInfo();
}
