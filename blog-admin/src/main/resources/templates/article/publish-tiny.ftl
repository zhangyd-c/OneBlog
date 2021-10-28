<#include "/include/macros.ftl">
<@header>
</@header>
    <div class="clearfix"></div>
    <form id="publishForm" class="form-horizontal form-label-left" novalidate>
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <@breadcrumb>
                    <ol class="breadcrumb">
                        <li><a href="/">首页</a></li>
                        <li><a href="/articles">文章列表</a></li>
                        <li class="active">发布文章-<a href="https://www.tiny.cloud/" target="_blank">TinyMCE 编辑器</a></li>
                    </ol>
                </@breadcrumb>
                <div class="x_panel">
                    <div class="x_title">
                        <h2>发布文章 <small>可以通过右上角“系统配置”-“文章编辑器”选择默认的文章发布编辑器</small></h2>
                        <#if !id??>
                            <ul class="nav navbar-right">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a class="changeEditor" data-href="/article/publish-md">Markdown 编辑器</a></li>
                                        <li><a class="changeEditor" data-href="/article/publish-we">WangEditor 编辑器</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </#if>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <input type="hidden" name="id">
                        <input type="hidden" name="isMarkdown" value="0">
                        <input type="hidden" name="editorType" value="tiny">
                        <div class="item form-group">
                            <label class="control-label col-md-1 col-sm-1 col-xs-1" for="title">标题 <span class="required">*</span></label>
                            <div class="col-md-8 col-sm-8 col-xs-8">
                                <input type="text" class="form-control col-md-7 col-xs-12" name="title" id="title" required="required" placeholder="请输入标题"/>
                            </div>
                            <div class="col-md-1 col-sm-1 col-xs-1">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" class="square" checked name="original"> 原创
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-1 col-sm-1 col-xs-12" for="content">内容 <span class="required">*</span></label>
                            <div class="col-md-10 col-sm-10 col-xs-10">
                                <div id="tinyEditor"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-1 col-sm-1 col-xs-12"></label>
                            <div class="col-md-10 col-sm-10 col-xs-12">
                                <button type="button" class="btn btn-success to-choose-info"><i class="fa fa-pencil"> 发布文章</i></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <@publishModal></@publishModal>
    </form>
</div>
<@chooseImgModal></@chooseImgModal>
<@footer>
    <script>
        zhyd.tinymce.init({
            selector: "#tinyEditor",
            uploadUrl: "/api/uploadFile",
            uploadFileName: "file",
            textareaName: "content",
        })
        articleId = '${id}';
        editorType = 'tiny';
    </script>
    <script src="/assets/js/zhyd.publish-article.js"></script>
</@footer>
