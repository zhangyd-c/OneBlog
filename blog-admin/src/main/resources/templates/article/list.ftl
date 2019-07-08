<#include "/include/macros.ftl">
<@header></@header>
<div class="">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <@breadcrumb>
                <ol class="breadcrumb">
                    <li><a href="/">首页</a></li>
                    <li class="active">文章管理</li>
                </ol>
            </@breadcrumb>
            <div class="x_panel">
                <div class="x_content">
                    <div class="<#--table-responsive-->">
                        <div class="btn-group hidden-xs" id="toolbar">
                            <@shiro.hasPermission name="article:publish">
                                <a class="btn btn-success" title="发表文章" href="${(config.articleEditor! == 'md')?string('/article/publishMd', '/article/publish')}"> <i class="fa fa-pencil fa-fw"></i>  </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="article:batchDelete">
                                <button id="btn_delete_ids" type="button" class="btn btn-danger" title="删除选中">
                                    <i class="fa fa-trash-o fa-fw"></i>
                                </button>
                            </@shiro.hasPermission>
                            <#-- 由草稿状态批量修改为已发布状态 -->
                            <@shiro.hasPermission name="article:publish">
                                <button id="btn_update_status" type="button" class="btn btn-default" title="批量发布">
                                    <i class="fa fa-bullhorn fa-fw"></i>
                                </button>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="article:batchPush">
                                <button id="btn_push_ids" type="button" class="btn btn-info" title="批量推送到百度">
                                    <i class="fa fa-send-o fa-fw"></i>
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
        // var recommended = row.recommended ? '<i class="fa fa-thumbs-o-down"></i>取消推荐' : '<i class="fa fa-thumbs-o-up"></i>推荐';
        // var top = row.top ? '<i class="fa fa-arrow-circle-down"></i>取消置顶' : '<i class="fa fa-arrow-circle-up"></i>置顶';
        var operateBtn = [
            '<@shiro.hasPermission name="article:push"><a class="btn btn-sm btn-info btn-push" title="推送" data-id="' + trId + '"><i class="fa fa-send-o"></i></a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:edit"><a class="btn btn-sm btn-success" href="/article/update/' + trId + '"><i class="fa fa-edit fa-fw"></i></a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:delete"><a class="btn btn-sm btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>',
            <#--'<@shiro.hasPermission name="article:top"><a class="btn btn-sm btn-success btn-top" data-id="' + trId + '">' + top + '</a></@shiro.hasPermission>',-->
            <#--'<@shiro.hasPermission name="article:recommend"><a class="btn btn-sm btn-success btn-recommend" data-id="' + trId + '">' + recommended + '</a></@shiro.hasPermission>'-->
        ];
        return operateBtn.join('');
    }

    $(function () {
        var options = {
            modalName: "文章",
            url: "/article/list",
            getInfoUrl: "/article/get/{id}",
            removeUrl: "/article/remove",
            columns: [
                {
                    checkbox: true
                }, {
                    field: 'title',
                    title: '标题',
                    width: '270px',
                    formatter: function (code, row, index) {
                        var title = code;
                        if(!title) {
                            return '-';
                        }
                        title = title.length > 30 ? (title.substr(0, 30) + '...') : title;
                        var id = row.id;
                        var status = row.status ? '<span class="label label-success" style="margin-right: 5px;">已发布</span>' : '<span class="label label-danger" style="margin-right: 5px;">草稿</span>';
                        return status + '<a href="' + appConfig.wwwPath + '/article/' + id + '" target="_blank" title="' + code + '">' + title + '</a>';
                    }
                }, {
                    field: 'coverImage',
                    title: '封面图',
                    width: '50px',
                    align: 'center',
                    editable: false,
                    formatter: function (code, row, index) {
                        return code ? '<a href="' + code + '" class="showImage" title="' + row.title + '" rel="external nofollow"><img src="' + code + '" alt="' + row.title + '" class="img-rounded" style="width: 30px;height: auto;"></a>' : '-';
                    }
                }, {
                    field: 'comment',
                    title: '评论',
                    width: '50px',
                    align: 'center',
                    formatter: function (code, row, index) {
                        var checked = code ? 'checked' : '';
                        return '<input type="checkbox" name="comment" class="js-switch btn-comment"  data-id="' + row.id + '" data-type="comment" ' + checked + '>';
                    }
                }, {
                    field: 'recommended',
                    title: '推荐 <i class="fa fa-question-circle-o" title="推荐的文章会在首页滚动显示"></i>',
                    width: '50px',
                    align: 'center',
                    formatter: function (code, row, index) {
                        var checked = code ? 'checked' : '';
                        return '<input type="checkbox" name="recommended" class="js-switch btn-recommended" data-id="' + row.id + '" data-type="recommend" ' + checked + '>';
                    }
                }, {
                    field: 'top',
                    title: '置顶',
                    width: '50px',
                    align: 'center',
                    formatter: function (code, row, index) {
                        var checked = code ? 'checked' : '';
                        return '<input type="checkbox" name="top" class="js-switch btn-top" data-id="' + row.id + '" data-type="top" ' + checked + '>';
                    }
                }, {
                    field: 'lookCount',
                    title: '浏览',
                    width: '50px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'commentCount',
                    title: '评论',
                    width: '50px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'loveCount',
                    title: '喜欢',
                    width: '50px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'createTime',
                    title: '发布时间',
                    width: '130px',
                    align: 'center',
                    formatter: function (code) {
                        return new Date(code).format("yyyy-MM-dd hh:mm:ss")
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

        table.bindClickEvent('.switchery', function () {
            var $input = $(this).prev();

            var id = $input.data("id");
            var type = $input.data("type");

            $.ajax({
                type: "post",
                url: "/article/update/" + type,
                traditional: true,
                data: {'id': id},
                success: function (json) {
                    if (json.status !== 200) {
                        $.alert.error(json.message);
                    }
                },
                error: $.alert.ajaxError
            });
        });

        /**
         * 推送到百度
         */
        table.bindClickEvent('.btn-push', function () {
            var $this = $(this);
            var userId = $this.attr("data-id");
            push(userId);
        });

        /**
         * 批量推送到百度
         */
        $("#btn_push_ids").click(function () {
            var selectedId = table.getSelectedIds();
            if (!selectedId || selectedId == '[]' || selectedId.length == 0) {
                $.alert.error("请至少选择一条记录");
                return;
            }
            push(selectedId);
        });

        /**
         * 批量修改状态
         */
        $("#btn_update_status").click(function () {
            var selectedId = table.getSelectedIds();
            if (!selectedId || selectedId == '[]' || selectedId.length == 0) {
                $.alert.error("请至少选择一条记录");
                return;
            }
            $.alert.confirm("确定批量发布？发布完成后用户可见", function () {
                $.ajax({
                    type: "post",
                    url: "/article/batchPublish",
                    traditional: true,
                    data: {'ids': selectedId},
                    success: function (json) {
                        $.alert.ajaxSuccess(json);
                        table.refresh();
                    },
                    error: $.alert.ajaxError
                });
            }, function () {

            }, 5000);
        });

        function push(ids) {
            $.alert.confirm("确定推送到百度站长平台？", function () {
                $.ajax({
                    type: "post",
                    url: "/article/pushToBaidu/urls",
                    traditional: true,
                    data: {'ids': ids},
                    success: function (json) {
                        $.alert.ajaxSuccess(json);
                        if (json.status == 200) {
                            var dataJson = JSON.parse(json.data);
                            /**
                             * success            int        成功推送的url条数
                             * remain            int        当天剩余的可推送url条数
                             * not_same_site    array    由于不是本站url而未处理的url列表
                             * not_valid        array    不合法的url列表
                             */
                            var successNum = dataJson.success;
                            var remain = dataJson.remain;
                            var notSameSite = dataJson.not_same_site;
                            var notValid = dataJson.not_valid;
                            var message = '成功推送' + successNum + '条url\n';
                            if (notValid) {
                                message += '不合法的url：' + notValid + '\n';
                            }
                            message += '今日剩余' + remain + '条可推送的url。';
                            $.alert.info(message, null, 5000);
                        }
                    },
                    error: $.alert.ajaxError
                });
            }, function () {

            }, 5000);
        }
    });
</script>
</@footer>