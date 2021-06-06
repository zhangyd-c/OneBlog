package com.zyd.blog.plugin.oauth;

import com.xkcoding.json.util.StringUtil;
import com.zyd.blog.business.entity.SocialConfig;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2021-04-27 15:45
 * @since 1.0.0
 */
public class JapUtil {

    public static com.fujieid.jap.social.SocialConfig blogSocialConfig2JapSocialConfig(SocialConfig socialConfig, String source) {
        com.fujieid.jap.social.SocialConfig config = new com.fujieid.jap.social.SocialConfig();
        config.setPlatform(source);
        config.setState(UuidUtils.getUUID());

        List<String> scopes = new ArrayList<>();
        if (StringUtil.isNotEmpty(socialConfig.getScope())) {
            scopes.addAll(Arrays.asList(socialConfig.getScope().split(",")));
        }
        config.setJustAuthConfig(AuthConfig.builder()
                .clientId(socialConfig.getClientId())
                .clientSecret(socialConfig.getClientSecret())
                .redirectUri(socialConfig.getRedirectUri())
                .scopes(scopes)
                .alipayPublicKey(socialConfig.getAlipayPublicKey())
                .stackOverflowKey(socialConfig.getStackOverflowKey())
                .agentId(socialConfig.getAgentId())
                .build());
        return config;
    }
}
