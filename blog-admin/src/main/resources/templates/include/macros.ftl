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
    <link href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/jquery-confirm@3.3.2/dist/jquery-confirm.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/jquery.fancybox@2.1.5/source/jquery.fancybox.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/toastr@2.0.3/nuget/content/content/toastr.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/icheck@1.0.2/skins/square/green.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-table@1.11.1/dist/bootstrap-table.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/daterangepicker@2.1.25/daterangepicker.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-datetimepicker-npm@4.17.37-npm/build/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@ztree/ztree_v3@3.5.37/css/metroStyle/metroStyle.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/switchery@0.0.2/switchery.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@adactive/bootstrap-tagsinput@0.8.2/dist/bootstrap-tagsinput-typeahead.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@adactive/bootstrap-tagsinput@0.8.2/dist/bootstrap-tagsinput.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/switchery-npm@0.8.2/index.min.css" rel="stylesheet">
<#--
    <link href="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.buttons.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.nonblock.css" rel="stylesheet">-->

    <link href="/assets/css/bootstrap-treetable.css" rel="stylesheet" type="text/css" />
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
                Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
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
                                        <textarea class="form-control col-md-7 col-xs-12" id="description" name="description" required="required" maxlength="200"></textarea>
                                    </div>
                                </div>
                                <div class="item form-group">
                                    <label class="control-label col-md-2 col-sm-2 col-xs-12" for="password">关键词 <span class="required">*</span></label>
                                    <div class="col-md-8 col-sm-8 col-xs-12">
                                        <textarea class="form-control col-md-7 col-xs-12" id="keywords" name="keywords" required="required" maxlength="50"></textarea>
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

