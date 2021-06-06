<#include "include/macros.ftl">
<@header>
    <style>
        .notice-box ul {
            -webkit-padding-start: 40px!important;
        }
        .statistics-panel {
            color: #73879C;
        }
        .statistics-box {
            z-index: 1;
            padding-bottom: 15px;
            margin-bottom: 0;
        }
        .statistics-box:hover {
            transform: translateY(-2px);
            -webkit-transform: translateY(-2px);
            -moz-transform: translateY(-2px);
            box-shadow: -10px 10px 30px -15px #9e9c9c;
            -webkit-box-shadow: -10px 10px 30px -15px #9e9c9c;
            -moz-box-shadow: -10px 10px 30px -15px #9e9c9c;
            transition: all .3s ease;
        }
        .statistics-box .icon i {
            margin: 0;
            font-size: 45px;
            line-height: 0;
            vertical-align: bottom;
            padding: 0;
        }

        .panel_toolbox {
            min-width: auto;
        }

        #statistics-article-list li.title {
            padding: 2px;
            width: 85%;
            float: left
        }

        .recentArticles th.title div{
            width: 200px;
        }

        .recentComments .content{
            width: 150px;
        }

        .recentComments .source div{
            width: 80px;
        }
        .word-prase {
            white-space: nowrap;
            word-wrap: normal;
            text-overflow: ellipsis;
            overflow: hidden;
        }

        #statistics-article-list li.count {
            padding: 2px;
            width: 15%;
            float: left;
            text-align: right;
        }
        .notice-box li{
            line-height: 25px;
        }
    </style>
</@header>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12 top_tiles">
        <#-- 文章 -->
        <a href="/articles" class="statistics-panel">
            <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 statistics-article" data-key="articleCount">
                <div class="tile-stats statistics-box">
                    <div class="icon"><i style="color: #5470c6;" class="fa fa-envira"></i></div>
                    <div class="count" style="color: #9a60b4"></div>
                    <h4>文章</h4>
                </div>
            </div>
        </a>
        <#-- 标签 -->
        <a href="/article/tags" class="statistics-panel">
            <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 statistics-tag" data-key="tagCount">
                <div class="tile-stats statistics-box">
                    <div class="icon"><i style="color: #91cc75;" class="fa fa-tags"></i></div>
                    <div class="count" style="color: #ea7ccc"></div>
                    <h4>标签</h4>
                </div>
            </div>
        </a>
        <#-- 分类 -->
        <a href="/article/types" class="statistics-panel">
            <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 statistics-type" data-key="typeCount">
                <div class="tile-stats statistics-box">
                    <div class="icon"><i style="color: #fac858;" class="fa fa-th"></i></div>
                    <div class="count" style="color: #60c0dd"></div>
                    <h4>分类</h4>
                </div>
            </div>
        </a>
        <#-- 评论 -->
        <a href="/comments" class="statistics-panel">
            <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 statistics-comment" data-key="commentCount">
                <div class="tile-stats statistics-box">
                    <div class="icon"><i style="color: #ee6666;" class="fa fa-comments-o"></i></div>
                    <div class="count" style="color: #d7504b"></div>
                    <h4>留言数</h4>
                </div>
            </div>
        </a>

        <#--总访问 量-->
        <a id="fangwen" href="#" class="statistics-panel">
            <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 statistics-lookCount" data-key="lookCount">
                <#--隐藏域，放进去值，方便运算-->
                <input type="hidden" id="fangwen_inp" value="">
                <div class="tile-stats statistics-box">
                    <div class="icon"><i style="color: #73c0de;" class="fa fa-bar-chart"></i></div>
                    <div class="count" id="l_count_mxx" style="color: #c6e579;"></div>
                    <h4>访问量</h4>
                </div>
            </div>
        </a>
        <#--总赞数-->
        <a href="/articles" class="statistics-panel">
            <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 statistics-zanCount" data-key="zanCount">
                <div class="tile-stats statistics-box">
                    <div class="icon"><i style="color: #FF6666;" class="fa fa-thumbs-o-up"></i></div>
                    <div class="count"></div>
                    <h4>总赞量</h4>
                </div>
            </div>
        </a>
    </div>
