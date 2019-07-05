/**
 * bootstrapTreeTable
 * v0.0.1
 * @author swifly
 * @modify yadong.zhang
 *      修改data格式
 *      增加简单分页
 *      增加全选操作
 */
(function ($) {
    "use strict";

    $.fn.bootstrapTreeTable = function (options, param) {
        var target = $(this).data('bootstrap.tree.table');
        target = target ? target : $(this);
        // 如果是调用方法
        if (typeof options == 'string') {
            return $.fn.bootstrapTreeTable.methods[options](target, param);
        }
        // 如果是初始化组件
        options = $.extend({}, $.fn.bootstrapTreeTable.defaults, options || {});
        // 是否有radio或checkbox
        var hasSelectItem = false;
        target.data_list = null; //用于存放格式化后的数据-按父分组
        target.data_obj = null; //用于存放格式化后的数据-按id存对象
        // 在外层包装一下div，样式用的bootstrap-table的
        var _main_div = $("<div class='bootstrap-tree-table'></div>");
        target.before(_main_div);
        _main_div.append(target);
        target.addClass("table table-hover treetable-table");
        if (options.striped) {
            target.addClass('table-striped');
        }
        if (options.bordered) {
            target.addClass('table-bordered');
        }
        // 工具条在外层包装一下div，样式用的bootstrap-table的
        if (options.toolbar) {
            var _tool_div = $("<div class='treetable-bars pull-left'></div>");
            _tool_div.append($(options.toolbar));
            _main_div.before(_tool_div);
        }
        // 格式化数据，优化性能
        var formatData = function (data) {
            var _root = options.rootIdValue ? options.rootIdValue : null
            $.each(data, function (index, item) {
                // 添加一个默认属性，用来判断当前节点有没有被显示
                item.isShow = false;
                // 这里兼容几种常见Root节点写法
                // 默认的几种判断
                var _defaultRootFlag = item[options.parentId] == '0' ||
                    item[options.parentId] == 0 ||
                    item[options.parentId] == null ||
                    item[options.parentId] == '';
                if (!item[options.parentId] || (_root ? (item[options.parentId] == options.rootIdValue) : _defaultRootFlag)) {
                    if (!target.data_list["_root_"]) {
                        target.data_list["_root_"] = [];
                    }
                    if (!target.data_obj["id_" + item[options.id]]) {
                        target.data_list["_root_"].push(item);
                    }
                } else {
                    if (!target.data_list["_n_" + item[options.parentId]]) {
                        target.data_list["_n_" + item[options.parentId]] = [];
                    }
                    if (!target.data_obj["id_" + item[options.id]]) {
                        target.data_list["_n_" + item[options.parentId]].push(item);
                    }
                }
                target.data_obj["id_" + item[options.id]] = item;
            });
        }
        // 得到根节点
        var getRootNodes = function () {
            return target.data_list["_root_"];
        };
        // 递归获取子节点并且设置子节点
        var handleNode = function (parentNode, lv, row_id, p_id, tbody) {
            var _ls = target.data_list["_n_" + parentNode[options.id]];
            var tr = renderRow(parentNode, _ls ? true : false, lv, row_id, p_id);
            tbody.append(tr);
            if (_ls) {
                $.each(_ls, function (i, item) {
                    var _row_id = row_id + "_" + i
                    handleNode(item, (lv + 1), _row_id, row_id, tbody)
                });
            }
        };
        // 绘制行
        var renderRow = function (item, isP, lv, row_id, p_id) {
            // 标记已显示
            item.isShow = true;
            item.row_id = row_id;
            item.p_id = p_id;
            item.lv = lv;
            var tr = $('<tr id="' + row_id + '" pid="' + p_id + '"></tr>');
            var _icon = options.expanderCollapsedClass;
            if (options.expandAll) {
                tr.css("display", "table");
                _icon = options.expanderExpandedClass;
            } else if (lv == 1) {
                tr.css("display", "table");
                _icon = (options.expandFirst) ? options.expanderExpandedClass : options.expanderCollapsedClass;
            } else if (lv == 2) {
                if (options.expandFirst) {
                    tr.css("display", "table");
                } else {
                    tr.css("display", "none");
                }
                _icon = options.expanderCollapsedClass;
            } else {
                tr.css("display", "none");
                _icon = options.expanderCollapsedClass;
            }
            $.each(options.columns, function (index, column) {
                // 判断有没有选择列
                if (column.field == 'selectItem') {
                    hasSelectItem = true;
                    var td = $('<td style="text-align:center;width:36px"></td>');
                    if (column.radio) {
                        var _ipt = $('<input name="select_item" type="radio" value="' + item[options.id] + '"></input>');
                        td.append(_ipt);
                    }
                    if (column.checkbox) {
                        var _ipt = $('<input name="select_item" type="checkbox" value="' + item[options.id] + '"></input>');
                        td.append(_ipt);
                    }
                    tr.append(td);
                } else {
                    // 过滤了特殊字符
                    var value = getItemField(item, column.field, true);
                    var td = $('<td title="' + value + '" name="' + column.field + '" style="' + ((column.width) ? ('width:' + column.width) : '') + ((column.align) ? (';text-align:' + column.align) : '') + '"></td>');
                    // 增加formatter渲染
                    if (column.formatter) {
                        td.html(column.formatter.call(this, value, item, index));
                    } else {
                        td.text(value);
                    }
                    if (options.expandColumn == index) {
                        if (!isP) {
                            td.prepend('<span class="treetable-expander"></span>')
                        } else {
                            td.prepend('<span class="treetable-expander ' + _icon + '"></span>')
                        }
                        for (var int = 0; int < (lv - 1); int++) {
                            td.prepend('<span class="treetable-indent"></span>')
                        }
                    }
                    tr.append(td);
                }
            });
            return tr;
        }
        // 加载完数据后渲染表格
        var renderTable = function (data) {

            var tbody = target.find("tbody");
            var thead = target.find("thead");
            // 加载完数据先清空
            tbody.html("");
            if (!data || data.total <= 0 || !data.rows || data.rows.length <= 0) {
                var _empty = '<tr><td colspan="' + options.columns.length + '"><div style="display: block;text-align: center;">没有找到匹配的记录</div></td></tr>'
                tbody.html(_empty);
                return;
            }
            data = data.rows;
            // 格式化数据
            formatData(data);
            // 开始绘制
            var rootNode = getRootNodes();
            if (rootNode) {
                $.each(rootNode, function (i, item) {
                    var _row_id = "row_id_" + i;
                    handleNode(item, 1, _row_id, "row_root", tbody);
                });
            }
            // 下边的操作主要是为了查询时让一些没有根节点的节点显示
            $.each(data, function (i, item) {
                if (!item.isShow) {
                    var tr = renderRow(item, false, 1, "", "");
                    tbody.append(tr);
                }
            });
            target.append(tbody);
            //动态设置表头宽度
            thead.css("width", tbody.children(":first").css("width"));
            registerExpanderEvent();
            registerRowClickEvent();
        };
        // 注册行点击选中事件
        var registerRowClickEvent = function () {
            if(!options.clickToSelect) {
                return false;
            }
            target.find("tbody").find("tr").unbind();
            target.find("tbody").find("tr").click(function () {
                if (hasSelectItem) {
                    var _ipt = $(this).find("input[name='select_item']");
                    if (_ipt.attr("type") == "radio" || _ipt.attr("type") == "checkbox") {
                        _ipt.prop('checked', true);
                        target.find("tbody").find("tr").removeClass("treetable-selected");
                        $(this).addClass("treetable-selected");
                    } else {
                        if (_ipt.prop('checked')) {
                            _ipt.prop('checked', false);
                            $(this).removeClass("treetable-selected");
                        } else {
                            _ipt.prop('checked', true);
                            $(this).addClass("treetable-selected");
                        }
                    }
                }
            });
        }
        // 注册小图标点击事件--展开缩起
        var registerExpanderEvent = function () {
            target.find("tbody").find("tr").find(".treetable-expander").unbind();
            target.find("tbody").find("tr").find(".treetable-expander").click(function () {
                var _isExpanded = $(this).hasClass(options.expanderExpandedClass);
                var _isCollapsed = $(this).hasClass(options.expanderCollapsedClass);
                if (_isExpanded || _isCollapsed) {
                    var tr = $(this).parent().parent();
                    var row_id = tr.attr("id");
                    var _ls = target.find("tbody").find("tr[id^='" + row_id + "_']"); //下所有
                    if (_isExpanded) {
                        $(this).removeClass(options.expanderExpandedClass);
                        $(this).addClass(options.expanderCollapsedClass);
                        if (_ls && _ls.length > 0) {
                            $.each(_ls, function (index, item) {
                                $(item).css("display", "none");
                            });
                        }
                    } else {
                        $(this).removeClass(options.expanderCollapsedClass);
                        $(this).addClass(options.expanderExpandedClass);
                        if (_ls && _ls.length > 0) {
                            $.each(_ls, function (index, item) {
                                // 父icon
                                var _p_icon = $("#" + $(item).attr("pid")).children().eq(options.expandColumn).find(".treetable-expander");
                                if (_p_icon.hasClass(options.expanderExpandedClass)) {
                                    $(item).css("display", "table");
                                }
                            });
                        }
                    }
                }
            });
        }
        // 根据column的field获取实际的值
        var getItemField = function (item, field, escape) {
            var value = item;

            if (typeof field !== 'string' || item.hasOwnProperty(field)) {
                return escape ? escapeHtml(item[field]) : item[field]
            }

            var props = field.split('.');
            for (var p in props) {
                value = value && value[props[p]]
            }
            return escape ? escapeHtml(value) : value
        };
        var escapeHtml = function(text){
            if (typeof text === 'string') {
                return text
                    .replace(/&/g, '&amp;')
                    .replace(/</g, '&lt;')
                    .replace(/>/g, '&gt;')
                    .replace(/"/g, '&quot;')
                    .replace(/'/g, '&#039;')
                    .replace(/`/g, '&#x60;')
            }
            return text
        };
        // 加载数据前
        target.load = function (parms) {
            // 加载数据前先清空
            target.data_list = {};
            target.data_obj = {};
            // 加载数据前先清空
            target.html("");
            // 构造表头
            var thr = $('<tr></tr>');
            $.each(options.columns, function (i, item) {
                var th = null;
                // 判断有没有选择列
                if (i == 0 && item.field == 'selectItem') {
                    hasSelectItem = true;
                    th = $('<th style="width:36px"><input name="btSelectAll" id="btSelectAll" type="checkbox" ></th>');
                } else {
                    th = $('<th style="' + ((item.width) ? ('width:' + item.width) : '') + '"></th>');
                }
                th.text(item.title);
                thr.append(th);
            });
            var thead = $('<thead class="treetable-thead"></thead>');
            thead.append(thr);
            target.append(thead);
            // 构造表体
            var tbody = $('<tbody class="treetable-tbody"></tbody>');
            target.append(tbody);
            // 添加加载loading
            var _loading = '<tr><td colspan="' + options.columns.length + '"><div style="display: block;text-align: center;">正在努力地加载数据中，请稍候……</div></td></tr>'
            tbody.html(_loading);
            // 默认高度
            if (options.height) {
                tbody.css("height", options.height);
            }
            if (options.url) {
                $.ajax({
                    type: options.type,
                    url: options.url,
                    data: parms ? parms : options.ajaxParams,
                    dataType: "JSON",
                    success: function (data, textStatus, jqXHR) {
                        renderTable(data);

                        initPagination(options, data);
                    },
                    error: function (xhr, textStatus) {
                        var _errorMsg = '<tr><td colspan="' + options.columns.length + '"><div style="display: block;text-align: center;">' + xhr.responseText + '</div></td></tr>'
                        tbody.html(_errorMsg);
                    }
                });
            } else {
                renderTable(options.data);
            }

            /* 全选 */
            var $selectAll = target.find('[name="btSelectAll"]')
            $selectAll.off('click').on('click', function (e) {
                var checked = $(e.currentTarget).prop('checked')
                checked ? checkAll() : uncheckAll();
            });
            function checkAll() {
                checkAll_(true);
            }

            function uncheckAll() {
                checkAll_(false);
            }
            function checkAll_(checked) {
                var rows;
                if (!checked) {
                    rows = target.bootstrapTreeTable('getSelections');
                }
                target.find("input[name=select_item]").filter(':enabled').prop('checked', checked);
                if (checked) {
                    rows = target.bootstrapTreeTable('getSelections');
                }
                target.trigger(checked ? 'checkAll' : 'uncheckAll', rows)
            }
        };
        /**
         * 初始化分页
         *
         * @param options
         * @param data
         * @param curPage
         */
        var initPagination = function (options, data, curPage) {
            if(!options.pagination) {
                return false;
            }
            var pageSizeArr = options.pageSize, total = data.total, pageSize = pageSizeArr[0], currentPage = curPage ? curPage : options.curPage,
                totalPage = (total >= pageSize) ? ((total / pageSize) + ((total % pageSize) > 0 ? 1 : 0)) : 1, i;
            if (totalPage <= 1) {
                return false;
            }
            // 可选的pageSize
            var pageSizeOptions = '';
            for (i in pageSizeArr) {
                pageSizeOptions += '<option value="' + pageSizeArr[i] + '" ' + (pageSize === pageSizeArr[i] ? 'selected="selected"' : '') + '>' + pageSizeArr[i] + '</option>';
            }
            // page item
            var pageItems = '';
            if(currentPage > 1) {
                pageItems += '<li class="page-item"><a class="page-link" href="javascript:;" data-id="' + (currentPage - 1) + '">«</a></li>';
            }

            for (i = 1; i < totalPage; i++) {
                pageItems += '<li class="page-item {active}"><a class="page-link" href="javascript:;" data-id="' + i + '">' + i + '</a></li>';
                pageItems = pageItems.replace("{active}", (currentPage === i) ? 'active' : '');
            }
            if(currentPage !== totalPage) {
                pageItems += '<li class="page-item"><a class="page-link" href="javascript:;" data-id="' + (currentPage + 1) + '">»</a></li>';
            }

            // 模板 --> 替换
            var tpl = '<div class="row table-pagination-box"><div class="col-sm-5 col-md-5"><div class="table-pagination-info"role="status"aria-live="polite">共{totalPage}页{total}条数据，每页显示<select name="pageSize"aria-controls="sampleTable"class="form-control form-control-sm table-pagesize">{pageSizeOption}</select>条记录</div></div><div class="col-sm-7 col-md-7"style="display: {display}"><ul class="pagination">{pageItem}</ul></div></div>';
            tpl = tpl.replace("{total}", total);
            tpl = tpl.replace("{totalPage}", totalPage);
            tpl = tpl.replace("{pageSizeOption}", pageSizeOptions);
            tpl = tpl.replace("{display}", (totalPage > 1) ? 'block' : 'none');
            tpl = tpl.replace("{pageItem}", pageItems);
            target.after(tpl);
        };
        // 添加数据刷新表格
        target.appendData = function (data) {
            // 下边的操作主要是为了查询时让一些没有根节点的节点显示
            $.each(data, function (i, item) {
                var _data = target.data_obj["id_" + item[options.id]];
                var _p_data = target.data_obj["id_" + item[options.parentId]];
                var _c_list = target.data_list["_n_" + item[options.parentId]];
                var row_id = ""; //行id
                var p_id = ""; //父行id
                var _lv = 1; //如果没有父就是1默认显示
                var tr; //要添加行的对象
                if (_data && _data.row_id && _data.row_id != "") {
                    row_id = _data.row_id; // 如果已经存在了，就直接引用原来的
                }
                if (_p_data) {
                    p_id = _p_data.row_id;
                    if (row_id == "") {
                        var _tmp = 0
                        if (_c_list && _c_list.length > 0) {
                            _tmp = _c_list.length;
                        }
                        row_id = _p_data.row_id + "_" + _tmp;
                    }
                    _lv = _p_data.lv + 1; //如果有父
                    // 绘制行
                    tr = renderRow(item, false, _lv, row_id, p_id);

                    var _p_icon = $("#" + _p_data.row_id).children().eq(options.expandColumn).find(".treetable-expander");
                    var _isExpanded = _p_icon.hasClass(options.expanderExpandedClass);
                    var _isCollapsed = _p_icon.hasClass(options.expanderCollapsedClass);
                    // 父节点有没有展开收缩按钮
                    if (_isExpanded || _isCollapsed) {
                        // 父节点展开状态显示新加行
                        if (_isExpanded) {
                            tr.css("display", "table");
                        }
                    } else {
                        // 父节点没有展开收缩按钮则添加
                        _p_icon.addClass(options.expanderCollapsedClass);
                    }

                    if (_data) {
                        $("#" + _data.row_id).before(tr);
                        $("#" + _data.row_id).remove();
                    } else {
                        // 计算父的同级下一行
                        var _tmp_ls = _p_data.row_id.split("_");
                        var _p_next = _p_data.row_id.substring(0, _p_data.row_id.length - 1) + (parseInt(_tmp_ls[_tmp_ls.length - 1]) + 1);
                        // 画上
                        $("#" + _p_next).before(tr);
                    }
                } else {
                    tr = renderRow(item, false, _lv, row_id, p_id);
                    if (_data) {
                        $("#" + _data.row_id).before(tr);
                        $("#" + _data.row_id).remove();
                    } else {
                        // 画上
                        var tbody = target.find("tbody");
                        tbody.append(tr);
                    }
                }
                item.isShow = true;
                formatData([item]);
            });
            registerExpanderEvent();
            registerRowClickEvent();
        }
        // 加载数据
        target.load();
        target.data('bootstrap.tree.table', target);
        return target;
    };

    // 组件方法封装........
    $.fn.bootstrapTreeTable.methods = {
        // 为了兼容bootstrap-table的写法，统一返回数组，这里返回了表格显示列的数据
        getSelections: function (target, data) {
            // 所有被选中的记录input
            var _ipts = target.find("tbody").find("tr").find("input[name='select_item']:checked");
            var chk_value = [];
            _ipts.each(function (i, v) {
                var _ipt = $(v);
                if (_ipt.attr("type") == "radio" || _ipt.attr("type") == "checkbox") {
                    var _data = target.data_obj["id_" + _ipt.val()];
                    chk_value.push(_data);
                } else {
                    _ipt.each(function (_i, _item) {
                        var _data = target.data_obj["id_" + $(_item).val()];
                        chk_value.push(_data);
                    });
                }
            });


            return chk_value;
        },
        // 刷新记录
        refresh: function (target, parms) {
            if (parms) {
                target.load(parms);
            } else {
                target.load();
            }
        },
        // 添加数据到表格
        appendData: function (target, data) {
            if (data) {
                target.appendData(data);
            }
        }
        // 组件的其他方法也可以进行类似封装........
    };

    $.fn.bootstrapTreeTable.defaults = {
        id: 'id', // 选取记录返回的值,用于设置父子关系
        parentId: 'parentId', // 用于设置父子关系
        rootIdValue: null, //设置根节点id值----可指定根节点，默认为null,"",0,"0"
        data: null, // 构造table的数据集合
        type: "POST", // 请求数据的ajax类型
        url: null, // 请求数据的ajax的url
        ajaxParams: {}, // 请求数据的ajax的data属性
        expandColumn: 1, // 在哪一列上面显示展开按钮
        expandAll: false, // 是否全部展开
        expandFirst: false, // 是否默认第一级展开--expandAll为false时生效
        striped: false, // 是否各行渐变色
        bordered: true, // 是否显示边框
        columns: [],
        toolbar: null, //顶部工具条
        height: 0,
        pagination: false, // 是否启用分页
        pageSize: [20, 30, 40, 50], // 可选的分页大小
        clickToSelect: false,
        expanderExpandedClass: 'fa fa-folder-open-o fa-fw',// 展开的按钮的图标
        expanderCollapsedClass: 'fa fa-folder-o fa-fw',// 缩起的按钮的图标
        curPage: 1
    };
})(jQuery);
