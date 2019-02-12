/**
 *
 * 图片预览
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018-04-25
 * @since 1.0
 */
/*
 * jq 1.9以后已不再支持$.browser 和 $.browser.version,此处自己实现
 * Use of jQuery.browser is deprecated.
 * It's included for backwards compatibility and plugins,
 * although they should work to migrate away.
 */
var userAgent = navigator.userAgent.toLowerCase();

// Figure out what browser is being used
jQuery.browser = {
    version: (userAgent.match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [0,'0'])[1],
    safari: /webkit/.test( userAgent ),
    opera: /opera/.test( userAgent ),
    msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
    mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
};

jQuery.fn.extend({
    uploadPreview: function (opts) {
        var _self = this,
                _this = $(this);
        opts = jQuery.extend({
            imgContainer: "",
            width: 100,
            height: 100,
            suffix: ["gif", "jpeg", "jpg", "bmp", "png"],
            callback: function () {
            }
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            var $this = this;
            if(!opts.imgContainer){
                console.error("未指定imgContainer");
                return;
            }
            var $container = $(opts.imgContainer);
            $container.html('<i class="fa fa-spinner fa-pulse"></i>');
            if ($this.value) {
                if (!RegExp("\.(" + opts.suffix.join("|") + ")$", "i").test($this.value.toLowerCase())) {
                    $.alert.error("只支持以下几种文件格式：[" + opts.suffix.join("，") + "]");
                    $this.value = "";
                    $container.html('');
                    return false
                }
                var $img = $("<img>");
                try {
                    setTimeout(function () {
                        $container.html('');
                        $img.attr('src', _self.getObjectURL($this.files[0]));
                        $img.addClass("img-responsive img-rounded auto-shake");
                        $img.appendTo($container);
                    }, 100);
                } catch (e) {
                    $.alert.error("当前浏览器不支持图片预览！");
                }
                opts.callback()
            } else {
                $container.html('');
            }
        })
    }
});