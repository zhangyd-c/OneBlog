/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/6/7 10:48
 * @since 1.0
 */
var $publishForm = $("#publishForm");

if(articleId){
    setTimeout(function () {
        $.ajax({
            type: "post",
            url: "/article/get/" + articleId,
            success: function (json) {
                $.alert.ajaxSuccess(json);
                var info = json.data;
                // 标签, 因为标签初始化是有延迟的，所以这而赋值的时候为了防止赋值失败，亦采用延迟处理
               setTimeout(function () {
                   var tags = info.tags;
                   for(var i = 0, len = tags.length; i < len ; i ++){
                       var tag = tags[i];
                       $('input[target="tagsinput"]').tagsinput('add', {"id": tag.id, "name": tag.name}, {add: false});
                   }
               }, 1000);
                if($('input[name=original]')){
                    $('input[name=original]').iCheck(info.original ? 'check' : 'uncheck');
                }
                if($('#comment')){
                    $('#comment').iCheck(info.comment ? 'check' : 'uncheck');
                }
                if(info['coverImage']){
                    $(".coverImage").attr('src', info['coverImage']);
                }
                var contentMd = info['contentMd'];
                if(contentMd){
                    $("#contentMd").val(contentMd);
                    if(simplemde){
                        simplemde.value(contentMd);
                    }
                }
                var contentHtml = info['content'];
                if(contentHtml){
                    $("#content").val(contentHtml);
                    if(editor){
                        editor.txt.html(contentHtml);
                    }
                }
                $publishForm.find("input[type!=checkbox], select, textarea").each(function () {
                    new Table().clearText($(this), this.type, info);
                });
            },
            error: $.alert.ajaxError
        });
    }, 1000);
}

$(".to-choose-info").click(function () {
    if(validator.checkAll($publishForm)) {
        $("#publishModal").modal('show');
    }
});

// 点击保存
$(".publishBtn").click(function () {
    if(validator.checkAll($publishForm)) {
        if(!$publishForm.find("input[name='tags']").val()) {
            $.alert.error("请至少选择一个标签");
            return;
        }
        if(!$("#description").val() || !$("#keywords").val()) {
            $.alert.error("请填写SEO相关的内容，填写后更容易被收录哦");
            return;
        }
        var isMarkdown = $("input[name=isMarkdown]").val();
        if(isMarkdown == 1 || isMarkdown == 'true'){
            $("#contentMd").val(simplemde.value());
            $("#content").val(simplemde.markdown(simplemde.value()));
        }

        $publishForm.ajaxSubmit({
            type: "post",
            url: "/article/save",
            success: function (json) {
                if(isMarkdown == 1) {
                    $.tool.delCache("smde_" + op.uniqueId);
                }
                $.alert.ajaxSuccess(json, function () {
                    window.location.href = '/articles';
                });
            },
            error: $.alert.ajaxError
        });
    }
});

$("#file-upload-btn").click(function () {
    $.modal.material.open({multiSelect: false}, function (selectedImageUrl) {
        $("#cover-img-input").val(selectedImageUrl);
        $(".preview img.coverImage").attr("src", selectedImageUrl);
    })
});

// 选择图片
$("#file-btn").click(function () {
    var $this = $(this);
    $("#cover-img-file").click();
});
$("input[name=file]").uploadPreview({ imgContainer: ".preview", width: 190, height: 200 });
