<#include "/include/macros.ftl">
<@header>
    <link href="https://cdn.bootcss.com/simplemde/1.11.2/simplemde.min.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/github-markdown-css/2.10.0/github-markdown.min.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/highlight.js/9.12.0/styles/github.min.css" rel="stylesheet">
    <style>
        .CodeMirror, .CodeMirror-scroll {
            min-height: 130px;
            max-height: 200px;
        }
        .CodeMirror .cm-spell-error:not(.cm-url):not(.cm-comment):not(.cm-tag):not(.cm-word) {
            background: none;
        }
    </style>
</@header>
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li><a href="/articles">文章列表</a></li>
                <li class="active">发布文章-Markdown编辑器</li>
            </ol>
            <div class="x_panel">
                <div class="x_title">
                    <h2>发布文章 <small>Markdown编辑器，使用 <a href="/article/publish">HTML编辑器</a></small></h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div id="wizard" class="form_wizard wizard_horizontal">
                        <ul class="wizard_steps">
                            <li>
                                <a href="#step-1">
                                    <span class="step_no">1</span>
                                    <span class="step_descr"> <small><i class="fa fa-pencil"></i> 写文章</small> </span>
                                </a>
                            </li>
                            <li>
                                <a href="#step-2">
                                    <span class="step_no">2</span>
                                    <span class="step_descr"> <small><i class="fa fa-info"></i> 完善配置</small> </span>
                                </a>
                            </li>
                            <li>
                                <a href="#step-3">
                                    <span class="step_no">3</span>
                                    <span class="step_descr"> <small><i class="fa fa-paw"></i> 设置SEO相关</small> </span>
                                </a>
                            </li>
                            <li>
                                <a href="#step-4">
                                    <span class="step_no">4</span>
                                    <span class="step_descr"> <small><i class="fa fa-paper-plane"></i> 发布</small> </span>
                                </a>
                            </li>
                        </ul>
                        <form id="publishForm" class="form-horizontal form-label-left" novalidate>
                            <input type="hidden" name="id">
                            <input type="hidden" name="isMarkdown" value="1">
                            <div id="step-1">
                                <div class="ln_solid"></div>
                                <div class="custom-panel">
                                    <div class="custom-container">
                                        <div class="item form-group">
                                            <label class="control-label col-md-1 col-sm-1 col-xs-1" for="title"></label>
                                            <div class="col-md-9 col-sm-9 col-xs-9">
                                                <input type="text" class="form-control col-md-7 col-xs-12" name="title" id="title" required="required" placeholder="请输入标题"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-1 col-sm-1 col-xs-1" for="contentMd"></label>
                                            <div class="col-md-10 col-sm-10 col-xs-10">
                                                <textarea class="form-control col-md-7 col-xs-12" id="content" name="content" style="display: none" required="required"></textarea>
                                                <textarea class="form-control col-md-7 col-xs-12 valid" id="contentMd" name="contentMd" style="display: none" required="required"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="step-2">
                                <div class="ln_solid"></div>
                                <div class="custom-panel">
                                    <div class="custom-container">
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-2" for="title">文章类型 <span class="required">*</span></label>
                                            <div class="col-md-2 col-sm-2 col-xs-2">
                                                <select class="form-control" name="original" required="required" id="original" style="width: 120px;">
                                                    <option value="1">原创</option>
                                                    <option value="0">转载</option>
                                                </select>
                                            </div>
                                            <div class="col-md-3 col-sm-3 col-xs-3">
                                                <select class="form-control" name="typeId" required="required" id="typeId" style="width: 120px;"></select>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-2" for="title">文章封面 <span class="required">*</span></label>
                                            <div class="col-md-3 col-sm-3 col-xs-3">
                                                <div class="choose-local-img">
                                                    <button type="button" class="btn btn-success" id="file-upload-btn">上传图片</button>
                                                    <input id="cover-img-file" type="file" name="file" required="required" style="display: none">
                                                    <input id="cover-img-input" type="hidden" name="coverImage">
                                                    <div class="preview">
                                                        <img class="coverImage" src="" alt="">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-2" for="mobile">文章标签 <span class="required">*</span></label>
                                            <div class="col-md-10 col-sm-10 col-xs-10"  >
                                                <div class="h5"><i class="fa fa-plus-square showContent pointer"> 点击查看</i>  <i class="fa fa-refresh fa-1x pointer" id="refressTag"> 刷新</i></div>
                                                <div class="disable-content" style="display: none;">
                                                    <ul class="list-unstyled list-inline" id="tag-list"></ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-2" for="status">文章状态 <span class="required">*</span></label>
                                            <div class="col-md-10 col-sm-10 col-xs-10">
                                                <ul class="list-unstyled list-inline">
                                                    <li>
                                                        <input type="radio" class="square" checked name="status" value="1"> 发布
                                                    </li>
                                                    <li>
                                                        <input type="radio" class="square" name="status" value="0"> 草稿
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-2" for="comment">开启评论 <span class="required">*</span></label>
                                            <div class="col-md-10 col-sm-10 col-xs-10">
                                                <input type="checkbox" class="square" name="comment" id="comment">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="step-3">
                                <div class="ln_solid"></div>
                                <div class="custom-panel">
                                    <div class="custom-container">
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-2" for="password">摘要 <span class="required">*</span></label>
                                            <div class="col-md-9 col-sm-9 col-xs-9">
                                                <textarea class="form-control col-md-7 col-xs-12" id="description" name="description" required="required"></textarea>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-2" for="password">关键词 <span class="required">*</span></label>
                                            <div class="col-md-9 col-sm-9 col-xs-9">
                                                <textarea class="form-control col-md-7 col-xs-12" id="keywords" name="keywords" required="required"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="step-4">
                                <div class="ln_solid"></div>
                                <div class="custom-panel">
                                    <div class="custom-container" style="text-align: center">
                                        点击【发布文章】按钮
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--上传图片弹框-->
<div class="modal fade" id="chooseImg" tabindex="-1" role="dialog" aria-labelledby="addroleLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addroleLabel">选择图片</h4>
            </div>
            <div class="modal-body">
                <div class="" role="tabpanel" data-example-id="togglable-tabs">
                    <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">素材库</a>
                        </li>
                        <li role="presentation" class="">
                            <a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">本地图片</a>
                        </li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div role="tabpanel" class="tab-pane fade active in material-box" id="tab_content1" aria-labelledby="home-tab">
                            <ul class="list-unstyled list-material">
                            </ul>
                        </div>
                        <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
                            <button type="button" class="btn btn-success" id="file-btn">选择图片</button>
                            <div class="preview" class="fa-2x">
                                <img class="coverImage" src="" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
                <button type="button" class="btn btn-success" data-dismiss="modal"><i class="fa fa-hand-o-up"> 确定</i></button>
            </div>
        </div>
    </div>
