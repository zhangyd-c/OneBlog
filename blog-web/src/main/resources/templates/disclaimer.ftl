<#include "include/macros.ftl">
<@header title="免责声明 | ${config.siteName}"
    keywords="免责声明"
    description="免责声明"
    canonical="/disclaimer">
    <style type="text/css">
            .bottom-line{
                text-align: center;
            }
            .disclaimer-box {
                margin: 30px 10px;
            }
            .disclaimer-box li {
                color: #000;
                line-height: 30px;
                margin: 5px 0 5px 10px;
            }
    </style>
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a> <i
            class="fa fa-angle-right"></i>免责声明
    </nav>
    <div class="row">
        <div class="col-sm-8 blog-main">
            <div class="blog-body overflow-initial fade-in">
                <div class="blog-info overflow-initial">
                    <div class="bottom-line">
                        <h1 class="blog-info-title">
                            <strong>免责声明</strong>
                        </h1>
                        <p>作者：<a href="${config.siteUrl}" title="网站作者">${config.authorName}</a>&nbsp;&nbsp;&nbsp;&nbsp; 发布时间：2018年3月16日</p>
                    </div>
                    <div class="blog-info-body">
                        <ul class="list-unstyled disclaimer-box">
                            <li>1、本网站属于个人非赢利性质的网站，所有转载的文章都以遵循原作者的版权声明注明了文章来源。</li>
                            <li>2、如果原文没有版权声明，按照目前互联网开放的原则，本网站将在不通知作者的情况下转载文章。</li>
                            <li>3、如果原文明确注明“禁止转载”，本网站将不会转载。</li>
                            <li>4、如果本网站转载的文章不符合作者的版权声明或者作者不想让本网站转载您的文章，<a href="mailto:yadong.zhang0415@gmail.com" target="_blank" title="点击给我发邮件" rel="external nofollow"><i class="fa fa fa-envelope fa-fw"></i>请邮件告知</a>，本站将会在第一时间删除相关信息！</li>
                            <li>5、本网站转载文章仅为留作备份和知识点分享的目的。</li>
                            <li>6、本网站将尽力确保所提供信息的准确性及可靠性，但不保证信息的正确性和完整性，且不对因信息的不正确或遗漏导致的任何损失或损害承担相关责任。</li>
                            <li>7、本网站所发布、转载的文章，其版权均归原作者所有。如其他自媒体、网站或个人从本网站下载使用，请在转载有关文章时务必尊重该文章的著作权，保留本网站注明的“原文来源”或者自行去原文处复制版权声明，并自负版权等法律责任。</li>
                            <li>8、本网站的所有原创文章皆可以任意转载，但转载时务必请注明出处。</li>
                            <li>9、尊重原创，知识共享！</li>
                        </ul>
                    </div>
                    <div class="article-footer overflow-initial">
                        <span class="blog-description hitokoto num"></span>
                    </div>
                </div>
            </div>
        </div>
        <#include "layout/sidebar.ftl"/>
    </div>
</div>
<@footer>
<script src="https://v1.hitokoto.cn/?encode=js&c=d&select=.hitokoto" defer></script>
</@footer>
