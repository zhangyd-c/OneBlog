package com.zyd.blog.core.shiro.realm;

import com.zyd.blog.business.entity.Resources;
import com.zyd.blog.business.entity.Role;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.UserStatusEnum;
import com.zyd.blog.business.enums.UserTypeEnum;
import com.zyd.blog.business.service.SysResourcesService;
import com.zyd.blog.business.service.SysRoleService;
import com.zyd.blog.business.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro-密码输入错误的状态下重试次数的匹配管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserService userService;
    @Resource
    private SysResourcesService resourcesService;
    @Resource
    private SysRoleService roleService;

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        User user = userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }

        // principal参数使用用户Id，方便动态刷新用户权限
        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();

        // 赋予角色
        List<Role> roleList = roleService.listRolesByUserId(userId);
        if (null != roleList) {
            for (Role role : roleList) {
                info.addRole(role.getName());
            }
        }

        // 赋予权限
        List<Resources> resourcesList = null;
        User user = userService.getByPrimaryKey(userId);
        if (null == user) {
            return info;
        }
        // ROOT用户默认拥有所有权限
        if (UserTypeEnum.ROOT.toString().equalsIgnoreCase(user.getUserType())) {
            resourcesList = resourcesService.listAll();
        } else {
            resourcesList = resourcesService.listByUserId(userId);
        }

        if (!CollectionUtils.isEmpty(resourcesList)) {
            Set<String> permissionSet = new HashSet<>();
            for (Resources resources : resourcesList) {
                String permission = null;
                if (!StringUtils.isEmpty(permission = resources.getPermission())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }
        return info;
    }

}
