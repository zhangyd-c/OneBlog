<#-- 公共顶部 -->
<#macro header sidebar=true setting=true>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${config.siteName}后台管理系统</title>
    <link href="/assets/images/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.3/css/toastr.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/square/green.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-daterangepicker/2.1.24/daterangepicker.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/zTree.v3/3.5.29/css/metroStyle/metroStyle.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/switchery/0.8.2/switchery.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput-typeahead.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput.css" rel="stylesheet">
<#--
    <link href="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.buttons.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.nonblock.css" rel="stylesheet">-->

    <link href="/assets/css/zhyd.core.css" rel="stylesheet">
    <#nested>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
    <#if sidebar>
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="/" class="site_title"><i class="fa fa-coffee"></i> <span>${config.siteName}</span></a>
                </div>
                <div class="clearfix"></div>
                <@shiro.user>
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="/assets/images/loading.gif" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span id="hello_msg">&nbsp;</span>
                        <h2>尊敬的管理员</h2>
                    </div>
                </div>
                </@shiro.user>
                <br />
                <#include "/layout/sidebar.ftl"/>
            </div>
        </div>
    </#if>
        <#if setting>
        <#include "/layout/setting.ftl"/>
        </#if>

        <div class="right_col" role="main" style="${sidebar?string('','margin-left: 0;')}">
</#macro>

<#-- 公共底部 -->
<#macro footer footerHtml=true>
    <#if footerHtml>
        <footer>
            <div class="pull-right">
                Copyright © 2018 <a href="https://www.zhyd.me" target="_blank">yadong.zhang</a> · Powered by <a href="https://gitee.com/yadong.zhang/DBlog" title="OneBlog是一款简洁美观、自适应的Java博客系统..." target="_blank"><strong>OneBlog</strong></a>. All Rights Reserved.
            </div>
            <div class="clearfix"></div>
        </footer>
    </#if>
    </div>
    </div>
    <#include "/layout/footer.ftl"/>

    <#nested>

    </body>
</html>
</#macro>

<#-- 面包屑导航内容 + 系统通知 -->
<#macro breadcrumb>
    <div class="row">
        <div class="col col-md-8">
            <nav class="breadcrumb">
                <div class="notify"><i class="fa fa-bullhorn fa-fw"></i></div>
                <div id="scrolldiv">
                    <div class="scrolltext">
                        <ul class="list-unstyled" id="notice-box">
                            <li class="scrolltext-title">
                                <a href="javascript:void(0)" rel="bookmark">其实我们可以将所有的问题归结为两种：一种是没饭吃饿出来的；一种是吃饱了撑出来的。</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="col col-md-4 text-right">
            <#nested>
        </div>
    </div>
</#macro>

<#-- 发布文章填写文章详情的弹窗模板 -->
<#macro publishmodal>
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

                                                    <button type="button" class="btn btn-round btn-info" id="file-upload-btn" style="margin-top: 10px;">上传封面图片</button>
                                                    <div class="tip" style="margin-top: 10px;color: #c3c3c3;">
                                                        <div class="clearfix"></div>
                                                        <small style="font-size: 12px;">图片不宜过大</small>
                                                        <div class="clearfix"></div>
                                                        <small style="font-size: 12px;">建议尺寸 1190*300</small>
                                                    </div>
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
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-angle-left"> 返回修改</i></button>
                    <button type="button" class="btn btn-success publishBtn"><i class="fa fa-paper-plane-o"> 确定发布</i></button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#-- 发布文章时选择图片的弹窗模板 -->
<#macro chooseImgModal>
    <div class="modal fade chooseImgModal" id="chooseImgModal" tabindex="-1" role="dialog" aria-labelledby="chooseImgLabelledby" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog <#--modal-lg-->" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="chooseImgLabelledby"><i class="fa fa-image fa-fw"></i>素材库</h4>
            </div>
            <div class="modal-body material-body">
                <div class="btn-group" style="width: 100%;margin: 0 5px 5px 5px;padding: 0 0 10px 0;border-bottom: 1px solid #e7e7eb;">
                    <form action="" id="materialForm">
                        <input id="input-material-upload" type="file" name="file" multiple="multiple" accept="image/bmp,image/png,image/jpeg,image/jpg,image/gif" style="display: none;">
                        <button id="btn-material-upload" type="button" class="btn btn-success btn-md" title="本地上传">
                            <i class="fa fa-cloud-upload fa-fw"></i> 本地上传
                        </button>
                    </form>
                </div>
                <div class="fade active in material-box">
                    <ul class="list-unstyled list-file">
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <span class="material-status pull-left">已选<span id="selected">0</span>个，可选<span id="selectable">1</span>个</span>
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
                <button type="button" class="btn btn-success btn-confirm" data-dismiss="modal"><i class="fa fa-hand-o-up"> 确定</i></button>
            </div>
        </div>
    </div>
</div>
</#macro>

<#-- 添加或者修改列表记录时的弹窗模板 -->
<#macro addOrUpdateMOdal defaultTitle="">
    <div class="modal fade" id="addOrUpdateModal" tabindex="-1" role="dialog" aria-labelledby="addroleLabel" data-backdrop="static">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addroleLabel">${defaultTitle}</h4>
            </div>
            <div class="modal-body">
                <form id="addOrUpdateForm" class="form-horizontal form-label-left" novalidate>
                    <#nested>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
                <button type="button" class="btn btn-success addOrUpdateBtn"><i class="fa fa-save"> 保存</i></button>
            </div>
        </div>
    </div>
</div>
</#macro>