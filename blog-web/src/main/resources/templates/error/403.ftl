<#include "include/macros.ftl">
<@header title="Forbidden :("></@header>
<div class="container custome-container">
    <div class="clearfix"></div>
    <div class="row blog-main">
        <div class="blog-body forbidden" style="overflow: hidden;">
            <div class="col-md-6 left">
                <div class="error-container text-center">
                    <p class="text-center"><i class="fa fa-info-circle"></i><strong>系统检测到您的访问状态异常，已被临时限制访问！</strong></p>
                    <p class="forbidden-img"><img class="img-responsive center-block" src="${config.staticWebSite}/img/forbidden.jpg" alt="你已被拉入黑名单"></p>
                    <p class="text-center"><i class="fa fa-spinner fa-pulse fa-fw fa-2x"></i>系统将于 <em><span class="limit-expiration-time"></span></em>秒后解除限制</p>
                </div>
            </div>
            <div class="col-md-6 right">
                <blockquote class="pull-left">
                    <div class="bottom-line title"><i class="fa fa-question-circle-o"></i><strong>为什么会被限制？</strong></div>
                    <ol>
                        <li>1.快速的、频繁的、大量的刷新页面</li>
                        <li>2.疑是Spider</strong></li>
                        <li>3.系统抽风</li>
                    </ol>
                    <div class="clear"></div>
                    <div class="bottom-line title" style="margin-top: 2.5rem;"><i class="fa fa-question-circle-o"></i><strong>如何解除限制？</strong></div>
                    <ol>
                        <li>1.出门喝杯咖啡，一般来说回来的时候限制就被解除了 <i class="fa fa-coffee fa-2x"></i></li>
                        <li>2.文明上网，勿刷流量！</li>
                        <li>3.请联系<a target="_blank" href="javascript:void(0);" title="点击QQ联系我"onclick="window.open('tencent://message/?uin=${config.qq}&amp;Site=www.${config.domain}&amp;Menu=yes')" rel="external nofollow">网站管理员</a></strong></li>
                    </ol>
                    <div class="clear"></div>
                </blockquote>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script type="text/javascript">
        var expire = parseInt('${expire?c}' || 60);
        $(".limit-expiration-time").text(expire);
        var timer = setInterval(function () {
            if(expire <= 1){
                window.clearInterval(timer);
                window.location.reload();
            } else {
                $(".forbidden-img").toggleClass("shake");
                $(".limit-expiration-time").text(expire = expire - 1);
            }
        }, 1000);
    </script>
</@footer>