<#-- 网站首页的项目介绍内容 -->
<#macro aboutOneBlog>
    <div class="modal fade" id="noticeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog modal-lg" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">关于OneBlog - 一个简洁美观、功能强大并且自适应的Java博客</h4>
            </div>
            <div class="modal-body notice-box">
                <div class="row">
                    <div class="col col-lg-4 col-sm-4 col-md-4 col-xs-4">
                        <fieldset>
                            <legend>关注公众号</legend>
                            <a href="https://gitee.com/yadong.zhang/static/raw/master/wx/wechat_account_500x500.jpg" class="showImage" title="关注公众号：码一码" rel="external nofollow" style="display: block;text-align: center">
                                <img src="https://gitee.com/yadong.zhang/static/raw/master/wx/wechat_account_500x500.jpg" class="img-rounded" alt="关注公众号：码一码" width="300">
                            </a>
                        </fieldset>
                    </div>
                    <div class="col col-lg-4 col-sm-4 col-md-4 col-xs-4">
                        <fieldset>
                            <legend>关于OneBlog</legend>

                            <h2>项目相关</h2>
                            <ul>
                                <li>开源项目地址①：<a href="https://gitee.com/yadong.zhang/DBlog" target="_blank">https://gitee.com/yadong.zhang/DBlog</a></li>
                                <li>开源项目地址②：<a href="https://github.com/zhangyd-c/OneBlog" target="_blank">https://github.com/zhangyd-c/OneBlog</a></li>
                                <li>博主网站首页：<a href="https://www.zhyd.me" target="_blank">https://www.zhyd.me</a></li>
                            </ul>
                            <h2>Demo演示</h2>
                            <ul>
                                <li><a href="http://dblog-admin.zhyd.me" target="_blank">后台</a>：用户名：root，密码：123456</li>
                                <li><a href="http://dblog-web.zhyd.me" target="_blank">前台</a></li>
                            </ul>
                            <h2>获取帮助</h2>
                            <ul>
                                <li>相关Wiki：<a href="https://gitee.com/yadong.zhang/DBlog/wikis" target="_blank">https://gitee.com/yadong.zhang/DBlog/wikis</a></li>
                                <li>提issue：<a href="https://gitee.com/yadong.zhang/DBlog/issues" target="_blank">https://gitee.com/yadong.zhang/DBlog/issues</a></li>
                                <li>留言：<a href="https://www.zhyd.me/guestbook" target="_blank">https://www.zhyd.me/guestbook</a></li>
                                <li>加QQ群：<a href="http://shang.qq.com/wpa/qunwpa?idkey=9f986e9b33b1de953e1ef9a96cdeec990affd0ac7855e00ff103514de2027b60" target="_blank">190886500</a></li>
                            </ul>
                            <h2>其他开源作品</h2>
                            <ul>
                                <li><a href="https://gitee.com/yadong.zhang/JustAuth" target="_blank">JustAuth</a>：史上最全的整合第三方登录的工具,目前已支持Github、Gitee、微博、钉钉、百度、Coding、腾讯云开发者平台、OSChina、支付宝、QQ、微信、淘宝、Google、Facebook、抖音、领英、小米、微软和今日头条等第三方平台的授权登录。 Login, so easy!</li>
                                <li><a href="https://gitee.com/yadong.zhang/blog-hunter" target="_blank">blog-hunter</a>：博客猎手，基于webMagic的博客爬取工具，支持慕课、csdn、iteye、cnblogs、掘金和V2EX等各大主流博客平台。博客千万篇，版权第一条。狩猎不规范，亲人两行泪。</li>
                                <li><a href="https://gitee.com/yadong.zhang/shiro" target="_blank">springboot-shiro</a>：Springboot + shiro权限管理。这或许是流程最详细、代码最干净、配置最简单的shiro上手项目了。</li>
                                <li><a href="https://gitee.com/yadong.zhang" target="_blank">查看更多...</a></li>
                            </ul>
                        </fieldset>
                    </div>
                    <div class="col col-lg-4 col-sm-4 col-md-4 col-xs-4">
                        <fieldset>
                            <legend>支持的功能</legend>
                            <ul>
                                <li><span><span><strong>多种编辑器</strong>：支持wangEditor和Markdown两种富文本编辑器，可以自行选择</span></span></li>
                                <li><span><span><strong>自动申请友情链接</strong>：在线申请友情链接，无需站长手动配置，只需申请方添加完站长的连接后自行申请即可</span></span></li>
                                <li><span><span><strong>百度推送</strong>：支持百度推送功能，加速百度搜索引擎收录博文</span></span></li>
                                <li><span><span><strong>评论系统</strong>：自研的评论系统，支持显示用户地址、浏览器和os信息，后台可审核评论、开启匿名评论、回复和邮件通知评论</span></span></li>
                                <li><span><span><strong>权限管理</strong>：后台配备完善的权限管理</span></span></li>
                                <li><span><span><strong>SEO</strong>：自带robots、sitemap等seo模板，实现自动生成robots和sitemap</span></span></li>
                                <li><span><span><strong>实时通讯</strong>：管理员可向在线的用户发送实时消息（需用户授权 - 基于websocket实现，具体参考<a href="https://www.zhyd.me/article/111">DBlog建站之Websocket的使用</a>）</span></span></li>
                                <li><span><span><strong>系统配置支持快速配置</strong>：可通过后台手动修改诸如域名信息、SEO优化、赞赏码、七牛云以及更新维护通知等</span></span></li>
                                <li><span><span><strong><i class="fa fa-fire fa-fw red"></i>多种文件存储</strong>：集成七牛云、阿里云OSS，实现文件云存储，同时支持本地文件存储</span></span></li>
                                <li><span><span><strong><i class="fa fa-fire fa-fw red"></i>文件搬运工</strong>：集成<a href="https://gitee.com/yadong.zhang/blog-hunter">blog-hunter</a>实现“文章搬运工”功能，支持一键同步imooc、csdn、iteye或者cnblogs上的文章，可抓取列表和单个文章</span></span></li>
                                <li><span><span><strong><i class="fa fa-fire fa-fw red"></i>第三方授权登录</strong>：集成<a href="https://gitee.com/yadong.zhang/JustAuth">JustAuth</a>实现第三方授权登录</span></span></li>
                            </ul>
                        </fieldset></div>
                </div>

            </div>
            <div class="modal-footer">
                <span class="pull-left">tips: 如不想显示该弹窗，可在 <code>index.ftl</code> 中搜索 <code>aboutOneBlog</code> 后删掉相关代码</span>
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
            </div>
        </div>
    </div>
</div>
</#macro>