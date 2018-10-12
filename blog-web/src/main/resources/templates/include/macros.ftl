<#-- 公共顶部 -->
<#macro header title="DBlog开源博客" keywords="" description="" canonical="">
<#include "/common/annotation.ftl">
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>${title}</title>
    <meta name="author" content="${config.authorName}(${config.authorEmail})">
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}" id="meta_description">
    <link rel="canonical" href="${config.siteUrl}${canonical}" />
    <#include "/layout/quote.ftl">
    <#--黑白界面
    <style>
        html {
            filter: grayscale(100%);
            -webkit-filter: grayscale(100%);
            -moz-filter: grayscale(100%);
            -ms-filter: grayscale(100%);
            -o-filter: grayscale(100%);
            filter: url("data:image/svg+xml;utf8,<svg xmlns="\'http://www.w3.org/2000/svg\'"><filter id="\'grayscale\'"><feColorMatrix type="\'matrix\'" values="\'0.3333" 0.3333="" 0="" 1="" 0\'=""></fecolormatrix></filter></svg>#grayscale");filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);-webkit-filter:grayscale(1);}
    </style>-->
    <#nested>
</head>
<body>
    <#include "/layout/header.ftl"/>
</#macro>

<#-- 公共底部 -->
<#macro footer>
    <#include "/layout/footer.ftl"/>

    <#nested>

    </body>
</html>
</#macro>

<#-- 分页组件 -->
<#macro pageBar>
    <#if page?exists && (page.pages > 1)>
    <nav class="pagination"
         data-url="${config.siteUrl}/${url?if_exists}"
         data-search="${(model.keywords == null || model.keywords == '')?string('false', 'true')}"
         data-total-page="${page.pages?c}"
         data-current-page="${page.pageNum?c}"
         data-pre="${page.prePage}"
         data-next="${page.nextPage}"></nav>
    </#if>
</#macro>


<#-- blog-header -->
<#macro blogHeader title="Header" weiboName="@我的微博">
    <div class="col-sm-12 blog-main">
        <div class="blog-header">
            <h1 class="blog-title">${title}</h1>
            <p class="blog-description" id="hitokoto"></p>
            <div class="info">
                <a href="javascript:void(0);" target="_blank" title="点击QQ联系我"onclick="window.open('tencent://message/?uin=${config.qq}&amp;Site=www.${config.domain}&amp;Menu=yes')" rel="external nofollow"><i class="fa fa fa-qq fa-fw"></i>QQ联系</a>
                |
                <a href="mailto:${config.authorEmail}" target="_blank" title="点击给我发邮件" rel="external nofollow"><i class="fa fa fa-envelope fa-fw"></i>邮箱联系</a>
                |
                <a href="${config.weibo}" target="_blank" title="点击查看我的微博" rel="external nofollow"><i class="fa fa fa-weibo fa-fw"></i>${weiboName}</a>
            </div>
        </div>
    </div>
</#macro>

<#-- 页面顶部、菜单下方提示栏 -->
<#macro prompt>
    <!--[if lt IE 9]><div class="alert alert-danger topframe" role="alert">Oh My God！你的浏览器实在<strong>太太太太太太旧了</strong>，赶紧升级浏览器 <a target="_blank" class="alert-link" href="http://browsehappy.com">立即升级</a></div><![endif]-->
    <#if config.maintenance?if_exists && config.maintenance>
    <div class="alert alert-warning fade-in" role="alert">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        系统预计将在<strong>${config.maintenanceData?string('yyyy年MM月dd日 HH点mm分')}</strong>进行更新，届时网站将无法使用，更新时间大约 5-10分钟，敬请悉知。
    </div>
    </#if>
</#macro>