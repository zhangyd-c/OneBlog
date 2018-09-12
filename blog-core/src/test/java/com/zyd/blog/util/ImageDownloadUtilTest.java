package com.zyd.blog.util;

import com.zyd.blog.business.util.ImageDownloadUtil;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/9/6 11:31
 * @since 1.8
 */
public class ImageDownloadUtilTest {

    @Test
    public void imoocTest() {
        String html = "<p>目前，该功能已内置了三个平台（imooc、csdn和iteye），根据不同的平台，程序已默认了一套抓取规则，如下图系列<br><img title=\"DBlog开源博客新增博客迁移功能（支持多个站点）_\"图片2=\"\"src=\"//img.mukewang.com/5b7fd07c000125ed18090932.png\"alt=\"图片描述\"style=\"cursor: pointer;\"><br><img title=\"DBlog开源博客新增博客迁移功能（支持多个站点）_\"图片3=\"\"src=\"//img.mukewang.com/5b7fd0870001dce917490934.png\"alt=\"图片描述\"style=\"cursor: pointer;\"><br><img title=\"DBlog开源博客新增博客迁移功能（支持多个站点）_\"图片4=\"\"src=\"//img.mukewang.com/5b7fd08d000190f617610917.png\"alt=\"图片描述\"style=\"cursor: pointer;\"><br><img title=\"DBlog开源博客新增博客迁移功能（支持多个站点）_\"图片5=\"\"src=\"//img.mukewang.com/5b7fd0940001b6c317440936.png\"alt=\"图片描述\"style=\"cursor: pointer;\"></p>";
        parseImgForHtml(html);
    }

    @Test
    public void iteyeTest() {
        String html = "<p>&nbsp;&nbsp;&nbsp;前段时间在项目中用到了上传头像，并且获取剪切后的头像功能，单一的上传头像很好处理，直接把改文件上传就可以，但是剪切后的头像，它的src却是一个base64字符串，如图：<br><img alt=\"\"src=\"http://dl2.iteye.com/upload/attachment/0109/3648/857431ce-8d0a-35b7-bdee-e3facc7bd0b6.png\"title=\"点击查看原始大小图片\"class=\"magplus\"width=\"699\"height=\"650\"><br>&nbsp;，直接将这个地址当做文件路径上传到后台肯定不行，因为java无法编译改地址，不能识别为一个图片路径。那么，这就用到了对base64位字符串进行解码处理，将其解析为一个可被正确识别的文件。</p>";
        parseImgForHtml(html);
    }

    /**
     * 解析img
     *
     * @param html
     * @return
     */
    private void parseImgForHtml(String html) {
        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher m = null;
        m = p.matcher(html);
        Set<String> imgUrlSet = new HashSet<>();
        while (m.find()) {
            String imgUrl = m.group(1);
            imgUrlSet.add(imgUrl);
        }
        System.out.println(html);
        if (!CollectionUtils.isEmpty(imgUrlSet)) {
            for (String imgUrl : imgUrlSet) {
                String filePath = "D://var/tmp/";
                String localPath = ImageDownloadUtil.download(imgUrl, filePath);
                html = html.replaceAll(imgUrl, localPath);
            }
        }
        System.out.println(html);
    }
}
