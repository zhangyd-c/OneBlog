<#include "include/macros.ftl">
<@header title="${config.siteName?if_exists} | 一个程序员的个人博客"
    keywords="${config.homeKeywords?if_exists}"
    description="${config.homeDesc?if_exists}"
    canonical="/${url?if_exists}">
</@header>

<div class="container custome-container">
    <@prompt></@prompt>
    <nav class="breadcrumb">
        <div class="notify"><i class="fa fa-volume-up"></i></div>
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
            <div class="blog-body expansion" style="padding: 0;">
                <div id="myCarousel" class="carousel slide" style="height:300px;">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <@articleTag method="recommendedList" pageSize="8">
                            <#if recommendedList?exists && (recommendedList?size > 0)>
                                <#list recommendedList as item>
                                <li data-target="#myCarousel" data-slide-to="${item_index}" class="${(item_index == 0)?string('active','')}"></li>
                                </#list>
                            </#if>
                        </@articleTag>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner">
                        <@articleTag method="recommendedList" pageSize="8">
                            <#if recommendedList?exists && (recommendedList?size > 0)>
                                <#list recommendedList as item>
                                <div class="item ${(item_index == 0)?string('active','')}">
                                    <a href="${config.siteUrl}/article/${item.id?c}">
                                        <img src="${config.qiuniuBasePath}${item.coverImage}" alt="${item.title}" title="${item.title}">
                                    </a>
                                    <div class="zyd-carousel-caption">${item.title}</div>
                                </div>
                                </#list>
                            </#if>
                        </@articleTag>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
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
            <#if page.list?exists && (page.list?size > 0)>
                <#list page.list as item>
                    <article class="fade-in">
                        <figure class="thumbnail">
                            <a href="${config.siteUrl}/article/${item.id?c}">
                                <#if item.coverImage?exists && (item.coverImage?length > 7)>
                                    <img width="150" height="150" data-original="${config.qiuniuBasePath}${item.coverImage}-img_150x150" class="img-responsive lazy-img" alt="${item.title?if_exists}">
                                <#else>
                                    <img width="150" height="150" data-original="${config.staticWebSite}/img/user/11.jpg" class="img-responsive lazy-img" alt="${item.title?if_exists}">
                                </#if>
                            </a>
                            <span class="cat"><a href="${config.siteUrl}/type/${item.typeId?c}">${item.type.name}</a></span>
                        </figure>
                        <header class="entry-header">
                            <h2 class="entry-title">
                                <a href="${config.siteUrl}/article/${item.id?c}" rel="bookmark" title="点击查看文章详情" data-toggle="tooltip" data-placement="bottom">${item.title}</a>
                            </h2>
                        </header>
                        <div class="entry-content">
                            <div class="archive-content">
                                ${item.description?if_exists}
                            </div>
                            <span class="title-l"></span>
                            <span class="entry-meta">
                                <span class="date" title="文章发表日期" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-calendar-o fa-fw"></i>${item.createTime?string('yyyy-MM-dd')}</span>
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
                            <li><a class="pointer" rel="external nofollow"><span class="label label-default">Java</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-primary">Springboot</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-success">Linux</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-info">Maven</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-warning">Bootstrap</span></a></li>
                            <li><a class="pointer" rel="external nofollow"><span class="label label-danger">阿里云</span></a></li>
                        </ul>
                    </form>
                </article>
            </#if>
        </div>
        <#include "layout/sidebar.ftl"/>
    </div>
</div>
<section class="links index-links">
    <ul class="list-unstyled list-inline">
        <#if indexLinkList?exists && (indexLinkList?size > 0)>
            <#list indexLinkList as item>
                <li>
                    <a href="${item.url}" target="_blank" title="${item.description?if_exists}" data-toggle="tooltip" data-placement="bottom">
                        <img src="${item.favicon}" alt="${item.name?if_exists}" onerror="this.src='${config.staticWebSite}/img/user.png'">${item.name?if_exists}
                    </a>
                </li>
            </#list>
            <li>
                <a href="${config.siteUrl}/links" title="更多链接" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-plus"></i>更多链接</a>
            </li>
        <#else>
            <li>
                <a href="${config.siteUrl}/links" title="申请友链" data-toggle="tooltip" data-placement="bottom">申请友链</a>
            </li>
        </#if>
    </ul>
</section>

<@footer></@footer>
