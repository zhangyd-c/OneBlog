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
                <li><a href="${config.siteUrl}/about" class="menu_a" title="关于博客" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-info fa-fw"></i>关于</a></li>
                <li><a href="${config.siteUrl}/links" class="menu_a" title="友情链接" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-link fa-fw"></i>友情链接</a></li>
            </ul>
        </div>
    </div>
</nav>
<nav id="mainmenu" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="menu-box">
        <div class="navbar-header">
            <span class="pull-right nav-search toggle-search" data-toggle="modal" data-target=".nav-search-box"><i class="fa fa-search"></i></span>
            <a type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="navbar-brand logo" href="#"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <div class="pull-left site-desc" style="line-height: 0.9;">
                <h1 style="font-size: 20px;font-weight: 700;" class="auto-shake"><a href="${config.siteUrl}" data-original-title="写博客、记日志、闲聊扯淡鼓捣技术" data-toggle="tooltip" data-placement="bottom">${config.siteName}</a></h1>
                <p class="site-description">写博客、记日志、闲聊扯淡鼓捣技术</p>
            </div>
            <ul class="nav navbar-nav ">
                <li>
                    <a href="/" class="menu_a"><i class="fa fa-home"></i>首页</a>
                </li>
                <@zhydTag method="types">
                    <#if types?? && types?size gt 0>
                        <#list types as item>
                            <#if item.nodes?exists && item.nodes?size gt 0>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle menu_a" data-toggle="dropdown" aria-expanded="false">
                                        <i class="${item.icon?if_exists}"></i>${item.name?if_exists} <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu" role="menu">
                                        <#list item.nodes as node>
                                        <li><a href="/type/${node.id?c}" title="点击查看《${node.name?if_exists}》的文章">${node.name?if_exists}</a></li>
                                        </#list>
                                    </ul>
                                </li>
                            <#else>
                                <li><a href="/type/${item.id?c}" class="menu_a"><i class="${item.icon?if_exists}"></i>${item.name?if_exists}</a></li>
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