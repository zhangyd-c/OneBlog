<#include "include/macros.ftl">
<@compress single_line=false>
<@header title="${config.siteName!} | 一个程序员的个人博客"
    keywords="${config.homeKeywords!}"
    description="${config.homeDesc!}"
    canonical="/${url!}">
</@header>

<div class="container custome-container">
    <#-- 广告位 -->
    <div class="ad-mark" id="HOMEPAGE_TOP" style="display: none;margin-bottom: 10px"></div>
    <@prompt></@prompt>
    <nav class="breadcrumb">
        <div class="notify"><i class="fa fa-bullhorn fa-fw"></i></div>
        <div id="scrolldiv">
            <div class="scrolltext">
                <ul class="list-unstyled" id="notice-box">
                    <li class="scrolltext-title">
                        <a href="javascript:void(0)" rel="bookmark">本站正式启用新域名:<a href="https:/www.zhyd.me" target="_blank"><strong>https:/www.zhyd.me</strong></a></a>
                    </li>
                    <li class="scrolltext-title">
                        <a href="javascript:void(0)" rel="bookmark">活了二十多年，没能为祖国、为人民做点什么，每思及此，伤心欲绝。</a>
                    </li>
                    <li class="scrolltext-title">
                        <a href="javascript:void(0)" rel="bookmark">其实我们可以将所有的问题归结为两种：一种是没饭吃饿出来的；一种是吃饱了撑出来的。</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="row">
        <div class="col-sm-8 blog-main">
            <@articleTag method="recommendedList" pageSize="8">
                <#if recommendedList?? && (recommendedList?size > 0)>
                <div class="blog-body expansion" style="padding: 0;">
                    <div id="myCarousel" class="carousel slide" style="height:300px;">
                        <ol class="carousel-indicators">
                            <#list recommendedList as item>
                            <li data-target="#myCarousel" data-slide-to="${item_index}" class="${(item_index == 0)?string('active','')}"></li>
                            </#list>
                        </ol>
                        <div class="carousel-inner">
                            <#list recommendedList as item>
                            <div class="item ${(item_index == 0)?string('active','')}">
                                <a href="${config.siteUrl}/article/${item.id?c}">
                                    <img src="${item.coverImage}" onerror="this.src='${config.staticWebSite}/img/defaultbanner.png'" alt="${item.title}" title="${item.title}">
                                </a>
                                <div class="zyd-carousel-caption">${item.title}</div>
                            </div>
                            </#list>
                        </div>
                        <a class="left carousel-control hide" href="#myCarousel" role="button" data-slide="prev">
                            <span class="fa fa-angle-left fa-fw fa-3x" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control hide" href="#myCarousel" role="button" data-slide="next">
                            <span class="fa fa-angle-right fa-fw fa-3x" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
                </#if>
            </@articleTag>
            <@zhydTag method="scrollmenus" position="scrollmenu">
                <#if scrollmenus?? && scrollmenus?size gt 0>
                    <div class="blog-body expansion" style="padding: 0;">
                        <div class="scrollmenu nav-tags">
                            <span>更多分类：</span>
                            <#list scrollmenus as item>
                                <a href="/type/${item.id?c}" id="scrollmenu-${item.id?c}"><i class="${item.icon!}"></i>${item.name!}</a>
                            </#list>
                        </div>
                    </div>
                </#if>
            </@zhydTag>
