<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">图片库管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="btn-group hidden-xs" id="toolbar" style="padding: 10px 0;">
                    <@shiro.hasPermission name="files">
                        <button id="btn_add" type="button" class="btn btn-info" title="新增图片">
                            <i class="fa fa-plus fa-fw"></i>
                        </button>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="files">
                        <button id="btn_delete_ids" type="button" class="btn btn-danger" title="删除选中">
                            <i class="fa fa-trash-o fa-fw"></i>
                        </button>
                    </@shiro.hasPermission>
                </div>
                <div class="x_panel">
                    <form id="file-form">
                        <div class="x_content file-container" id="file-container">
                            <div class="col-md-55">
                                <div class="thumbnail">
                                    <div class="image view view-first">
                                        <img style="display: block;margin: 0 auto;margin-top: 10px;" src="/assets/images/loading.gif" alt="image" />
                                    </div>
                                    <div class="caption">
                                        <p>暂无可用的图片... </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<@addOrUpdateMOdal defaultTitle="添加图片">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="url">选择图片 <span class="required">*</span></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="file" class="form-control" name="file" id="file" accept="image/bmp,image/png,image/jpeg,image/jpg,image/gif" required="required"/>
        </div>
    </div>
</@addOrUpdateMOdal>
<@footer>
    <script>
        var curSstorageType = '${config.storageType}';
        $(function () {
            loadData(1);

            function loadData(pageNumber){
                $.ajax({
                    url: "/file/list",
                    data: {pageNumber: pageNumber},
                    type: "POST",
                    success: function (json) {
                        var tpl = '{{#list}}<div class="col-md-55">\n' +
                            '                    <div class="thumbnail">\n' +
                            '                        <div class="image view view-first pointer file-item">\n' +
                            '                            <img style="width: 100%; display: block;" src="{{fullFilePath}}" onerror="this.alt=\'图片加载失败\'" alt="{{originalFileName}}" title="{{originalFileName}}" />\n' +
                            '                            <div class="vmask">\n' +
                            '                                <p>点击选中</p>\n' +
                            '                                <div class="tools tools-bottom">\n' +
                            '                                    <a href="{{fullFilePath}}" class="file-icon showImage" title="{{filePath}}"><i class="fa fa-eye"></i></a>\n' +
                            '                                    <a href="{{fullFilePath}}" target="_blank" class="file-icon" title="复制地址（打开标签后复制）"><i class="fa fa-link"></i></a>\n' +
                            '                                    <a class="pointer file-icon" data-event="del" data-value="{{id}}" data-storage-type="{{storageType}}" title="删除文件"><i class="fa fa-times"></i></a>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                            <div class="selected-mask">\n' +
                            '                                <input type="checkbox" class="square mask-checkbox" name="ids" value="{{id}}" data-storage-type="{{storageType}}" />' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                        <div class="caption">\n' +
                            '                            <p><span title="{{originalFileName}}">{{originalFileName}}</span><img src="/assets/images/icons/{{storageType}}.svg" alt="{{storageType}}" title="{{storageType}}"></p>\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                </div>{{/list}}';
                        var html = Mustache.render(tpl, json);
                        var pageTpl = '<ul class="list-unstyled">{{#data}}<li class="file-page">\n' +
                            '    <div class="file-page-body">\n' +
                            '        {{#hasPreviousPage}}<a class="btn btn-default btn-sm file-pagination" data-page="{{prePage}}">\n' +
                            '            <i class="fa fa-caret-left"></i>\n' +
                            '        </a>{{/hasPreviousPage}}<span style="margin-right: 5px;">{{pageNum}}/{{pages}}</span>{{#hasNextPage}}<a class="btn btn-default btn-sm file-pagination" data-page="{{nextPage}}">\n' +
                            '            <i class="fa fa-caret-right"></i>\n' +
                            '        </a>{{/hasNextPage}}<input class="form-control input-sm file-input">\n' +
                            '    <a class="btn btn-default btn-sm file-jump">\n' +
                            '            Go\n' +
                            '        </a>\n' +
                            '    \n' +
                            '    </div>\n' +
                            '</li>{{/data}}</ul>';
                        html += Mustache.render(pageTpl, {data: json});
                        $("#file-container").html(html);

                        // 绑定分页点击事件
                        $(".file-pagination").unbind("click").click(function () {
                            var $this = $(this);
                            var pageNumber = $this.data("page");
                            loadData(!pageNumber || isNaN(pageNumber) ? 1 : parseInt(pageNumber));
                        });
                        // 绑定分页-跳转页面点击事件
                        $(".file-jump").unbind("click").click(function () {
                            var $this = $(this);
                            var jumpTarget = $(".file-input").val();
                            loadData(!jumpTarget || isNaN(jumpTarget) ? 1 : parseInt(jumpTarget));
                        });

                        gentelella.initiICheck();
                        $('.mask-checkbox').on('ifChanged', function (event) {
                            var $this = $(this),
                                $thumbnail = $this.parents("div.thumbnail");
                            if ($this.is(':checked')) {
                                $thumbnail.addClass("selected");
                            } else {
                                $thumbnail.removeClass("selected");
                            }
                        });
                        bindFileItemEvent();
                        function bindFileItemEvent() {
                            $(".file-item").click(function () {
                                var $checkbox = $(this).find('.mask-checkbox');
                                $checkbox.iCheck($checkbox.is(':checked') ? "uncheck" : "check");
                            });
                        }
                        $("#btn_delete_ids").click(function () {
                            var canBeDeleted = true;
                            $('.mask-checkbox').each(function () {
                                var $this = $(this);
                                var storageType = $this.data("storage-type");
                                if($this.is(':checked') && storageType != curSstorageType) {
                                    canBeDeleted = false;
                                    return false;
                                }
                            });
                            if(!canBeDeleted) {
                                $.alert.error("【不可删除】当前选择的文件存储于不同的云存储平台！");
                                return false;
                            }
                            del($("#file-form").serialize());
                        });
                        $(".file-icon").click(function () {
                            $(".file-item").unbind("click");
                            var event = $(this).data("event");
                            var id = $(this).data("value");
                            var storageType = $(this).data("storage-type");
                            if(event) {
                                del({'ids': id}, storageType);
                            } else {
                                setTimeout(function () {
                                    bindFileItemEvent();
                                })
                            }
                        });
                        function del(data, storageType){
                            if(storageType && storageType != curSstorageType) {
                                $.alert.error("【不可删除】该文件存储于[" + storageType + "]，当前系统的云存储类型为[" + curSstorageType + "]");
                                return false;
                            }
                            $.alert.confirm("确定删除该选中的文件？不可恢复，请确认！", function () {
                                $.ajax({
                                    type: "POST",
                                    url: "/file/remove",
                                    traditional: true,
                                    data: data,
                                    success: function (json) {
                                        $.alert.ajaxSuccess(json, function () {
                                            window.location.reload();
                                        });
                                    }
                                })
                            })
                        }
                    },
                    error: $.alert.ajaxError
                });
            }
            $("#btn_add").click(function () {
                $("#addOrUpdateModal").modal('show');
                $(".addOrUpdateBtn").unbind('click').click(function () {
                    var $form = $("#addOrUpdateForm");
                    if (validator.checkAll($form)) {
                        $form.ajaxSubmit({
                            type: "post",
                            url: "/file/add",
                            success: function (json) {
                                $.alert.ajaxSuccess(json, function () {
                                    window.location.reload();
                                });
                            },
                            error: $.alert.ajaxError
                        });
                    }
                })
            });
        });
    </script>
</@footer>