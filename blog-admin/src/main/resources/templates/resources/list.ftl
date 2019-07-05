<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">资源管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <table id="tree-table-box">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<@addOrUpdateMOdal defaultTitle="添加资源链接">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">资源名称 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="name" id="name" required="required" placeholder="请输入资源名称"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">资源类型 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <select name="type" id="type" required="required" class="form-control col-md-7 col-xs-12">
                <option value="">请选择</option>
                <option value="menu">菜单</option>
                <option value="button">按钮</option>
            </select>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">父级资源 </label>
        <div class="col-md-6 col-sm-6 col-xs-6">
            <select id="parentId" name="parentId" class="form-control col-md-5 col-xs-5">
                <option value="">请选择</option>
                <@zhydTag method="availableMenus">
                    <#if availableMenus?? && availableMenus?size gt 0>
                        <#list availableMenus as item>
                            <option value="${item.id?c}">${item.name}</option>
                            <#if item.nodes?? && item.nodes?size gt 0>
                                <#list item.nodes as node>
                                    <option value="${node.id?c}">&nbsp;&nbsp;|-${node.name}</option>
                                </#list>
                            </#if>
                        </#list>
                    <#else>
                        <option value="">无</option>
                    </#if>
                </@zhydTag>
            </select>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="url">资源链接 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="url" id="url" placeholder="请输入资源链接"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="permission">资源权限 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="permission" id="permission" placeholder="请输入资源权限"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="sort">资源排序 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="sort" id="sort" placeholder="请输入资源排序"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="icon">资源图标 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="icon" id="icon" placeholder="请输入资源图标"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mobile">是否可用 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <ul class="list-unstyled list-inline">
                <li><input type="radio" class="flat" checked="checked" name="available" value="1"> 可用</li>
                <li><input type="radio" class="flat" name="available" value="0"> 禁用</li>
            </ul>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mobile">外部链接 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <ul class="list-unstyled list-inline">
                <li><input type="radio" class="flat" checked name="external" value="0"> 否</li>
                <li><input type="radio" class="flat" name="external" value="1"> 是</li>
            </ul>
        </div>
    </div>
</@addOrUpdateMOdal>
<@footer>
    <script type="text/javascript" src="/assets/js/zhyd.treetable.js"></script>
    <script>
        $(function () {
            $.table.init({
                modalName: "资源",
                columns: [{
                    field: 'selectItem',
                    checkbox: true
                }, {
                    field: '-',
                    title: '层级',
                    width: "60px",
                    align: "center"
                }, {
                    field: 'name',
                    title: '资源名',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'id',
                    title: '资源ID',
                    width: '60px',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'type',
                    title: '资源类型',
                    formatter: function (code) {
                        return code == 'menu' ? '菜单' : '按钮';
                    }
                }, {
                    field: 'url',
                    title: '资源地址',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'permission',
                    title: '资源权限',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'parent.name',
                    title: '父级资源',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'icon',
                    title: '资源图标',
                    width: '120px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? '<i class="' + code + ' fa-fw"></i>'+ code : '-';
                    }
                }, {
                    field: 'sort',
                    title: '排序',
                    width: '70px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'external',
                    title: '外部资源',
                    width: '100px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? '<span class="label label-success">是</span>' : '<span class="label label-danger">否</span>';
                    }
                }, {
                    field: 'available',
                    title: '可用',
                    width: '80px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? '<span class="label label-success">可用</span>' : '<span class="label label-danger">不可用</span>';
                    }
                }]
            }, {
                listUrl: "/resources/list",
                getInfoUrl: "/resources/get/{id}",
                updateUrl: "/resources/edit",
                removeUrl: "/resources/remove",
                createUrl: "/resources/add",
                saveRolesUrl: "/resources/saveRoleResources"
            })
        });
    </script>
</@footer>