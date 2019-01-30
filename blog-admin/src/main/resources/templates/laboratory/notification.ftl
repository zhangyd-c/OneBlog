<#include "/include/macros.ftl">
<@header>
    <style>
        .messanger {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -ms-flex-direction: column;
            flex-direction: column;
        }

        .messanger .messages {
            -webkit-box-flex: 1;
            -ms-flex: 1;
            flex: 1;
            margin: 10px 0;
            padding: 0 10px;
            max-height: 260px;
            overflow-y: auto;
            overflow-x: hidden;
        }

        .messanger .messages .message, .messanger .messages .ready {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            margin-bottom: 15px;
            -webkit-box-align: start;
            -ms-flex-align: start;
            align-items: flex-start;
        }

        .messanger .messages .message.me {
            -webkit-box-orient: horizontal;
            -webkit-box-direction: reverse;
            -ms-flex-direction: row-reverse;
            flex-direction: row-reverse;
        }

        .messanger .messages .message.me img {
            margin-right: 0;
            margin-left: 15px;
        }

        .messanger .messages .message.me .info {
            background-color: #009688;
            color: #FFF;
        }

        .messanger .messages .message.me .info:before {
            display: none;
        }

        .messanger .messages .message.me .info:after {
            position: absolute;
            right: -13px;
            top: 0;
            content: "";
            width: 0;
            height: 0;
            border-style: solid;
            border-width: 0 16px 16px 0;
            border-color: transparent #009688 transparent transparent;
            -webkit-transform: rotate(270deg);
            -ms-transform: rotate(270deg);
            transform: rotate(270deg);
        }

        .messanger .messages .message img {
            border-radius: 50%;
            margin-right: 15px;
            width: 48px;
        }

        .messanger .messages .message .info, .messanger .messages .ready .info {
            margin: 0;
            background-color: #ddd;
            padding: 5px 10px;
            border-radius: 3px;
            position: relative;
            -ms-flex-item-align: start;
            align-self: flex-start;
        }

        .messanger .messages .message .info:before {
            position: absolute;
            left: -14px;
            top: 0;
            content: "";
            width: 0;
            height: 0;
            border-style: solid;
            border-width: 0 16px 16px 0;
            border-color: transparent #ddd transparent transparent;
        }

        .messanger .sender {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
        }

        .messanger .sender input[type="text"] {
            -webkit-box-flex: 1;
            -ms-flex: 1;
            flex: 1;
            border: 1px solid #009688;
            outline: none;
            padding: 5px 10px;
        }

        .messanger .sender button {
            border-radius: 0;
        }
    </style>
</@header>
    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <@breadcrumb>
                <ol class="breadcrumb">
                    <li><a href="/">首页</a></li>
                    <li class="active">socket通知</li>
                </ol>
            </@breadcrumb>
            <div class="x_panel">
                <div class="x_title">
                    <h2>向web端发送消息通知 <small>需要web端用户授权</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content" style="display: flex;justify-content: center;">
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2><i class="fa fa-whatsapp"></i> 聊天 <small>socket通信，当前在线: ${online!("0")}</small></h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li></li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div class="messanger">
                                    <div class="messages">
                                        <div class="ready">
                                            <p class="info">......</p>
                                        </div>
                                        <#--<div class="message">
                                            <img src="/assets/images/user.png">
                                            <p class="info">Hello there!<br>Good Morning</p>
                                        </div>
                                        <div class="message me">
                                            <img src="/assets/images/loading.gif">
                                            <p class="info">Hi<br>Good Morning</p>
                                        </div>
                                        <div class="message"><img src="/assets/images/user.png">
                                            <p class="info">How are you?</p>
                                        </div>
                                        <div class="message me"><img src="/assets/images/loading.gif">
                                            <p class="info">I'm Fine.</p>
                                        </div>-->
                                    </div>
                                    <div class="sender">
                                        <input type="text" name="msg" id="msg" placeholder="Send Message">
                                        <button class="btn btn-primary" id="send-btn" type="button" style="margin: 0;"><i class="fa fa-lg fa-fw fa-paper-plane"></i></button>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script type="text/javascript">
        $("#send-btn").click(function () {
            var $messages = $(".messages");
            var msg = $("#msg").val();
            $.post("/api/notice", {msg : msg}, function (json) {
                if (json.status == 200) {
                    $messages.append('<div class="message me"><img src="/assets/images/loading.gif"><p class="info">' + msg + '</p></div>');
                } else {
                    if (json.message) {
                        $.alert.error(json.message);
                    }
                }
            })
        });
    </script>

</@footer>