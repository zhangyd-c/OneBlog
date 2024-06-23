<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="/">首页</a></li>
            <li class="active">自定义页面管理</li>
        </ol>
        <div class="x_panel">
            <div class="x_content">
                <div class="btn-group hidden-xs" id="toolbar">
                    <@shiro.hasPermission name="page:add">
                        <button id="btn_add" type="button" class="btn btn-info" title="新增自定义页面">
                            <i class="fa fa-plus"></i> 新增自定义页面
                        </button>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="page:batchDelete">
                        <button id="btn_delete_ids" type="button" class="btn btn-danger" title="批量删除">
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
<@addOrUpdateMOdal defaultTitle="添加自定义页面">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="url">页面URL <span class="required">*</span></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="url" id="url" required="required" placeholder="url"/>
            <small style="font-size: 12px;">不包含域名部分，比如 <span class="red">https://www.baidu.com/</span><span class="green">asd</span>，则提取出来的 url 为 <strong><span class="green">asd</span></strong>。目前仅支持 .xml，.txt，.html 结尾的三种页面</small>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="content">页面内容 <span class="required">*</span></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <textarea class="form-control col-md-7 col-xs-12" id="content" name="content" rows="15" placeholder="请输入页面内容"></textarea>
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
            var url = row.url;
            var operateBtn = [
                '<a class="btn btn-xs btn-link" href="${config.siteUrl!}/' + url + '" target="_blank"><i class="fa fa-rocket fa-fw"></i>预览</a>',
                '<@shiro.hasPermission name="page:edit"><a class="btn btn-xs btn-primary btn-update" data-id="' + trId + '"><i class="fa fa-edit"></i>编辑</a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="page:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o"></i>删除</a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                modalName: "自定义页面",
                url: "/page/list",
                getInfoUrl: "/page/get/{id}",
                updateUrl: "/page/edit",
                removeUrl: "/page/remove",
                createUrl: "/page/add",
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id',
                        title: 'ID',
                        width: '80px',
                        editable: false
                    },
                    {
                        field: 'url',
                        title: '页面URL',
                        width: '150px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },
                    {
                        field: 'content',
                        title: '内容',
                        width: '350px',
                        formatter: function (code, row, index) {
                            var content = code;
                            if(!content) {
                                return '-';
                            }
                            return content.length > 30 ? (content.substr(0, 30) + '...') : content;
                        }
                    },
                    {
                        field: 'createTime',
                        title: '添加日期',
                        align: 'center',
                        width: '120px',
                        formatter: function (code) {
                            return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                        }
                    },
                    {
                        field: 'updateTime',
                        title: '更新日期',
                        align: 'center',
                        width: '120px',
                        formatter: function (code) {
                            return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                        }
                    },
                    {
                        field: 'operate',
                        title: '操作',
                        width: '120px',
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
