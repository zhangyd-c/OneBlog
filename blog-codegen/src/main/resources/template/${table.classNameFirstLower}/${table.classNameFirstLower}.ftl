<#assign po = table.classNameFirstLower>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<${r"#"}include "/include/macros.ftl">
<${r"@"}header></${r"@"}header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="/">首页</a></li>
            <li class="active">${table.remark}管理</li>
        </ol>
        <div class="x_panel">
            <div class="x_content">
                <div class="btn-group hidden-xs" id="toolbar">
                    <${r"@"}shiro.hasPermission name="${po}:add">
                        <button id="btn_add" type="button" class="btn btn-info" title="新增${table.remark}">
                            <i class="fa fa-plus"></i> 新增
                        </button>
                    </${r"@"}shiro.hasPermission>
                    <${r"@"}shiro.hasPermission name="${po}:batchDelete">
                        <button id="btn_delete_ids" type="button" class="btn btn-danger" title="批量删除">
                            <i class="fa fa-trash-o"></i> 批量删除
                        </button>
                    </${r"@"}shiro.hasPermission>
                </div>
                <table id="tablelist">
                </table>
            </div>
        </div>
    </div>
</div>
<${r"@"}addOrUpdateMOdal defaultTitle="添加${table.remark}">
    <input type="hidden" name="id">
    <#list table.columns as column>
        <#if column.columnNameFirstLower != 'id' && column.columnNameFirstLower != 'insertTime' && column.columnNameFirstLower != 'createTime' && column.columnNameFirstLower != 'updateTime'>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="${column.columnNameFirstLower}">
            <#if column.remark?exists && column.remark?length gt 0>
            ${column.remark!}
            <#else >
            ${column.columnNameFirstLower}
            </#if>
            <span class="required">*</span>
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="${column.columnNameFirstLower}" id="${column.columnNameFirstLower}" required="required" placeholder="${column.columnNameFirstLower}"/>
        </div>
    </div>
        </#if>
    </#list>
</${r"@"}addOrUpdateMOdal>
<${r"@"}footer>
    <script>
        function operateFormatter(code, row, index) {
            var trId = row.id;
            var operateBtn = [
                '<${r"@"}shiro.hasPermission name="${po}:edit"><a class="btn btn-xs btn-primary btn-update" data-id="' + trId + '"><i class="fa fa-edit"></i>编辑</a></${r"@"}shiro.hasPermission>',
                '<${r"@"}shiro.hasPermission name="${po}:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o"></i>删除</a></${r"@"}shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                modalName: "${table.remark}",
                url: "/${po}/list",
                getInfoUrl: "/${po}/get/{id}",
                updateUrl: "/${po}/edit",
                removeUrl: "/${po}/remove",
                createUrl: "/${po}/add",
                columns: [
                    {
                        checkbox: true
                    },
                    <#list table.columns as column>
                    <#if column.columnNameFirstLower == "updateTime" || column.columnNameFirstLower == "createTime" || column.columnNameFirstLower == "insertTime">
                    {
                        field: '${column.columnNameFirstLower}',
                        title: '${column.remark}',
                        formatter: function (code) {
                            return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                        }
                    },
                    <#else>
                    {
                        field: '${column.columnNameFirstLower}',
                        title: '${column.remark}',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },
                    </#if>
                    </#list>
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
</${r"@"}footer>
