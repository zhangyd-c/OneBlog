package com.zyd.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.business.service.SysUserRoleService;
import com.zyd.blog.business.service.SysUserService;
import com.zyd.blog.business.vo.UserConditionVO;
import com.zyd.blog.framework.object.PageResult;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.PasswordUtil;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
public class RestUserController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysUserRoleService userRoleService;

    @RequiresPermissions("users")
    @PostMapping("/list")
    public PageResult list(UserConditionVO vo) {
        PageInfo<User> pageInfo = userService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    /**
     * 保存用户角色
     *
     * @param userId
     * @param roleIds
     *         用户角色
     *         此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @RequiresPermissions("user:allotRole")
    @PostMapping("/saveUserRoles")
    @BussinessLog("分配用户角色")
    public ResponseVO saveUserRoles(Long userId, String roleIds) {
        if (StringUtils.isEmpty(userId)) {
            return ResultUtil.error("error");
        }
        userRoleService.addUserRole(userId, roleIds);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions("user:add")
    @PostMapping(value = "/add")
    @BussinessLog("添加用户")
    public ResponseVO add(User user) {
        User u = userService.getByUserName(user.getUsername());
        if (u != null) {
            return ResultUtil.error("该用户名["+user.getUsername()+"]已存在！请更改用户名");
        }
        try {
            user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            userService.insert(user);
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }

    @RequiresPermissions(value = {"user:batchDelete", "user:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    @BussinessLog("删除用户")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            userService.removeByPrimaryKey(id);
            userRoleService.removeByUserId(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个用户");
    }

    @RequiresPermissions("user:get")
    @PostMapping("/get/{id}")
    @BussinessLog("获取用户详情")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.userService.getByPrimaryKey(id));
    }

    @RequiresPermissions("user:edit")
    @PostMapping("/edit")
    @BussinessLog("编辑用户")
    public ResponseVO edit(User user) {
        try {
            userService.updateSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("用户修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
