<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="baidu-site-verification" content="d4kcdwiGHw" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="${config.authorName} <${config.authorEmail}>">
    <title>${config.siteName} | 一个程序员的个人博客</title>
    <link rel="canonical" href="${config.siteUrl}" />
    <#include "layout/quote.ftl">
</head>
<body>
<#include "layout/header.ftl"/>
<div class="container custome-container">
    <div class="row">
        <div class="col">
            <form>
                <div class="form-group">
                    <label for="comment">聊天框:</label> <textarea class="form-control" rows="5" id="text"></textarea>
                </div>
                <div class="form-group">
                    <button onclick="$.websocket.send($('#text').val())" type="button" class="btn btn-primary">发送</button>
                    <button onclick="$.websocket.close()" type="button" class="btn btn-danger">关闭</button>
                </div>
                <div>
                    <ul class="list-group" id="message"></ul>
                </div>
            </form>
        </div>
    </div>
</div>
<#include "layout/footer.ftl"/>
<script>
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        $("#message").append("<li class=\"list-group-item\">" + innerHTML + "</li>");
    }

    $.websocket.open({
        host: "ws://localhost:8443/websocket",
        reconnect: true,
        callback: setMessageInnerHTML
    });
</script>
</body>
</html>
