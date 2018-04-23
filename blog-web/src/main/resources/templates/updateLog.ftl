<#include "include/macros.ftl">
<@header title="网站更新记录 | 一个程序员的个人博客"
    keywords=""
    description="网站更新维护日志"
    canonical="/updateLog">
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>网站更新记录
    </nav>
    <div class="row">
        <div class="col-sm-8">
            <#if list?exists && (list?size > 0)>
                <article class="fade-in update-logs">
                    <ul class="list-unstyled">
                        <#list list as item>
                            <li>
                                更新版本：${item.version}
                                <div>更新内容：${item.description}</div>
                                <span>${item.recordeTime?string('yyyy/MM/dd')}</span>
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
