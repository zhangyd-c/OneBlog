<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">模板管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="template:add">
                            <button id="btn_add" type="button" class="btn btn-info" title="新增模板">
                                <i class="fa fa-plus fa-fw"></i>
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="template:batchDelete">
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
<@addOrUpdateMOdal defaultTitle="添加模板">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="refKey">KEY <span class="required">*</span></label>
        <div class="col-md-9 col-sm-9 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="refKey" id="refKey" required="required" placeholder="KEY"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="refValue">VALUE <span class="required">*</span></label>
        <div class="col-md-9 col-sm-9 col-xs-12">
            <textarea class="form-control col-md-7 col-xs-12" rows="20" id="refValue" name="refValue" required="required" placeholder="VALUE"></textarea>
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
                '<@shiro.hasPermission name="template:edit"><a class="btn btn-sm btn-success btn-update" data-id="' + trId + '"title="编辑"><i class="fa fa-edit fa-fw"></i></a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="template:delete"><a class="btn btn-sm btn-danger btn-remove" data-id="' + trId + '"title="删除"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                modalName: "模板",
                url: "/template/list",
                getInfoUrl: "/template/get/{id}",
                updateUrl: "/template/edit",
                removeUrl: "/template/remove",
                createUrl: "/template/add",
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'id',
                        title: 'ID',
                        width: '40px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'refKey',
                        title: 'KEY',
                        width: '300px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'operate',
                        title: '操作',
                        align: "center",
                        width: '80px',
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