</div>
<!--上传图片弹框-->
<@footer>

	<script type="text/javascript" src="https://cdn.bootcss.com/highlight.js/9.12.0/highlight.min.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/simplemde/1.11.2/simplemde.min.js"></script>
    <script type="text/javascript" src="/assets/js/inline-attachment.js"></script>
    <script type="text/javascript" src="/assets/js/codemirror.inline-attachment.js"></script>
    <script src="/assets/js/jquery.smartWizard.js"></script>
    <script>
        var op = {
            id: "contentMd",
            uniqueId: "mdEditor_1",
            uploadUrl: "/api/upload2QiniuForMd"
        };
        zhyd.initMdEditor(op);
        articleId = '${id}';
    </script>
    <script src="/assets/js/zhyd.publish-article.js"></script>
    <script>
        console.log('init_SmartWizard');

        $('#wizard').smartWizard({
            labelNext:'<i class="fa fa-angle-right"></i> 下一步',
            labelPrevious:'<i class="fa fa-angle-left"></i> 上一步',
            labelFinish:'<i class="fa fa-pencil"></i> 发布文章',
            // Events
            onLeaveStep: leaveAStepCallback,
            onFinish: onFinishCallback,
            buttonOrder: ['next', 'prev', 'finish']
        });

        $('.buttonNext').addClass('btn btn-info');
        $('.buttonPrevious').addClass('btn btn-primary');
        $('.buttonFinish').addClass('btn btn-success');

        function leaveAStepCallback(obj, context){
            var from = context.fromStep;
            var to = context.toStep;
            if(from < to){
                return validateSteps(from); // return false to stay on step and true to continue navigation
            }
            return true;
        }

        function onFinishCallback(objs, context){
            if(validateAllSteps(context.fromStep)){
                $("#publishForm").ajaxSubmit({
                    type: "post",
                    url: "/article/save",
                    success: function (json) {
                        $.tool.delCache("smde_" + op.uniqueId);
                        $.alert.ajaxSuccessConfirm(json, function () {
                            window.location.href = '/articles';
                        }, function () {
                            window.location.href = '/articles';
                        });
                    },
                    error: $.alert.ajaxError
                });
            }
        }

        function validateSteps(stepNum){
            if(stepNum == 1){
                $("#contentMd").val(simplemde.value());
                $("#content").val(simplemde.markdown(simplemde.value()));
            }
            var $step = $("#step-" + stepNum);
            return validator.checkAll($step);
        }

        function validateAllSteps(stepnumber){
            for(var i = 1; i <= stepnumber; i ++){
                if(!validateSteps(i)){
                    $.alert.error("第" + i + "部分未完成校验！");
                    return false;
                }
            }
            return true;
        }
    </script>
</@footer>