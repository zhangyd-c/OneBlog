<#include "include/macros.ftl">
<@header title="归档目录 | ${config.siteName}"
    keywords="归档目录"
    description="归档目录"
    canonical="/archives">
    <style type="text/css">
        .bottom-line{
            text-align: center;
        }
        .archives-meta{
            margin: 10px;
        }
        /* 文章归档盒子 */
        .archives-box {
            margin: 30px 10px;
        }

        .archives-box .month {
            color: #000;
            line-height: 30px;
            margin: 5px 0 5px 10px;
        }

        .archives-box .month-li {
            line-height: 25px;
            text-indent: 2em;
        }

        .archives-box .num {
            color: #999;
            font-size: 14px;
            font-weight: 100;
        }

        .archives-box .article-item {
            text-indent: 4em;
        }

    </style>
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a> <i
            class="fa fa-angle-right"></i>归档目录
    </nav>
    <div class="row">
        <div class="col-sm-8 blog-main">
            <div class="blog-body overflow-initial fade-in">
                <div class="blog-info overflow-initial">
                    <div class="bottom-line">
                        <h1 class="blog-info-title">
                            <strong>文章归档目录</strong>
                        </h1>
                        <@zhydTag method="siteInfo">
                            <div class="archives-meta"> 站点统计：${siteInfo.typeCount!(0)}个分类&nbsp;&nbsp; ${siteInfo.tagCount!(0)}个标签&nbsp;&nbsp; ${siteInfo.articleCount!(0)}篇文章&nbsp;&nbsp; ${siteInfo.commentCount!(0)}条留言&nbsp;&nbsp; 最后更新：${siteInfo.recordeTime} </div>
                        </@zhydTag>
                        <p class="blog-description hitokoto"></p>
                    </div>
                    <div class="blog-info-body">
                        <div class="archives-box overflow-initial">
                            <#list archives['years'] as year>
                                <h3 class="year pointer">${year}年 </h3>
                                <ul class="list-box" style="display: none;">
                                <#list archives[year] as month>
                                    <li>
                                        <span class="month pointer">${month}月</span>
                                        <ul class="list-box" style="display: none;">
                                            <#assign yearMonth='${year}-${month}'/>
                                            <#list archives[yearMonth] as day>
                                                <li class="month-li">
                                                    <#assign dateTime='${yearMonth}-${day}'/>
                                                    <span class="day pointer">${day}日 <span class="num">${archives[dateTime]?size}篇</span> </span>
                                                    <ul class="list-box" style="display: none;">
                                                        <#list archives[dateTime] as article>
                                                            <li class="article-item"><a href="${config.siteUrl}/article/${article.id?c}" rel="bookmark" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="${article.title}">${article.title}</a> </li>
                                                        </#list>
                                                    </ul>
                                                </li>
                                            </#list>
                                        </ul>
                                    </li>
                                </#list>
                                </ul>
                            </#list>
                        </div>
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
    <script>
        $(document).ready(function() {
            (function() {
                $('.archives-box .year, .archives-box .month').each(function() {
                    var num = $(this).next().find(".article-item").size();
                    var text = $(this).text();
                    $(this).html(text + ' <span class="num">' + num + ' 篇</span>');
                });
                var listBox = $('.archives-box ul.list-box'),
                        listBoxFirst = $('.archives-box ul:first, .archives-box ul:first .list-box');
                listBox.hide(1, function() {
                    listBoxFirst.show();
                });
                $(".year, .month, .day").click(function () {
                    var $this = $(this);
                    $this.next(".list-box:first").slideToggle(400);
                    return false;
                });
            })();
        });
    </script>
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=.hitokoto%27);dom=$('.hitokoto');for(var i=0;i<dom.length;i%2B%2B){dom[i].innerText=hitokoto;}})()//" defer></script>
</@footer>
