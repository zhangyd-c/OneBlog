/**
 * MIT License
 *
 * Copyright (c) 2018 yadong.zhang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * 项目核心Js类，负责项目前端模板方面的初始化等操作
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018-04-25
 * @since 1.0
 */
var editor = null, simplemde = null;

var zhyd = window.zhyd || {
    initSidebar: function () {
        var a = function () {
            $RIGHT_COL.css("min-height", $(window).height());
            var a = $BODY.outerHeight(),
                    b = $BODY.hasClass("footer_fixed") ? -10 : $FOOTER.height(),
                    c = $LEFT_COL.eq(1).height() + $SIDEBAR_FOOTER.height(),
                    d = a < c ? c : a;
            d -= $NAV_MENU.height() + b, $RIGHT_COL.css("min-height", d)
        };
        $SIDEBAR_MENU.find("a").on("click", function (b) {
            var c = $(this).parent();
            c.is(".active") ? (c.removeClass("active active-sm"), $("ul:first", c).slideUp(function () {
                a()
            })) : (c.parent().is(".child_menu") ? $BODY.is(".nav-sm") && ($SIDEBAR_MENU.find("li").removeClass("active active-sm"), $SIDEBAR_MENU.find("li ul").slideUp()) : ($SIDEBAR_MENU.find("li").removeClass("active active-sm"), $SIDEBAR_MENU.find("li ul").slideUp()), c.addClass("active"), $("ul:first", c).slideDown(function () {
                a()
            }))
        }), $MENU_TOGGLE.on("click", function () {
            $BODY.hasClass("nav-md") ? ($SIDEBAR_MENU.find("li.active ul").hide(), $SIDEBAR_MENU.find("li.active").addClass("active-sm").removeClass("active")) : ($SIDEBAR_MENU.find("li.active-sm ul").show(), $SIDEBAR_MENU.find("li.active-sm").addClass("active").removeClass("active-sm")), $BODY.toggleClass("nav-md nav-sm"), a()
        }), $SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent("li").addClass("current-page"), $SIDEBAR_MENU.find("a").filter(function () {
            return this.href == CURRENT_URL
        }).parent("li").addClass("current-page").parents("ul").slideDown(function () {
            a()
        }).parent().addClass("active"), $(window).smartresize(function () {
            a()
        }), a(), $.fn.mCustomScrollbar && $(".menu_fixed").mCustomScrollbar({
            autoHideScrollbar: !0,
            theme: "minimal",
            mouseWheel: {
                preventDefault: !0
            }
        })
    },
    initDaterangepicker: function () {
        $('.myDatepicker').datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            ignoreReadonly: true,
            allowInputToggle: true
        });
    },
    initValidator: function () {
        "undefined" != typeof validator && (console.log("zhyd.initValidator"), validator.message.date = "not a real date", $("form").on("blur", "input[required], input.optional, select.required", validator.checkField).on("change", "select.required", validator.checkField).on("keypress", "input[required][pattern]", validator.keypress), $(".multi.required").on("keyup blur", "input", function () {
            validator.checkField.apply($(this).siblings().last()[0])
        }), $("form").submit(function (a) {
            a.preventDefault();
            var b = !0;
            return validator.checkAll($(this)) || (b = !1), b && this.submit(), !1
        }));
    },
    initHelloMsg: function () {
        var $helloMsg = $("#hello_msg");
        var now = new Date();
        var nowHours = now.getHours();
        $helloMsg.html((nowHours >= 0 && nowHours <= 5) ? "凌晨好" : (nowHours > 5 && nowHours <= 9) ? "早上好" : ((nowHours > 9 && nowHours <= 12) ? "上午好" : ((nowHours > 12 && nowHours <= 13) ? "中午好" : ((nowHours > 13 && nowHours <= 18) ? "下午好" : "晚上好"))));
    },
    initWangEditor: function (options) {
        var $op = $.extend({
            id: "wangEditor",
            contentId: "content",
            uploadUrl: "",
            uploadFileName: "file"
        }, options);
        var E = window.wangEditor;
        editor = new E('#' + $op.id);
        // debug模式下，有 JS 错误会以throw Error方式提示出来
        editor.customConfig.debug = false;
        // 关闭粘贴样式的过滤
        editor.customConfig.pasteFilterStyle = false;
        editor.customConfig.zIndex = 100;

        if($op.contentId){
            var $content = $('#' + $op.contentId);
            editor.customConfig.onchange = function (html) {
                // 监控变化，同步更新到 textarea
                $content.val(html);
            };
        }

        if($op.uploadUrl){
            // 上传图片到服务器
            editor.customConfig.uploadImgServer = $op.uploadUrl;
            editor.customConfig.uploadFileName = 'file';
            // 将图片大小限制为 5M
            editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
            editor.customConfig.customAlert = function (info) {
                // info 是需要提示的内容
                $.alert.error(info);
            };
            editor.customConfig.uploadImgHooks = {
                error: function (xhr, editor) {
                    $.alert.error("图片上传出错");
                },
                timeout: function (xhr, editor) {
                    $.alert.error("请求超时");
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
                        $.alert.error(result.message);
                    }
                }
            };
        }
        editor.create();
        E.fullscreen.init('#' + $op.id);
    },
    initMdEditor: function (options) {
        var $op = $.extend({
            id: "mdEditor",
            uniqueId: "mdEditor_1",
            uploadUrl: ""
        }, options);
        // js实现aop切面编程，实时保存文章内容
        Function.prototype.after=function(afterfn){
            var __self=this;
            //保存原函数的引用
            return function(){
                //返回包含了原函数和新函数的"代理"函数
                afterfn.apply(this,arguments);//(1)
                //执行新函数,且保证this不被劫持,新函数接受的参数
                //也会被原封不动地传入原函数,新函数在原函数之前执行
                return __self.apply(this,arguments);//(2)
                //执行原函数并返回原函数的执行结果
                //并且保证this不被劫持
            }
        };
        var showMsg = function(){
            var $div = $('<div></div>');
            $div.css({'position':'absolute','right':'10px','top':0,'padding':'5px','font-size':'12px','color': '#ccc','opacity': 0});
            $div.html("自动保存完成");
            $div.appendTo($(".CodeMirror"));
            $div.animate({opacity: 1}, 1000, function(){
                $div.animate({opacity: 0}, 1000, function(){
                    $(this).remove();
                })
            })
        };
        SimpleMDE.prototype.autosave = SimpleMDE.prototype.autosave.after(showMsg);

        // Powered by https://github.com/sparksuite/simplemde-markdown-editor
        simplemde = new SimpleMDE({
            // textarea的DOM对象
            element: document.getElementById($op.id),
            // 自动下载FontAwesome，设为false为不下载(如果设为false则必须手动引入)
            autoDownloadFontAwesome: false,
            // 自动聚焦输入框
            autofocus: true,
            // 是否自动保存正在编写的文本
            autosave: {
                // 启用自动保存功能
                enabled: true,
                // 自动保存的间隔，以毫秒为单位。默认为10000（10s）
                delay: 15000,
                // 唯一的字符串标识符(保证每个SimpleMDE编辑器的uniqueId唯一)
                uniqueId: $op.uniqueId,
                msg: "自动保存成功了"
            },
            placeholder: "请输入文本内容",
            // 如果设置为true，则会出现JS警报窗口，询问链接或图像URL(插入图片或链接弹窗)。默认为false
            promptURLs: true,
            renderingConfig: {
                // 如果设置为true，将使用highlight.js高亮显示。默认为false
                codeSyntaxHighlighting: true
            },
            showIcons: ["code", "table", "clean-block", "horizontal-rule"],
            tabSize: 4,
            // 编辑器底部的状态栏
            status: true,
            status: ["autosave", "lines", "words", "cursor"], // Optional usage
            status: ["autosave", "lines", "words", "cursor", {
                className: "keystrokes",
                defaultValue: function(el) {
                    this.keystrokes = 0;
                    el.innerHTML = "0 Keystrokes";
                },
                onUpdate: function(el) {
                    el.innerHTML = ++this.keystrokes + " Keystrokes";
                }
            }]

        });
        var $fullscreen = $(".editor-toolbar a.fa-arrows-alt, .editor-toolbar a.fa-columns");
        $fullscreen.click(function () {
            var $this = $(this);
            if($fullscreen.hasClass("active")){
                $(".CodeMirror, .CodeMirror-scroll").css('max-height', 'none');
            } else {
                $(".CodeMirror, .CodeMirror-scroll").css('max-height', '200px');
            }
        });
        if($op.uploadUrl) {
            inlineAttachment.editors.codemirror4.attach(simplemde.codemirror, {
                uploadUrl: $op.uploadUrl
            });
        }
        $(".editor-preview-side").addClass("markdown-body");
    }
};

