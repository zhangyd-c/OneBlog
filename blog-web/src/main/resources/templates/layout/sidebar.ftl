<div class="col-sm-3 blog-sidebar">
    <#-- 广告位 -->
    <div class="ad-mark" id="SIDEBAR_TOP" style="display: none"></div>
    <#if articleDetail?? && (config.enableHitokoto == 1 || config.enableHitokoto == "1")>
        <div class="sidebar-module">
            <h5 class="custom-title"><i class="fa fa-hand-peace-o fa-fw icon"></i><strong>说给你听</strong><small></small></h5>
            <div class="div-quote">
                <i class="fa fa-quote-left fa-fw"></i><p class="hitokoto" style="margin-left: 15px;"></p>
            </div>
        </div>
    <#else>
        <div class="sidebar-module" style="position: relative;">
            <h5 class="custom-title"><i class="fa fa-home fa-fw icon"></i><strong>关于</strong><small></small></h5>
            <div class="widget">
                <div id="feed_widget">
                    <div class="feed-about">
                        <div class="about-main">
                            <#if config.wxCode?? && (config.wxCode?length > 0)>
                            <div class="about-img"><a href="${config.wxCode}" class="showImage" title="微信公众号"><img src="${config.wxCode}" alt="微信公众号"></a></div>
                            </#if>
                            <div class="about-the">${config.siteDesc!}</div>
                        </div>
                        <div class="clear"></div>
                        <!-- 方案一：图标展示 -->
                        <ul class="widget-icon">
                            <#if config.wxCode?? && (config.wxCode?length > 0)>
                            <li class="weixin auto-shake" data-container="body"
                                data-toggle="popover" data-trigger="hover" data-placement="bottom" data-html="true" data-content="<img src='${config.wxCode}' style='width: 130px;' alt='QR Code'>">
                                <a class="tag-icon" title="微信" rel="external nofollow"><i class="fa fa-weixin"></i></a>
                            </li>
                            </#if>
                            <#if config.qq?? && (config.qq?length > 0)>
                            <li class="tqq auto-shake">
                                <a class="tag-icon" href="javascript:window.open('tencent://message/?uin=${config.qq}&Site=www.${config.domain}&Menu=yes')" title="点击QQ联系我" target="blank" rel="external nofollow"><i class="fa fa-qq"></i></a>
                            </li>
                            </#if>
                            <#if config.weibo?? && (config.weibo?length > 0)>
                            <li class="tsina auto-shake">
                                <a class="tag-icon" href="${config.weibo}" title="点击查看我的微博" target="_blank" rel="external nofollow"><i class="fa fa-weibo"></i></a>
                            </li>
                            </#if>
                            <#if config.github?? && (config.github?length > 0)>
                            <li class="github auto-shake">
                                <a class="tag-icon" href="${config.github}" title="点击查看我的github" target="_blank" rel="external nofollow"><i class="fa fa-github"></i></a>
                            </li>
                            </#if>
                        </ul>
                        <!-- 方案二：列表展示 -->
                        <#--<ul class="list-unstyled">
                            <li><i class="fa fa-info-circle fa-fw"></i>博主：<small>张亚东</small></li>
                            <li><i class="fa fa-user-circle fa-fw"></i>职业：<small>码农</small></li>
                            <li><i class="fa fa-home fa-fw"></i>籍贯：<small>长白山某棵树上</small></li>
                            <li><i class="fa fa-envelope-square fa-fw"></i>邮箱：<small>10000@qq.com</small></li>
                        </ul>-->
                    </div>
                </div>
            </div>
        </div>
    </#if>
    <div class="sidebar-module article-module hide" style="top: 0;">
        <h5 class="custom-title"><i class="fa fa-book fa-fw icon"></i><strong>本文目录</strong><i class="fa fa-close pull-right close-article-menu hide pointer"></i><small></small></h5>
        <div id="article-menu" style="overflow:auto">
            <ul class="list-unstyled"></ul>
        </div>
    </div>
    <div class="sidebar-module">
        <h5 class="custom-title"><i class="fa fa-tags fa-fw icon"></i><strong>标签云</strong><small></small></h5>
        <@zhydTag method="tagsList" pageSize="10">
            <#if tagsList?? && (tagsList?size > 0)>
                <#list tagsList as item>
                    <a style="font-size: <@zhydTag method="random" max="15" min="10">${random}</@zhydTag>px;margin: 5px;" href="${config.siteUrl}/tag/${item.id?c}" title="${item.name!}" data-toggle="tooltip" data-placement="bottom">
                        ${item.name!}
                    </a>
                </#list>
            </#if>
        </@zhydTag>
    </div>
    <#if config.comment! && config.comment == 1>
    <@zhydTag method="recentComments" pageSize="5">
        <#if recentComments?? && recentComments?size gt 0>
            <div class="sidebar-module">
                <h5 class="custom-title"><i class="fa fa-comments fa-fw icon"></i><strong>近期评论</strong><small></small></h5>
                <ul class="list-unstyled list-inline comments">
                <#list recentComments as item>
                    <li>
                        <div class="clearfix" style="display: block;">
                            <span>
                                <img alt="${item.nickname!}" src="${item.avatar!}" class="avatar auto-shake" width="50" height="50" onerror="this.src='${config.staticWebSite}/img/user.png'">
                            </span>
                            <span class="newest_comment_author">
                                ${item.nickname!}
                            </span>
                            <span class="fr" style="float: right;">
                                <time style="font-size: 12px;">${item.createTime?string('yyyy-MM-dd HH:mm:ss')}</time>
                            </span>
                        </div>
                        <div style="background-color: #f5f5f5;line-height: 1.7;border-radius: 3px;color: #333;" class="newest_comment_content pd10 mar10-t pos-r mar5-b pjt fs13">
                            <a href="${item.sourceUrl}#comment-${item.id?c}" title="${item.briefContent!}">
                                ${item.briefContent!}
                            </a>
                        </div>
                        <span class="gray fs12" style="word-break: break-all;">来自：
                            <a href="${item.sourceUrl}#comment-${item.id?c}">${item.articleTitle}</a>
                        </span>
                    </li>
                </#list>
                </ul>
            </div>
        </#if>
    </@zhydTag>
    </#if>
    <div class="sidebar-module">
        <ul class="nav nav-tabs sidebar-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"><i class="fa fa-list"></i>近期文章</a></li>
            <li role="presentation"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><i class="fa fa-thumbs-o-up"></i>站长推荐</a></li>
            <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab"><i class="fa fa-hand-peace-o"></i>随机文章</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="profile">
                <ol class="list-unstyled">
                    <@articleTag method="recentArticles" pageSize="10">
                        <#if recentArticles?? && (recentArticles?size > 0)>
                            <#list recentArticles as item>
                                <li>
                                    <a href="${config.siteUrl}/article/${item.id?c}" title="${item.title}" data-toggle="tooltip" data-placement="bottom">
                                        <i class="fa fa-book fa-fw"></i> ${item.title}
                                    </a>
                                </li>
                            </#list>
                        <#else>
                            <li class="empty-list">
                                <i class="fa fa-bookmark-o fa-fw"></i> 暂无相关文章
                            </li>
                        </#if>
                    </@articleTag>
                </ol>
            </div>
            <div role="tabpanel" class="tab-pane" id="home">
                <ol class="list-unstyled">
                    <@articleTag method="recommendedList" pageSize="10">
                        <#if recommendedList?? && (recommendedList?size > 0)>
                            <#list recommendedList as item>
                                <li>
                                    <a href="${config.siteUrl}/article/${item.id?c}" title="${item.title}" data-toggle="tooltip" data-placement="bottom">
                                        <i class="fa fa-book fa-fw"></i> ${item.title}
                                    </a>
                                </li>
                            </#list>
                        <#else>
                            <li class="empty-list">
                                <i class="fa fa-bookmark-o fa-fw"></i> 暂无相关文章
                            </li>
                        </#if>
                    </@articleTag>
                </ol>
            </div>
            <div role="tabpanel" class="tab-pane" id="messages">
                <ol class="list-unstyled">
                    <@articleTag method="randomList" pageSize="10">
                        <#if randomList?? && (randomList?size > 0)>
                            <#list randomList as item>
                                <li>
                                    <a href="${config.siteUrl}/article/${item.id?c}" title="${item.title}" data-toggle="tooltip" data-placement="bottom">
                                        <i class="fa fa-book fa-fw"></i> ${item.title}
                                    </a>
                                </li>
                            </#list>
                        <#else>
                            <li class="empty-list">
                                <i class="fa fa-bookmark-o fa-fw"></i> 暂无相关文章
                            </li>
                        </#if>
                    </@articleTag>
                </ol>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <div class="sidebar-module">
        <h5 class="custom-title"><i class="fa fa-info fa-fw icon"></i><strong>网站信息</strong><small></small></h5>
        <ul class="ul-default site-info">
            <@zhydTag method="siteInfo">
                <li> <i class="fa fa-file fa-fw"></i>  文章总数：${siteInfo.articleCount!(0)} 篇</li>
                <li> <i class="fa fa-tags fa-fw"></i> 标签总数：${siteInfo.tagCount!(0)} 个</li>
                <li> <i class="fa fa-folder-open fa-fw"></i> 分类总数：${siteInfo.typeCount!(0)} 个</li>
                <li> <i class="fa fa-comments fa-fw"></i> 留言数量：${siteInfo.commentCount!(0)} 条</li>
                <li> <i class="fa fa-users fa-fw"></i> 在线人数：<span class="online">1</span>人</li>
                <li> <i class="fa fa-calendar fa-fw"></i> 运行天数：${siteInfo.installdate!(1)}天</li>
                <li> <i class="fa fa-pencil-square fa-fw"></i> 最后更新：${siteInfo.lastUpdateTime!("暂无更新记录")}</li>
                <li> <i class="fa fa-vine fa-fw"></i>  系统版本：<a href="https://gitee.com/yadong.zhang/DBlog/releases/${appInfo.version!}" rel="nofollow" target="_blank" style="color: #4286ca;font-weight: 600;">${appInfo.version!}</a></li>
            </@zhydTag>
        </ul>
    </div>
    <#-- 广告位 -->
    <div class="ad-mark" id="SIDEBAR_BOTTOM" style="display: none"></div>
</div>
