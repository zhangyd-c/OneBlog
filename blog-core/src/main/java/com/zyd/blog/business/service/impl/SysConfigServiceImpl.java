/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.consts.DateConst;
import com.zyd.blog.business.entity.Config;
import com.zyd.blog.business.service.SysConfigService;
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
            Date recordeTime = (Date) map.get("recordeTime");
            if (!StringUtils.isEmpty(recordeTime)) {
                map.put("recordeTime", DateUtil.date2Str(recordeTime, "yyyy年MM月dd日HH点"));
            }
            Date buildSiteDate = DateUtil.str2Date("2016-10-27 00:00:00", DateConst.YYYY_MM_DD_HH_MM_SS_EN);
            // 获取建站天数
            map.put("buildSiteDate", DateUtil.getGapDay(buildSiteDate, new Date()));
        }
        return map;
    }
}
