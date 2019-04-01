package com.zyd.blog.core.shiro.credentials;

import com.zyd.blog.business.consts.SessionConst;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.business.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Shiro-密码输入错误的状态下重试次数的匹配管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
public class RetryLimitCredentialsMatcher extends CredentialsMatcher {

    /**
     * 用户登录次数计数  redisKey 前缀
     */
    private static final String SHIRO_LOGIN_COUNT = "shiro_login_count_";
    /**
     * 用户登录是否被锁定    一小时 redisKey 前缀
     */
    private static final String SHIRO_IS_LOCK = "shiro_is_lock_";
    /**
     * 登录失败时重试的次数，默认5次
     */
    private static final int DEFAULT_RETRY_NUM = 5;
    /**
     * session有效期，默认1小时
     */
    private static final int DEFAULT_SESSIONTIME_OUT = 1;
    /**
     * session有效期的时间单位，默认小时
     */
    private static final TimeUnit DEFAULT_SESSIONTIME_OUT_UNIT = TimeUnit.HOURS;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysConfigService configService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Long userId = (Long) info.getPrincipals().getPrimaryPrincipal();
        User user = userService.getByPrimaryKey(userId);
        String username = user.getUsername();
        // 访问一次，计数一次
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String loginCountKey = SHIRO_LOGIN_COUNT + username;
        String isLockKey = SHIRO_IS_LOCK + username;
        opsForValue.increment(loginCountKey, 1);

        if (redisTemplate.hasKey(isLockKey)) {
            String unit = "分钟";
            long time = TimeUnit.SECONDS.toMinutes(redisTemplate.getExpire(isLockKey));
            if (time <= 0) {
                unit = "秒";
                time = TimeUnit.SECONDS.toSeconds(redisTemplate.getExpire(isLockKey));
            } else if (time > 60) {
                unit = "小时";
                time = TimeUnit.SECONDS.toHours(redisTemplate.getExpire(isLockKey));
            }
            throw new ExcessiveAttemptsException("帐号[" + username + "]已被禁止登录！剩余" + time + unit);
        }

        Map<String, Object> configs = configService.getConfigs();
        Object loginRetryNumObj = configs.get("loginRetryNum");
        Object sessionTimeOutObj = configs.get("sessionTimeOut");
        Object sessionTimeOutUnitObj = configs.get("sessionTimeOutUnit");
        int loginRetryNum = StringUtils.isEmpty(loginRetryNumObj) ? DEFAULT_RETRY_NUM : Integer.parseInt(String.valueOf(loginRetryNumObj));
        int sessionTimeOut = StringUtils.isEmpty(sessionTimeOutObj) ? DEFAULT_SESSIONTIME_OUT : Integer.parseInt(String.valueOf(sessionTimeOutObj));
        TimeUnit sessionTimeOutUnit = StringUtils.isEmpty(sessionTimeOutUnitObj) ? DEFAULT_SESSIONTIME_OUT_UNIT : TimeUnit.valueOf(String.valueOf(sessionTimeOutUnitObj));

        String loginCount = String.valueOf(opsForValue.get(loginCountKey));
        int retryCount = ((loginRetryNum + 1) - Integer.parseInt(loginCount));
        if (retryCount <= 0) {
            opsForValue.set(isLockKey, "LOCK");
            redisTemplate.expire(isLockKey, sessionTimeOut, sessionTimeOutUnit);
            redisTemplate.expire(loginCountKey, sessionTimeOut, sessionTimeOutUnit);
            throw new ExcessiveAttemptsException("由于密码输入错误次数过多，帐号[" + username + "]已被禁止登录！");
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (!matches) {
            throw new AccountException("帐号或密码不正确！您还剩" + retryCount + "次重试的机会");
        }

        //清空登录计数
        redisTemplate.delete(loginCountKey);
        try {
            userService.updateUserLastLoginInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 当验证都通过后，把用户信息放在session里
        // 注：User必须实现序列化
        SecurityUtils.getSubject().getSession().setAttribute(SessionConst.USER_SESSION_KEY, user);
        return true;
    }
}
