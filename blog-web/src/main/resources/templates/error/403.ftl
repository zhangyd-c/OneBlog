<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="${config.authorName} <${config.authorEmail}>">
    <title>Forbidden :(</title>
    <#include "/layout/quote.ftl">
</head>
<body>
<#include "/layout/header.ftl"/>
<div class="container custome-container">
    <div class="clearfix"></div>
    <div class="row blog-main">
        <div class="blog-body forbidden">
            <div class="col-md-6 left">
                <div class="error-container text-center">
                    <p class="text-center"><i class="fa fa-info-circle"></i><strong>系统检测到您的访问状态异常，已被临时限制访问！</strong></p>
                    <p class="forbidden-img"><img class="img-responsive center-block" src="${config.staticWebSite}/img/forbidden.jpg" alt="你已被拉入黑名单"></p>
                    <p class="text-center"><i class="fa fa-spinner fa-pulse fa-fw fa-2x"></i>系统将于 <em><span class="limit-expiration-time"></span></em>秒后解除限制</p>
                </div>
            </div>
            <div class="col-md-6 right">
                <blockquote class="pull-left">
                    <div class="bottom-line title"><i class="fa fa-question-circle-o"></i><strong>我为什么会被限制？</strong></div>
                    <ol>
                        <li>1.快速的、频繁的、大量的刷新页面</li>
                        <li>2.疑是Spider</strong></li>
                        <li>3.系统抽风</li>
                    </ol>
                    <div class="clear"></div>
                    <div class="bottom-line title"><i class="fa fa-question-circle-o"></i><strong>如何解除限制？</strong></div>
                    <ol>
                        <li>1.出门喝杯咖啡，一般来说回来的时候限制就被解除了 <i class="fa fa-coffee fa-2x"></i></li>
                        <li>2.请联系<a target="_blank" href="javascript:void(0);" title="点击QQ联系我"onclick="window.open('tencent://message/?uin=843977358&amp;Site=www.${config.domain}&amp;Menu=yes')" rel="external nofollow">网站管理员</a></strong></li>
                    </ol>
                    <div class="clear"></div>
                    <div class="bottom-line title"><i class="fa fa-question-circle-o"></i><strong>限制规则是怎么样的</strong></div>
                    <ol>
                        <li>前两次访问异常，系统只需要进行人机校验即可正常访问</li>
                        <li>从第三次开始，限制规则如下：</li>
                        <li>第三次异常访问，限制<strong>5分钟</strong></li>
                        <li>第四次异常访问，限制<strong>10分钟</strong></li>
                        <li>第五次异常访问，限制<strong>15分钟</strong></li>
                        <li>...</li>
                        <li>以此类推</li>
                    </ol>
                </blockquote>
            </div>
        </div>
    </div>
</div>
<#include "/layout/footer.ftl"/>
</body>
<script type="text/javascript">
    var expire = parseInt('${expire?c}' || 60);
    $(".limit-expiration-time").text(expire);
    var timer = setInterval(function () {
        console.log(expire);
        if(expire <= 0){
            console.log("限制结束，将刷新页面");
            window.clearInterval(timer);
            window.location.reload();
        }
        $(".forbidden-img").toggleClass("shake");
        expire = expire - 1;
        $(".limit-expiration-time").text(expire);
    }, 1000);
</script>
</html>
