<#include "include/macros.ftl">
<@header title="站长推荐 | ${config.siteName}"
    keywords="站长推荐"
    description="站长推荐"
    canonical="/recommended">
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>站长推荐
    </nav>
    <div class="row">
        <div class="col-sm-8">
            <#if list?exists && (list?size > 0)>
                <article class="fade-in recommended-articles">
                    <ul class="list-unstyled">
                        <#list list as item>
                            <li>
                                <i class="fa fa-thumbs-o-up"></i><a href="${config.siteUrl}/article/${item.id?c}" rel="bookmark" title="点击查看文章详情" data-toggle="tooltip" data-placement="bottom">${item.title}</a>
                                <span class="info">${item.createTime?string('yyyy/MM/dd')}</span>
                            </li>
                        </#list>
                    </ul>
                </article>
            <#else >
                <article class="fade-in">
                    <div class="h3 text-center">亲，啥也没找到啊~~<img src="http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif" alt="[囧]"></div>
                    <img src="${config.staticWebSite}/img/wrong.gif" class="center-block" alt="惩罚我" style="width: 110px;margin-top: -10px;">
                </article>
            </#if>
        </div>
        <#include "layout/sidebar.ftl"/>
    </div>
</div>

<@footer></@footer>

