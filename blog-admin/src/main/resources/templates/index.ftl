<#include "layout/header.ftl"/>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>欢迎光临
                    <small>${config.siteName}后台</small>
                </h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="col-md-7 col-sm-7 col-xs-12">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>最近发布</h2>
                                    <a href="/article/publish" class="btn btn-default pull-right"><i class="fa fa-plus"></i> 发布新文章</a>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <ul class="list-unstyled lately lately-article">
                                        <@articleTag method="recentArticles">
                                            <#if recentArticles?? && recentArticles?size gt 0>
                                                <#list recentArticles as item>
                                                    <li style="line-height: 1.8;">
                                                        <span>${item.createTime?string('yyyy-MM-dd HH:mm')}</span>
                                                        <a href="${config.siteUrl}/article/${item.id?c}" target="_blank">${item.title}</a>
                                                    </li>
                                                </#list>
                                            </#if>
                                        </@articleTag>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>近期评论</h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <ul class="list-unstyled lately lately-comment">
                                        <@zhydTag method="recentComments">
                                            <#if recentComments?? && recentComments?size gt 0>
                                                <#list recentComments as item>
                                                    <li style="line-height: 1.8;border-bottom: 1px solid #e8e9e8;margin-bottom: 10px;">
                                                        <strong>${item.nickname}：</strong>
                                                        <a href="${config.siteUrl}${item.sourceUrl}" target="_blank">${item.briefContent?if_exists}</a>
                                                        <p><span>${item.createTime?string('yyyy-MM-dd HH:mm')}</span></p>
                                                    </li>
                                                </#list>
                                            <#else>
                                                <li style="line-height: 1.8;"><span>暂无评论</span></li>
                                            </#if>
                                        </@zhydTag>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-5 col-sm-5 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>系统日志</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <ul class="list-unstyled timeline">
                                <li>
                                    <div class="block">
                                        <div class="tags">
                                            <a href="javascript:;" class="tag">
                                                <span>11月11日</span>
                                            </a>
                                        </div>
                                        <div class="block_content">
                                            <h2 class="title">
                                                <span>上线说明</span>
                                            </h2>
                                            <div class="byline">
                                                <span>13 hours ago</span> by <a>zyd</a>
                                            </div>
                                            <p class="excerpt">上线了<a>Read&nbsp;More</a>
                                            </p>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="block">
                                        <div class="tags">
                                            <a href="javascript:;" class="tag">
                                                <span>01月06日</span>
                                            </a>
                                        </div>
                                        <div class="block_content">
                                            <h2 class="title">
                                                <span>更新说明</span>
                                            </h2>
                                            <div class="byline">
                                                <span>13 hours ago</span> by <a>zyd</a>
                                            </div>
                                            <p class="excerpt">列表页面的js代码提出为zyd.table.js公共代码</p>
                                            <p class="excerpt">zyd.tool.js</p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<#include "layout/footer.ftl"/>