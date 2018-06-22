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
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2017-04-01
 * @since 1.0
 */

/*// 动态切换浏览器窗口title https://zhangge.net/
jQuery(document).ready(function() {
    function c() {
        document.title = document[a] ? "(●_●) 床前明月光.....《" + d + "》" : d
    }
    var a, b, d = document.title;
    "undefined" != typeof document.hidden ? (a = "hidden", b = "visibilitychange") : "undefined" != typeof document.mozHidden ? (a = "mozHidden", b = "mozvisibilitychange") : "undefined" != typeof document.webkitHidden && (a = "webkitHidden", b = "webkitvisibilitychange");
    "undefined" == typeof document.addEventListener && "undefined" == typeof document[a] || document.addEventListener(b, c, !1)
});*/

function initNavbar() {
    $(".navbar .navbar-nav li").each(function () {
        var $this = $(this);
        if ($this.hasClass("dropdown")) {
            $this.on("mouseover", function () {
                $this.addClass("open").find("a:first-child").attr("aria-expanded", "true");
            }).on("mouseout", function () {
                $this.removeClass("open").find("a:first-child").attr("aria-expanded", "false");
            });
        }
        $this.find("a").each(function () {
            var $this = $(this);
            var $parent = $this.parent();
            $parent.removeClass("active");
            if ($this.attr("href") === $.tool.currentPath()) {
                $parent.toggleClass("active");
            }
        });
    });
}

function initArticeMenu() {
    $(function () {
        if ($('.blog-info-body') && $('.blog-info-body')[0]) {
            // console.log("生成文章目录");
            var padding = [0, 10, 20, 30, 40];
            var liDom, aDom, spanDom;
            var dNum = 0;
            $('.blog-info-body').find('h2,h3').each(function (index, item) {
                var $this = $(this);
                $this.before($('<span id="menu_'+index+'" class="menu-point"></span>'));
                $this.addClass("menu-title");
                var tagText = $this.text();
                var tagName = $this[0].tagName.toLowerCase();
                var tagIndex = parseInt(tagName.charAt(1)) - 1;
                spanDom = '<i class="fa fa-angle-right"></i>';
                aDom = '<a href="#menu_' + index + '" style="display:inline-block;">' + tagText + '</a>';
                liDom = '<li style="padding-left:' + padding[tagIndex] + 'px;line-height: 2;">' + spanDom + aDom + '</li>';
                $("#article-menu ul").append(liDom);
                dNum++;
            });
            if (dNum > 0) {
                $("#article-menu").show();
                $('.article-module').removeClass('hide');
                var sc = $(document);//得到document文档对象。
                var am = $(".article-module");// 文章目录对象
                var win = $(window); //得到窗口对象
                win.scroll(function () {
                    bindMenuScroll();
                });
                bindMenuScroll();
                function bindMenuScroll() {
                    if ($.tool.currentPath().indexOf('/article/') !== -1) {
                        if (sc.scrollTop() >= 200) {
                            if (!am.hasClass("fixed")) {
                                var top = win.width() > 768 ? '85px' : '55px';
                                am.addClass('fixed').css({width: '21.7%',right: 0, border: '1px solid rgba(0, 0, 0, 0.1)'}).animate({top: top}, 100);
                                $('.close-article-menu').removeClass('hide');
                            }
                        } else {
                            am.removeClass('fixed').removeAttr('style');
                            $('.close-article-menu').addClass('hide');
                        }
                    }
                }
                $('.close-article-menu').click(function () {
                    am.addClass('hide');
                });
            }

        }
    });
}