function countChecked() {
    "all" === checkState && $(".bulk_action input[name='table_records']").iCheck("check"), "none" === checkState && $(".bulk_action input[name='table_records']").iCheck("uncheck");
    var a = $(".bulk_action input[name='table_records']:checked").length;
    a ? ($(".column-title").hide(), $(".bulk-actions").show(), $(".action-cnt").html(a + " Records Selected")) : ($(".column-title").show(), $(".bulk-actions").hide())
}

function gd(a, b, c) {
    return new Date(a, b - 1, c).getTime()
}

!function (a, b) {
    var c = function (a, b, c) {
        var d;
        return function () {
            function h() {
                c || a.apply(f, g), d = null
            }

            var f = this,
                    g = arguments;
            d ? clearTimeout(d) : c && a.apply(f, g), d = setTimeout(h, b || 100)
        }
    };
    jQuery.fn[b] = function (a) {
        return a ? this.bind("resize", c(a)) : this.trigger(b)
    }
}(jQuery, "smartresize");

var CURRENT_URL = window.location.href.split("#")[0].split("?")[0],
        $BODY = $("body"),
        $MENU_TOGGLE = $("#menu_toggle"),
        $SIDEBAR_MENU = $("#sidebar-menu"),
        $SIDEBAR_FOOTER = $(".sidebar-footer"),
        $LEFT_COL = $(".left_col"),
        $RIGHT_COL = $(".right_col"),
        $NAV_MENU = $(".nav_menu"),
        $FOOTER = $("footer"),
        randNum = function () {
            return Math.floor(21 * Math.random()) + 20
        };
