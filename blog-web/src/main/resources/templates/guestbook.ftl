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
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div class="alert alert-default alert-dismissible" role="alert" style="padding: 0px;">
                    <ul>
                        <li><i class="fa fa-lightbulb-o fa-fw"></i> 随便留言，只要不是反人类、反国家的危险言论。<strong>相信我！删除真的只需要1秒！</strong></li>
                        <li><i class="fa fa-lightbulb-o fa-fw"></i> 欢迎各种花式留言！e.g.<code>System.out.println("突如其来的骚，闪了老子的腰！");</code></li>
                        <li><i class="fa fa-heartbeat fa-fw"></i> <strong>爱谁谁...</strong></li>
                    </ul>
                </div>
            </div>
        </div>
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
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/highlight.js@9.12.0/lib/highlight.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/simplemde@1.11.2/dist/simplemde.min.js"></script>
</@footer>
</@compress>