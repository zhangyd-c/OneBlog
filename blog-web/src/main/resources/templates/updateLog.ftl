<#include "include/macros.ftl">
<@compress single_line=true>
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
        <div class="col-sm-8 blog-main">
            <#if list?exists && (list?size > 0)>
                <div class="blog-body fade-in update-logs updatelog-body">
                    <#list list as item>
                        <h3 class="update-title pointer">${item.recordeTime?string('yyyy年MM月dd日 HH时')} | Version: ${item.version}</h3>
                        <ul class="list-box list-unstyled" style="display: none;">
                            <li>
                                <div>更新内容：${item.description}</div>
                            </li>
                        </ul>
                    </#list>
                </div>
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
<@footer>
    <script>
        $(document).ready(function() {
            (function() {
                var listBox = $('.updatelog-body ul.list-box'),
                    listBoxFirst = $('.updatelog-body ul:first, .updatelog-body ul:first .list-box');
                listBox.hide(1, function() {
                    listBoxFirst.show();
                });
                $(".update-title").click(function () {
                    var $this = $(this);
                    $this.next(".list-box:first").slideToggle(400);
                    return false;
                });
            })();
        });
    </script>
</@footer>
</@compress>