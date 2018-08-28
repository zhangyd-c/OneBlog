<#include "include/macros.ftl">
<@header title="Page Not Found :("></@header>
<div class="container custome-container">
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12 blog-main">
            <div class="blog-body">
                <div class="error-container">
                    <div class="h1">
                        <strong>Not found</strong> <span frown>:(</span>
                    </div>
                    <div>
                        <p class="h4">抱歉，您尝试查看的页面不存在。</p>
                        <p class="h4">出现这种情况可能有以下两种原因:</p>
                        <ul>
                            <li>当前请求的地址错误</li>
                            <li>当前请求的地址已废弃</li>
                        </ul>
                        <div style="width:100%;text-align: right">
                            <a onclick="javascript:history.go(-1);" style="cursor: pointer" rel="external nofollow">上一页</a>
                            <span class="sepa"></span>
                            <a href="${config.siteUrl}/" >返回首页</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<@footer></@footer>