</div>
<div class="row">
    <#-- 分类文章数统计 -->
    <div class="col-md-4 col-sm-4 col-xs-12">
        <div class="x_panel fixed_height_320 statistics-box">
            <div class="x_title">
                <h2>分类文章数统计</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div id="echart_type" style="height: 250px;"></div>
            </div>
        </div>
    </div>
    <#-- 文章访问TOP.10 -->
    <div class="col-md-4 col-sm-4 col-xs-12">
        <div class="x_panel fixed_height_320 statistics-box">
            <div class="x_title">
                <h2>文章访问TOP.10</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content" id="statistics-article-list">
                <ul class="list-unstyled">
                    <@articleTag method="hotList" pageSize="10">
                        <#if hotList?? && (hotList?size > 0)>
                            <#list hotList as item>
                            <li class="title word-prase"><a href="${config.siteUrl}/article/${item.id?c}" title="${item.title}">${item.title}</a></li>
                            <li class="count"><span title="浏览人次：${item.lookCount?c}">${item.lookCount?c}</span></li>
                            </#list>
                        </#if>
                    </@articleTag>
                </ul>
            </div>
        </div>
    </div>
    <#-- 爬虫访问统计TOP.10 -->
    <div class="col-md-4 col-sm-4 col-xs-12">
        <div class="x_panel fixed_height_320 statistics-box">
            <div class="x_title">
                <h2>爬虫访问统计TOP.10</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div id="echart_spider" style="height: 250px;"></div>
            </div>
        </div>
    </div>

    <#-- 近期文章 -->
    <div class="col-md-6 col-sm-6 col-xs-12">
        <div class="x_panel statistics-box">
            <div class="x_title">
                <h2>近期文章 <small> </small></h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a href="/articles" target="_blank" title="查看更多"><i class="fa fa-ellipsis-h"></i></a></li>
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <table class="table table-bordered recentArticles">
                    <thead>
                    <tr>
                        <th class="title"><div class="word-prase">标题</div></th>
                        <th>分类</th>
                        <th>浏览数</th>
                        <th>发布时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <@articleTag method="recentArticles" pageSize="5">
                        <#if recentArticles?? && (recentArticles?size > 0)>
                            <#list recentArticles as item>
                            <tr>
                                <th class="title"><div class="word-prase"><a href="${config.siteUrl}/article/${item.id?c}" title="${item.title}">${item.title}</a></div></th>
                                <td><a href="${config.siteUrl}/type/${item.type.id?c}" target="_blank">${item.type.name}</a></td>
                                <td>${item.lookCount?c}</td>
                                <td>${item.createTime?string('yyyy-MM-dd')}</td>
                            </tr>
                            </#list>
                        </#if>
                    </@articleTag>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <#-- 近期评论 -->
    <div class="col-md-6 col-sm-6 col-xs-12">
        <div class="x_panel statistics-box">
            <div class="x_title">
                <h2>近期评论 <small> </small></h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a href="/comments" target="_blank" title="查看更多"><i class="fa fa-ellipsis-h"></i></a></li>
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <table class="table table-bordered recentComments">
                    <thead>
                    <tr>
                        <th><div>发起人</div></th>
                        <th class="content"><div class="word-prase">评论内容</div></th>
                        <th class="source"><div class="word-prase">出处</div></th>
                        <th>评论时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <@zhydTag method="recentComments" pageSize="5">
                        <#if recentComments?? && (recentComments?size > 0)>
                            <#list recentComments as item>
                            <tr>
                                <th class="title word-prase"><div><a href="${item.url}" target="_blank" rel="external nofollow">${item.nickname!}</a></div></th>
                                <td class="content"><div class="word-prase">${item.briefContent!}</div></td>
                                <td class="source"><div class="word-prase"><a href="${config.siteUrl}${item.sourceUrl}#comment-${item.id?c}" target="_blank" rel="external nofollow">${item.articleTitle!}</a></div></td>
                                <td>${item.createTime?string('yyyy-MM-dd')}</td>
                            </tr>
                            </#list>
                        </#if>
                    </@zhydTag>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
