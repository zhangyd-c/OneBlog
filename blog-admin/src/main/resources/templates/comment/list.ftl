<#include "/layout/header.ftl"/>
<div class="">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">评论管理</li>
            </ol>
            <div class="x_panel">
                <div class="x_content">
                    <div class="<#--table-responsive-->">
                        <div class="btn-group hidden-xs" id="toolbar">
                            <button id="btn_delete_ids" type="button" class="btn btn-default" title="删除选中">
                                <i class="fa fa-trash-o"></i> 批量删除
                            </button>
                        </div>
                        <table id="tablelist">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/layout/footer.ftl"/>
<!--添加弹框-->
<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="addroleLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addroleLabel">回复评论</h4>
            </div>
            <div class="modal-body">
                <form id="replyForm" class="form-horizontal form-label-left" novalidate>
                    <input type="hidden" name="sid">
                    <input type="hidden" name="pid">
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">评论 </label>
                        <div class="col-md-7 col-sm-7 col-xs-12">
                            <textarea class="form-control col-md-7 col-xs-12" id="content" name="content" placeholder="请输入评论"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary replyBtn">回复</button>
            </div>
        </div>
    </div>
</div>
<!--/添加弹框-->
<!--添加弹框-->
<div class="modal fade" id="auditModal" tabindex="-1" role="dialog" aria-labelledby="addroleLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addroleLabel">审核评论</h4>
            </div>
            <div class="modal-body">
                <form id="auditForm" class="form-horizontal form-label-left" novalidate>
                    <input type="hidden" name="id">
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">状态 </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="status" id="status" class="form-control">
                                <option value="">请选择</option>
                                <option value="VERIFYING">正在审核</option>
                                <option value="APPROVED">审核通过</option>
                                <option value="REJECT">审核失败</option>
                                <option value="DELETED">已删除</option>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">备注 </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <textarea class="form-control col-md-7 col-xs-12" id="remark" name="remark" placeholder="请输入审核备注"></textarea>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">发送邮件通知 </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" class="square" name="sendEmail"> 勾选发送
                                </label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary auditBtn">回复</button>
            </div>
        </div>
    </div>
</div>
<script>
    /**
     * 操作按钮
     * @param code
     * @param row
     * @param index
     * @returns {string}
     */
    function operateFormatter(code, row, index) {
        var id = row.id;
        var sid = row.sid;
        var operateBtn = [
            '<a class="btn btn-xs btn-primary btn-reply" data-id="' + id + '" data-sid="' + sid + '"><i class="fa fa-edit"></i>回复</a>',
            '<a class="btn btn-xs btn-warning btn-audit" data-id="' + id + '" data-sid="' + sid + '"><i class="fa fa-edit"></i>审核</a>',
            '<a class="btn btn-xs btn-danger btn-remove" data-id="' + id + '" data-sid="' + sid + '"><i class="fa fa-trash-o"></i>删除</a>'
        ];
        return operateBtn.join('');
    }

    $(function () {
        var options = {
            modalName: "评论",
            url: "/comment/list",
            getInfoUrl: "/comment/get/{id}",
            updateUrl: "/comment/edit",
            removeUrl: "/comment/remove",
            createUrl: "/comment/add",
            columns: [
                {
                    checkbox: true
                }, {
                    field: 'avatar',
                    title: '作者',
                    editable: false,
                    width: '280px',
                    formatter: function (code, row, index) {
                        return '<ul class="list-unstyled">' +
                                '<li><a href="' + row.url + '" target="_blank"><img src="' + filterXSS(row.avatar) + '" style="width: 20px;border-radius: 50%;position: relative;top: -2px;"/>' + filterXSS(row.nickname) + '</a></li>' +
                                '<li>IP: <span style="color: #a9a9a9;">'+row.ip+'</span></li>' +
                                '<li>地址: <span style="color: #a9a9a9;">'+row.address+'</span></li>' +
                                '<li>邮箱: <span style="color: #a9a9a9;">'+filterXSS(row.email)+'</span></li>' +
                                '<li>设备: <span style="color: #a9a9a9;">'+row.os + ' ' + row.browser +'</span></li>' +
                                '<li style="color: #a9a9a9;">'+row.createTimeString+'</li></ul>';
                    }
                }, {
                    field: 'content',
                    title: '内容',
                    editable: false,
                    formatter: function (code, row, index) {
                        return filterXSS(row.content);
                    }
                }, {
                    field: 'sid',
                    title: '回复至',
                    editable: false,
                    width: '150px',
                    formatter: function (code, row, index) {
                        var url = appConfig.wwwPath + row.sourceUrl;
                        return '<a href="' + url + '" target="_blank">' + row.articleTitle + '</a>';
                    }
                }, {
                    field: 'support',
                    title: '赞/踩',
                    width: '60px',
                    editable: false,
                    formatter: function (code, row, index) {
                        return row.support + "/" + row.oppose;
                    }
                }, {
                    field: 'statusDesc',
                    title: '状态',
                    width: '70px',
                    editable: false,
                    formatter: function (code, row, index) {
                        return code == '正在审核' ? '<strong style="color: red;">' + code + '</strong>' : code;
                    }
                }, {
                    field: 'operate',
                    title: '操作',
                    width: '200px',
                    formatter: operateFormatter //自定义方法，添加操作按钮
                }
            ],
            rowStyle: function (row, index) {
                //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
                var strclass = "";
                if (row.status == 'REJECT'|| row.status == 'DELETED') {
                    strclass = 'warning';
                } else if (row.status == 'VERIFYING') {
                    strclass = 'danger';
                }
                return { 'classes': strclass }
            }
        };
        //1.初始化Table
        $.tableUtil.init(options);
        //2.初始化Button的点击事件
        $.buttonUtil.init(options);

        /**
         * 回复
         */
        $('#tablelist').on('click', '.btn-reply', function () {
            var $this = $(this);
            $("#replyForm input,#replyForm select,#replyForm textarea").each(function () {
                var $this = $(this);
                clearText($this, this.type);
            });
            var pid = $this.attr("data-id");
            var sid = $this.attr("data-sid");
            $("#replyForm input[name=sid]").val(sid);
            $("#replyForm input[name=pid]").val(pid);
            $("#replyModal").modal('show');
            $(".replyBtn").unbind("click");
            $(".replyBtn").click(function () {
                debugger
                if (validator.checkAll($("#replyForm"))) {
                    $.ajax({
                        type: "post",
                        url: "/comment/reply",
                        data: $("#replyForm").serialize(),
                        success: function (json) {
                            $.tool.ajaxSuccess(json);
                            $("#replyModal").modal('hide');
                            $.tableUtil.refresh();
                        },
                        error: $.tool.ajaxError
                    });
                }
            })
        });
        /**
         * audit
         */
        $('#tablelist').on('click', '.btn-audit', function () {
            var $this = $(this);
            var userId = $this.attr("data-id");
            var $form = $("#auditForm");
            $("#auditForm input,#auditForm select,#auditForm textarea").each(function () {
                var $this = $(this);
                clearText($this, this.type);
            });
            $("#auditForm input[name=id]").val(userId);
            $("#auditModal").modal('show');
            $(".auditBtn").unbind("click");
            $(".auditBtn").click(function () {
                if (validator.checkAll($form)) {
                    $.ajax({
                        type: "post",
                        url: "/comment/audit",
                        data: $("#auditForm").serialize(),
                        success: function (json) {
                            $.tool.ajaxSuccess(json);
                            $("#auditModal").modal('hide');
                            $.tableUtil.refresh();
                        },
                        error: $.tool.ajaxError
                    });
                }
            })
        });
    });
</script>