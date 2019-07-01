<nav id="topmenu" class="navbar navbar-default navbar-fixed-top">
    <div class="menu-box">
        <div class="pull-left">
            <ul class="list-unstyled list-inline">
                <li><span id="currentTime"></span></li>
            </ul>
            <div class="clear"></div>
        </div>
        <div class="menu-topmenu-container pull-right">
            <ul class="list-unstyled list-inline pull-left">
                <li><a href="${config.siteUrl}/about" class="menu_a" title="关于博客" data-toggle="tooltip" data-placement="bottom">关于本站</a></li>
                <li><a href="${config.siteUrl}/links" class="menu_a" title="友情链接" data-toggle="tooltip" data-placement="bottom">友情链接</a></li>
            </ul>
            <#if user??>
                <ul class="list-unstyled list-inline nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle menu_a" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-user fa-fw"></i>${user.username!} <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/oauth/logout"><i class="fa fa-sign-out"></i>退出</a></li>
                        </ul>
                    </li>
                </ul>
            <#else>
                <ul class="list-unstyled list-inline pull-left">
                    <li><a href="javascript:;;" data-toggle="modal" data-target="#oauth" rel="nofollow" title="授权登录">登录</a></li>
                </ul>
            </#if>
        </div>
    </div>
</nav>
<div class="modal" id="oauth" tabindex="-1" role="dialog" aria-labelledby="oauthTitle">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <ul class="list-inline">
                    <li><h4 class="modal-title" id="oauthTitle">登录</h4></li>
                    <li><a href="javascript:$.alert.info('没用，别点了！我就没打算开发这个功能。Just to show you~~');"><h4 class="modal-title" id="myModalLabel">注册</h4></a></li>
                </ul>
            </div>
            <div class="modal-body">
                <div class="oauth">
                    <ul class="list-unstyled list-inline oauth-list">
                        <@zhydTag method="listAvailableOAuthPlatforms">
                            <#if listAvailableOAuthPlatforms?? && listAvailableOAuthPlatforms?size gt 0>
                                <#list listAvailableOAuthPlatforms as item>
                                    <li>
                                        <a href="${config.siteUrl}/oauth/render/${item}" target="">
                                            <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/${item}.png" alt="" class="img-circle">
                                        </a>
                                    </li>
                                </#list>
                            <#else>
                                <li>
                                    稍等一下， 博主正在快马加鞭的配置~~
                                </li>
                            </#if>
                        </@zhydTag>
                        <#--<li>
                            <a href="${config.siteUrl}/oauth/render/gitee" target="">
                                <img src="https://gitee.com/logo_icon.png" alt="" class="img-circle">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/github" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/github.png" alt="" class="img-circle">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/weibo" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/weibo.png" alt="" class="img-circle">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/dingtalk" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/dingding.png" alt="" class="img-circle">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/baidu" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/baidu.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/coding" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/coding.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/tencentCloud" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/tencent_cloud.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/oschina" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/oschinas.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/alipay" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/alipay.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/qq" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/qq.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/wechat" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/wechats.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/taobao" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/taobao.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/google" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/google.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="${config.siteUrl}/oauth/render/facebook" target="">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/facebook.png" alt="">
                            </a>
                        </li>-->
                    </ul>
                    <div class="oauth-line">
                        <span>
                            Powered by <a href="https://gitee.com/yadong.zhang/JustAuth" target="_blank">JustAuth</a>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<nav id="mainmenu" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="menu-box">
        <div class="navbar-header">
            <span class="pull-right nav-search toggle-search" data-toggle="modal" data-target=".nav-search-box"><i class="fa fa-search"></i></span>
            <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand logo" href="#"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <div class="pull-left site-desc">
                <h1 class="auto-shake"><a href="${config.siteUrl}" data-original-title="${config.siteDesc}" data-toggle="tooltip" data-placement="bottom">${config.siteName}</a></h1>
                <p class="site-description">${config.siteDesc}</p>
            </div>
            <ul class="nav navbar-nav ">
                <li>
                    <a href="/" class="menu_a"><i class="fa fa-home"></i>首页</a>
                </li>
                <@zhydTag method="types">
                    <#if types?? && types?size gt 0>
                        <#list types as item>
                            <#if item.nodes?? && item.nodes?size gt 0>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle menu_a" data-toggle="dropdown" aria-expanded="false">
                                        <i class="${item.icon!}"></i>${item.name!} <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu" role="menu">
                                        <#list item.nodes as node>
                                        <li><a href="/type/${node.id?c}" title="点击查看《${node.name!}》的文章">${node.name!}</a></li>
                                        </#list>
                                    </ul>
                                </li>
                            <#else>
                                <li><a href="/type/${item.id?c}" class="menu_a"><i class="${item.icon!}"></i>${item.name!}</a></li>
                            </#if>
                        </#list>
                    </#if>
                </@zhydTag>
                <li><a href="/guestbook" class="menu_a"><i class="fa fa-comments-o"></i>留言板</a></li>
                <li><span class="pull-right nav-search main-search" data-toggle="modal" data-target=".nav-search-box"><i class="fa fa-search"></i></span></li>
            </ul>
        </div>
    </div>
</nav>