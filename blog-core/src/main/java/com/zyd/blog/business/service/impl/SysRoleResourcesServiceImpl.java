package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.RoleResources;
import com.zyd.blog.business.service.SysRoleResourcesService;
import com.zyd.blog.framework.holder.RequestHolder;
import com.zyd.blog.persistence.beans.SysRoleResources;
import com.zyd.blog.persistence.mapper.SysRoleResourcesMapper;
import com.zyd.blog.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色资源
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysRoleResourcesServiceImpl implements SysRoleResourcesService {
    @Autowired
    private SysRoleResourcesMapper resourceMapper;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    public RoleResources insert(RoleResources entity) {
        Assert.notNull(entity, "RoleResources不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        resourceMapper.insert(entity.getSysRoleResources());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<RoleResources> entities) {
        Assert.notNull(entities, "entities不可为空！");
        List<SysRoleResources> sysRoleResources = new ArrayList<>();
        String regIp = IpUtil.getRealIp(RequestHolder.getRequest());
        for (RoleResources RoleResources : entities) {
            RoleResources.setUpdateTime(new Date());
            RoleResources.setCreateTime(new Date());
            sysRoleResources.add(RoleResources.getSysRoleResources());
        }
        resourceMapper.insertList(sysRoleResources);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return resourceMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    public boolean update(RoleResources entity) {
        Assert.notNull(entity, "RoleResources不可为空！");
        entity.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKey(entity.getSysRoleResources()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    public boolean updateSelective(RoleResources entity) {
        Assert.notNull(entity, "RoleResources不可为空！");
        entity.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKeySelective(entity.getSysRoleResources()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public RoleResources getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysRoleResources sysRoleResources = resourceMapper.selectByPrimaryKey(primaryKey);
        return null == sysRoleResources ? null : new RoleResources(sysRoleResources);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public RoleResources getOneByEntity(RoleResources entity) {
        Assert.notNull(entity, "User不可为空！");
        SysRoleResources sysRoleResources = resourceMapper.selectOne(entity.getSysRoleResources());
        return null == sysRoleResources ? null : new RoleResources(sysRoleResources);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<RoleResources> listAll() {
        List<SysRoleResources> sysRoleResources = resourceMapper.selectAll();
        return getRoleResources(sysRoleResources);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public List<RoleResources> listByEntity(RoleResources entity) {
        Assert.notNull(entity, "RoleResources不可为空！");
        List<SysRoleResources> sysRoleResources = resourceMapper.select(entity.getSysRoleResources());
        return getRoleResources(sysRoleResources);
    }

    private List<RoleResources> getRoleResources(List<SysRoleResources> sysRoleResources) {
        if (CollectionUtils.isEmpty(sysRoleResources)) {
            return null;
        }
        List<RoleResources> RoleResources = new ArrayList<>();
        for (SysRoleResources r : sysRoleResources) {
            RoleResources.add(new RoleResources(r));
        }
        return RoleResources;
    }

    /**
     * 添加角色资源
     * @param roleId
     * @param resourcesId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addRoleResources(Long roleId, String resourcesId) {
        //删除
        removeByRoleId(roleId);
        //添加
        if (!StringUtils.isEmpty(resourcesId)) {
            String[] resourcesArr = resourcesId.split(",");
            SysRoleResources r = null;
            List<SysRoleResources> roleResources = new ArrayList<>();
            for (String ri : resourcesArr) {
                r = new SysRoleResources();
                r.setRoleId(roleId);
                r.setResourcesId(Long.parseLong(ri));
                r.setCreateTime(new Date());
                r.setUpdateTime(new Date());
                roleResources.add(r);

            }
            resourceMapper.insertList(roleResources);
        }
    }

    /**
     * 通过角色id批量删除
     * @param roleId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void removeByRoleId(Long roleId) {
        //删除
        Example example = new Example(SysRoleResources.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        resourceMapper.deleteByExample(example);
    }
}
