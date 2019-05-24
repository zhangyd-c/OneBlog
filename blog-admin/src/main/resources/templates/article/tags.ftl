<#include "/include/macros.ftl">
<@header></@header>
<div class="">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <@breadcrumb>
                <ol class="breadcrumb">
                    <li><a href="/">首页</a></li>
                    <li class="active">文章标签管理</li>
                </ol>
            </@breadcrumb>
            <div class="x_panel">
                <div class="x_content">
                    <div class="<#--table-responsive-->">
                        <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="tag:add">
                        <button id="btn_add" type="button" class="btn btn-info" title="新增标签">
                            <i class="fa fa-plus fa-fw"></i>
                        </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="tag:batchDelete">
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
</div>
<@addOrUpdateMOdal defaultTitle="添加标签">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">名称 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="name" id="name" required="required" placeholder="请输入标签名称"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">标签描述 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <textarea class="form-control col-md-7 col-xs-12" id="description" name="description" placeholder="请输入标签描述" maxlength="100"></textarea>
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
            var trId = row.id;
            var operateBtn = [
                '<@shiro.hasPermission name="tag:edit"><a class="btn btn-sm btn-success btn-update" data-id="' + trId + '"title="编辑"><i class="fa fa-edit fa-fw"></i></a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="tag:delete"><a class="btn btn-sm btn-danger btn-remove" data-id="' + trId + '"title="删除"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                modalName: "标签",
                url: "/tag/list",
                getInfoUrl: "/tag/get/{id}",
                updateUrl: "/tag/edit",
                removeUrl: "/tag/remove",
                createUrl: "/tag/add",
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'id',
                        title: 'ID',
                        width: '60px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'name',
                        title: '名称',
                        width: '150px',
                        formatter: function (code, row, index) {
                            var id = row.id;
                            return '<a href="' + appConfig.wwwPath + '/tag/' + id + '" target="_blank">' + row.name + '</a>';
                        }
                    }, {
                        field: 'description',
                        title: '描述',
                        width: '450px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'operate',
                        title: '操作',
                        align: "center",
                        width: '100px',
                        formatter: operateFormatter //自定义方法，添加操作按钮
                    }
                ]
            };
            // 初始化table组件
            var table = new Table(options);
            table.init();
        });
    </script>
</@footer>