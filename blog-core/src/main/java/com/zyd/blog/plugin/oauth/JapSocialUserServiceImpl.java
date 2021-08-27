package com.zyd.blog.plugin.oauth;

import com.fujieid.jap.core.JapUser;
import com.fujieid.jap.core.JapUserService;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.UserTypeEnum;
import com.zyd.blog.business.service.SysUserService;
import com.zyd.blog.util.BeanConvertUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2021-04-27 15:22
 * @since 1.0.0
 */
@Slf4j
@Service
public class JapSocialUserServiceImpl implements JapUserService {
    @Autowired
    private SysUserService userService;

    /**
     * Get user information in the current system by social platform and social user id
     * <p>
     * It is suitable for the {@code jap-social} module
     *
     * @param platform social platform，refer to {@code me.zhyd.oauth.config.AuthSource#getName()}
     * @param uid      social user id
     * @return JapUser
     */
    @Override
    public JapUser getByPlatformAndUid(String platform, String uid) {
        User user = userService.getByUuidAndSource(uid, platform);
        if (null == user) {
            return null;
        }
        return new JapUser()
                .setUserId(user.getId() + "")
                .setUsername(user.getUsername())
                .setAdditional(user);
    }

    /**
     * Save the social login user information to the database and return JapUser
     * <p>
     * It is suitable for the {@code jap-social} module
     *
     * @param userInfo User information obtained through justauth third-party login, type {@code me.zhyd.oauth.model.AuthUser}
     * @return When saving successfully, return {@code JapUser}, otherwise return {@code null}
     */
    @Override
    public JapUser createAndGetSocialUser(Object userInfo) {
        AuthUser authUser = (AuthUser) userInfo;
        User newUser = BeanConvertUtil.doConvert(authUser, User.class);
        newUser.setSource(authUser.getSource());
        if (null != authUser.getGender()) {
            newUser.setGender(Integer.valueOf(authUser.getGender().getCode()));
        }
        User user = userService.getByUuidAndSource(authUser.getUuid(), authUser.getSource());
        newUser.setUserType(UserTypeEnum.USER);
        if (null != user) {
            newUser.setId(user.getId());
            userService.updateSelective(newUser);
        } else {
            userService.insert(newUser);
        }

        try {
            userService.updateUserLastLoginInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JapUser()
                .setUserId(newUser.getId() + "")
                .setUsername(newUser.getUsername())
                .setAdditional(newUser);
    }

}
