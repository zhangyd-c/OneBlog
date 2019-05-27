<#include "/include/macros.ftl">
<@header></@header>
<div class="">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <@breadcrumb>
                <ol class="breadcrumb">
                    <li><a href="/">首页</a></li>
                    <li class="active">评论管理</li>
                </ol>
            </@breadcrumb>
            <div class="x_panel">
                <div class="x_content">
                    <div class="<#--table-responsive-->">
                        <div class="btn-group hidden-xs" id="toolbar">
                            <@shiro.hasPermission name="comment:batchDelete">
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
                    <input type="hidden" name="sid" id="sid">
                    <input type="hidden" name="pid" id="pid">
                    <div class="item form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-2" for="description">评论 </label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <textarea class="form-control col-md-12 col-xs-12" rows="10" cols="20" id="content" name="content" placeholder="请输入评论"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
                <button type="button" class="btn btn-success replyBtn"><i class="fa fa-mail-reply"> 回复</i></button>
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
                    <input type="hidden" name="id" id="audit_id">
                    <input type="hidden" name="sid" id="audit_sid">
                    <div class="item form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-2" for="description">状态 </label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <select name="status" id="status" class="form-control" required="required">
                                <option value="">请选择</option>
                                <option value="APPROVED">审核通过</option>
                                <option value="REJECT">审核失败</option>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group hide" id="status-remark">
                        <label class="control-label col-md-2 col-sm-2 col-xs-2" for="description">备注 </label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <textarea class="form-control col-md-7 col-xs-12" id="remark" name="remark" placeholder="请输入审核备注（审核失败/删除的原因）"></textarea>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-2" for="description">回复该评论 </label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <textarea class="form-control col-md-7 col-xs-12" id="contentText" name="contentText" rows="10" cols="20" placeholder="请输入评论"></textarea>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-2" for="description">发送邮件 </label>
                        <div class="col-md-9 col-sm-9 col-xs-9">
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
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
                <button type="button" class="btn btn-success auditBtn"><i class="fa fa-hand-o-up"> 提交审核</i></button>
            </div>
        </div>
    </div>
