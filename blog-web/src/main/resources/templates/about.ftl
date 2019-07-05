<#include "include/macros.ftl">
<@compress single_line=false>
<@header title="关于 | ${config.siteName}" description="关于${config.siteName}" canonical="/about"></@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>关于
    </nav>
    <div class="row about-body">
        <@blogHeader title="关于本站"></@blogHeader>
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <h5 class="custom-title"><i class="fa fa-user-secret fa-fw"></i><strong>博主简介</strong><small></small></h5>
                <div class="info">
                    <p>
                        码农一枚
                    </p>
                </div>
                <h5 class="custom-title"><i class="fa fa-coffee fa-fw"></i><strong>关于博客</strong><small></small></h5>
                <div class="info">
                    本站为非商业化站点，无盈利性质，为博主个人博客。本站二次开发于 <a href="https://gitee.com/yadong.zhang/DBlog" title="" data-toggle="tooltip" data-placement="right" target="_blank" data-original-title="OneBlog是一款简洁美观、自适应的Java博客系统..."><strong>OneBlog</strong></a>。<br>
                </div>
                <h5 class="custom-title"><i class="fa fa-copyright fa-fw"></i><strong>关于版权</strong><small></small></h5>
                <div class="info">
                    本站所有标注为原创的文章，转载请标明出处。<br>
                    本站所有转载的文章，一般都会在文章中注明原文出处。<br>
                    所有转载的文章皆来源于互联网，若因此对原作者造成侵权，烦请原作者<a target="_blank" href="mailto:${config.authorEmail}" title="点击给我发邮件" data-toggle="tooltip" data-placement="bottom" rel="external nofollow"><strong>告知</strong></a>，我会立刻删除相关内容。
                </div>
                <@praise></@praise>
            </div>
        </div>
        <#--<div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div id="comment-box" data-id="-3"></div>
            </div>
        </div>-->
    </div>
</div>

<@footer>
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>
</@footer>
</@compress>