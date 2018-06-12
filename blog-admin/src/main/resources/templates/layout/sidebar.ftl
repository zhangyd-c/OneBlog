<!-- sidebar menu -->
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
    <div class="menu_section">
        <ul class="nav side-menu">
            <@shiro.user>
                <li><a href="/"><i class="fa fa-home"></i>首页</a></li>
            </@shiro.user>
            <@zhydTag method="menus" userId="${user.id}">
                <#if menus?? && menus?size gt 0>
                    <#list menus as item>
                        <#if item.nodes?? && item.nodes?size gt 0>
                            <li>
                                <a><i class="${item.icon?if_exists}"></i> ${item.name?if_exists}<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <#list item.nodes as node>
                                        <@shiro.hasPermission name="${node.permission?if_exists}">
                                        <li><a href="${node.url?if_exists}" ${(item.external?exists && item.external)?string('target="_blank"','')}><i class="${node.icon?if_exists}"></i>${node.name?if_exists}</a></li>
                                        </@shiro.hasPermission>
                                    </#list>
                                </ul>
                            </li>
                        <#else>
                            <li><a href="${item.url?if_exists}" ${(item.external?exists && item.external)?string('target="_blank"','')}><i class="${item.icon?if_exists}"></i>${item.name?if_exists}</a></li>
                        </#if>
                    </#list>
                </#if>
            </@zhydTag>
        </ul>
    </div>
</div>
<div class="sidebar-footer hidden-small">
    <a >&nbsp;</a>
    <a >&nbsp;</a>
    <a >&nbsp;</a>
    <a href="/passport/logout" data-toggle="tooltip" data-placement="top" title="" data-original-title="退出系统">
        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
    </a>
</div>