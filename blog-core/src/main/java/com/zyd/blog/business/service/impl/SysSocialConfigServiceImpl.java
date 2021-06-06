package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xkcoding.json.util.StringUtil;
import com.zyd.blog.business.entity.SocialConfig;
import com.zyd.blog.business.service.SysSocialConfigService;
import com.zyd.blog.business.vo.SocialConfigConditionVO;
import com.zyd.blog.persistence.beans.SysSocialConfig;
import com.zyd.blog.persistence.mapper.SysSocialConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yadong.zhang email:yadong.zhang(a)innodev.com.cn
 * @version 1.0
 * @date 2021/04/27 14:34
 * @since 1.0
 */
@Service
public class SysSocialConfigServiceImpl implements SysSocialConfigService {

    @Autowired
    private SysSocialConfigMapper sysSocialConfigMapper;

    @Override
    public PageInfo<SocialConfig> findPageBreakByCondition(SocialConfigConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysSocialConfig> list = sysSocialConfigMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        PageInfo bean = new PageInfo<SysSocialConfig>(list);
        bean.setList(list.stream().map(SocialConfig::new).collect(Collectors.toList()));
        return bean;
    }

    @Override
    public SocialConfig getByClientId(String clientId) {
        Assert.notNull(clientId, "This argument is required; it must not be null");
        SysSocialConfig entity = sysSocialConfigMapper.getByClientId(clientId);
        return null == entity ? null : new SocialConfig(entity);
    }

    @Override
    public SocialConfig getByPlatform(String platform) {
        if (StringUtil.isEmpty(platform)) {
            return null;
        }
        Example example = new Example(SysSocialConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("platform", platform.toLowerCase());
        SysSocialConfig sysSocialConfig = sysSocialConfigMapper.selectOneByExample(example);
        if (null == sysSocialConfig) {
            return null;
        }
        return new SocialConfig(sysSocialConfig);
    }

    @Override
    public List<SocialConfig> listAvailable() {
        List<SysSocialConfig> list = sysSocialConfigMapper.listAvailable();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.stream().map(SocialConfig::new).collect(Collectors.toList());
    }

    @Override
    public SocialConfig insert(SocialConfig entity) {
        Assert.notNull(entity, "This argument is required; it must not be null");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysSocialConfigMapper.insertSelective(entity.getSysSocialConfig());
        return entity;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "This argument is required; it must not be null");
        return sysSocialConfigMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    public boolean updateSelective(SocialConfig entity) {
        Assert.notNull(entity, "This argument is required; it must not be null");
        entity.setUpdateTime(new Date());
        return sysSocialConfigMapper.updateByPrimaryKeySelective(entity.getSysSocialConfig()) > 0;
    }

    @Override
    public SocialConfig getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "This argument is required; it must not be null");
        SysSocialConfig entity = sysSocialConfigMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new SocialConfig(entity);
    }

    @Override
    public List<SocialConfig> listAll() {
        List<SysSocialConfig> entityList = sysSocialConfigMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        return entityList.stream().map(SocialConfig::new).collect(Collectors.toList());
    }
}