<#--            <div class="separateline"><span>以下为最新文章</span></div>-->
         <#--   <div class="blog-body expansion" style="padding: 0;margin-bottom: 0;border-bottom: 1px solid #eeeeee;">
                <div class="scrollmenu nav-tags">
                    <a href="/type/1" id="scrollmenu-1" class="red" style="color: red">最新文章</a>
                    <a href="/type/1" id="scrollmenu-1">置顶文章</a>
                    <a href="/type/1" id="scrollmenu-1">热门文章</a>
                    <a href="/type/1" id="scrollmenu-1">评论最多</a>
                    <a href="/type/1" id="scrollmenu-1">点赞最多</a>
                </div>
            </div>-->
            <#if page.list?? && (page.list?size > 0)>
                <#list page.list as item>
                    <article class="fade-in">
                        <#if item.coverImage?? && (item.coverImage?length > 7)>
                            <figure class="thumbnail">
                                <a href="${config.siteUrl}/article/${item.id?c}">
                                    <img width="150" height="150" <#if config.lazyloadPath!>data-original<#else>src</#if>="${item.coverImage}" onerror="this.src='${config.staticWebSite}/img/default.png'" class="img-responsive lazy-img" alt="${item.title!}">
                                </a>
                            </figure>
                        </#if>
                        <header class="entry-header">
                            <#if item.original?string('true','false') == 'true'>
                                <span class="art art-original"><i class="fa fa-check fa-fw"></i>原创</span>
                            <#else>
                                <span class="art art-original-0"><i class="fa fa-reply fa-fw"></i>转载</span>
                            </#if>
                            <#if item.private>
                                <span class="art art-type art-type-yellow"><i class="fa fa-lock fa-fw"></i>私密</span>
                            </#if>
                            <#if item.top>
                                <span class="art art-top"><i class="fa fa-chevron-circle-up fa-fw"></i>置顶</span>
                            </#if>
                            <span class="art art-type"><a href="${config.siteUrl}/type/${item.typeId?c}"><i class="${item.type.icon!} fa-fw"></i> ${item.type.name}</a></span>
                            <h2 class="entry-title">
                                <a href="${config.siteUrl}/article/${item.id?c}" rel="bookmark" title="${item.title}" data-toggle="tooltip" data-placement="bottom">${item.title}</a>
                            </h2>
                        </header>
                        <div class="entry-content">
                            <div class="archive-content">
                                ${item.description!}
                            </div>
                            <span class="entry-meta">
                                <span class="date" title="文章发表日期" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-clock-o fa-fw"></i>${item.createTime?string('yyyy-MM-dd')}</span>
                                <span class="views" title="文章阅读次数" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-eye fa-fw"></i>浏览(${item.lookCount!(0)})</span>
                                <span class="comment" title="文章评论次数" data-toggle="tooltip" data-placement="bottom">
                                    <a href="${config.siteUrl}/article/${item.id?c}#comment-box" rel="external nofollow">
                                        <i class="fa fa-comments-o fa-fw"></i>评论(${item.commentCount!(0)})
                                    </a>
                                </span>
                            </span>
                            <div class="clear"></div>
                            <span class="entry-more">
                                <a href="${config.siteUrl}/article/${item.id?c}" rel="bookmark" title="点击查看文章详情" data-toggle="tooltip" data-placement="bottom">阅读全文</a>
                            </span>
                        </div>
                    </article>
                </#list>
                <@pageBar></@pageBar>
            <#else >
                <article class="fade-in" style="height: auto">
                    <div class="rows">
                        <div class="col-md-10 col-sm-12">
                            <div class="h3 text-center">亲，啥也没找到啊~~<img src="http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif" alt="[囧]"></div>
                            <div class="h5 text-center">换个姿势，再来一次~~</div>
                        </div>
                        <div class="col-md-2 col-sm-12">
                            <img src="${config.staticWebSite}/img/wrong.gif" class="center-block" alt="惩罚我" style="width: 110px;margin-top: -10px;">
                        </div>
                    </div>
                    <form action="/" method="post" class="form-horizontal searchForm">
                        <input type="hidden" name="pageNumber" value="1">
                        <div class="input-group">
                            <input type="text" class="form-control br-none" name="keywords" placeholder="换个姿势，再来一次~~">
                            <span class="input-group-btn">
                                <button class="btn btn-default br-none nav-search-btn pointer" type="submit"><i class="fa fa-search"></i> 搜索</button>
                            </span>
                        </div>
                        <div class="clear" style="margin-bottom: 10px"></div>
                        <ul class="list-unstyled list-inline search-hot">
                            <li><strong style="position: relative;top: 2px;color: #999999;">热门搜索：</strong></li>
                            <@zhydTag method="searchOptions">
                                <#if searchOptions?? && (searchOptions?size > 0)>
                                    <#list searchOptions as item>
                                        <li><a class="pointer" rel="external nofollow"><span class="label label-info">${item}</span></a></li>
                                    </#list>
                                </#if>
                            </@zhydTag>
                        </ul>
                    </form>
                </article>
            </#if>
        </div>
        <#include "layout/sidebar.ftl"/>
    </div>
</div>
<#-- 广告位 -->
<div id="HOMEPAGE_OPEN_SCREEN" style="display: none" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">站长力荐<i class="fa fa-fire red"></i></h4>
            </div>
            <div class="modal-body ad-mark" id="ad-body">
            </div>
        </div>
    </div>
</div>
<@footer>
    <script>
        var typeMatch = location.href.match(/\/type\/([0-9]+)/);
        if(null != typeMatch) {
            var typeId = typeMatch[1];
            $("#scrollmenu-" + typeId).addClass("active").attr("href", "javascript:void(0)");
        }
    </script>
</@footer>
</@compress>
