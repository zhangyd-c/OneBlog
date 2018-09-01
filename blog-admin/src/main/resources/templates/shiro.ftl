<#include "include/macros.ftl">
<@header></@header>
    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Shiro标签测试</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <h2>1.guest（游客）</h2>
                    <pre>&lt;@shiro.guest&gt;
    您当前是游客，&lt;a href="javascript:void(0);" class="dropdown-toggle qqlogin" &gt;登录&lt;/a&gt;
&lt;/@shiro.guest&gt;                    </pre>
                    <h2>2.principal标签</h2>
                    principal标签，取值取的是你登录的时候。在Realm实现类中的如下代码：
                    <pre>....
return new SimpleAuthenticationInfo(user,user.getPswd(), getName());                    </pre>
                    在new SimpleAuthenticationInfo(第一个参数,....)的第一个参数放的如果是一个username，那么就可以直接用。
                    <pre>&lt;@shiro.principal/&gt;</pre>
                    如果第一个参数放的是对象，要取username字段。
                    <pre>&lt;@shiro.principal property="username"/&gt;</pre>
                    <h2>3.hasRole标签（判断是否拥有这个角色）</h2>
                    <pre>&lt;@shiro.hasRole name="admin"&gt;
	拥有角色admin
&lt;/@shiro.hasRole&gt;                    </pre>
                    <h2>4.hasAnyRoles标签（判断是否拥有这些角色的其中一个）</h2>
                    <pre>&lt;@shiro.hasAnyRoles name="admin,user,member"&gt;
    用户[&lt;@shiro.principal/&gt;]拥有角色admin或user或member
&lt;/@shiro.hasAnyRoles&gt;                    </pre>
                    <h2>5.lacksRole标签（判断是否不拥有这个角色）</h2>
                    <pre>&lt;@shiro.lacksRole name="admin"&gt;
    用户[&lt;@shiro.principal/&gt;]不拥有admin角色
&lt;/@shiro.lacksRole&gt;                    </pre>
                    <h2>6.hasPermission标签（判断是否有拥有这个权限）</h2>
                    <pre>&lt;@shiro.hasPermission name="user:add"&gt;
	用户[&lt;@shiro.principal/&gt;]拥有user:add权限
&lt;/@shiro.hasPermission&gt;                    </pre>
                    <h2>7.lacksPermission标签（判断是否没有这个权限）</h2>
                    <pre>&lt;@shiro.lacksPermission name="user:add"&gt;
	用户[&lt;@shiro.principal/&gt;]不拥有user:add权限
&lt;/@shiro.lacksPermission&gt;                    </pre>
                </div>
            </div>
        </div>
    </div>
</div>
<@footer></@footer>