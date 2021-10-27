<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="/">首页</a></li>
            <li class="active">广告管理</li>
        </ol>
        <div class="x_panel">
            <div class="x_content">
                <div class="btn-group hidden-xs" id="toolbar">
                    <@shiro.hasPermission name="bizAd:add">
                        <button id="btn_add" type="button" class="btn btn-info" title="新增广告">
                            <i class="fa fa-plus"></i> 新增
                        </button>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="bizAd:batchDelete">
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
<@addOrUpdateMOdal defaultTitle="添加广告">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="position">
            广告位置
            <span class="required">*</span>
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <select id="position" name="position" class="form-control col-md-5 col-xs-5" required>
                <option value="">请选择</option>
                <#list positions as item>
                    <option value="${item.name!}">${item.desc} - ${item.name!}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <small>广告位置为 <code>首页开屏广告 - HOMEPAGE_OPEN_SCREEN</code> 时，广告类型必须为 <code>POP</code></small>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="type">
            广告类型
            <span class="required">*</span>
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <select id="type" name="type" class="form-control col-md-5 col-xs-5" required>
                <option value="">请选择</option>
                <#list types as item>
                    <option value="${item.name!}">${item.desc} - ${item.name!}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="title">
            广告标题
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="title" id="title" placeholder="请输入广告标题"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="picture">
            广告图片
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="picture" id="picture" placeholder="请输入图片广告的图片地址（URL）"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="link">
            广告链接
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="link" id="link" placeholder="请输入广告指向的链接地址（URL）"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="content">
            广告内容
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <textarea class="form-control col-md-7 col-xs-12" id="content" name="content" placeholder="仅作用于 JS 类型的广告。可以在此输入 HTML。" rows="10"></textarea>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="expiringDate">
            广告到期日
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <div class='input-group date myDatepicker'>
                <input type='text' class="form-control" readonly="readonly" id="expiringDate" name="expiringDate"  placeholder="请输入广告到期日"/>
                <span class="input-group-addon">
                   <span class="fa fa-calendar"></span>
                </span>
            </div>
        </div>
    </div>
</@addOrUpdateMOdal>
<@footer>
    <script>
        var positionMap = {}
        <#list positions as item>
        positionMap['${item.name!}'] = '${item.desc}'
        </#list>
        var typeMap = {}
        <#list types as item>
        typeMap['${item.name!}'] = '${item.desc}'
        </#list>

        function operateFormatter(code, row, index) {
            var trId = row.id;
            var operateBtn = [
                '<@shiro.hasPermission name="bizAd:edit"><a class="btn btn-xs btn-primary btn-update" data-id="' + trId + '"><i class="fa fa-edit"></i>编辑</a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="bizAd:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o"></i>删除</a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                modalName: "广告",
                url: "/bizAd/list",
                getInfoUrl: "/bizAd/get/{id}",
                updateUrl: "/bizAd/edit",
                removeUrl: "/bizAd/remove",
                createUrl: "/bizAd/add",
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id',
                        title: 'ID',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },
                    {
                        field: 'type',
                        title: '广告类型',
                        width: '80px',
                        formatter: function (code) {
                            return code ? typeMap[code] : '-';
                        }
                    },
                    {
                        field: 'position',
                        title: '广告位置',
                        width: '160px',
                        formatter: function (code) {
                            return code ? positionMap[code] : '-';
                        }
                    },
                    {
                        field: 'title',
                        title: '广告标题',
                        width: '270px',
                        formatter: function (code, row, index) {
                            var content = code;
                            if(!content) {
                                return '-';
                            }
                            content = content.length > 30 ? (content.substr(0, 30) + '...') : content;
                            return '<span title="' + code + '">' + content + '</a>';
                        }
                    },
                    {
                        field: 'picture',
                        title: '广告图片',
                        width: '100px',
                        formatter: function (code) {
                            return code ? '<a href="' + code + '" class="showImage" rel="external nofollow">' +
                                '<img src="' + code + '" onerror="this.src=\'/assets/images/favicon.ico\'" class="img-rounded" style="width: 80px;">' +
                                '</a>' : '-';
                        }
                    },
                    {
                        field: 'link',
                        title: '广告链接',
                        width: '150px',
                        formatter: function (code) {
                            return code ? '<a href="' + code + '" rel="external nofollow" target="_blank">查看</a>' : '-';
                        }
                    },
                    {
                        field: 'expiringDate',
                        title: '广告到期日',
                        formatter: function (code) {
                            return code ? new Date(code).format("yyyy-MM-dd hh:mm:ss") : '-'
                        }
                    },
                    {
                        field: 'showNumber',
                        title: '展示次数',
                        width: '80px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },
                    {
                        field: 'clickNumber',
                        title: '点击次数',
                        width: '80px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },
                    {
                        field: 'operate',
                        title: '操作',
                        width: '130px',
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
