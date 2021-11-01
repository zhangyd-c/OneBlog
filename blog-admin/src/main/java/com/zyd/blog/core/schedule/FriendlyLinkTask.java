package com.zyd.blog.core.schedule;

import com.zyd.blog.business.entity.Link;
import com.zyd.blog.business.enums.ConfigKeyEnum;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.business.service.SysLinkService;
import com.zyd.blog.business.util.LinksUtil;
import com.zyd.blog.persistence.beans.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class FriendlyLinkTask {

    @Autowired
    private SysLinkService sysLinkService;
    @Autowired
    private SysConfigService sysConfigService;
    @Value("${app.enableAutoCheckLink}")
    private boolean enableAutoCheckLink;

    /**
     * 每晚凌晨12点，检查友情链接
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void check() {
        // 未开启自动检查友联的功能
        if (!enableAutoCheckLink) {
            return;
        }
        List<Link> linkList = sysLinkService.listAll();
        if (CollectionUtils.isEmpty(linkList)) {
            return;
        }
        SysConfig sysConfig = sysConfigService.getByKey(ConfigKeyEnum.DOMAIN.getKey());
        if (StringUtils.isEmpty(sysConfig)) {
            return;
        }
        String domain = sysConfig.getConfigValue();
        for (Link link : linkList) {
            if (!link.isStatus()) {
                // 被禁用的网站如果又包含了本站链接，说明其已经恢复本站的友联
                // 因此自动恢复其友联
                if (LinksUtil.hasLinkByHtml(link.getUrl(), domain)
                        || LinksUtil.hasLinkByChinaz(link.getUrl(), domain)) {
                    link.setStatus(true);
                    link.setDescription("");
                    sysLinkService.updateSelective(link);
                }
                continue;
            }

            // 已经不存在本站链接，自动下架该网站的友联
            if (!(LinksUtil.hasLinkByHtml(link.getUrl(), domain))
                    && !LinksUtil.hasLinkByChinaz(link.getUrl(), domain)) {
                link.setStatus(false);
                link.setDescription("系统检测到该网站已经取消本站的链接，因此自动封禁其友链");
                sysLinkService.updateSelective(link);
                log.info("系统监测到该网站（[{}]）已经私自取消本站链接，因此自动下架的友联", link.getName());
            }

        }
    }

}