$(document).ready(function () {
    $(".collapse-link").on("click", function () {
        var a = $(this).closest(".x_panel"),
                b = $(this).find("i"),
                c = a.find(".x_content");
        a.attr("style") ? c.slideToggle(200, function () {
            a.removeAttr("style")
        }) : (c.slideToggle(200), a.css("height", "auto")), b.toggleClass("fa-chevron-up fa-chevron-down")
    }), $(".close-link").click(function () {
        var a = $(this).closest(".x_panel");
        a.remove()
    });
}), $(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip({
        container: "body"
    })
}), $(".progress .progress-bar")[0] && $(".progress .progress-bar").progressbar(), $(document).ready(function () {
    if ($(".js-switch")[0]) {
        var a = Array.prototype.slice.call(document.querySelectorAll(".js-switch"));
        a.forEach(function (a) {
            new Switchery(a, {
                color: "#26B99A"
            })
        })
    }
}), $(document).ready(function () {
    $("input[type=checkbox], input[type=radio]").iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
        increaseArea: '20%' // optional
    });
}), $("table input").on("ifChecked", function () {
    checkState = "", $(this).parent().parent().parent().addClass("selected"), countChecked()
}), $("table input").on("ifUnchecked", function () {
    checkState = "", $(this).parent().parent().parent().removeClass("selected"), countChecked()
});
var checkState = "";
$(".bulk_action input").on("ifChecked", function () {
    checkState = "", $(this).parent().parent().parent().addClass("selected"), countChecked()
}), $(".bulk_action input").on("ifUnchecked", function () {
    checkState = "", $(this).parent().parent().parent().removeClass("selected"), countChecked()
}), $(".bulk_action input#check-all").on("ifChecked", function () {
    checkState = "all", countChecked()
}), $(".bulk_action input#check-all").on("ifUnchecked", function () {
    checkState = "none", countChecked()
}), $(document).ready(function () {
    $(".expand").on("click", function () {
        $(this).next().slideToggle(200), $expand = $(this).find(">:first-child"), "+" == $expand.text() ? $expand.text("-") : $expand.text("+")
    })
}), "undefined" != typeof NProgress && ($(document).ready(function () {
    NProgress.start()
}), $(window).load(function () {
    NProgress.done()
}));
var originalLeave = $.fn.popover.Constructor.prototype.leave;
$.fn.popover.Constructor.prototype.leave = function (a) {
    var c, d,
            b = a instanceof this.constructor ? a : $(a.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type);
    originalLeave.call(this, a), a.currentTarget && (c = $(a.currentTarget).siblings(".popover"), d = b.timeout, c.one("mouseenter", function () {
        clearTimeout(d), c.one("mouseleave", function () {
            $.fn.popover.Constructor.prototype.leave.call(b, b)
        })
    }))
}, $("body").popover({
    selector: "[data-popover]",
    trigger: "click hover",
    delay: {
        show: 50,
        hide: 400
    }
}), $(document).ready(function () {
    // 工具提示
    $('[data-toggle="tooltip"]').tooltip();
    // 图片预览
    $(".showImage").fancybox();
    /* 自定义下拉 div */
    $(".custom-dropdown").on("click", function () {
        var a = $(this).closest(".custom-panel"),
                b = $(this).find("i"),
                c = a.find(".custom-container");
        a.attr("style") ? c.slideToggle(200, function () {
            a.removeAttr("style")
        }) : (c.slideToggle(200), a.css("height", "auto")), b.toggleClass("fa-angle-double-up fa-angle-double-down")
    });

    $(".showContent").click(function () {
        $(this).toggleClass('fa-plus-square fa-minus-square');
        $(".disable-content").slideToggle(400);
    });
    zhyd.initDaterangepicker();
    zhyd.initValidator();
    zhyd.initSidebar();

    $.ajax({
        cache: false,
        type: "post",
        url: "/comment/listVerifying",
        success: function (json) {
            $.alert.ajaxSuccess(json);
            var $box = $(".msg_list > li:last-child");
            if (!json.data) {
                var html = '<li><a><span class="image"><img src="/assets/images/loading.gif" alt="user avatar"></span> <span><span>系统管理员</span> <span class="time">3 mins ago</span></span> <span class="message">暂无消息</span></a></li>';
                $box.before(html);
                return;
            }
            var tpl = '{{#data}}<li><a href="/comments"><span class="image"><img src="{{avatar}}" alt="user avatar"></span> <span><span>{{nickname}}</span> <span class="time">{{createTimeString}}</span></span> <span class="message">点击查看&审核</span></a></li>{{/data}}';
            var html = Mustache.render(tpl, json);
            $box.before(html);
            $(".noticeNum").text(json.data.length);
        },
        error: $.alert.ajaxError
    });

    $("img.lazy-img").lazyload({
        placeholder: appConfig.staticPath + "/img/loading.gif",
        effect: "fadeIn",
        threshold: 100
    });
    $(window).bind("load", function () {
        var timeout = setTimeout(function () {
            $("img.lazy-img").trigger("sporty");
        }, 3000);
    });

    /**
     * 图片预览
     * 必须指定预览图片的容器，格式：data-preview-container = "#containerID"
     */
    $(".uploadPreview").each(function () {
        var $this = $(this);
        $this.uploadPreview({ imgContainer: $this.data("preview-container") });
    });

    zhyd.initHelloMsg();

    $("#updPassBtn").click(function () {
        var $form = $("#updPassForm");
        if(validator.checkAll($form)) {
            $form.ajaxSubmit({
                type: "POST",
                url: '/passport/updatePwd',
                success: function (json) {
                    $.alert.ajaxSuccess(json);
                    if(json.status == 200){
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    }

                },
                error: $.alert.ajaxError
            });
        }
    });

});