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
                                <a><i class="${item.icon!}"></i> ${item.name!}<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <#list item.nodes as node>
                                        <#if node.permission!>
                                            <@shiro.hasPermission name="${node.permission!}">
                                                <li><a href="${node.url!}" ${(item.external?? && item.external)?string('target="_blank"','')}><i class="${node.icon!}"></i>${node.name!}</a></li>
                                            </@shiro.hasPermission>
                                        <#else>
                                            <li><a href="${node.url!}" ${(item.external?? && item.external)?string('target="_blank"','')}><i class="${node.icon!}"></i>${node.name!}</a></li>
                                        </#if>
                                    </#list>
                                </ul>
                            </li>
                        <#else>
                            <li><a href="${item.url!}" ${(item.external?? && item.external)?string('target="_blank"','')}><i class="${item.icon!}"></i>${item.name!}</a></li>
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