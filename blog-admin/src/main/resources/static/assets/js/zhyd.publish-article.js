/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/6/7 10:48
 * @since 1.0
 */
var $publishForm = $("#publishForm");

// 加载所有分类
function loadType(){
    $.ajax({
        type: "post",
        url: "/type/listAll",
        success: function (json) {
            $.alert.ajaxSuccess(json);
            var data = '';
            if(data = json.data){
                var typeOptions = '<option value="">选择分类</option>';
                for(var i = 0, len = data.length; i < len; i ++){
                    var type = data[i];
                    typeOptions += '<option value="' + type.id + '">' + type.name + '</option>';
                }
                $("select#typeId").html(typeOptions);
            }
            $("#refressType").removeClass("fa-spin");
            $.alert.showSuccessMessage("分类加载完成！");
        },
        error: $.alert.ajaxError
    });
}

// 加载所有标签
function loadTag() {
    $.ajax({
        type: "post",
        url: "/tag/listAll",
        success: function (json) {
            $.alert.ajaxSuccess(json);
            var data = '';
            if (data = json.data) {
                var tagHtml = '';
                for (var i = 0, len = data.length; i < len; i++) {
                    var tag = data[i];
                    tagHtml += '<li>'
                            + '<input type="checkbox" class="square ignore" name="tags" value="' + tag.id + '"> ' + tag.name
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
            $.alert.showSuccessMessage("标签加载完成！");
        },
        error: $.alert.ajaxError
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

if(articleId){
    setTimeout(function () {
        $.ajax({
            type: "post",
            url: "/article/get/" + articleId,
            success: function (json) {
                $.alert.ajaxSuccess(json);
                var info = json.data;
                // 标签
                var tags = info.tags;
                for(var i = 0, len = tags.length; i < len ; i ++){
                    var tag = tags[i];
                    $("input[name=tags][value=" + tag.id + "]").iCheck('check');
                }
                if($('input[name=original]')){
                    $('input[name=original]').iCheck(info.original ? 'check' : 'uncheck');
                }
                if($('#comment')){
                    $('#comment').iCheck(info.comment ? 'check' : 'uncheck');
                }
                if(info['coverImage']){
                    $(".coverImage").attr('src', appConfig.qiniuPath + info['coverImage']);
                }
                if(info['contentMd']){
                    $("#contentMd").val(info['contentMd']);
                    if(simplemde){
                        simplemde.value(info['contentMd']);
                    }
                }

                if(info['content']){
                    $("#content").val(info['content']);
                    if(editor){
                        editor.txt.html(info['content']);
                    }
                }
                $publishForm.find("input[type!=checkbox], select, textarea").each(function () {
                    clearText($(this), this.type, info);
                });
            },
            error: $.alert.ajaxError
        });
    }, 1000);
}

// 点击保存
$(".publishBtn").click(function () {
    if(validator.checkAll($publishForm)) {
        $publishForm.ajaxSubmit({
            type: "post",
            url: "/article/save",
            success: function (json) {
                $.alert.ajaxSuccess(json, function () {
                    window.location.href = '/articles';
                });
            },
            error: $.alert.ajaxError
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
                $.alert.ajaxSuccess(json);
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
            error: $.alert.ajaxError
        });
    }
});

// 选择图片
$("#file-btn").click(function () {
    var $this = $(this);
    $("#cover-img-file").click();
});
$("input[name=file]").uploadPreview({ imgContainer: ".preview", width: 190, height: 200 });
