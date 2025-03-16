/*
 * Copyright (c) 2020-2040, 北京符节科技有限公司 (support@fujieid.com & https://www.fujieid.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zyd.blog.util;

import com.zyd.blog.business.entity.BizAdBo;
import com.zyd.blog.business.service.BizAdService;
import com.zyd.blog.framework.holder.SpringContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
public class ArticleUtil {

    private static final String AD_TEMPLATE = "<div class=\"article_detail_ad\"><img src=\"${ad.picture!}\" class=\"article_detail_ad_icon\" width=\"130\" alt=\"\"/><div class=\"article_detail_ad_content\"><div style=\"text-align: left;\"><div class=\"article_detail_ad_title\"><a href=\"${ad.link!}\" target=\"_blank\" rel=\"noopener\">${ad.title!}</a></div><div class=\"article_detail_ad_desc\">${ad.brief!}</div></div><div style=\"text-align: left;\">${ad.content!}</div></div></div>";
    private static final String CUSTOM_AD_PLACEHOLDER = "#CUSTOM_AD_PLACEHOLDER#";

    public static String insertAdsIntoArticle(String articleContent) {
        // 文章中没有广告占位符，直接忽略
        if (StringUtils.isEmpty(articleContent) || !articleContent.contains(CUSTOM_AD_PLACEHOLDER)) {
            return articleContent;
        }
        BizAdService adService = SpringContextHolder.getBean(BizAdService.class);
        List<BizAdBo> ads = adService.listArticleAds();
        // 没有可用的广告，直接忽略文章中插入的广告占位符
        if (CollectionUtils.isEmpty(ads)) {
            return articleContent.replaceAll(CUSTOM_AD_PLACEHOLDER, "");
        }
        // 分隔占位符
        List<String> placeholders = new ArrayList<>(Arrays.asList(articleContent.split(CUSTOM_AD_PLACEHOLDER)));
        // 打乱广告的顺序
        int adSize = ads.size();
        if (adSize > 1) {
            Collections.shuffle(ads);
        }
        StringBuilder sb = new StringBuilder();
        int placeholderNum = placeholders.size();
        for (int i = 0; i < placeholderNum; i++) {
            sb.append(placeholders.get(i));
            if (adSize <= i) {
                break;
            }
            Map<String, Object> params = new HashMap<>();
            params.put("ad", ads.get(i));
            String adHtml = FreeMarkerUtil.template2String(AD_TEMPLATE, params, false);
            sb.append(adHtml);
        }
        sb.append(placeholders.get(placeholderNum - 1));
        return sb.toString();
    }
}
