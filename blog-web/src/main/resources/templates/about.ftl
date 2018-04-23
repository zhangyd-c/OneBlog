<#include "include/macros.ftl">
<@header title="关于 | ${config.siteName}"
    keywords="${config.siteName},关于博客"
    description="一个程序员的个人博客,关于我的个人原创博客 - ${config.siteName}"
    canonical="/about">
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>关于
    </nav>
    <div class="row">
        <@blogHeader title="关于本站"></@blogHeader>
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <h5 class="legend-title">博主简介</h5>
                <div class="info">
                    <p>
                        10多年寒窗过来的。高中时我班长兼信息技术科课代表，也算由此开始了我与软件开发之间的缘分之路。大学主修Java方向，在校两年，两年下来自我感觉还不错，第三年下半学期去实习。
                        还好，进了GLD在山东的研发分公司，因为是校招，基础工资不算高（但是大公司节日福利很不错），干了将近一年。后来因为女朋友来了北京，所以在去年10月份索性辞了工作，直接来了帝都。
                    </p>
                    <p>
                        目前北漂于帝都。这繁华，醉我心。没进京之前，我还是一个只有120斤的苗条帅小伙，现在我已经有<del>130斤</del>(140斤)，涨了<del>9斤</del>(19斤)肉外加吸了1斤霾
                    </p>
                    <p>
                        2016年10月27日，小雨，来北京马上就1年整了，至今一事无成，是不是该调整自己了？不过，难啊，迈出第一步是最难的...
                    </p>
                    <blockquote style="overflow: hidden">
                        人生不过几十载，
                        又能几次重头来。
                        本就匆匆过路客，
                        缘何俗事惹自哀？<br>
                        世人笑我太痴呆，
                        我笑他人实不该。
                        不被凡尘俗事扰，
                        且安且得且自在。
                        <br>
                    </blockquote>
                </div>
                <h5 class="legend-title">关于博客</h5>
                <div class="info">
                    构思于<kbd>2016年10月</kbd>中旬，上线于<kbd>2016年10月27日</kbd>。本站为非商业化站点，无盈利性质，为博主个人博客。
                    本站所有标注为原创的文章，皆为站长亲自创作，因能力有限，可能部分内容并不能引起各位共鸣，若各位有那么一丝丝的空闲时间，劳烦各位能将我文章或者网站不足的地方提出来。
                    <p><kbd>授之以鱼不如授之以渔。</kbd></p>
                    <p>感谢各位哥哥姐姐弟弟妹妹领导以及领导夫人，感谢STV、BTV、SBTV...好！
                </div>
                <h5 class="legend-title">关于版权</h5>
                <div class="info">
                    本站大多数文章为站长原创，可能会有少部分文章系从互联网上转载过来的。一般能确认原创出处的，都会在文章中注明。但若因此造成侵权行为，烦请原作者<a target="_blank" href="mailto:yadong.zhang0415@gmail.com" title="点击给我发邮件" data-toggle="tooltip" data-placement="bottom" rel="external nofollow"><strong>告知</strong></a>，我会立刻删除相关内容。
                </div>
                <div class="alert alert-warning alert-dismissible simple-alert fade-in" role="alert">
                    <strong>注!</strong> 如果您觉得我的网站能够帮助到您，请您多多支持。我的支付宝账号：<span class="label label-success">1602008388#qq.com</span>（#==@）！
                    扫码点击：<a class="btn btn-danger btn-sm reward" onclick="PaymentUtils.show();">打赏</a>
                </div>
            </div>
        </div>
        <#--<div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div id="comment-box" data-id="-3"></div>
            </div>
        </div>-->
    </div>
</div>

<@footer>
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>
</@footer>
