<#include "include/macros.ftl">
<@header>
    <style>
        .about ul {
            -webkit-padding-start: 40px!important;
        }
    </style>
</@header>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>欢迎光临
                    <small>DBlog${config.siteName}后台</small>
                </h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>关于开源项目【DBlog】</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content about" style="max-height: 200px;overflow-y: auto">
                                <h2>项目相关</h2>
                                <ul>
                                    <li>开源项目地址：<a href="https://gitee.com/yadong.zhang/DBlog" target="_blank">https://gitee.com/yadong.zhang/DBlog</a></li>
                                    <li>博主网站首页：<a href="https://www.zhyd.me" target="_blank">https://www.zhyd.me</a></li>
                                </ul>
                                <h2>获取帮助</h2>
                                <ul>
                                    <li>相关Wiki：<a href="https://gitee.com/yadong.zhang/DBlog/wikis" target="_blank">https://gitee.com/yadong.zhang/DBlog/wikis</a></li>
                                    <li>提issue：<a href="https://gitee.com/yadong.zhang/DBlog/issues" target="_blank">https://gitee.com/yadong.zhang/DBlog/issues</a></li>
                                    <li>留言：<a href="https://www.zhyd.me/guestbook" target="_blank">https://www.zhyd.me/guestbook</a></li>
                                    <li>加QQ群：<a href="http://shang.qq.com/wpa/qunwpa?idkey=9f986e9b33b1de953e1ef9a96cdeec990affd0ac7855e00ff103514de2027b60" target="_blank">190886500</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <@shiro.hasPermission name="articles">
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>最近发布</h2>
                                <@shiro.hasPermission name="article:publish">
                                    <a href="/article/publishMd" class="btn btn-success btn-xs pull-right"><i class="fa fa-pencil"></i> 发布文章</a>
                                </@shiro.hasPermission>
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
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="comments">
                    <div class="col-md-6 col-sm-6 col-xs-12">
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
                    </@shiro.hasPermission>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>系统日志</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content" style="max-height: 200px;overflow-y: auto">
                                <ul class="list-unstyled timeline">
                                    <li>
                                        <div class="block">
                                            <div class="tags">
                                                <a href="javascript:;" class="tag">
                                                    <span>06月05日</span>
                                                </a>
                                            </div>
                                            <div class="block_content">
                                                <h2 class="title">
                                                    <span>更新说明</span>
                                                </h2>
                                                <div class="byline">
                                                    <span>13 hours ago</span> by <a>zyd</a>
                                                </div>
                                                <div class="excerpt">
                                                    <h5>修改功能：</h5>
                                                    <ol class="list-unstyled">
                                                        <li>1. admin用户首页报错的问题    </li>
                                                    </ol>
                                                    <h5>优化功能：</h5>
                                                    <ol class="list-unstyled">
                                                        <li>1. ROOT用户默认拥有所有权限</li>
                                                        <li>2. admin页面改为macro引用的方式</li>
                                                        <li>3. 登录界面</li>
                                                        <li>4. 日志记录</li>
                                                    </ol>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="block">
                                            <div class="tags">
                                                <a href="javascript:;" class="tag">
                                                    <span>05月25日</span>
                                                </a>
                                            </div>
                                            <div class="block_content">
                                                <h2 class="title">
                                                    <span>更新说明</span>
                                                </h2>
                                                <div class="byline">
                                                    <span>13 hours ago</span> by <a>zyd</a>
                                                </div>
                                                <div class="excerpt">
                                                    <h5>修改功能：</h5>
                                                    <ol class="list-unstyled">
                                                        <li>1. 修复后台标签等分页失败的问题</li>
                                                        <li>2. 修复前台自动申请友链失败的问题</li>
                                                        <li>3. 其他一些问题</li>
                                                    </ol>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="block">
                                            <div class="tags">
                                                <a href="javascript:;" class="tag">
                                                    <span>05月22日</span>
                                                </a>
                                            </div>
                                            <div class="block_content">
                                                <h2 class="title">
                                                    <span>更新说明</span>
                                                </h2>
                                                <div class="byline">
                                                    <span>13 hours ago</span> by <a>zyd</a>
                                                </div>
                                                <div class="excerpt">
                                                    <h5>修改功能：</h5>
                                                    <ol class="list-unstyled">
                                                        <li>1. 完善shiro权限（数据库、页面）。注：需要重新执行下`sys_resources`和`sys_role_resources`两张表的`insert`语句</li>
                                                        <li>2. redis配置默认不含密码（鉴于大多数朋友的redis都没有密码做此修改，不过本人 <strong>强烈建议设置下密码</strong>）</li>
                                                    </ol>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="block">
                                            <div class="tags">
                                                <a href="javascript:;" class="tag">
                                                    <span>05月18日</span>
                                                </a>
                                            </div>
                                            <div class="block_content">
                                                <h2 class="title">
                                                    <span>更新说明</span>
                                                </h2>
                                                <div class="byline">
                                                    <span>13 hours ago</span> by <a>zyd</a>
                                                </div>
                                                <div class="excerpt">
                                                    <h5>修复bug：</h5>
                                                    <ol class="list-unstyled">
                                                        <li>1. web端自动申请友链后不显示的问题</li>
                                                        <li>2. config表修改后不能实时刷新的问题</li>
                                                    </ol>
                                                    <h5>增加功能：</h5>
                                                    <ol class="list-unstyled">
                                                        <li>1. 网站赞赏码</li>
                                                        <li>2. 百度推送功能(链接提交到百度站长平台)</li>
                                                    </ol>
                                                    <h5>修改功能：</h5>
                                                    <ol class="list-unstyled">
                                                        <li>1. 百度api的ak和百度推送的token以及七牛云的配置改为通过config表管理</li>
                                                        <li>2. admin模块菜单通过标签实时获取(链接提交到百度站长平台)</li>
                                                        <li>2. 弹窗工具类js结构调整</li>
                                                    </ol>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
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
</div>
<@footer></@footer>