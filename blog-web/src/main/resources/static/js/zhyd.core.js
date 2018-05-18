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
 * 项目核心Js库，主要包含核心工具类和 相关插件
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2017-09-02
 * @since 1.0
 */
(function ($) {
    $.extend({
        notification: {
            isSupported: function () {
                return 'Notification' in window;
            },
            isPermissionGranted: function () {
                return Notification.permission === 'granted';
            },
            requestPermission: function () {
                //验证浏览器是否支持Notification，如果不支持打印提示信息并返回
                if (!this.isSupported()) {
                    // console.log('当前浏览器不支持Notification API');
                    return;
                }
                //该方法将会询问用户是否允许显示通知,不能由页面调用(onload)，必须由用户主动事件触发(onclick等)
                //当用户同意之后，再次调用该方法则无效，即该方法仅对Notification.Permission不为'granted'的时候起作用
                Notification.requestPermission(function (status) {
                    //status是授权状态，如果用户允许显示桌面通知，则status为'granted'
                    // console.log('status: ' + status);
                    //permission只读属性:
                    //  default 用户没有接收或拒绝授权 不能显示通知
                    //  granted 用户接受授权 允许显示通知
                    //  denied  用户拒绝授权 不允许显示通知
                    var permission = Notification.permission;
                    // console.log('permission: ' + permission);
                });
            },
            show: function (options) {
                // this.requestPermission();
                if (!this.isSupported()) {
                    // console.log('当前浏览器不支持Notification API');
                    return;
                }
                if (!this.isPermissionGranted()) {
                    // console.log('当前页面未被授权使用Notification通知');
                    return;
                }
                var op = $.extend({
                    title: "您有一条新消息",
                    icon: appConfig.staticPath + '/img/notification.png',
                    delay: 5000,
                    notification: null
                }, options);

                var n = new Notification(op.title, {
                    icon: op.icon,
                    noscreen: true,
                    body: op.notification
                });
                //  用户点击之后当天不再显示
                var hide = localStorage.getItem("hide");
                if (!hide || parseInt(hide) < new Date().getDate()) {
                    n.onshow = function () {
                        setTimeout(function () {
                            n.close();
                        }, op.delay);
                    };
                }


                n.onclick = function () {
                    // 打开视图?
                    n.close();
                    //  用户点击之后当天不再显示
                    localStorage.setItem("hide", new Date().getDate());
                };

                n.onerror = function () {
                    // console.log('产生错误');
                };

                n.onclose = function () {
                    // console.log('关闭通知窗口');
                };
            }
        }
    });
})(jQuery);
(function ($) {
    $.extend({
        websocket: {
            _this: null,
            _initialized: false,
            init: function (options) {
                if (!this.isSupported()) {
                    // console.error('Not support websocket');
                    return;
                }
                var op = $.extend({
                    callback: function () {
                    },
                    host: null,
                    reconnect: false
                }, options);
                if (!op.host) {
                    // console.error("初始化WebSocket失败，无效的请求地址");
                    return;
                }
                try {
                    this._this = new WebSocket(op.host);
                } catch (error) {
                    return;
                }
                this._initialized = true;
                //连接发生错误的回调方法
                this._this.onerror = function () {
                    // console.log("与服务器连接失败...");
                };

                //连接成功建立的回调方法
                this._this.onopen = function (event) {
                    // console.log("与服务器连接成功...");
                };

                //接收到消息的回调方法
                this._this.onmessage = function (event) {
                    // dwz.notification.show({notification: event.data});
                    op.callback(event.data);
                    // console.log("接收到服务器端推送的消息：" + event.data);
                };

                //连接关闭的回调方法
                this._this.onclose = function () {
                    $.websocket._initialized = false;
                    // console.log("已关闭当前链接");
                    if (op.reconnect) {
                        // 自动重连
                        setTimeout(function () {
                            $.websocket.open(op);
                        }, 5000);
                    }
                }
            },
            open: function (options) {
                var op = $.extend({
                    callback: function () {
                    },
                    host: null,
                    reconnect: false
                }, options);

                if (this._initialized) {
                    this.close();
                }
                this.init(options);
                //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
                window.onbeforeunload = function () {
                    // console.log("窗口关闭了");
                    $.websocket.close();
                }
            },
            isSupported: function () {
                return 'WebSocket' in window;
            },
            send: function (message) {
                if (!this._this) {
                    return;
                }
                this._this.send(message);
            },
            close: function () {
                if (!this._this) {
                    return;
                }
                this._this.close();
            }
        }
    });
})(jQuery);
(function ($) {
    // 覆盖jquery-confirm中的函数
    $.jqAlert = $.alert;
    $.jqConfirm = $.confirm;

    $.extend({
        alert: {
            info: function (content, delayTime, callback) {
                delayTime = delayTime ? "confirm|" + delayTime : "confirm|3000";
                $.jqAlert({
                    title: '友情提示',
                    content: content,
                    confirmButton: '关闭',
                    autoClose: delayTime,
                    confirm: callback
                });
            },
            error: function (content, delayTime, callback) {
                delayTime = delayTime ? "confirm|" + delayTime : "confirm|3000";
                $.jqAlert({
                    title: '警告',
                    content: content,
                    confirmButton: '关闭',
                    autoClose: delayTime,
                    confirm: callback
                });
            },
            confirm: function (content, confirmCallback, cancelCallback, delayTime) {
                delayTime = delayTime ? "cancel|" + delayTime : "cancel|5000";
                $.jqConfirm({
                    confirmButtonClass: 'btn-success',
                    cancelButtonClass: 'btn-default',
                    title: '友情提示',
                    content: content,
                    autoClose: delayTime,
                    confirmButton: '确定',
                    cancelButton: '关闭',
                    confirm: confirmCallback,
                    cancel: cancelCallback
                });
            },
            ajaxSuccessConfirm: function (json, callback) {
                if (json.status == 200) {
                    if(json.message){
                        $.alert.confirm(json.message, callback);
                    }
                } else {
                    if(json.message){
                        $.alert.error(json.message);
                    }
                }
            },
            ajaxSuccess: function (json) {
                if (json.status == 200) {
                    if(json.message){
                        $.alert.info(json.message);
                    }
                } else {
                    if(json.message){
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
        tool: {
            isEmpty: function (value) {
                return value == null || this.trim(value) == "";
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
                // 域
                var domain = document.domain;
                // 当前页
                var nowurl = document.URL;
                // 来源页
                var fromurl = document.referrer;

                return window.location.pathname;
            },
            getMeta: function (name) {
                var meta = document.getElementsByTagName('meta');
                var share_desc = '';
                for (var i in meta) {
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
            parseFormSerialize: function (serialize) {
                var result = {};
                if (!serialize) {
                    return null;
                }
                var datas = serialize.split("&");
                for (var i = 0, len = datas.length; i < len; i++) {
                    var singleInfo = datas[i].split("=");
                    result[singleInfo[0]] = decodeURIComponent(singleInfo[1]);
                }
                return result;
            }
        }
    });
    /**
     * 扩展String方法
     */
    $.extend(String.prototype, {
        trim: function () {
            return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
        },
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
    $.extend(Date.prototype, {
        format: function (format) {
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
        }
    });

    /* 星星点赞 */
    $.extend({
        rating: {
            _rating: ".rating",
            init: function (count) {
                count = (count || 5) + 1;
                $(this._rating).each(function () {
                    var $this = $(this);
                    var starCount = parseInt($this.attr("data-star") || 0);
                    var star = '', active = '';
                    for (var i = 1; i < count; i++) {
                        if (starCount > 0 && i <= starCount) {
                            active = 'active';
                        }
                        star += '<i class="fa fa-star-o ' + active + '"></i> ';
                        active = '';
                    }
                    $this.html(star);

                    if ($this.hasClass("ignore")) {
                        return;
                    }

                    /* 绑定星星的鼠标滑过事件 */
                    var $i = $this.find("i");
                    $i.each(function () {
                        $(this).mouseover(function () {
                            $i.each(function () {
                                $(this).removeClass("active");
                            });
                            $(this).addClass("active");
                            var brotherNode = $(this).prevAll();
                            brotherNode.each(function () {
                                $(this).addClass("active");
                            });
                        });
                    });

                    /* 绑定星星的点击事件 */
                    $this.on('click', 'i', function () {
                        var brotherNode = $(this).prevAll();
                        var selectedCount = brotherNode.length + 1;
                    });
                });
            }
        }
    });

    /* 鼠标点击向上冒泡弹出提示动画 */
    $.extend({
        bubble: {
            _tip: ['法制', '爱国', '敬业', '诚信', '友善', '富强', '民主', '文明', '和谐', '自由', '平等', '公正'],
            init: function () {
                var bubbleIndex = 0;
                $('body').click(function (e) {
                    bubbleIndex = bubbleIndex >= $.bubble._tip.length ? 0 : bubbleIndex;
                    if (!e.originalEvent) {
                        return;
                    }
                    var x = e.originalEvent.x || e.originalEvent.layerX || 0;
                    var y = e.originalEvent.y || e.originalEvent.layerY || 0;
                    var html = '<span style="position: fixed;z-index:9999;left: ' + x + 'px;top: ' + y + 'px;"></span>';
                    var $box = $(html).appendTo($(this));
                    $box.effectBubble({
                        y: -100,
                        className: 'thumb-bubble',
                        fontSize: 0.5,
                        content: '<i class="fa fa-smile-o"></i>' + $.bubble._tip[bubbleIndex]
                    });
                    setTimeout(function () {
                        $box.remove();
                    }, 1002);
                    bubbleIndex++;
                });
            },
            unbind: function (duration) {
                $("body").unbind('click');
                if (duration && !isNaN(duration = parseInt(duration))) {
                    setTimeout(function () {
                        $.bubble.init();
                    }, duration);
                }
            }
        }
    });
    /* 鼠标点击弹出提示动画 */

    $.fn.extend({
        // 翻牌效果
        flipCard: function (options) {
            var op = $.extend({}, options);
            return this.each(function () {
                var $box = $(this);
                $box.removeClass('animation-rotate-up').removeClass('animation-rotate-up-back');
                $box.addClass("animation-rotate-up");
                setTimeout(function () {
                    $box.addClass("animation-rotate-up-back");
                }, 500);
            });
        },

        // 文字向上冒泡
        effectBubble: function (options) {
            var op = $.extend({content: '+1', y: -100, duration: 1000, effectType: 'ease', className: '', fontSize: 2}, options);

            return this.each(function () {
                var $box = $(this), flyId = 'effect-fly-' + (new Date().getTime());

                var tpl = '<span id="#flyId#" class="effect-bubble-fly #class#" style="opacity: 1;top:#top#px;left:#left#px;font-size: #fontSize#rem;">#content#</span>';
                var html = tpl.replaceAll('#left#', 12).replaceAll('#top#', -8)
                        .replaceAll('#flyId#', flyId).replaceAll('#content#', op.content)
                        .replaceAll('#class#', op.className).replaceAll('#fontSize#', op.fontSize);

                var $fly = $(html).appendTo($box);
                $fly.fadeIn(100, "swing", function () {
                    $fly.animate({top: op.y, opacity: 0}, 100, function () {
                        $fly.fadeOut(op.duration, function () {
                            $fly.remove();
                        });
                    });
                });
            });
        }
    });
})(jQuery);

/* 返回顶部插件 */
(function ($) {
    $.fn.toTop = function (opt) {
        //variables
        var elem = this;
        var win = $(window);
        var doc = $('html, body');
        var options = opt;
        //如果没有配置自定义的参数，则使用默认
        if (!options) {
            options = $.extend({
                autohide: true,
                offset: 100,
                speed: 500,
                right: 15,
                bottom: 50
            }, opt);
        }
        elem.css({
            'position': 'fixed',
            'right': options.right,
            'bottom': options.bottom,
            'cursor': 'pointer'
        });
        if (options.autohide) {
            elem.css('display', 'none');
        }
        elem.click(function () {
            doc.animate({scrollTop: 0}, options.speed);
        });
        win.scroll(function () {
            var scrolling = win.scrollTop();
            if (options.autohide) {
                if (scrolling > options.offset) {
                    elem.fadeIn(options.speed);
                }
                else elem.fadeOut(options.speed);
            }
        });
    };
})(jQuery);
/* 返回顶部插件 end */

/* 文字滚动 start */
(function ($) {
    $.fn.textSlider = function (settings) {
        settings = jQuery.extend({
            speed: "normal",
            line: 2,
            timer: 3000
        }, settings);
        return this.each(function () {
            $.fn.textSlider.scllor($(this), settings);
        });
    };
    $.fn.textSlider.scllor = function ($this, settings) {
        var ul = $("ul:eq(0)", $this);
        var timerID;
        var li = ul.children();
        var liHight = $(li[0]).height();
        var upHeight = 0 - settings.line * liHight;//滚动的高度；
        var scrollUp = function () {
            ul.animate({marginTop: upHeight}, settings.speed, function () {
                for (i = 0; i < settings.line; i++) {
                    ul.find("li:first", $this).appendTo(ul);
                }
                ul.css({marginTop: 0});
            });
        };
        var autoPlay = function () {
            timerID = window.setInterval(scrollUp, settings.timer);
        };
        var autoStop = function () {
            window.clearInterval(timerID);
        };
        //事件绑定
        ul.hover(autoStop, autoPlay).mouseout();
    };
})(jQuery);
/* 文字滚动 end */