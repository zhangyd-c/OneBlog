<#include "/include/macros.ftl">
<#setting number_format="#">
<@header>
    <link href="https://cdn.jsdelivr.net/npm/simplemde@1.11.2/dist/simplemde.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/github-markdown-css@2.10.0/github-markdown.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/highlight.js@9.12.0/styles/github.min.css" rel="stylesheet">
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
    <form id="publishForm" class="form-horizontal form-label-left" novalidate>
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <@breadcrumb>
                    <ol class="breadcrumb">
                        <li><a href="/">首页</a></li>
                        <li><a href="/articles">文章列表</a></li>
                        <li class="active">发布文章-Markdown编辑器</li>
                    </ol>
                </@breadcrumb>
                <div class="x_panel">
                    <div class="x_title">
                        <h2>发布文章 <small>可以通过右上角“系统配置”-“文章编辑器”选择默认的文章发布编辑器</small></h2>
                        <#if !id??>
                        <div class="pull-right"><small>切换到 <a class="pointer" id="changeEditor" data-href="/article/publish">wangEditor编辑器</a></small></div>
                        </#if>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <input type="hidden" name="id">
                        <input type="hidden" name="isMarkdown" value="1">
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
                            <label class="control-label col-md-1 col-sm-1 col-xs-12" for="password">内容 <span class="required">*</span></label>
                            <div class="col-md-10 col-sm-10 col-xs-10">
                                <textarea class="form-control col-md-7 col-xs-12" id="content" name="content" style="display: none" required="required"></textarea>
                                <textarea class="form-control col-md-7 col-xs-12 valid" id="contentMd" name="contentMd" style="display: none" required="required"></textarea>
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
        <@publishmodal></@publishmodal>
    </form>
</div>
<@chooseImgModal></@chooseImgModal>
<@footer>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/highlight.js@9.12.0/lib/highlight.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/simplemde@1.11.2/dist/simplemde.min.js"></script>
    <script type="text/javascript" src="/assets/js/inline-attachment.js"></script>
    <script type="text/javascript" src="/assets/js/codemirror.inline-attachment.js"></script>
    <script>
        var op = {
            id: "contentMd",
            uniqueId: "mdEditor_1",
            uploadUrl: "/api/uploadFileForMd"
        };
        zhyd.simpleMDE.init(op);
        articleId = '${id}';
    </script>
    <script src="/assets/js/zhyd.publish-article.js"></script>
</@footer>