<#-- 网站首页的项目介绍内容 -->
<@aboutOneBlog></@aboutOneBlog>
<@footer>
<script src="https://cdn.jsdelivr.net/npm/echarts@4.1.0/dist/echarts.min.js"></script>
<script src="/assets/js/zhyd.echarts.js"></script>
<#-- 显示或者关闭首页的弹窗，当点击“不再显示后”，如果清缓存，这个弹窗仍然会再次显示 -->
<script type="text/javascript">
    var neverShowNoticeModal = localStorage.getItem("neverShowNoticeModal");
    if(!neverShowNoticeModal) {
        $("#noticeModal").modal('show');
    }
    $("#neverShowNoticeModal").click(function (){
        localStorage.setItem("neverShowNoticeModal", 1);
    })
</script>
    <script>
        /* 顶部卡片统计 */
        $.post("/statistics/siteInfo", function (json) {
            $.alert.ajaxSuccess(json);
            if (json.status == 200) {
                //给隐藏域里面设置访问量，好让点击的时候能看到具体的数字，心理有个数
                var $this = $(".statistics-lookCount");
                var jsonData = json.data;
                var jsonKey = $this.data("key");
                $("#fangwen_inp").val(jsonData[jsonKey]);

                //end

                function setValue(dom, value) {
                    var $dom = dom;
                    $dom.find("div.tile-stats .count").text(value);

                }

                $(".statistics-tag, .statistics-type, .statistics-comment, .statistics-article, .statistics-lookCount, .statistics-zanCount").each(function () {
                    var $this = $(this);
                    var jsonKey = $this.data("key");
                    setValue($this, jsonData[jsonKey]);
                });
            }
            //设置显示数字格式，k或者w
            $(function () {
                var num = $("#l_count_mxx").text();

                // var num = 1209;
                //点击显示访问量
                $("#fangwen").click(function () {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg("总访问量是：" + $("#fangwen_inp").val(), {
                            time: 2000  //20s后自动关闭

                        });
                    });
                });
                //转换数字格式，k或者w
                if (num >= 10000) {
                    num = Math.round(num / 1000) / 10 + 'W';

                } else if (num >= 1000) {
                    num = Math.round(num / 100) / 10 + 'K';
                }
                $("#l_count_mxx").text(num);
                //点击显示详细的


            });

        });


        /* 分类文章数统计 */
        $.post("/statistics/listType", function (json) {
            $.alert.ajaxSuccess(json);
            if (json.status == 200) {
                var jsonData = json.data;
                zhyd.createChart({
                    id: 'echart_type',
                    legendData: getNames(jsonData, 'name'),
                    series: {name: '分类文章数统计', type: 'pie', seriesData: jsonData}
                });
            }
        });

        /* 爬虫访问统计 */
        $.post("/statistics/listSpider", function (json) {
            $.alert.ajaxSuccess(json);
            if (json.status == 200) {
                var jsonData = json.data || [{name: '暂无', value: 0}];
                zhyd.createChart({id:'echart_spider', legendData: getNames(jsonData, 'name'), series:{name:'爬虫访问统计', type: 'pie', seriesData: jsonData}});
            }
        });

        function getNames(arr, key) {
            if(!arr){
                return [];
            }
            var resultArr = [];
            $.each(arr, function (i, v) {
                resultArr.push(v[key]);
            });
            return resultArr;
        }

        init_echarts();
    </script>
</@footer>
