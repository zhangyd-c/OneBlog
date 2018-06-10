<#include "/include/macros.ftl">
<@header></@header>
<div class="">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">文章管理</li>
            </ol>
            <div class="x_panel">
                <div class="x_content">
                    <div class="<#--table-responsive-->">
                        <div class="btn-group hidden-xs" id="toolbar">
                            <@shiro.hasPermission name="article:publish">
                                <a class="btn btn-default" title="发布文章" href="/article/publishMd"> <i class="fa fa-pencil"></i> 发布文章 </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="article:batchDelete">
                                <button id="btn_delete_ids" type="button" class="btn btn-default" title="删除选中">
                                    <i class="fa fa-trash-o"></i> 批量删除
                                </button>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="article:batchPush">
                                <button id="btn_push_ids" type="button" class="btn btn-info" title="批量推送">
                                    <i class="fa fa-send-o"></i> 批量推送到百度
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
        var operateBtn = [
            '<@shiro.hasPermission name="article:push"><a class="btn btn-xs btn-info btn-push" title="推送" data-id="' + trId + '"><i class="fa fa-send-o"></i>推送</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:top"><a class="btn btn-xs btn-success btn-top" data-id="' + trId + '"><i class="fa fa-arrow-circle-up"></i>置顶</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:recommend"><a class="btn btn-xs btn-success btn-recommend" data-id="' + trId + '"><i class="fa fa-thumbs-o-up"></i>推荐</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:edit"><a class="btn btn-xs btn-primary" href="/article/update/' + trId + '"><i class="fa fa-edit"></i>编辑</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o"></i>删除</a></@shiro.hasPermission>'
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
                    width: '250px',
                    editable: false,
                    formatter: function (code, row, index) {
                        var id = row.id;
                        var original= row.original ? "原创" : "转载";
                        return '<strong>['+original+']</strong> <a href="' + appConfig.wwwPath + '/article/' + id + '" target="_blank">' + code + '</a>';
                    }
                }, {
                    field: 'type',
                    title: '分类',
                    width: '90px',
                    editable: false,
                    formatter: function (code) {
                        var type = code;
                        return '<a href="' + appConfig.wwwPath + '/type/' + type.id + '" target="_blank"> ' + type.name + '</a> ';
                    }
                }, {
                    field: 'tags',
                    title: '标签',
                    width: '140px',
                    editable: false,
                    formatter: function (code) {
                        var tags = code;
                        var tagHtml = '';
                        if (tags) {
                            for (var i = 0, len = tags.length; i < len; i++) {
                                var tag = tags[i];
                                tagHtml += '<a class="btn btn-default btn-xs" href="' + appConfig.wwwPath + '/tag/' + tag.id + '" target="_blank"> ' + tag.name + '</a> ';
                            }
                        }
                        return tagHtml;
                    }
                }, {
                    field: 'recommended',
                    title: '推荐',
                    width: '50px',
                    editable: false,
                    formatter: function (code) {
                        return code ? '<span style="color: #26B99A;font-weight: 700">是</span>' : '否';
                    }
                }, {
                    field: 'top',
                    title: '置顶',
                    width: '50px',
                    editable: false,
                    formatter: function (code) {
                        return code ? '<span style="color: #26B99A;font-weight: 700">是</span>' : '否';
                    }
                }, {
                    field: 'status',
                    title: '状态',
                    width: '50px',
                    editable: false,
                    formatter: function (code) {
                        return code ? '<span class="label label-success">已发布</span>' : '<span class="label label-danger">草稿</span>';
                    }
                }, {
                    field: 'comment',
                    title: '开启评论',
                    width: '50px',
                    editable: false,
                    formatter: function (code) {
                        return code ? '<span class="label label-success">开启</span>' : '<span class="label label-danger">关闭</span>';
                    }
                }, {
                    field: 'createTime',
                    title: '发布时间',
                    editable: false,
                    width: '100px',
                    formatter: function (code) {
                        return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                    }
                }, {
                    field: 'lookCount',
                    title: '浏览',
                    editable: false,
                    width: '50px'
                }, {
                    field: 'commentCount',
                    title: '评论',
                    editable: false,
                    width: '50px'
                }, {
                    field: 'loveCount',
                    title: '喜欢',
                    editable: false,
                    width: '50px'
                }, {
                    field: 'operate',
                    title: '操作',
                    width: '230px',
                    formatter: operateFormatter //自定义方法，添加操作按钮
                }
            ]
        };
        //1.初始化Table
        $.tableUtil.init(options);
        //2.初始化Button的点击事件
        $.buttonUtil.init(options);

        /**
         * 推荐
         */
        $('#tablelist').on('click', '.btn-recommend', function () {
            var $this = $(this);
            var id = $this.attr("data-id");
            update("recommend", id);
        });

        /**
         * 置顶
         */
        $('#tablelist').on('click', '.btn-top', function () {
            var $this = $(this);
            var id = $this.attr("data-id");
            update("top", id);
        });

        function update(type, id) {
            $.ajax({
                type: "post",
                url: "/article/update/" + type,
                traditional: true,
                data: {'id': id},
                success: function (json) {
                    $.alert.ajaxSuccess(json);
                },
                error: $.alert.ajaxError
            });
        }

        /**
         * 推送到百度
         */
        $('#tablelist').on('click', '.btn-push', function () {
            var $this = $(this);
            var userId = $this.attr("data-id");
            push(userId);
        });

        /**
         * 批量推送到百度
         */
        $("#btn_push_ids").click(function () {
            var selectedId = getSelectedId();
            if (!selectedId || selectedId == '[]' || selectedId.length == 0) {
                $.alert.error("请至少选择一条记录");
                return;
            }
            push(selectedId);
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
                            $.alert.info(message, 5000);
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