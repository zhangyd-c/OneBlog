<#include "/include/macros.ftl">
<@header>

</@header>
<form id="publishForm" class="form-horizontal form-label-left" novalidate onkeydown="if(event.keyCode==13){return false;}">
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <@breadcrumb>
                <ol class="breadcrumb">
                    <li><a href="/">首页</a></li>
                    <li><a href="/articles">文章列表</a></li>
                    <li class="active">发布文章-wangEditor编辑器</li>
                </ol>
            </@breadcrumb>
            <div class="x_panel">
                <div class="x_title">
                    <h2>发布文章 <small>wangEditor编辑器，使用 <a href="/article/publishMd">Markdown编辑器</a></small></h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <input type="hidden" name="isMarkdown" value="0">
                    <input type="hidden" name="id">
                    <div class="item form-group">
                        <label class="control-label col-md-1 col-sm-1 col-xs-12" for="title">标题 <span class="required">*</span></label>
                        <div class="col-md-8 col-sm-8 col-xs-12">
                            <input type="text" class="form-control col-md-7 col-xs-12" name="title" id="title" required="required" placeholder="请输入标题"/>
                        </div>
                        <div class="col-md-1 col-sm-1 col-xs-12">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" class="square" name="original"> 原创
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-1 col-sm-1 col-xs-12" for="password">内容 <span class="required">*</span></label>
                        <div class="col-md-11 col-sm-11 col-xs-12">
                            <div id="editor" style="width: 100%;height: 150px;"></div>
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
    <div class="modal fade" id="publishModal" tabindex="-1" role="dialog" aria-labelledby="publishLabel" data-backdrop="static">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#article" id="article-tab" role="tab" data-toggle="tab" aria-expanded="true">文章属性</a>
                            </li>
                            <li role="presentation" class="">
                                <a href="#seo" role="tab" id="seo-tab" data-toggle="tab" aria-expanded="false">SEO</a>
                            </li>
                        </ul>
                        <div id="" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade active in" id="article" aria-labelledby="article-tab">
                                <div class="row">
                                    <div class="col col-md-3">
                                        <div class="item form-group">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div class="choose-local-img" style="text-align: center;">
                                                    <input id="cover-img-file" type="file" name="file" style="display: none">
                                                    <input id="cover-img-input" type="hidden" name="coverImage">
                                                    <div class="preview fa-2x" style="width: 100%;height: 186.98px;background: #f8fafc;border-radius: 5px;text-align: center;">
                                                        <img class="coverImage" src="" alt="">
                                                    </div>

                                                    <button type="button" class="btn btn-round btn-info" id="file-upload-btn" style="margin-top: 10px;">上传图片</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col col-md-9">
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="nickname">分类 <span class="required">*</span></label>
                                            <div class="col-md-8 col-sm-8 col-xs-12">
                                                <div class="input-group">
                                                    <select class="form-control" name="typeId" id="typeId"  target="combox" data-url="/type/listAll" data-method="post" required="required"></select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="mobile">标签(*3) <span class="required">*</span></label>
                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                <input type="text" name="tags" target="tagsinput" data-bind-box="#tags-list">
                                                <ul class="list-unstyled list-inline tags-list" id="tags-list" target="combox" data-url="/tag/listAll" data-method="post" style="max-height: 150px;overflow-y: scroll;"></ul>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="mobile">状态 <span class="required">*</span></label>
                                            <div class="col-md-10 col-sm-10 col-xs-12 fixed-radio-checkbox">
                                                <ul class="list-unstyled list-inline">
                                                    <li><input type="radio" class="square" checked name="status" value="1"> 发布</li>
                                                    <li><input type="radio" class="square" name="status" value="0"> 草稿</li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="comment">开启评论 </label>
                                            <div class="col-md-10 col-sm-10 col-xs-12 fixed-radio-checkbox">
                                                <input type="checkbox" class="square" name="comment" id="comment">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="seo" aria-labelledby="seo-tab">
                                <div class="item form-group">
                                    <label class="control-label col-md-2 col-sm-2 col-xs-12" for="password">摘要 <span class="required">*</span></label>
                                    <div class="col-md-8 col-sm-8 col-xs-12">
                                        <textarea class="form-control col-md-7 col-xs-12" id="description" name="description" required="required"></textarea>
                                    </div>
                                </div>
                                <div class="item form-group">
                                    <label class="control-label col-md-2 col-sm-2 col-xs-12" for="password">关键词 <span class="required">*</span></label>
                                    <div class="col-md-8 col-sm-8 col-xs-12">
                                        <textarea class="form-control col-md-7 col-xs-12" id="keywords" name="keywords" required="required"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 返回修改</i></button>
                    <button type="button" class="btn btn-success publishBtn"><i class="fa fa-hand-o-up"> 确定发布</i></button>
                </div>
            </div>
        </div>
    </div>
</form>
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
<@footer>
    <script>
        articleId = '${id}';
        $(function () {
            zhyd.wangEditor.init({
                container: "#editor",
                textareaName: "content",
                uploadUrl: "/api/upload2Qiniu",
                uploadFileName: "file",
                uploadType: "article",
                customCss: {
                    "height": "100%",
                    "max-height": "115px"
                }
            })
        });
    </script>
    <script src="/assets/js/zhyd.publish-article.js"></script>
</@footer>
