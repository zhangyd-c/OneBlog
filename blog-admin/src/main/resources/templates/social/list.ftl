<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">社会化登录配置管理</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="link:add">
                            <button id="btn_add" type="button" class="btn btn-info" title="新增配置">
                                <i class="fa fa-plus fa-fw"></i>
                            </button>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="link:batchDelete">
                            <button id="btn_delete_ids" type="button" class="btn btn-danger" title="批量删除">
                                <i class="fa fa-trash-o fa-fw"></i>
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
<@addOrUpdateMOdal defaultTitle="添加社会化登录配置">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="platformName">平台名 <span class="required">*</span></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" id="platformName" name="platformName" required="required" placeholder="请输入平台名"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="platformName">平台标识 <span class="required">*</span></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <select name="platform" id="platform" class="form-control" required="required">
                <option value="">请选择</option>
                <@zhydTag method="justAuthSources">
                    <#if justAuthSources?? && (justAuthSources?size > 0)>
                        <#list justAuthSources as item>
                            <option value="${item}">${item}</option>
                        </#list>
                    </#if>
                </@zhydTag>
            </select>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="clientId">clientId <span class="required">*</span></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="clientId" id="clientId" required="required" placeholder="请输入clientId"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="clientSecret">clientSecret <span class="required">*</span></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="clientSecret" id="clientSecret" required="required" placeholder="请输入clientSecret"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="redirectUri">redirectUri <i class="fa fa-question-circle" title="回调地址，选择平台标识后会自动填充，注意域名是否正确"></i><span class="required">*</span></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" id="redirectUri" name="redirectUri" placeholder="请输入redirectUri， 应为：${config.siteUrl}/oauth/social/{平台标识}" required="required"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="scope">授权范围（半角逗号分隔）<i class="fa fa-question-circle" title="如果不填，将使用默认值"></i></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" id="scope" name="scope" placeholder="请输入授权范围，使用半角逗号分隔"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="logo">平台 Logo  <i class="fa fa-question-circle" title="回调地址，选择平台标识后会自动填充，参考 https://github.com/justauth/justauth-oauth-logo，请注意版本"></i></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" id="logo" name="logo" placeholder="请输入 logo，如：https://cdn.jsdelivr.net/gh/justauth/justauth-oauth-logo@1.10/github.png"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="alipayPublicKey">阿里云公钥  <i class="fa fa-question-circle" title="阿里云平台必填"></i></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" id="alipayPublicKey" name="alipayPublicKey" placeholder="请输入阿里云公钥"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="stackOverflowKey">Stack Overflow Key <i class="fa fa-question-circle" title="Stack Overflow 平台必填"></i></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" id="stackOverflowKey" name="stackOverflowKey" placeholder="请输入 Stack Overflow Key"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="agentId">agentId <i class="fa fa-question-circle" title="部分平台可用，比如当选择企业微信时，该值标识授权方的网页应用ID"></i></label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" id="agentId" name="agentId" placeholder="请输入 agentId"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="status">是否需要申请unionid <i class="fa fa-question-circle" title="只针对 QQ 平台"></i></label>
        <div class="col-md-7 col-sm-7 col-xs-7 fixed-radio-checkbox">
            <ul class="list-unstyled list-inline">
                <li>
                    <div class="radio">
                        <label>
                            <input type="radio" class="flat" name="unionId" value="1"> 是
                        </label>
                    </div>
                </li>
                <li>
                    <div class="radio">
                        <label>
                            <input type="radio" class="flat" name="unionId" value="0" checked> 否
                        </label>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="status">状态 <i class="fa fa-question-circle" title="当禁用时，web 端将不再显示"></i></label>
        <div class="col-md-7 col-sm-7 col-xs-7 fixed-radio-checkbox">
            <ul class="list-unstyled list-inline">
                <li>
                    <div class="radio">
                        <label>
                            <input type="radio" class="flat" name="available" value="1" checked> 启用
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
</@addOrUpdateMOdal>
<@footer>
    <script>

        $("#platform").change(function (){
            var $this = $(this);
            var val = $this.val();
            var webUrl = '${config.siteUrl}';
            $("#redirectUri").val(webUrl + "/oauth/social/" + val);
            $("#logo").val("https://cdn.jsdelivr.net/gh/justauth/justauth-oauth-logo@1.10/" + val.toLowerCase() + ".png");
        })
        function operateFormatter(code, row, index) {
            var trId = row.id;
            var operateBtn = [
                '<@shiro.hasPermission name="link:edit"><a class="btn btn-sm btn-success btn-update" data-id="' + trId + '"title="编辑"><i class="fa fa-edit fa-fw"></i></a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="link:delete"><a class="btn btn-sm btn-danger btn-remove" data-id="' + trId + '"title="删除"><i class="fa fa-trash-o fa-fw"></i></a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                modalName: "社会化登录配置",
                url: "/social/config/list",
                getInfoUrl: "/social/config/get/{id}",
                updateUrl: "/social/config/edit",
                removeUrl: "/social/config/remove",
                createUrl: "/social/config/add",
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'platformName',
                        title: '平台名',
                        width: '150px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'platform',
                        title: '平台标识',
                        width: '100px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'scope',
                        title: '授权范围',
                        width: '250px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'clientId',
                        title: 'clientId',
                        width: '100px',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    }, {
                        field: 'logo',
                        title: 'logo',
                        width: '40px',
                        formatter: function (code) {
                            return !code ? '' : '<img src="'+code+'" width="20">';
                        }
                    }, {
                        field: 'available',
                        title: '状态',
                        width: '40px',
                        formatter: function (code, row, index) {
                            return code ? "启用" : "<strong style='color: red;'>禁用</strong>";
                        }
                    }, {
                        field: 'operate',
                        title: '操作',
                        align: "center",
                        width: '80px',
                        formatter: operateFormatter //自定义方法，添加操作按钮
                    }
                ],
                rowStyle: function (row, index) {
                    var strclass = "";
                    if (!row.available) {
                        strclass = 'danger';
                    }
                    return { 'classes': strclass }
                }
            };
            // 初始化table组件
            var table = new Table(options);
            table.init();
        });
    </script>
</@footer>
