package com.zyd.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Role;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.business.service.SysRoleResourcesService;
import com.zyd.blog.business.service.SysRoleService;
import com.zyd.blog.business.vo.RoleConditionVO;
import com.zyd.blog.core.shiro.ShiroService;
import com.zyd.blog.framework.object.PageResult;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统角色管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@RestController
@RequestMapping("/roles")
public class RestRoleController {
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysRoleResourcesService roleResourcesService;
    @Autowired
    private ShiroService shiroService;

    @RequiresPermissions("roles")
    @PostMapping("/list")
    public PageResult getAll(RoleConditionVO vo) {
        PageInfo<Role> pageInfo = roleService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("user:allotRole")
    @PostMapping("/rolesWithSelected")
    public ResponseVO<List<Role>> rolesWithSelected(Integer uid) {
        return ResultUtil.success(null, roleService.queryRoleListWithSelected(uid));
    }

    @RequiresPermissions("role:allotResource")
    @PostMapping("/saveRoleResources")
    @BussinessLog("分配角色拥有的资源")
    public ResponseVO saveRoleResources(Long roleId, String resourcesId) {
        if (StringUtils.isEmpty(roleId)) {
            return ResultUtil.error("error");
        }
        roleResourcesService.addRoleResources(roleId, resourcesId);
        // 重新加载所有拥有roleId的用户的权限信息
        shiroService.reloadAuthorizingByRoleId(roleId);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions("role:add")
    @PostMapping(value = "/add")
    @BussinessLog("添加角色")
    public ResponseVO add(Role role) {
        roleService.insert(role);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"role:batchDelete", "role:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    @BussinessLog("删除角色")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            roleService.removeByPrimaryKey(id);
            roleResourcesService.removeByRoleId(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个角色");
    }

    @RequiresPermissions("role:get")
    @PostMapping("/get/{id}")
    @BussinessLog("获取角色详情")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.roleService.getByPrimaryKey(id));
    }

    @RequiresPermissions("role:edit")
    @PostMapping("/edit")
    @BussinessLog("编辑角色")
    public ResponseVO edit(Role role) {
        try {
            roleService.updateSelective(role);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("角色修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
