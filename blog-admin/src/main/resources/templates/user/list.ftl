<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">用户管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="user:add">
                        <button id="btn_add" type="button" class="btn btn-info" title="新增用户">
                            <i class="fa fa-plus fa-fw"></i>
                        </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="user:batchDelete">
                            <button id="btn_delete_ids" type="button" class="btn btn-danger" title="删除选中">
                                <i class="fa fa-trash-o fa-fw"></i>
                            </button>
                        </@shiro.hasPermission>
                    </div>
                    <table id="tablelist">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!--弹框-->
<div class="modal fade bs-example-modal-sm" id="selectRole" tabindex="-1" role="dialog" aria-labelledby="selectRoleLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="selectRoleLabel">分配角色</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="zTreeDemoBackground left">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
            </div>
        </div>
    </div>
</div>
<@addOrUpdateMOdal defaultTitle="添加用户">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">用户名 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="username" id="username" required="required" placeholder="请输入用户名"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">密码 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="password" class="form-control col-md-7 col-xs-12" id="password" name="password" placeholder="请输入密码 6位以上"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nickname">昵称 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="nickname" id="nickname" placeholder="请输入昵称"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mobile">手机 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="tel" class="form-control col-md-7 col-xs-12" name="mobile" id="mobile" data-validate-length-range="6,20" placeholder="请输入手机号"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">邮箱 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="email" class="form-control col-md-7 col-xs-12" name="email" id="email" placeholder="请输入邮箱"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="qq">QQ </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="number" class="form-control col-md-7 col-xs-12" name="qq" id="qq" placeholder="请输入QQ"/>
        </div>
    </div>
</@addOrUpdateMOdal>
<@footer>
    <script>
        /**
         * 操作按钮
         * @param code
         * @param row
         * @param index
         * @returns {string}
         */
        function operateFormatter(code, row, index) {
            var currentUserId = '${user.id}';
            var trUserId = row.id;
            var operateBtn = [
                '<@shiro.hasPermission name="user:edit"><a class="btn btn-sm btn-success btn-update" data-id="' + trUserId + '"title="编辑"><i class="fa fa-edit fa-fw"></i></a></@shiro.hasPermission>',
            ];
            if (currentUserId != trUserId) {
                operateBtn.push('<@shiro.hasPermission name="user:delete"><a class="btn btn-sm btn-danger btn-remove" data-id="' + trUserId + '"title="删除"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>');
                operateBtn.push('<@shiro.hasPermission name="user:allotRole"><a class="btn btn-sm btn-info btn-allot" data-id="' + trUserId + '" title="分配角色"><i class="fa fa-circle-thin fa-fw"></i></a></@shiro.hasPermission>')
            }
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                url: "/user/list",
                getInfoUrl: "/user/get/{id}",
                updateUrl: "/user/edit",
                removeUrl: "/user/remove",
                createUrl: "/user/add",
                saveRolesUrl: "/user/saveUserRoles",
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'id',
                        title: 'ID',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'username',
                        title: '用户名',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'nickname',
                        title: '昵称',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'email',
                        title: '邮箱',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'qq',
                        title: 'qq',
                        align: 'center',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'userType',
                        title: '用户类型',
                        align: 'center',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'statusEnum',
                        title: '状态',
                        align: 'center',
                        formatter: function (code) {
                            return (code && code == 'NORMAL') ? '<span class="label label-success">正常</span>' : '<span class="label label-danger">禁用</span>';
                        }
                    }, {
                        field: 'lastLoginTime',
                        title: '最后登录时间',
                        align: 'center',
                        formatter: function (code) {
                            return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                        }
                    }, {
                        field: 'loginCount',
                        title: '登录次数',
                        align: 'center',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'operate',
                        title: '操作',
                        align: "center",
                        width: '150px',
                        formatter: operateFormatter //自定义方法，添加操作按钮
                    }
                ],
                modalName: "用户"
            };
            // 初始化table组件
            var table = new Table(options);
            table.init();

            /* 分配用户角色 */
            table.bindClickEvent('.btn-allot', function () {
                console.log("分配权限");
                var $this = $(this);
                var userId = $this.attr("data-id");
                $.ajax({
                    async: false,
                    type: "POST",
                    data: {uid: userId},
                    url: '/roles/rolesWithSelected',
                    dataType: 'json',
                    success: function (json) {
                        var data = json.data;
                        console.log(data);
                        var setting = {
                            check: {
                                enable: true,
                                chkboxType: {"Y": "ps", "N": "ps"},
                                chkStyle: "radio"
                            },
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            },
                            callback: {
                                onCheck: function (event, treeId, treeNode) {
                                    console.log(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
                                    var treeObj = $.fn.zTree.getZTreeObj(treeId);
                                    var nodes = treeObj.getCheckedNodes(true);
                                    var ids = new Array();
                                    for (var i = 0; i < nodes.length; i++) {
                                        //获取选中节点的值
                                        ids.push(nodes[i].id);
                                    }
                                    console.log(ids);
                                    console.log(userId);
                                    $.post(options.saveRolesUrl, {"userId": userId, "roleIds": ids.join(",")}, function (obj) {
                                    }, 'json');
                                }
                            }
                        };
                        var tree = $.fn.zTree.init($("#treeDemo"), setting, data);
                        tree.expandAll(true);//全部展开

                        $('#selectRole').modal('show');
                    }
                });
            });
        });
    </script>
</@footer>