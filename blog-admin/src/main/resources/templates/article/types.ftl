<#include "/include/macros.ftl">
<@header></@header>
<div class="">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <@breadcrumb>
                <ol class="breadcrumb">
                    <li><a href="/">首页</a></li>
                    <li class="active">文章分类管理</li>
                </ol>
            </@breadcrumb>
            <div class="x_panel">
                <div class="x_content">
                    <div class="<#--table-responsive-->">
                        <table id="tree-table-box">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<@addOrUpdateMOdal defaultTitle="添加分类">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">名称 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="name" id="name" required="required" placeholder="请输入分类名称"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">父级 </label>
        <div class="col-md-6 col-sm-6 col-xs-6">
            <select id="pid" name="pid" class="form-control col-md-5 col-xs-5" target="combox" data-url="/type/listParent" data-method="post"></select>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="description">描述 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <textarea class="form-control col-md-7 col-xs-12" id="description" name="description" placeholder="请输入分类描述" maxlength="100"></textarea>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="sort">排序 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="sort" id="sort" placeholder="请输入排序"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="available">是否可用 <span class="required">*</span></label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <ul class="list-unstyled list-inline">
                <li>
                    <div class="radio">
                        <label>
                            <input type="radio" class="flat" checked name="available" value="1"> 可用
                        </label>
                    </div>
                </li>
                <li>
                    <div class="radio">
                        <label>
                            <input type="radio" class="flat" name="available" value="0"> 禁用
                        </label>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="icon">图标 </label>
        <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="text" class="form-control col-md-7 col-xs-12" name="icon" id="icon" placeholder="请输入图标，比如：fa fa-qq"/>
        </div>
    </div>
</@addOrUpdateMOdal>
<@footer>
    <script type="text/javascript" src="/assets/js/zhyd.treetable.js"></script>
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
                '<@shiro.hasPermission name="type:edit"><a class="btn btn-sm btn-success btn-update" data-id="' + trId + '"title="编辑"><i class="fa fa-edit fa-fw"></i></a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="type:delete"><a class="btn btn-sm btn-danger btn-remove" data-id="' + trId + '"title="删除"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            $.table.init({
                modalName: "分类",
                columns: [{
                    field: 'selectItem',
                    checkbox: true
                }, {
                    field: '-',
                    title: '层级',
                    width: "60px",
                    align: "center"
                }, {
                    field: 'id',
                    title: 'ID',
                    width: '60px',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'name',
                    title: '名称',
                    width: '180px',
                    formatter: function (code, row, index) {
                        var id = row.id;
                        return '<a href="' + appConfig.wwwPath + '/type/' + id + '" target="_blank">' + row.name + '</a>';
                    }
                }, {
                    field: 'parent.name',
                    title: '父级分类',
                    width: '180px',
                    formatter: function (code, row, index) {
                        var parent = row.parent;
                        if(!parent) {
                            return "-";
                        }
                        return '<a href="' + appConfig.wwwPath + '/type/' + parent.id + '" target="_blank">' + parent.name + '</a>';
                    }
                }, {
                    field: 'description',
                    title: '描述',
                    width: '550px',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'sort',
                    title: '排序',
                    width: '50px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? code : '-';
                    }
                }, {
                    field: 'available',
                    title: '可用',
                    width: '50px',
                    align: 'center',
                    formatter: function (code) {
                        return code ? '<span class="label label-success">可用</span>' : '<span class="label label-danger">不可用</span>';
                    }
                }, {
                    field: 'icon',
                    title: '图标',
                    width: '50px',
                    align: 'center',
                    formatter: function (code, row, index) {
                        return '<i class="' + row.icon + '"></i>';
                    }
                }]
            }, {
                listUrl: "/type/list",
                getInfoUrl: "/type/get/{id}",
                updateUrl: "/type/edit",
                removeUrl: "/type/remove",
                createUrl: "/type/add"
            });

            /**
             * 当修改类型信息时，禁用父级列表中value和当前待修改的类型的id一致的option
             */
            var editId;
            $("#addOrUpdateModal").unbind('show.bs.modal').on('show.bs.modal', function () {
                editId = $("#addOrUpdateModal").find("form>input[name=id]").val();
                if(editId) {
                    $("select#pid option[value='" + editId + "']").attr("disabled","disabled");
                }
            }).unbind('hide.bs.modal').on('hide.bs.modal', function () {
                if(editId) {
                    $("select#pid option[value='" + editId + "']").removeAttr("disabled");
                    editId = null;
                }
            });
        });
    </script>
</@footer>