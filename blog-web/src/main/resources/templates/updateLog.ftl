<#include "include/macros.ftl">
<@compress single_line=false>
<@header title="网站更新记录 | ${config.siteName!}"
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
            <div class="panel-group" id="updatelog-body">
                <#list list as item>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a class="panel-title" data-toggle="collapse" data-parent="#updatelog-body" href="#panel-element-${item.id?c}" contenteditable="true">${item.recordeTime?string('yyyy年MM月dd日 HH时')} v${item.version}</a>
                        </div>
                        <div id="panel-element-${item.id?c}" class="panel-collapse collapse">
                            <div class="panel-body" contenteditable="true">
                                ${item.description}
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
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