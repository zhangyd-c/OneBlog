<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">网站通知管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="notice:add">
                            <button id="btn_add" type="button" class="btn btn-default" title="添加公告">
                                <i class="fa fa-plus"></i> 添加公告
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="notice:batchDelete">
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
<@addOrUpdateMOdal defaultTitle="发布通知">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">标题 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="title" id="title" required="required"
                   placeholder="请输入标题"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="content">内容 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <textarea class="form-control col-md-7 col-xs-12" id="content" name="content" required="required"></textarea>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="status">状态 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12 fixed-radio-checkbox">
            <ul class="list-unstyled list-inline">
                <li>
                    <div class="radio">
                        <label> <input type="radio" class="square" name="status" required="required" value="RELEASE"> 已发布 </label>
                    </div>
                </li>
                <li>
                    <div class="radio">
                        <label> <input type="radio" class="square" name="status" required="required" value="NOT_RELEASE"> 未发布 </label>
                    </div>
                </li>
            </ul>
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
            var status = row.status;
            var html = '';
            if (status && status == 'NOT_RELEASE') {
                html = '<@shiro.hasPermission name="notice:release"><a class="btn btn-xs btn-success btn-release" data-id="' + trId + '"><i class="fa fa-rocket fa-fw"></i>发布</a></@shiro.hasPermission>';
            } else {
                html = '<@shiro.hasPermission name="notice:withdraw"><a class="btn btn-xs btn-warning btn-withdraw" data-id="' + trId + '"><i class="fa fa-rocket fa-rotate-180 fa-fw"></i>撤回</a></@shiro.hasPermission>';
            }
            var operateBtn = [
                html,
                '<@shiro.hasPermission name="notice:edit"><a class="btn btn-xs btn-primary btn-update" data-id="' + trId + '"><i class="fa fa-edit"></i>编辑</a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="notice:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o"></i>删除</a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                modalName: "网站通知",
                url: "/notice/list",
                getInfoUrl: "/notice/get/{id}",
                updateUrl: "/notice/edit",
                removeUrl: "/notice/remove",
                createUrl: "/notice/add",
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'id',
                        title: 'ID',
                        width: '60px',
                        editable: false
                    }, {
                        field: 'title',
                        title: '标题',
                        width: '150px',
                        editable: false,
                        formatter: function (code) {
                            return '<a href="' + code + '" target="_blank" rel="nofollow ">' + code + '</a>';
                        }
                    }, {
                        field: 'content',
                        title: '内容',
                        width: '300px',
                        editable: false,
                        formatter: function (code) {
                            return '<a href="' + code + '" target="_blank" rel="nofollow ">' + code + '</a>';
                        }
                    }, {
                        field: 'status',
                        title: '状态',
                        editable: false,
                        width: '60px',
                        formatter: function (code, row, index) {
                            return (code && code == 'RELEASE') ? "已发布" : "未发布";
                        }
                    }, {
                        field: 'operate',
                        title: '操作',
                        width: '150px',
                        formatter: operateFormatter //自定义方法，添加操作按钮
                    }
                ]
            };
            //1.初始化Table
            $.tableUtil.init(options);
            //2.初始化Button的点击事件
            $.buttonUtil.init(options);

            // 发布
            $('#tablelist').on('click', '.btn-release', function () {
                var $this = $(this);
                var id = $this.data("id");
                $.alert.confirm("确定发布该条通知？发布后将对用户可见！", function () {
                    $.ajax({
                        type: "post",
                        url: "/notice/release/" + id,
                        success: function (json) {
                            $.alert.ajaxSuccess(json);
                            $.tableUtil.refresh();
                        },
                        error: $.alert.ajaxError
                    });
                }, function () {

                }, 5000);
            });
            // 撤回
            $('#tablelist').on('click', '.btn-withdraw', function () {
                var $this = $(this);
                var id = $this.data("id");
                $.alert.confirm("确定撤回该条通知？撤回后将对用户不可见！", function () {
                    $.ajax({
                        type: "post",
                        url: "/notice/withdraw/" + id,
                        success: function (json) {
                            $.alert.ajaxSuccess(json);
                            $.tableUtil.refresh();
                        },
                        error: $.alert.ajaxError
                    });
                }, function () {

                }, 5000);
            });
        });
    </script>
</@footer>