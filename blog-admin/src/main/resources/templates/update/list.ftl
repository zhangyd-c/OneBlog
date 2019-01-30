<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">更新记录管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="updateLog:add">
                            <button id="btn_add" type="button" class="btn btn-default" title="新增更新记录">
                                <i class="fa fa-plus"></i> 新增更新记录
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="updateLog:batchDelete">
                            <button id="btn_delete_ids" type="button" class="btn btn-default" title="删除选中">
                                <i class="fa fa-trash-o"></i> 批量删除
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
<@addOrUpdateMOdal defaultTitle="添加更新记录">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="version">当前版本 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="version" id="version" required="required" placeholder="请输入当前版本"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">更新内容 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <textarea class="form-control col-md-7 col-xs-12" id="description" name="description" required="required" placeholder="请输入更新内容"></textarea>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="recordeTime">维护日期 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <div class='input-group date' id='myDatepicker'>
                <input type='text' class="form-control" required="required" readonly="readonly" id="recordeTime" name="recordeTime" placeholder="请输入维护日期"/>
                <span class="input-group-addon">
                                   <span class="glyphicon glyphicon-calendar"></span>
                                </span>
            </div>
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
                '<@shiro.hasPermission name="updateLog:edit"><a class="btn btn-xs btn-primary btn-update" data-id="' + trId + '"><i class="fa fa-edit"></i>编辑</a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="updateLog:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o"></i>删除</a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            $('#myDatepicker').datetimepicker({
                format: 'YYYY-MM-DD HH:mm:ss',
                ignoreReadonly: true,
                allowInputToggle: true
            });

            var options = {
                modalName: "更新记录",
                url: "/update/list",
                getInfoUrl: "/update/get/{id}",
                updateUrl: "/update/edit",
                removeUrl: "/update/remove",
                createUrl: "/update/add",
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'id',
                        title: 'ID',
                        width: '40px',
                        editable: false
                    }, {
                        field: 'version',
                        title: '版本',
                        width: '130px',
                        editable: false
                    }, {
                        field: 'description',
                        title: '更新内容',
                        width: '200px',
                        editable: false
                    }, {
                        field: 'recordeTime',
                        title: '更新时间',
                        width: '100px',
                        editable: false,
                        formatter: function (code) {
                            return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                        }
                    }, {
                        field: 'operate',
                        title: '操作',
                        width: '130px',
                        formatter: operateFormatter //自定义方法，添加操作按钮
                    }
                ]
            };
            //1.初始化Table
            $.tableUtil.init(options);
            //2.初始化Button的点击事件
            $.buttonUtil.init(options);
        });
    </script>
</@footer>