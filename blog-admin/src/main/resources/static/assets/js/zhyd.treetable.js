/**
 * 多级菜单
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/9/3 12:30
 */

$.extend({
    /**
     * 表格插件
     */
    table: {
        _default_config: {
            containerBox: '#tree-table-box',
            modalName: '',
            columns: [],
            data: [],
            toobarTemplate: '<div id="tree-table-toolbar" class="btn-group" role="group" aria-label="..."><button id="add-btn" type="button" class="btn btn-info" title="新增"><i class="fa fa-plus fa-fw"> </i> </button><button id="batch-delete-btn" type="button" class="btn btn-danger" title="批量删除"><i class="fa fa-trash-o fa-fw"> </i> </button></div>',
            oprater: {
                title: '操作',
                width: '100px',
                align: "center",
                formatter: function (value, row, index) {
                    var curId = row.id;
                    var actions = [];
                    actions.push('<a class="btn btn-success btn-sm edit-btn" href="javascript:;" data-id="' + curId + '"><i class="fa fa-edit fa-fw"></i></a> ');
                    // actions.push('<a class="btn btn-info btn-sm add-node-btn" href="javascript:;" data-id="' + curId + '"><i class="fa fa-plus fa-fw"></i></a> ');
                    actions.push('<a class="btn btn-danger btn-sm del-node-btn" href="javascript:;" data-id="' + curId + '"><i class="fa fa-trash-o fa-fw"></i></a>');
                    return actions.join('');
                }
            }
        },
        /**
         *
         * @param tableOptions 表格组件的参数
         * @param urlOptions 与后台交互的url参数
         */
        init: function (tableOptions, urlOptions) {
            var initOptions = $.extend({}, this._default_config, tableOptions);
            if (initOptions.toobarTemplate) {
                $(initOptions.containerBox).before(initOptions.toobarTemplate);
            }
            initOptions.columns.push(initOptions.oprater);
            $(initOptions.containerBox).bootstrapTreeTable({
                url: urlOptions.listUrl,
                toolbar: "#tree-table-toolbar",    //顶部工具条
                columns: initOptions.columns,
                data: initOptions.data
            });
            this.initBtnEvent(tableOptions, urlOptions);
            return initOptions;
        },
        /**
         * 刷新列表
         * @param tableOptions
         */
        refresh: function (tableOptions) {
            var initOptions = $.extend({}, this._default_config, tableOptions);
            // $(initOptions.containerBox).data('bootstrap.tree.table').load();
            $(initOptions.containerBox).bootstrapTreeTable('refresh');
        },
        /**
         * 初始化按钮事件
         * @param tableOptions
         * @param urlOptions
         */
        initBtnEvent: function (tableOptions, urlOptions) {
            var initOptions = $.extend({}, this._default_config, tableOptions);

            // 获取已选择的id
            function getSelectedIds() {
                var selecteds = $(initOptions.containerBox).bootstrapTreeTable('getSelections');
                var selectedIds = [];
                $.each(selecteds, function (index, item) {
                    selectedIds.push(item.id);
                });
                return (!selectedIds || selectedIds === '[]' || selectedIds.length === 0) ? null : selectedIds;
            }

            function bindSaveInfoEvent(url) {
                var $addOrUpdateBtn = $(".addOrUpdateBtn");
                $addOrUpdateBtn.unbind('click');
                $addOrUpdateBtn.click(function () {
                    var $addOrUpdateForm = $("#addOrUpdateForm");
                    if (validator.checkAll($addOrUpdateForm)) {
                        $.ajax({
                            type: "post",
                            url: url,
                            data: $addOrUpdateForm.serialize(),
                            success: function (json) {
                                $.alert.ajaxSuccess(json);
                                $("#addOrUpdateModal").modal('hide');
                                $.table.refresh();
                            },
                            error: $.alert.ajaxError
                        });
                    }
                })
            }
            function resetForm(info) {
                $("#addOrUpdateModal form").find("input, select, textarea").each(function () {
                    var $this = $(this);
                    var type = this.type;
                    var $div = $this.parents(".item");
                    if ($div && $div.hasClass("bad")) {
                        $div.removeClass("bad");
                        $div.find("div.alert").remove();
                    }
                    if (info) {
                        var thisName = $this.attr("name");
                        var thisValue = info[thisName];
                        if (type == 'radio') {
                            $this.prop('checked', ((thisValue && 1 == $this.val()) || (!thisValue && 0 == $this.val())));
                        } else if (type.startsWith('select')) {
                            if(thisValue == 'true' || thisValue == true) {
                                thisValue = 1;
                            } else if(thisValue == 'false' || thisValue == false) {
                                thisValue = 0;
                            }
                            $this.val(thisValue);
                        } else {
                            $this.val(thisValue);
                        }
                    } else {
                        if (type === 'radio' || type === 'checkbox') {
                            $this.prop('checked', false);
                        }else{
                            $this.val('');
                        }
                    }
                });
            }

            // 添加
            $("#add-btn").click(function () {
                resetForm();
                var $addOrUpdateModal = $("#addOrUpdateModal");
                $addOrUpdateModal.find(".modal-dialog .modal-content .modal-header h5.modal-title").html("添加" + initOptions.modalName);
                $addOrUpdateModal.modal('show');

                bindSaveInfoEvent(urlOptions.createUrl);
            });
            // 编辑
            $(initOptions.containerBox).on('click', '.edit-btn', function () {
                var $this = $(this);
                var userId = $this.attr("data-id");
                $.ajax({
                    type: "post",
                    url: urlOptions.getInfoUrl.replace("{id}", userId),
                    success: function (json) {
                        var info = json.data;
                        // console.log(info);
                        resetForm(info);
                        var $addOrUpdateModal = $("#addOrUpdateModal");
                        $addOrUpdateModal.modal('show');
                        $addOrUpdateModal.find(".modal-dialog .modal-content .modal-header h5.modal-title").html("修改" + initOptions.modalName);
                        bindSaveInfoEvent(urlOptions.updateUrl);
                    },
                    error: $.alert.ajaxError
                });
            });
            // 添加子项
            $(initOptions.containerBox).on('click', '.add-node-btn', function () {
                getSelectedIds();
            });

            function remove(ids) {
                $.alert.confirm("确定删除已选中的" + initOptions.modalName + "的" + ids.length + "条信息？", function () {
                    $.ajax({
                        type: "post",
                        url: urlOptions.removeUrl,
                        traditional: true,
                        data: {'ids': ids},
                        success: function (json) {
                            $.alert.ajaxSuccess(json);
                            $.table.refresh();
                        },
                        error: $.alert.ajaxError
                    }, function () {

                    }, 5000);
                });
            }

            // 删除子项
            $(initOptions.containerBox).on('click', '.del-node-btn', function () {
                var $this = $(this);
                var selectedId = $this.attr("data-id");
                remove([selectedId]);
            });

            // 批量删除
            $("#batch-delete-btn").click(function () {
                var selectedId = getSelectedIds();
                if (!selectedId) {
                    $.alert.error("请至少选择一条记录");
                    return false;
                }
                remove(selectedId);
            });

        }
    }
});