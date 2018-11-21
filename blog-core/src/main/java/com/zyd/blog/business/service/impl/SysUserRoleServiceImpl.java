package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.UserRole;
import com.zyd.blog.business.service.SysUserRoleService;
import com.zyd.blog.framework.holder.RequestHolder;
import com.zyd.blog.persistence.beans.SysUserRole;
import com.zyd.blog.persistence.mapper.SysUserRoleMapper;
import com.zyd.blog.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户角色
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper resourceMapper;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    public UserRole insert(UserRole entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        resourceMapper.insert(entity.getSysUserRole());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<UserRole> entities) {
        Assert.notNull(entities, "entities不可为空！");
        List<SysUserRole> sysUserRole = new ArrayList<>();
        String regIp = IpUtil.getRealIp(RequestHolder.getRequest());
        for (UserRole UserRole : entities) {
            UserRole.setUpdateTime(new Date());
            UserRole.setCreateTime(new Date());
            sysUserRole.add(UserRole.getSysUserRole());
        }
        resourceMapper.insertList(sysUserRole);
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
    public boolean update(UserRole entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKey(entity.getSysUserRole()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    public boolean updateSelective(UserRole entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKeySelective(entity.getSysUserRole()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public UserRole getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysUserRole sysUserRole = resourceMapper.selectByPrimaryKey(primaryKey);
        return null == sysUserRole ? null : new UserRole(sysUserRole);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public UserRole getOneByEntity(UserRole entity) {
        Assert.notNull(entity, "User不可为空！");
        SysUserRole sysUserRole = resourceMapper.selectOne(entity.getSysUserRole());
        return null == sysUserRole ? null : new UserRole(sysUserRole);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<UserRole> listAll() {
        List<SysUserRole> sysUserRole = resourceMapper.selectAll();
        return getUserRole(sysUserRole);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public List<UserRole> listByEntity(UserRole entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        List<SysUserRole> sysUserRole = resourceMapper.select(entity.getSysUserRole());
        return getUserRole(sysUserRole);
    }

    private List<UserRole> getUserRole(List<SysUserRole> sysUserRole) {
        if (CollectionUtils.isEmpty(sysUserRole)) {
            return null;
        }
        List<UserRole> UserRole = new ArrayList<>();
        for (SysUserRole r : sysUserRole) {
            UserRole.add(new UserRole(r));
        }
        return UserRole;
    }

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addUserRole(Long userId, String roleIds) {
        //删除
        removeByUserId(userId);
        //添加
        String[] roleids = roleIds.split(",");
        UserRole u = null;
        List<UserRole> roles = new ArrayList<>();
        for (String roleId : roleids) {
            u = new UserRole();
            u.setUserId(userId);
            u.setRoleId(Long.parseLong(roleId));
            roles.add(u);
        }
        insertList(roles);
    }

    /**
     * 根据用户ID删除用户角色
     *
     * @param userId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void removeByUserId(Long userId) {
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        resourceMapper.deleteByExample(example);
    }
}
