/**
 * bootstrap版评论框
 *
 * @date 2018-01-05 10:57
 * @author zhyd(yadong.zhang0415#gmail.com)
 * @link https://github.com/zhangyd-c
 *
 *
 *
 * 'code', // 插入代码
 //			'head', // 标题
 'bold', // 粗体
 'italic', // 斜体
 'underline', // 下划线
 //			'strikeThrough', // 删除线
 //			'foreColor', // 文字颜色
 //			'backColor', // 背景颜色
 'image', // 插入图片
 'link', // 插入链接
 'list', // 列表
 //			'justify', // 对齐方式
 'quote', // 引用
 'emoticon', // 表情
 //			'table', // 表格
 //			'video', // 插入视频
 //			'undo', // 撤销
 //			'redo' // 重复
 *
 */
$.extend({
    comment: {
        detailKey: 'comment-detail',
        menus: ['code', 'bold', 'italic', 'underline', 'image', 'link', 'list', 'quote', 'emoticon'],
        sid: 0,
        _commentDetailModal: '',
        _detailForm: '',
        _detailFormBtn: '',
        _closeBtn: '',
        _commentPid: '',
        _commentPlace: '',
        _commentPost: '',
        _cancelReply: '',
        _commentReply: '',
        initDom: function () {
            $.comment._commentDetailModal = $('#comment-detail-modal');
            $.comment._detailForm = $('#detail-form');
            $.comment._detailFormBtn = $('#detail-form-btn');
            $.comment._closeBtn = $('#comment-detail-modal .close');
            $.comment._commentPid = $('#comment-pid');
            $.comment._commentPlace = $('#comment-place');
            $.comment._commentPost = $('#comment-post');
            $.comment._cancelReply = $('#cancel-reply');
            $.comment._commentReply = $('.comment-reply');
        },
        init: function (options) {
            var $box = $('#comment-box');
            if (!$box || !$box[0]) {
                return;
            }
            var op = $.extend({
                menus: $.comment.menus,
                customMenu: true
            }, options);
            var detailInfoJson = $.tool.parseFormSerialize(localStorage.getItem(this.detailKey));
            var currentUser = '';
            if(detailInfoJson){
                currentUser = '<small> - 欢迎回来，<a href="'+detailInfoJson.url+'" target="_blank" rel="external nofollow">' + filterXSS(detailInfoJson.nickname) + '<i class="fa fa-smile-o"></i></a></small>';
            }
            var commentBox = '<div id="comment-place">'
                    + '<div class="comment-post" id="comment-post" style="position: relative">'
                    + '<h4 class="bottom-line"><i class="fa fa-commenting-o fa-fw icon"></i><strong>发表评论</strong>' + currentUser + '</h4>'
                    + '<div class="cancel-reply" id="cancel-reply" style="display: none;"><a href="javascript:void(0);" onclick="$.comment.cancelReply(this)" rel="external nofollow"><i class="fa fa-share"></i>取消回复</a></div>'
                    + '<form class="form-horizontal" role="form" id="comment-form">'
                    + '<input type="hidden" name="pid" id="comment-pid" value="0" size="22" tabindex="1">'
                    + '<textarea id="comment_content" name="content" style="display: none"></textarea>'
                    + '<div id="editor" style="width: 100%;height: 150px;"></div>'
                    + '<div style="position: absolute;right: 10px;bottom: 65px;font-size: 14px;font-weight: 700;color: #ececec;">张亚东博客<br>https://www.zhyd.me<br>讲文明、要和谐</div>'
                    + '<a id="comment-form-btn" type="button" data-loading-text="正在提交评论..." class="btn btn-default btn-block">提交评论</a>'
                    + '</form></div></div>';
            $box.html(commentBox);
            // 初始化并缓存常用的dom元素
            $.comment.initDom();
            // 创建编辑框
            $.comment.createEdit(op);
            $.comment.loadCommentList($box);
            $.comment.initValidatorPlugin();
        },
        createEdit: function (options) {
            var $selector = '#editor';
            var E = window.wangEditor;
            var editor = new E($selector);
            if(options.customMenu){
                editor.customConfig.menus = options.menus;
            }

            // debug模式下，有 JS 错误会以throw Error方式提示出来
            editor.customConfig.debug = false;

            // 关闭粘贴样式的过滤
            editor.customConfig.pasteFilterStyle = false;
            // 插入网络图片的回调
            editor.customConfig.linkImgCallback = function (url) {
                // console.log(url) // url 即插入图片的地址
            };
            editor.customConfig.zIndex = 100;
            var $content = $('#comment_content');
            editor.customConfig.onchange = function (html) {
                // 让编辑器始终处于最底部
                // var $dom = document.getElementsByClassName("w-e-text")[0];
                // $dom.scrollTop = $dom.scrollHeight;
                // 监控变化，同步更新到 textarea
                $content.val(filterXSS(html));
            };
            editor.create();
            $content.val(editor.txt.html());
            E.fullscreen.init($selector);
        },
        loadCommentList: function (box, pageNumber) {
            var sid = box.attr("data-id");
            // 该属性为了静态页面中防止请求服务器特加的属性，实际可能不用。
            var autoLoad = box.attr("data-auto-load");
            if(autoLoad && autoLoad == "false"){
            	return false;
            }
            if(!sid){
                throw "未指定sid！";
            }
            this.sid = sid;
            $.ajax({
                type: "post",
                url: "/api/comments",
                data: {sid: sid, pageNumber: pageNumber || 1},
                success: function (json) {
                    $.alert.ajaxSuccess(json);
                    // 加载 评论列表 start
                    var commentList = json.data.commentList;
                    var commentListBox  = '';
                    if(!commentList){
                        commentListBox = '<div class="commentList">'
                                + '<h4 class="bottom-line"><i class="fa fa-comments-o fa-fw icon"></i><strong><em>0</em> 条评论</strong></h4>'
                                + '<ul class="comment">';
                        commentListBox += '<li><div class="list-comment-empty-w fade-in">'
                                +'<div class="empty-prompt-w">'
                                +'<span class="prompt-null-w">还没有评论，快来抢沙发吧！</span>'
                                +'</div>'
                                +'</div></li>';
                        // 加载 评论列表 end
                        commentListBox += '</ul></div>';
                        $(commentListBox).appendTo(box);
                    }else{
                        // 首次加载-刷新页面后第一次加载，此时没有点击加载更多进行分页
                        if(!pageNumber) {
                            commentListBox = '<div class="commentList">'
                                    + '<h5 class="bottom-line"><i class="fa fa-comments-o fa-fw icon"></i><strong><em>' + json.data.total + '</em> 条评论</strong></h5>'
                                    + '<ul class="comment">';
                        }
                        for(var i = 0, len = commentList.length; i < len ; i ++){
                            var comment = commentList[i];
                            var userUrl = comment.url || "javascript:void(0)";
                            var parent = comment.parent;
                            var parentQuote = parent ? '<a href="#comment-' + parent.id + '" class="comment-quote">@' + parent.nickname + '</a><div style="background-color: #f5f5f5;padding: 5px;margin: 5px;border-radius: 4px;"><i class="fa fa-quote-left"></i><p></p><div style="padding-left: 10px;">' + filterXSS(parent.content) + '</div></div>' : '';
                            commentListBox += '<li>' +
                                    '    <div class="comment-body fade-in" id="comment-'+comment.id+'">' +
                                    '        <div class="cheader">' +
                                    '           <div class="user-img"><img class="userImage" src="' + comment.avatar + '" onerror="this.src=\'' + appConfig.staticPath + '/img/user.png\'"></div>' +
                                    '           <div class="user-info">' +
                                    '              <div class="nickname">' +
                                    '                 <a target="_blank" href="' + userUrl + '" rel="external nofollow"><strong>' + comment.nickname + '</strong></a>' +
                                    '              </div>            ' +
                                    '             <div class="timer">' +
                                    '                  <i class="fa fa-clock-o fa-fw"></i>' + comment.createTimeString +
                                    '                  <i class="fa fa-map-marker fa-fw"></i>' + comment.address +
                                    '              </div>' +
                                    '          </div>' +
                                    '        </div>' +
                                    '        <div class="content">' + parentQuote + '<div>' + filterXSS(comment.content) + '</div></div>' +
                                    '        <div class="sign">' +
                                    '            <i class="icons os-' + comment.osShortName + '"></i>'+ comment.os +' <i class="sepa"></i>' +
                                    '            <i class="icons browser-' + comment.browserShortName + '"></i>' + comment.browser + ' <i class="sepa"></i>' +
                                    '            <a href="javascript:void(0);" class="comment-up" onclick="$.comment.praise(' + comment.id + ', this)"><i class="fa fa-thumbs-o-up"></i>赞(<span class="count">' + comment.support + '</span>)<i class="sepa"></i></a>' +
                                    '            <a href="javascript:void(0);" class="comment-down" onclick="$.comment.step(' + comment.id + ', this)"><i class="fa fa-thumbs-o-down"></i>踩(<span class="count">' + comment.oppose + '</span>)<i class="sepa"></i></a>' +
                                    '            <a href="javascript:void(0);" class="comment-reply" onclick="$.comment.reply(' + comment.id + ', this)"><i class="fa fa-reply"></i>回复</a>' +
                                    '            <a href="javascript:void(0);" class="comment-flag hide" onclick="$.comment.report(' + comment.id + ', this)"><i class="fa fa-flag"></i>举报</a>' +
                                    '        </div>' +
                                    '    </div>' +
                                    '</li>';
                        }
                        // 如果存在下一页，则显示加载按钮
                        if(json.data.hasNextPage){
                            commentListBox += '<li><div class="list-comment-empty-w fade-in">'
                                    +'<div class="empty-prompt-w">'
                                    +'<span class="prompt-null-w pointer load-more">加载更多 <i class="fa fa-angle-double-down"></i></span>'
                                    +'</div>'
                                    +'</div></li>';
                        }
                        // 加载 评论列表 end

                        if(!pageNumber) {
                            // 首次加载-刷新页面后第一次加载，此时没有点击加载更多进行分页
                            commentListBox += '</ul></div>';
                            $(commentListBox).appendTo(box);
                        }else{
                            // 点击加载更多时，列表追加到ul中
                            $(commentListBox).appendTo($(".comment"));
                        }

                        // 加载更多按钮
                        $(".load-more").click(function () {
                            $(this).parents('li').hide();
                            $.comment.loadCommentList(box, json.data.nextPage)
                        });
                    }
                },
                error: $.alert.ajaxError
            });
        },
        initValidatorPlugin: function () {
            $.comment._detailForm.bootstrapValidator({
                message: "输入值无效",
                feedbackIcons: {
                    valid: "fa fa-check",
                    invalid: "fa fa-remove",
                    validating: "fa fa-refresh"
                },
                fields: {
                    nickname: {
                        validators: {
                            notEmpty: {
                                message: "昵称必填"
                            }
                        }
                    },
                    url: {
                        validators: {
                            uri: {
                                message: "URL地址不正确"
                            }
                        }
                    },
                    email: {
                        validators: {
                            emailAddress: {
                                message: "邮箱地址不正确"
                            }
                        }
                    }
                }
            });
        },
        submit: function (target) {
            var $this = $(target);
            $this.button('loading');
            var detail = localStorage.getItem(this.detailKey);
            var data = $("#comment-form").serialize();
            if(!detail){
            }else{
                var detailInfoJson = $.tool.parseFormSerialize(detail);
                $.comment._detailForm.find("input").each(function () {
                    var $this = $(this);
                    var inputName = $this.attr("name");
                    if(detailInfoJson[inputName]){
                        $this.val(detailInfoJson[inputName]);
                    }
                });
                var $img = $.comment._detailForm.find('img');
                $img.attr('src', detailInfoJson.avatar);
                $img.removeClass('hide');

            }
            this._commentDetailModal.modal('show');
            this._closeBtn.unbind('click');
            this._closeBtn.click(function () {
                setTimeout(function () {
                    $this.html("<i class='fa fa-close'></i>取消操作...");
                    setTimeout(function () {
                        $this.button('reset');
                    }, 1000);
                }, 500);
            });
            // 模态框抖动
            this._commentDetailModal.find('.modal-content').addClass("shake");
            $.comment._detailForm.find("input[name=qq]").unbind('change');
            $.comment._detailForm.find("input[name=qq]").change(function () {
                var $this = $(this);
                var qq = $this.val();
                var $nextImg = $this.next('img');
                if(qq){
                    $.ajax({
                        type: "post",
                        url: "/api/qq/" + qq,
                        success: function (json) {
                            $.alert.ajaxSuccess(json);
                            var data = json.data;
                            $.comment._detailForm.find("input").each(function () {
                                var $this = $(this);
                                var inputName = $this.attr("name");
                                if(data[inputName]){
                                    $this.val(data[inputName]);
                                }
                            });
                            $nextImg.attr('src', data.avatar);
                            $nextImg.removeClass('hide');
                        },
                        error: $.alert.ajaxError
                    });
                }else{
                    $nextImg.addClass('hide');
                }

            });

            // 提交评论
            this._detailFormBtn.unbind('click');
            this._detailFormBtn.click(function () {
                $.comment._detailForm.bootstrapValidator("validate");
                if (_form.valid($.comment._detailForm)) {
                    data = data + "&" + $.comment._detailForm.serialize();
                    localStorage.setItem($.comment.detailKey, $.comment._detailForm.serialize());
                    submitForm(data);
                }
            });

            function submitForm(data) {
                $.ajax({
                    type: "post",
                    url: "/api/comment",
                    data: data + '&sid=' + $.comment.sid,
                    success: function (json) {
                        $.alert.ajaxSuccess(json);
                        $.comment._commentDetailModal.modal('hide');

                        setTimeout(function () {
                            $this.html("<i class='fa fa-check'></i>" + json.message);
                            setTimeout(function () {
                                $this.button('reset');
                                window.location.reload();
                            }, 3000);
                        }, 1000);
                    },
                    error: function (data) {
                        $.alert.ajaxError();
                        $this.button('reset');
                    }
                });
            }
        },
        reply: function (pid, target) {
            this._commentPid.val(pid);
            this._cancelReply.show();
            this._commentPost.find('h5 i').addClass("shake");
            $(target).hide();
            $(target).parents('.comment-body').append(this._commentPost);
        },
        cancelReply: function (target) {
            this._commentPid.val("");
            this._cancelReply.hide();
            $(target).parents(".comment-body").find('.comment-reply').show();
            this._commentPost.find('h5 i').addClass("shake");
            this._commentPlace.append(this._commentPost);
        },
        /* 赞 */
        praise: function (pid, target) {
            $.bubble.unbind();
            $.ajax({
                type: "post",
                url: "/api/doSupport/" + pid,
                success: function (json) {
                    $.alert.ajaxSuccess(json);
                    if(json.status == 200){
                        $(target).effectBubble({y:-80, className:'thumb-bubble', fontSize: 1, content: '<i class="fa fa-smile-o"></i>+1'});
                        var oldCount = $(target).find('span.count').text();
                        $(target).find('span.count').text(parseInt(oldCount) + 1);
                    }
                    $.bubble.init();
                },
                error: function () {
                    $.alert.ajaxError();
                    $.bubble.init();
                }
            });
        },
        /* 踩 */
        step: function (pid, target) {
            $.bubble.unbind();
            $.ajax({
                type: "post",
                url: "/api/doOppose/" + pid,
                success: function (json) {
                    $.alert.ajaxSuccess(json);
                    if(json.status == 200){
                        $(target).effectBubble({y:-80, className:'thumb-bubble', fontSize: 1, content: '<i class="fa fa-meh-o"></i>+1'});
                        var oldCount = $(target).find('span.count').text();
                        $(target).find('span.count').text(parseInt(oldCount) + 1);
                    }
                    $.bubble.init();
                },
                error: function () {
                    $.alert.ajaxError();
                    $.bubble.init();
                }
            });
        },
        /* 举报 */
        report: function (pid, target) {
        }
    }
});

$(function () {
    $.comment.init({customMenu: true});

    $("#comment-form-btn").click(function () {
        $.comment.submit($(this));
    });

    window.wangEditor.fullscreen = {
        init: function (editorSelector) {
            $(editorSelector + " .w-e-toolbar").append('<div class="w-e-menu"><a class="_wangEditor_btn_fullscreen" href="###" onclick="window.wangEditor.fullscreen.toggleFullscreen(\'' + editorSelector + '\')" data-toggle="tooltip" data-placement="bottom" title data-original-title="全屏编辑"><i class="fa fa-expand"></i></a></div>')
        }, toggleFullscreen: function (editorSelector) {
            $(editorSelector).toggleClass('fullscreen-editor');
            var $a = $(editorSelector + ' ._wangEditor_btn_fullscreen');
            var $i = $a.find("i:first-child");
            if ($i.hasClass("fa-expand")) {
                $a.attr("data-original-title", "退出全屏");
                $i.removeClass("fa-expand").addClass("fa-compress")
            } else {
                $a.attr("data-original-title", "全屏编辑");
                $i.removeClass("fa-compress").addClass("fa-expand")
            }
        }
    };
});