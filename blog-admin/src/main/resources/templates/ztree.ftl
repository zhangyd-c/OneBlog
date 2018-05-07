<#include "layout/header.ftl"/>
        <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Checkbox Operation</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i
                                    class="fa fa-wrench"></i></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Settings 1</a>
                                </li>
                                <li><a href="#">Settings 2</a>
                                </li>
                            </ul>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "layout/footer.ftl"/>
<SCRIPT type="text/javascript">
    var setting = {
        check: {
            enable: true,
            chkboxType: {"Y": "ps", "N": "ps"},
            chkStyle: "checkbox"
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: onCheck
        }
    };
    var tree = "";
    //查询菜单信息
    $.post('/getZtree', {rid: 1}, function (zNodes) {
        for (var i = 0; i < zNodes.length; i++) {
            if (zNodes[i].pId) {
                zNodes[i].checked = true;
            } else {
                //zNodes[i].icon = "/images/532.ico";//设置图标
            }
        }
        tree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        tree.expandAll(true);//全部展开
    }, 'json');

    function onCheck(event, treeId, treeNode) {
        console.log(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var nodes = treeObj.getCheckedNodes(true);
        var ids = new Array();
        for (var i = 0; i < nodes.length; i++) {
            //获取选中节点的值
            ids.push(nodes[i].id);
        }
        console.log(ids);
        ajaxSubmit(0, ids);
    }

    //角色-菜单信息入库
    function ajaxSubmit(rid, idstr) {
        console.log("保存角色信息...");
        // $.post("/roleMenu/bindSaveInfoEvent.jhtml", {"roleId": rid, "ids": idstr}, function (obj) {
        // }, 'json');
    }
</SCRIPT>