</div>
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
            var id = row.id;
            var sid = row.sid;
            var operateBtn = [
                '<@shiro.hasPermission name="comment:reply"><a class="btn btn-primary btn-reply" data-id="' + id + '" data-sid="' + sid + '" title="回复"><i class="fa fa-reply"></i></a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="comment:audit"><a class="btn btn-warning btn-audit" data-id="' + id + '" data-sid="' + sid + '" title="审核"><i class="fa fa-shield"></i></a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="comment:delete"><a class="btn btn-danger btn-remove" data-id="' + id + '" data-sid="' + sid + '" title="删除"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>'
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
                        width: '200px',
                        formatter: function (code, row, index) {
                            return '<ul class="list-unstyled">' +
                                    '<li>' +
                                    '<a href="' + row.url + '" target="_blank"><img src="' + filterXSS(row.avatar) + '" onerror="this.src=\'/assets/images/user.png\'" style="width: 20px;border-radius: 50%;position: relative;top: -2px;"/> ' + filterXSS(row.nickname) + '</a>' +
                                    '<a href="javascript:void(0);" onclick="window.open(\'tencent://message/?uin=' + row.qq + '&amp;Menu=yes\')" rel="external nofollow" target="_blank"><i class="fa fa-qq fa-fw"></i></a>' +
                                    '<a href="mailto:' + filterXSS(row.email) + '" rel="external nofollow" target="_blank"><i class="fa fa-envelope fa-fw"></i></a>' +
                                    '</li>' +
                                    '<li><i class="fa fa-address-book-o fa-fw"></i> <span style="color: #a9a9a9;">' + row.ip + ' | ' + row.address + '</span></li>' +
                                    '<li><i class="fa fa-windows fa-fw"></i> <span style="color: #a9a9a9;">' + row.os + ' | ' + row.browser + '</span></li>' +
                                    '<li><i class="fa fa-clock-o fa-fw"></i> <span style="color: #a9a9a9;">' + row.createTimeString + '</span></li></ul>';
                        }
                    }, {
                        field: 'content',
                        title: '内容',
                        width: '380px',
                        formatter: function (code, row, index) {
                            var content = filterXSS(row.content);
                            var source = '<a href="' + appConfig.wwwPath + row.sourceUrl + '" target="_blank">' + row.articleTitle + '</a>';
                            var $parent = row.parent;
                            var parent = $parent ? '<div style="background-color: #f1f1f1;padding: 5px;margin: 5px;border-radius: 4px;"><div style="padding-left: 10px;"><i class="fa fa-quote-left fa-fw"></i><strong>原评：</strong>' + filterXSS($parent.content) + '</div></div>' : '';
                            return '<div style="border-bottom: 1px solid #dcddde;margin-bottom: 10px;">评论自：'+source+'</div>' +
                                    '<div class="col-md-12">' + content + parent + '</div>';
                        }
                    }, {
                        field: 'support',
                        title: '赞/踩',
                        width: '40px',
                        align: "center",
                        formatter: function (code, row, index) {
                            return row.support + "/" + row.oppose;
                        }
                    }, {
                        field: 'status',
                        title: '状态',
                        width: '40px',
                        align: "center",
                        formatter: function (code, row, index) {
                            var html = '';
                            if(code == 'VERIFYING'){
                                html = '<span class="label label-danger">' + row.statusDesc + '</span>';
                            } else if (code == 'REJECT') {
                                html = '<span class="label label-warning">' + row.statusDesc + '</span>';
                            } else if (code == 'DELETED') {
                                html = '<span class="label label-danger">' + row.statusDesc + '</span>';
                            } else {
                                html = '<span class="label label-success">' + row.statusDesc + '</span>';
                            }
                            return html;
                        }
                    }, {
                        field: 'operate',
                        title: '操作',
                        align: "center",
                        width: '100px',
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
            // 初始化table组件
            var table = new Table(options);
            table.init();

            var $tablelist = $(table.options.tableBox);
            /**
             * 回复
             */
            $tablelist.on('click', '.btn-reply', function () {
                var $this = $(this);
                var $replyForm = $("#replyForm");
                $replyForm.find("input,select,textarea").each(function () {
                    var $this = $(this);
                    new Table().clearText($this, this.type);
                });
                var pid = $this.attr("data-id");
                var sid = $this.attr("data-sid");
                $("#sid").val(sid);
                $("#pid").val(pid);
                $("#replyModal").modal('show');
                var $replyBtn = $(".replyBtn");
                $replyBtn.unbind("click");
                $replyBtn.click(function () {
                    if (validator.checkAll($replyForm)) {
                        $.ajax({
                            type: "post",
                            url: "/comment/reply",
                            data: $replyForm.serialize(),
                            success: function (json) {
                                $.alert.ajaxSuccess(json);
                                $("#replyModal").modal('hide');
                                table.refresh();
                            },
                            error: $.alert.ajaxError
                        });
                    }
                })
            });
            /**
             * audit
             */
            $tablelist.on('click', '.btn-audit', function () {
                var $this = $(this);
                var userId = $this.attr("data-id");
                var $auditForm = $("#auditForm");
                $auditForm.find("input,select,textarea").each(function () {
                    var $this = $(this);
                    new Table().clearText($this, this.type);
                });
                $("#audit_id").val(userId);
                $("#audit_sid").val($this.attr("data-sid"));

                $("#auditModal").modal('show');
                var $auditBtn = $(".auditBtn");
                $auditBtn.unbind("click");
                $auditBtn.click(function () {
                    if (validator.checkAll($auditForm)) {
                        $.ajax({
                            type: "post",
                            url: "/comment/audit",
                            data: $("#auditForm").serialize(),
                            success: function (json) {
                                $.alert.ajaxSuccess(json);
                                $("#auditModal").modal('hide');
                                table.refresh();
                                zhyd.initCommentNotify();
                            },
                            error: $.alert.ajaxError
                        });
                    }
                })
            });

            $("#status").change(function () {
                var thisVal = $(this).val();
                if(thisVal === "REJECT" || thisVal === "DELETED") {
                    $("#status-remark").removeClass("hide");
                } else {
                    $("#status-remark").addClass("hide");
                    $("#remark").val("");
                }
            });
        });
    </script>
</@footer>