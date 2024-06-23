<#include "include/macros.ftl">
<@compress single_line=false>
<@header title="留言板 | ${config.siteName}" canonical="/guestbook" hasEditor=true>
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>留言板
    </nav>
    <div class="row guestbook-body">
        <@blogHeader title="留言板"></@blogHeader>
        <#if config.guestbookHtml?? && (config.guestbookHtml?length > 0)>
            ${config.guestbookHtml!}
        <#else >
            <div class="col-sm-12 blog-main">
                <div class="blog-body expansion">
                    <h4><i class="fa fa-lightbulb-o fa-fw"></i> 须知</h4>
                    <div class="blockquote-slidebar">
                        “<a href="${config.siteUrl}" data-original-title="${config.siteDesc}" data-toggle="tooltip" data-placement="bottom">${config.siteName}</a>” 严格遵守中华人民共和国政府颁布的《<a href="http://www.gov.cn/gongbao/content/2011/content_1860864.htm" target="_blank" rel="nofollow"><strong>互联网信息服务管理办法</strong></a>》第十五条规定以及「<a href="https://www.isc.org.cn/zxzx/xhdt/listinfo-27187.html" target="_blank" rel="nofollow"><strong>七条底线</strong></a>」等相关法律法规，并以此作为 “<a href="${config.siteUrl}" data-original-title="${config.siteDesc}" data-toggle="tooltip" data-placement="bottom">${config.siteName}</a>” 运营的基本准则。
                        对于含有以下类型的评论信息，我们将采取最严格的管理措施，予以杜绝：
                    </div>
                    <div class="alert alert-default alert-dismissible" role="alert" style="padding: 0;">
                        <ul>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 关于国家政治的话题；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 关于黄赌毒的话题；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 反对宪法所确定的基本原则的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 损害国家荣誉和利益的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 煽动民族仇恨、民族歧视，破坏民族团结的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 侮辱、滥用英烈形象，否定英烈事迹，美化粉饰侵略战争行为的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 破坏国家宗教政策，宣扬邪教和封建迷信的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 散布谣言，扰乱社会秩序，破坏社会稳定的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 煽动非法集会、结社、游行、示威、聚众扰乱社会秩序的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 侮辱或者诽谤他人，侵害他们合法权益的；</li>
                            <li><i class="fa fa-lightbulb-o fa-fw"></i> 含有法律、行政法规禁止的其他内容的。</li>
                        </ul>
                    </div>

                    <div class="blockquote-slidebar">
                        <i class="fa fa-lightbulb-o fa-fw"></i> 对于违反以上规定的成员/评论，一经发现，直接屏蔽评论内容，并拉黑发布评论的 IP，同时将保留相关证据，在需要的时候提供给国家有关部门，以备查验。<strong>相信我！删除/拉黑真的只需要0.1秒！</strong>
                    </div>
                </div>
            </div>
        </#if>
        <#-- 广告位 -->
        <div class="col-sm-12 ad-mark" id="COMMENT_BOX_TOP" style="display: none"></div>
        <#if config.comment! && config.comment == 1>
            <div class="col-sm-12 blog-main">
                <div class="blog-body expansion">
                    <div id="comment-box" data-id="-1"></div>
                </div>
            </div>
        <#else>
            <div class="blog-body clear overflow-initial expansion gray">
                <i class="fa fa-close fa-fw"></i>评论功能已被站长关闭
            </div>
        </#if>
    </div>
</div>

<@footer>
    <#if (config.enableHitokoto == 1 || config.enableHitokoto == "1")>
        <script src="https://v1.hitokoto.cn/?encode=js&c=i&select=.hitokoto" defer></script>
    </#if>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/highlight.js@9.12.0/lib/highlight.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/simplemde@1.11.2/dist/simplemde.min.js"></script>
</@footer>
</@compress>