function initScrollMenu() {
    var topmenu = $("#topmenu"); //得到导航对象
    var mainmenu = $("#mainmenu"); //得到导航对象
    var win = $(window); //得到窗口对象
    var sc = $(document);//得到document文档对象。
    var am = $(".article-module");// 文章目录对象
    bindScroll();
    win.scroll(function () {
        bindScroll();
    });
    function bindScroll(){
        if (sc.scrollTop() >= 100) {
            if (!mainmenu.hasClass("transparent")) {
                topmenu.animate({opacity: '0'}, 0);
                mainmenu.addClass('transparent');
                if (win.width() > 768) {
                    mainmenu.animate({top: '0', 'z-index': 1000}, 1);
                }
            }
        } else {
            topmenu.animate({opacity: '1'}, 0);
            mainmenu.removeClass('transparent');
            if (win.width() > 768) {
                mainmenu.animate({top: '30', 'z-index': 998}, 1);
            }
        }
    }
}
var PaymentUtils = window.payment || {
    config: [{url: appConfig.qiuniuBasePath + appConfig.zfbPraiseCode, desc: '支付宝转账'},{url: appConfig.qiuniuBasePath + appConfig.wxPraiseCode, desc: '微信转账'}],
    show : function () {
        $("#reward").modal('show');
        this.change(0);
        $("#reward input").on('ifChecked', function(event){
            var index = $(this).data("index");
            PaymentUtils.change(index);
        });
    },
    hide : function () {
        $("#reward").modal('hide');
    },
    change: function (index) {
        var config = this.config[index];
        $("#qrcode-container").empty();
        $('<img  src="' + config.url + '" style="width: 250px;height: auto;" alt="'+config.desc+'">').appendTo($("#qrcode-container"));
    }

};
$(function () {

    $(document).ready(function () {
        NProgress.start();
    });
    $(window).load(function () {
        NProgress.done();
    });

    initNavbar();
    initArticeMenu();
    initScrollMenu();

    console.group("关于本站");
    console.log("写博客、记日志、闲聊扯淡鼓捣技术\n志同道合者欢迎进QQ交流群（190886500）");
    console.groupEnd();
    console.log("%c生活真他妈好玩，因为生活老他妈玩我！", "color:green;font-size:20px;font-weight:blod");
    console.groupEnd();
    console.log("爱谁谁...");

    $('.to-top').toTop({
        autohide: true,//返回顶部按钮是否自动隐藏。可以设置true或false。默认为true
        offset: 100,//页面滚动到距离顶部多少距离时隐藏返回顶部按钮。默认值为420
        speed: 500,//滚动和渐隐的持续时间，默认值为500
        right: 25,//返回顶部按钮距离屏幕右边的距离，默认值为15
        bottom: 50//返回顶部按钮距离屏幕顶部的距离，默认值为30
    });

    $("[data-toggle='tooltip']").tooltip();
    $('[data-toggle="popover"]').popover();

    // 图片预览
    $(".showImage").fancybox();

    // # NProgress页面顶部进度条
    $(document).ajaxStart(function () {
        NProgress.start();
    }).ajaxStop(function () {
        NProgress.done();
    });
    // Loading弹窗
    // $(document).ajaxStart(function () {
    //     $("#loading").show();
    // }).ajaxStop(function () {
    //     $("#loading").hide();
    // });

    if ($("#scrolldiv")) {
        $("#scrolldiv").textSlider({line: 1, speed: 300, timer: 5000});
    }

    if ($.rating) {
        $.rating.init(5);
    }

    if ($.bubble) {
        $.bubble.init();
    }

    getCurrentDate();
    setInterval(function () {
        getCurrentDate();
    }, 1000);
    function getCurrentDate(){
        var now = new Date();
        var weekArr = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');
        $("#currentTime").html(now.format('yyyy年MM月dd日 hh时mm分ss秒') + " " + weekArr[now.getDay()]);
    }

    $.websocket.open({
        host: "ws://localhost:8443/websocket",
        reconnect: true,
        callback: function (json) {
            var onlineCount = json;
            $(".online").html(onlineCount);
        }
    });

    /**
     * 显示取链的表格
     */
    $(".showContent").click(function () {
        $(this).toggleClass('fa-plus-square fa-minus-square');
        // $(".disable-content").toggleClass('fade-in hide');
        $(".disable-content").slideToggle(400);
    });

    if(/iphone|ipod|ipad|ipad|mobile/i.test(navigator.userAgent.toLowerCase())){
        $('.share-sd').click(function() {
            $('#share').animate({
                        opacity: 'toggle',
                        top: '-80px'
                    },
                    500).animate({
                        top: '-60px'
                    },
                    'fast');
            return false;
        });
    } else {
        $(".share-sd").mouseover(function() {
            $(this).children("#share").show();
        });
        $(".share-sd").mouseout(function() {
            $(this).children("#share").hide();
        });
    }

    $("#social .like").click(function () {
        var $this = $(this);
        var $a = $(this).find("a");
        var $count = $a.find("i.count");
        var id = $a.data("id");
        $.bubble.unbind();
        $.ajax({
            type: "post",
            url: "/api/doPraise/" + id,
            success: function (json) {
                $.alert.ajaxSuccess(json);
                if(json.status === 200){
                    $this.effectBubble({y:-80, className:'thumb-bubble', fontSize: 1, content: '<i class="fa fa-smile-o"></i>+1'});
                    $count.text(parseInt($count.text()) + 1);
                }
                $.bubble.init();
            },
            error: function () {
                $.alert.ajaxError();
                $.bubble.init();
            }
        });
    });

    $("img.lazy-img").lazyload({
        placeholder : appConfig.staticPath + "/img/loading.gif",
        effect: "fadeIn",
        threshold: 100
    });
    $(window).bind("load", function() {
        var timeout = setTimeout(function() {
            $("img.lazy-img").trigger("sporty");
        }, 3000);
    });

    /* 热门搜索标签点击事件 */
    $(".search-hot li").click(function () {
        var $this = $(this);
        var text = $this.find("a span").text();
        $this.parents(".searchForm").find("input[name=keywords]").val(text);
        $this.parents(".searchForm").find(".nav-search-btn").click();
    });

    /* 分页按钮点击事件 */
    $(".page-btn li a").click(function () {
        var $this = $(this);
        var $parents = $this.parents(".page-btn");
        var search = $parents.data("search");
        var url = $parents.data("url");
        var pageNum = $this.data("page") || 1;
        if(!pageNum){
            return;
        }
        var action = url + "/" + pageNum;

        if(search){
            $("#searchForm").find("input[name=pageNumber]").val(pageNum);
            $(".nav-search-btn").click();
        } else {
            window.location.href = action;
        }
    });

    /* 首页通知 */
    if($('#notice-box') && $('#notice-box')[0]){
        $.ajax({
            type: "post",
            url: "/api/listNotice",
            success: function (json) {
                if(json.status == 200 && json.data && json.data.length > 0){
                    var tpl = '{{#data}}<li class="scrolltext-title">'
                            + '<a href="javascript:void(0)" rel="bookmark">{{&content}}</a>'
                            + '</li>{{/data}}';
                    var html = Mustache.render(tpl, json);
                    $("#notice-box").html(html);
                }
            },
            error: function () {
                $.alert.ajaxError();
            }
        });
    }

    $('#myCarousel').mouseover(function () {
        $(".carousel-control").removeClass("hide");
    }).mouseout(function () {
        $(".carousel-control").addClass("hide");
    }).carousel({
        interval: 5000
    });
});
