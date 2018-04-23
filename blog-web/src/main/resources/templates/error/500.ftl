<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="${config.authorName} <${config.authorEmail}>">
    <title>Internal Server Error :(</title>
    <#include "layout/quote.ftl">
</head>
<body>
<#include "layout/header.ftl"/>
<div class="container custome-container">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12 blog-main">
            <div class="blog-body">
                <div class="error-container">
                    <div class="h1">
                        <strong>Internal Server Error</strong> <span frown>:(</span>
                    </div>
                    <div>
                        <p class="h4">抱歉，服务器发生异常。</p>
                        <p class="h4">您可以：</p>
                        <ul>
                            <li>稍后重试</li>
                            <li>联系<a target="_blank" href="javascript:void(0);" title="点击QQ联系我"onclick="window.open('tencent://message/?uin=843977358&amp;Site=www.${config.domain}&amp;Menu=yes')" rel="external nofollow">网站管理员</a></li>
                        </ul>
                        <div style="width:100%;text-align: right">
                            <a onclick="javascript:history.go(-1);" style="cursor: pointer">上一页</a>
                            <span class="sepa"></span>
                            <a href="${config.siteUrl}/">返回首页</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "layout/footer.ftl"/>
</body>
</html>
