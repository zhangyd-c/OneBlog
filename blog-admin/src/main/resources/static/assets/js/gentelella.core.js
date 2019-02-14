/**
 * gentelella前端框架的核心js类 (https://colorlib.com/polygon/gentelella/index.html)
 * @date 2019-01-29
 */
var gentelella = window.gentelella || {
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
        "undefined" != typeof validator && (validator.message.date = "not a real date", $("form").on("blur", "input[required], input.optional, select.required", validator.checkField).on("change", "select.required", validator.checkField).on("keypress", "input[required][pattern]", validator.keypress), $(".multi.required").on("keyup blur", "input", function () {
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
    initSwitchery: function (delay) {
        setTimeout(function () {
            var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
            elems.forEach(function (html) {
                var switchery = new Switchery(html, {
                    color: '#26B99A',
                    size: 'small'
                });
            });
        }, delay || 0);
    },
    initiICheck: function () {
        $("input[type=checkbox], input[type=radio]").iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
            increaseArea: '20%' // optional
        });
    }
};

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
    gentelella.initiICheck();
});
"undefined" != typeof NProgress && ($(document).ready(function () {
    NProgress.start()
}), $(window).load(function () {
    NProgress.done()
}));
$(document).ready(function () {
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
    gentelella.initSidebar();
    gentelella.initDaterangepicker();
    gentelella.initValidator();
    gentelella.initHelloMsg();
});