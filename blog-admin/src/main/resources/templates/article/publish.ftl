<#include "/layout/header.ftl"/>
<div class="">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">发布文章</li>
            </ol>
            <div class="x_panel">
                <div class="x_title">
                    <h2>发布文章 <small></small></h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="publishForm" class="form-horizontal form-label-left" novalidate>
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
                            <label class="control-label col-md-1 col-sm-1 col-xs-12" for="title">封面 <span class="required">*</span></label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <div class="choose-local-img">
                                    <button type="button" class="btn btn-success" id="file-upload-btn">上传图片</button>
                                    <input id="cover-img-file" type="file" name="file" style="display: none">
                                    <input id="cover-img-input" type="hidden" name="coverImage">
                                    <div class="preview" class="fa-2x">
                                        <img class="coverImage" src="" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-1 col-sm-1 col-xs-12" for="password">内容 <span class="required">*</span></label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <div id="editor" style="width: 100%;height: 150px;"></div>
                                <textarea class="form-control col-md-7 col-xs-12" id="content" name="content" style="display: none" required="required"></textarea>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-1 col-sm-1 col-xs-12" for="nickname"><i class="fa fa-refresh fa-fw fa-1x pointer" id="refressType"></i>分类 <span class="required">*</span></label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <div class="input-group">
                                    <select class="form-control" name="typeId" id="typeId">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-1 col-sm-1 col-xs-12" for="mobile"><i class="fa fa-refresh fa-fw fa-1x pointer" id="refressTag"></i>标签 <span class="required">*</span></label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <ul class="list-unstyled list-inline" id="tag-list">
                                </ul>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-1 col-sm-1 col-xs-12" for="mobile">状态 <span class="required">*</span></label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <ul class="list-unstyled list-inline">
                                    <li>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="square" checked name="status" value="1"> 发布
                                            </label>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="square" name="status" value="0"> 草稿
                                            </label>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="custom-panel">
                            <h2 class="x_title custom-dropdown">其他录入项 <small> - 方便SEO收录</small> <i class="pull-right fa fa-angle-double-up"></i></h2>
                            <div class="custom-container">
                                <div class="item form-group">
                                    <label class="control-label col-md-1 col-sm-1 col-xs-12" for="password">摘要 <span class="required">*</span></label>
                                    <div class="col-md-10 col-sm-10 col-xs-12">
                                        <textarea class="form-control col-md-7 col-xs-12" id="description" name="description" required="required"></textarea>
                                    </div>
                                </div>
                                <div class="item form-group">
                                    <label class="control-label col-md-1 col-sm-1 col-xs-12" for="password">关键词 <span class="required">*</span></label>
                                    <div class="col-md-10 col-sm-10 col-xs-12">
                                        <textarea class="form-control col-md-7 col-xs-12" id="keywords" name="keywords" required="required"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-10 col-sm-10 col-xs-12">
                                <button type="button" class="btn btn-success publishBtn">保存</button>
                                <button type="reset" class="btn btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<#--<style type="text/css">
    .img-tool{
        width: 200px;
    }
    .img-tool tools{
        opacity: 1;
        transform: translateY(0);
        transition: all .2s ease-in-out;
        color: #fff;
        text-align: center;
        font-size: 17px;
        padding: 3px;
        background: rgba(0, 0, 0, .35);
        /* margin: 72px 0 0; */
        position: relative;
    }
    .img-tool tools a{
        display: inline-block;
        color: #FFF;
        font-size: 18px;
        font-weight: 400;
        padding: 0 4px;
    }
</style>-->
<#--<div>
    <img src="http://ofndwaoqp.bkt.clouddn.com/flyat%2Farticle%2F1516606560576.jpg-pw" alt="">
    <div class="img-tool">
        <div class="mask">
            <div class="tools tools-bottom">
                <a href="javascript:void(0)"><i class="fa fa-link"></i></a>
                <a href="javascript:void(0)"<i class="fa fa-pencil"></i></a>
                <a href="javascript:void(0)"><i class="fa fa-times"></i></a>
            </div>
        </div>
    </div>
