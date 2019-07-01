/**
 *
 * 项目工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2017-09-02
 * @since 1.0
 */
(function ($) {
    // 覆盖jquery-confirm中的函数
    $.jqAlert = $.alert;
    $.jqConfirm = $.confirm;

    $.extend({
        alert: {
            info: function (content, callback, delayTime) {
                delayTime = delayTime ? "confirm|" + delayTime : "confirm|3000";
                $.jqAlert({
                    icon: 'fa fa-info-circle',
                    title: '友情提示',
                    content: content,
                    type: 'green',
                    typeAnimated: true,
                    autoClose: delayTime,
                    buttons: {
                        confirm: {
                            text: "关闭",
                            btnClass: 'btn-default',
                            action: callback
                        }
                    }
                });
            },
            error: function (content, callback, delayTime) {
                delayTime = delayTime ? "confirm|" + delayTime : "confirm|3000";
                $.jqAlert({
                    icon: 'fa fa-exclamation-circle',
                    title: '警告',
                    content: content,
                    autoClose: delayTime,
                    type: 'orange',
                    typeAnimated: true,
                    buttons: {
                        confirm: {
                            text: "关闭",
                            btnClass: 'btn-default',
                            action: callback
                        }
                    }
                });
            },
            confirm: function (content, confirmCallback, cancelCallback, delayTime) {
                delayTime = delayTime ? "cancel|" + delayTime : "cancel|5000";
                $.jqConfirm({
                    icon: 'fa fa-question-circle',
                    title: '确认？',
                    content: content,
                    autoClose: delayTime,
                    type: 'dark',
                    typeAnimated: true,
                    buttons: {
                        confirm: {
                            text: '确定',
                            btnClass: 'btn-green',
                            action: confirmCallback
                        },
                        cancel: {
                            text: '取消',
                            btnClass: 'btn-default',
                            action: cancelCallback
                        }
                    }
                });
            },
            ajaxSuccessConfirm: function (json, callback, cancelCallback) {
                if (json.status == 200) {
                    if (json.message) {
                        $.alert.confirm(json.message, callback, cancelCallback);
                    }
                } else {
                    if (json.message) {
                        $.alert.error(json.message);
                    }
                }
            },
            ajaxSuccess: function (json, callback) {
                if (json.status == 200) {
                    if (json.message) {
                        $.alert.info(json.message, callback);
                    }
                } else {
                    if (json.message) {
                        $.alert.error(json.message);
                    }
                }
            },
            ajaxError: function () {
                $.alert.error("网络超时！");
            }
        }
    });
    $.extend({
        modal: {
            material: {
                _loadData: function(config, callback){
                    $("#selectable").html(config.selectable), $("#selected").html(0);
                    zhyd.mask.loading($(".material-body"), "加载中...");
                    $.ajax({
                        url: "/file/list",
                        data: {pageNumber: config && config.pageNumber ? config.pageNumber : 1},
                        type: "POST",
                        success: function (json) {
                            zhyd.mask.closeAll($(".material-body"));
                            var $box = $(".list-file");
                            var tpl = '{{#list}}<li class="material-item" data-imgUrl="{{fullFilePath}}"><div style="position: relative;">' +
                                '<div class="selected-mask mask-xs"><div class="inner"></div><div class="icon"></div></div>' +
                                '<img class="lazy-img" data-original="{{fullFilePath}}" alt="image"></div></li>{{/list}}{{^list}}<li>素材库为空</li>{{/list}}';
                            var html = Mustache.render(tpl, json);
                            var pageTpl = '{{#data}}<li class="material-page">\n' +
                                '    <div class="material-page-body">\n' +
                                '        {{#hasPreviousPage}}<a class="btn btn-default btn-sm material-pagination" data-page="{{prePage}}">\n' +
                                '            <i class="fa fa-caret-left"></i>\n' +
                                '        </a>{{/hasPreviousPage}}<span style="margin-right: 5px;">{{pageNum}}/{{pages}}</span>{{#hasNextPage}}<a class="btn btn-default btn-sm material-pagination" data-page="{{nextPage}}">\n' +
                                '            <i class="fa fa-caret-right"></i>\n' +
                                '        </a>{{/hasNextPage}}<input class="form-control input-sm material-input">\n' +
                                '    <a class="btn btn-default btn-sm material-jump">\n' +
                                '            Go\n' +
                                '        </a>\n' +
                                '    \n' +
                                '    </div>\n' +
                                '</li>{{/data}}';
                            html += Mustache.render(pageTpl, {data: json});
                            $box.html(html);

                            // 图片懒加载
                            var $lazyImg = $("img.lazy-img");
                            $lazyImg.lazyload({
                                placeholder : appConfig.cmsPath + "/assets/images/loading.gif",
                                effect: "fadeIn",
                                threshold: 100
                            });
                            $lazyImg.trigger("sporty");

                            // 绑定分页点击事件
                            $(".material-pagination").unbind("click").click(function () {
                                var $this = $(this);
                                var pageNumber = $this.data("page");
                                config.pageNumber = !pageNumber || isNaN(pageNumber) ? 1 : parseInt(pageNumber);
                                $.modal.material._loadData(config);
                            });
                            // 绑定分页-跳转页面点击事件
                            $(".material-jump").unbind("click").click(function () {
                                var $this = $(this);
                                var jumpTarget = $(".material-input").val();
                                config.pageNumber = !jumpTarget || isNaN(jumpTarget) ? 1 : parseInt(jumpTarget);
                                $.modal.material._loadData(config);
                            });

                            // 绑定图片点击事件
                            var selectable = 0;
                            var $li = $box.find("li.material-item");
                            $li.unbind("click").click(function () {
                                var $this = $(this);
                                if(config.multiSelect) {
                                    if($this.hasClass("active") || $this.hasClass("selected")) {
                                        selectable --;
                                        $this.removeClass("active selected");
                                    } else {
                                        if(selectable >= config.selectable) {
                                            $.alert.error("最多只能选择" + config.selectable + "张图片！");
                                            return false;
                                        }
                                        selectable ++;
                                        $this.addClass("active selected");
                                    }

                                    $("#selected").html(selectable);
                                } else {
                                    $this.addClass("current");
                                    $li.each(function () {
                                        !$(this).hasClass("current") && $(this).removeClass("active selected");
                                    });
                                    $this.toggleClass("active selected").removeClass("current");
                                    if($this.hasClass("active") || $this.hasClass("selected")) {
                                        $("#selected").html(1);
                                    } else {
                                        $("#selected").html(0);
                                    }
                                }
                            });

                            // 执行回调
                            callback && callback($box);
                        },
                        error: function () {
                            zhyd.mask.closeAll($(".material-body"));
                        }
                    })
                },
                open: function (config, callback){
                    config = $.extend({
                        // 是否多选
                        multiSelect: false,
                        // 可选择的数量，当multiSelect为true时可用
                        selectable: 1
                    }, config);
                    $("#chooseImgModal").modal('show');
                    var $this = this;
                    // modal show事件，默认会有300ms的延迟，所以需要加setTimeout，且延迟时间要比modal弹出的时间大
                    // 主要为了解决首次弹出素材库时，图片懒加载无法转换成真实图片地址的问题
                    setTimeout(function () {
                        $this._loadData(config, function ($box) {

                            $(".btn-confirm").unbind("click").click(function () {
                                var $this = $(this);
                                var imgUrls = [];
                                $box.find("li").each(function () {
                                    var $thisLi = $(this);
                                    if($thisLi.hasClass("active") || $thisLi.hasClass("selected") ){
                                        var imgUrl = $thisLi.attr("data-imgUrl");
                                        imgUrls.push(imgUrl);
                                    }
                                });
                                if(config.multiSelect) {
                                    callback(imgUrls);
                                } else {
                                    callback(imgUrls[0]);
                                }
                            });

                            $("#btn-material-upload").unbind("click").click(function () {
                                var $input = $("#input-material-upload");
                                $input.click().unbind("change").change(function () {
                                    var selectedFiles = document.getElementById("input-material-upload").files;
                                    if(!selectedFiles || selectedFiles.length <= 0) {
                                        return false;
                                    }
                                    var $form = $("#materialForm");
                                    if (validator.checkAll($form)) {
                                        $form.ajaxSubmit({
                                            type: "post",
                                            url: "/file/add",
                                            success: function (json) {
                                                if (json.status == 200) {
                                                    $.modal.material._loadData(config)
                                                } else {
                                                    if (json.message) {
                                                        $.alert.error(json.message);
                                                    }
                                                }
                                            },
                                            error: $.alert.ajaxError
                                        });
                                    }
                                });
                            });
                        })
                    }, 301);
                }
            }
        }
    });
    $.extend({
        toastr: {
            initToastr: function () {
                if (toastr) {
                    toastr.options = {
                        closeButton: true,
                        debug: false,
                        progressBar: true,
                        positionClass: "toast-top-right",
                        onclick: null,
                        showDuration: "300",//显示动作时间
                        hideDuration: "1000",//隐藏动作时间
                        timeOut: "3000",//自动关闭超时时间
                        extendedTimeOut: "1000",
                        showEasing: "swing",
                        hideEasing: "linear",
                        showMethod: "fadeIn",
                        hideMethod: "fadeOut"
                    };
                }
            },
            info: function (message) {
                this.initToastr();
                toastr.info(message);
            },
            success: function (message) {
                this.initToastr();
                toastr.success(message);
            },
            warning: function (message) {
                this.initToastr();
                toastr.warning(message);
            },
            error: function (message) {
                this.initToastr();
                toastr.error(message);
            }
        }
    });
    $.extend({
        notify: {
            _open: function (type, title, text) {
                "undefined" != typeof PNotify && new PNotify({
                    title: title,
                    text: text,
                    type: type,// Type of the notice. 'notice', 'info', 'success', or 'error'.
                    addclass: "dark",// blue purple green aero red dark
                    styling: "bootstrap3", // Can be 'brighttheme', 'bootstrap3', 'bootstrap4'
                    icons: "fontawesome4", // Can be 'brighttheme', 'bootstrap3', 'fontawesome4', 'fontawesome5'
                    shadow: true,
                    delay: 3000
                    // hide: !1
                });
            },
            info: function (message) {
                this._open("info", "通知", message);
            },
            success: function (message) {
                this._open("success", "成功", message);
            },
            notice: function (message) {
                this._open("notice", "注意", message);
            },
            error: function (message) {
                this._open("error", "警告", message);
            }
        }
    });
    $.extend({
        tool: {
            cache: function (key, value) {
                if (!value) {
                    return localStorage.getItem(key);
                }
                localStorage.setItem(key, value);
                return false;
            },
            delCache: function (key) {
                if (this.cache(key)) {
                    localStorage.removeItem(key);
                }
            },
            isEmpty: function (value) {
                if (value == null || this.trim(value) == "") {
                    return true;
                }
                return false;
            },
            isInteger: function () {
                return (new RegExp(/^\d+$/).test(this));
            },
            isNumber: function (value, element) {
                return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
            },
            trim: function (value) {
                if (value == null) {
                    return "";
                }
                return value.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
            },
            html2Txt: function (value) {
                value = this.trim(value);
                value = value.replace(/(\n)/g, "");
                value = value.replace(/(\t)/g, "");
                value = value.replace(/(\r)/g, "");
                value = value.replace(/<\/?[^>]*>/g, "");
                value = value.replace(/\s*/g, "");
                return value;
            },
            currentPath: function () {
                /* // 域
                 var domain = document.domain;
                 // 当前页
                 var nowurl = document.URL;
                 // 来源页
                 var fromurl = document.referrer;

                 console.log(domain);
                 console.log(fromurl);
                 console.log(nowurl);*/
                return window.location.pathname;
            },
            getMeta: function (name) {
                var meta = document.getElementsByTagName('meta');
                var share_desc = '';
                for (i in meta) {
                    if (typeof meta[i].name != "undefined" && meta[i].name.toLowerCase() == name.toLowerCase()) {
                        share_desc = meta[i].content;
                        break;
                    }
                }
                return share_desc;
            },
            random: function (min, max) {
                return Math.floor((Math.random() * max) + min);
            },
            shuffle: function (arr) {
                if (!arr) {
                    return arr;
                }
                var len = arr.length;
                for (var i = 0; i < len; i++) {
                    var end = len - 1;
                    var index = (Math.random() * (end + 1)) >> 0;
                    var temp = arr[end];
                    arr[end] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }
    });
})(jQuery);
/**
 * 扩展String方法
 */
$.extend(String.prototype, {
    /*trim: function () {
        return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
    },*/
    startsWith: function (pattern) {
        return this.indexOf(pattern) === 0;
    },
    endsWith: function (pattern) {
        var d = this.length - pattern.length;
        return d >= 0 && this.lastIndexOf(pattern) === d;
    },
    replaceSuffix: function (index) {
        return this.replace(/\[[0-9]+\]/, '[' + index + ']').replace('#index#', index);
    },
    getRequestURI: function () {
        var indexOf = this.indexOf("?");
        return (indexOf == -1) ? this : this.substr(0, indexOf);
    },
    getParams: function (encode) {
        var params = {},
            indexOf = this.indexOf("?");
        if (indexOf != -1) {
            var str = this.substr(indexOf + 1),
                strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                var item = strs[i].split("=");
                var val = encode ? item[1].encodeParam() : item[1];
                params[item[0]] = item.length > 1 ? val : '';
            }
        }
        return params;
    },
    encodeParam: function () {
        return encodeURIComponent(this);
    },
    replaceAll: function (os, ns) {
        return this.replace(new RegExp(os, "gm"), ns);
    },
    skipChar: function (ch) {
        if (!this || this.length === 0) {
            return '';
        }
        if (this.charAt(0) === ch) {
            return this.substring(1).skipChar(ch);
        }
        return this;
    },
    isPositiveInteger: function () {
        return (new RegExp(/^[1-9]\d*$/).test(this));
    },
    isInteger: function () {
        return (new RegExp(/^\d+$/).test(this));
    },
    isNumber: function (value, element) {
        return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
    },
    isValidPwd: function () {
        return (new RegExp(/^([_]|[a-zA-Z0-9]){6,32}$/).test(this));
    },
    isValidMail: function () {
        return (new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(this.trim()));
    },
    isSpaces: function () {
        for (var i = 0; i < this.length; i += 1) {
            var ch = this.charAt(i);
            if (ch != ' ' && ch != "\n" && ch != "\t" && ch != "\r") {
                return false;
            }
        }
        return true;
    },
    isMobile: function () {
        return (new RegExp(/(^[0-9]{11,11}$)/).test(this));
    },
    isUrl: function () {
        return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
    },
    isExternalUrl: function () {
        return this.isUrl() && this.indexOf("://" + document.domain) == -1;
    },
    parseCurrency: function (num) {
        var numberValue = parseFloat(this);
        return parseFloat(numberValue.toFixed(num || 2));
    }
});

/**
 * Created by yadong.zhang on 2017-03-19.
 */

/**
 * 时间对象的格式化;
 */
Date.prototype.format = function (format) {
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    };

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};