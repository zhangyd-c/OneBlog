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
                                <a class="btn btn-default" title="发表文章" href="/article/publishMd"> <i class="fa fa-pencil"></i> 发表文章 </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="article:batchDelete">
                                <button id="btn_delete_ids" type="button" class="btn btn-default" title="删除选中">
                                    <i class="fa fa-trash-o"></i> 批量删除
                                </button>
                            </@shiro.hasPermission>
                            <#-- 由草稿状态批量修改为已发布状态 -->
                            <@shiro.hasPermission name="article:publish">
                                <button id="btn_update_status" type="button" class="btn btn-default" title="批量发布">
                                    <i class="fa fa-trash-o"></i> 批量发布
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
        var recommended = row.recommended ? '<i class="fa fa-thumbs-o-down"></i>取消推荐' : '<i class="fa fa-thumbs-o-up"></i>推荐';
        var top = row.top ? '<i class="fa fa-arrow-circle-down"></i>取消置顶' : '<i class="fa fa-arrow-circle-up"></i>置顶';
        var operateBtn = [
            '<@shiro.hasPermission name="article:push"><a class="btn btn-xs btn-info btn-push" title="推送" data-id="' + trId + '"><i class="fa fa-send-o"></i>推送</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:edit"><a class="btn btn-xs btn-primary" href="/article/update/' + trId + '"><i class="fa fa-edit"></i>编辑</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o"></i>删除</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:top"><a class="btn btn-xs btn-success btn-top" data-id="' + trId + '">' + top + '</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="article:recommend"><a class="btn btn-xs btn-success btn-recommend" data-id="' + trId + '">' + recommended + '</a></@shiro.hasPermission>'
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
                    width: '200px',
                    editable: false,
                    formatter: function (code, row, index) {
                        var title = code;
                        title = title.length > 10 ? (title.substr(0, 10) + '...') : title;
                        var id = row.id;
                        // var original= row.original ? "原创" : "转载";
                        // return '<strong>['+original+']</strong> <a href="' + appConfig.wwwPath + '/article/' + id + '" target="_blank">' + code + '</a>';
                        var status = row.status ? '<span class="label label-success">已发布</span>' : '<span class="label label-danger">草稿</span>';

                        return status + '<a href="' + appConfig.wwwPath + '/article/' + id + '" target="_blank" title="' + code + '">' + title + '</a>';
                    }
                }, {
                    field: 'type',
                    title: '分类',
                    width: '80px',
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
                                tagHtml += ' <a class="" href="' + appConfig.wwwPath + '/tag/' + tag.id + '" target="_blank"> ' + tag.name + '</a> |';
                            }
                        }
                        return tagHtml.substr(0, tagHtml.length - 1);
                    }
                }, {
                    field: 'comment',
                    title: '评论',
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
                    width: '200px',
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

        /**
         * 批量修改状态
         */
        $("#btn_update_status").click(function () {
            var selectedId = getSelectedId();
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