</div>-->
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
<!--上传图片弹框-->
<#include "/layout/footer.ftl"/>
<script>
    $(function () {
        setTimeout(function () {
            $('.network-img-checkbox').on('ifChanged', function(event){
                $(".choose-network-img").toggleClass("hide");
                $(".choose-local-img").toggleClass("hide");
            });
        },1000);

        var E = window.wangEditor;
        var editor = new E('#editor');
        // debug模式下，有 JS 错误会以throw Error方式提示出来
        editor.customConfig.debug = true;
        // 关闭粘贴样式的过滤
        editor.customConfig.pasteFilterStyle = false;
        // 插入网络图片的回调
        editor.customConfig.linkImgCallback = function(url) {
            console.log(url) // url 即插入图片的地址
        };
        editor.customConfig.zIndex = 100;

        var $content = $('#content');
        editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            $content.val(filterXSS(html))
            console.log(html);
        };

        // 下面两个配置，使用其中一个即可显示“上传图片”的tab。但是两者不要同时使用！！！
        // editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
        // 上传图片到服务器
        editor.customConfig.uploadImgServer = '/api/upload2Qiniu';
        editor.customConfig.uploadFileName = 'file';
        // 将图片大小限制为 5M
        editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
        editor.customConfig.customAlert = function (info) {
            // info 是需要提示的内容
            $.tool.alertError(info);
        }
        editor.customConfig.uploadImgHooks = {
            error: function (xhr, editor) {
                $.tool.alertError("图片上传出错");
            },
            timeout: function (xhr, editor) {
                $.tool.alertError("请求超时");
            },
            customInsert: function (insertImg, result, editor) {
                // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
                console.log('customInsert：' + insertImg, result, editor);
                if(result.status == 200){
                    console.log(result.data);
                    var imgFullPath = appConfig.qiniuPath + result.data + appConfig.qiniuImgStyle;
                    // editor.txt.append(' <a href="' + imgFullPath + '" class="showImage" title="" rel="external nofollow"><img src="' + imgFullPath + '" class="img-responsive  img-rounded" alt="" style="width: 95%;"/></a>');
                    editor.txt.append('<img src="' + imgFullPath + '" alt="" style="width: 95%;max-width: 100%;height: auto;border-radius: 6px;"/>');
                } else {
                    $.tool.alertError(result.message);
                }
            }
        };

        editor.create();
        E.fullscreen.init('#editor');

        // 加载所有分类
        function loadType(){
            $.ajax({
                type: "post",
                url: "/type/listAll",
                success: function (json) {
                    $.tool.ajaxSuccess(json);
                    var data = '';
                    if(data = json.data){
                        var typeOptions = '';
                        for(var i = 0, len = data.length; i < len; i ++){
                            var type = data[i];
                            typeOptions += '<option value="' + type.id + '">' + type.name + '</option>';
                        }
                        $("select#typeId").html(typeOptions);
                    }
                    $("#refressType").removeClass("fa-spin");
                    $.tool.showSuccessMessage("分类加载完成！");
                },
                error: $.tool.ajaxError
            });
        }

        // 加载所有标签
        function loadTag() {
            $.ajax({
                type: "post",
                url: "/tag/listAll",
                success: function (json) {
                    $.tool.ajaxSuccess(json);
                    var data = '';
                    if (data = json.data) {
                        var tagHtml = '';
                        for (var i = 0, len = data.length; i < len; i++) {
                            var tag = data[i];
                            tagHtml += '<li>'
                                    + '  <div class="checkbox">'
                                    + '<label>'
                                    + '<input type="checkbox" class="square" name="tags" value="' + tag.id + '"> ' + tag.name
                                    + '       </label>'
                                    + '   </div>'
                                    + '</li>';
                        }
                        $("#tag-list").html(tagHtml);
                        $("input[type=checkbox], input[type=radio]").iCheck({
                            checkboxClass: 'icheckbox_square-green',
                            radioClass: 'iradio_square-green',
                            increaseArea: '20%' // optional
                        });
                    }
                    $("#refressTag").removeClass("fa-spin");
                    $.tool.showSuccessMessage("标签加载完成！");
                },
                error: $.tool.ajaxError
            });
        }
        $("#refressType").click(function () {
            $(this).addClass("fa-spin");
            loadType();
        });
        $("#refressTag").click(function () {
            $(this).addClass("fa-spin");
            loadTag();
        });
        loadTag();
        loadType();

        var articleId = '${id}';
        if(articleId){
            setTimeout(function () {
                $.ajax({
                    type: "post",
                    url: "/article/get/" + articleId,
                    success: function (json) {
                        $.tool.ajaxSuccess(json);
                        var info = json.data;
                        // 标签
                        var tags = info.tags;
                        for(var i = 0, len = tags.length; i < len ; i ++){
                            var tag = tags[i];
                            $("input[name=tags][value=" + tag.id + "]").iCheck('check');
                        }
                        $('input[name=original]').iCheck(info.original ? 'check' : 'uncheck');
                        $("#publishForm input[type!=checkbox], #publishForm select, #publishForm textarea").each(function () {
                            var $this = $(this);
                            var $div = $this.parents(".item");
                            if ($div.hasClass("bad")) {
                                $div.toggleClass("bad");
                                $div.find("div.alert").remove();
                            }
                            if(info['coverImage']){
                                $(".coverImage").attr('src', appConfig.qiniuPath + info['coverImage']);
                            }
                            var type = this.type;
                            var thisName = $this.attr("name");
                            var thisValue = info[thisName];

                            if(thisName == 'content'){
                                $this.val(thisValue);
                                editor.txt.html(thisValue);
                                return;
                            }
                            if(type == 'radio'){
                                if((thisValue || thisValue == 1)){
                                    if($this.val() == 1){
                                        $this.iCheck('check');
                                    }
                                }else{
                                    if($this.val() == 0){
                                        $this.iCheck('check');
                                    }
                                }
                                return;
                            }else{
                                if (thisValue && thisName != 'password') {
                                    $this.val(thisValue);
                                }
                            }
                        });
                    },
                    error: $.tool.ajaxError
                });
            }, 100);
        }
        // 点击保存
        $(".publishBtn").click(function () {
            if(validator.checkAll($("#publishForm"))) {
                $("#publishForm").ajaxSubmit({
                    type: "post",
                    url: "/article/save",
                    success: function (json) {
                        $.tool.ajaxSuccessConfirm(json, function () {
                            window.location.href = '/articles';
                        });
                    },
                    error: $.tool.ajaxError
                });
            }
        });

        var loadImg = false;
        // 选择图片
        $("#file-upload-btn").click(function () {
            $("#chooseImg").modal('show');
            if(!loadImg){
                // 加载素材库
                $.ajax({
                    type: "post",
                    url: "/api/material",
                    success: function (json) {
                        $.tool.ajaxSuccess(json);
                        loadImg = true;
                        json.qiniuPath = appConfig.qiniuPath;
                        var $box = $(".list-material");
                        var tpl = '{{#data}}<li data-imgUrl="{{.}}"><div class="col-md-55"><img class="lazy-img" data-original="{{qiniuPath}}{{.}}" alt="image"></div></li>{{/data}}{{^data}}<li>素材库为空</li>{{/data}}';
                        var html = Mustache.render(tpl, json);
                        $box.html(html);
                        $box.find("li").click(function () {
                            $box.find("li").each(function () {
                               $(this).removeClass("active");
                            });
                            var $this = $(this);
                            $this.toggleClass("active");
                            if($this.hasClass("active")){
                                var imgUrl = $this.attr("data-imgUrl");
                                console.log(imgUrl);
                                $("#cover-img-input").val(imgUrl);
                                $(".preview img.coverImage").attr("src", appConfig.qiniuPath + imgUrl);
                            }
                        });
                        $("img.lazy-img").lazyload({
                            placeholder : appConfig.staticPath + "/img/loading.gif",
                            effect: "fadeIn",
                            threshold: 100
                        });
                        $("img.lazy-img").trigger("sporty");
                    },
                    error: $.tool.ajaxError
                });
            }
        });

        // 选择图片
        $("#file-btn").click(function () {
            var $this = $(this);
            $("#cover-img-file").click();
        });
        $("input[name=file]").uploadPreview({ imgContainer: ".preview", width: 190, height: 200 });
    });
</script>