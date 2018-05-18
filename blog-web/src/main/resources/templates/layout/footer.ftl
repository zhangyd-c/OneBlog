<#-- 搜索弹窗 -->
<div class="modal fade nav-search-box" tabindex="-1" role="dialog" aria-labelledby="navSearchModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header" style="padding: 5px 15px;">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: 10px;"><span aria-hidden="true">&times;</span></button>
                <h4><i class="fa fa-search"></i> 搜索文章</h4>
            </div>
            <div class="modal-body">
                <form action="/" method="post" class="form-horizontal searchForm" id="searchForm">
                    <input type="hidden" name="pageNumber" value="1">
                    <div class="input-group bottom-line">
                        <input type="text" class="form-control br-none" name="keywords" value="${model.keywords}" required="required" placeholder="输入搜索内容">
                        <span class="input-group-btn">
                        <button class="btn btn-default br-none nav-search-btn pointer" type="submit"><i class="fa fa-search"></i> 搜索</button>
                    </span>
                    </div>
                    <div class="clear"></div>
                    <ul class="list-unstyled list-inline search-hot">
                        <li><strong style="position: relative;top: 2px;color: #999999;">热门搜索：</strong></li>
                        <li><a class="pointer" rel="external nofollow"><span class="label label-default">Java</span></a></li>
                        <li><a class="pointer" rel="external nofollow"><span class="label label-primary">Springboot</span></a></li>
                        <li><a class="pointer" rel="external nofollow"><span class="label label-success">Linux</span></a></li>
                        <li><a class="pointer" rel="external nofollow"><span class="label label-info">Maven</span></a></li>
                        <li><a class="pointer" rel="external nofollow"><span class="label label-warning">Bootstrap</span></a></li>
                        <li><a class="pointer" rel="external nofollow"><span class="label label-danger">阿里云</span></a></li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
<!--评论弹框-->
<div class="modal fade bs-example-modal-sm" id="comment-detail-modal" tabindex="-1" role="dialog" aria-labelledby="comment-detail-modal-label">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="comment-detail-modal-label">评论信息框</h4>
                <small><i class="fa fa-lightbulb-o fa-fw"></i>可以通过QQ号实时获取昵称和头像</small>
            </div>
            <div class="modal-body">
                <form id="detail-form">
                    <input type="hidden" name="avatar">
                    <div class="form-group input-logo">
                        <input type="text" class="form-control" name="qq" placeholder="选填" value="">
                        <img class="pull-left hide" alt="">
                        <span class="fa fa-qq pull-left" aria-hidden="true">QQ</span>
                    </div>
                    <div class="form-group input-logo">
                        <input type="text" class="form-control" name="nickname" placeholder="必填" value="匿名">
                        <span class="fa fa-user pull-left" aria-hidden="true">昵称</span>
                    </div>
                    <div class="form-group input-logo">
                        <input type="text" class="form-control" name="email" placeholder="选填">
                        <span class="fa fa-envelope pull-left" aria-hidden="true">邮箱</span>
                    </div>
                    <div class="form-group input-logo">
                        <input type="text" class="form-control" name="url" placeholder="选填">
                        <span class="fa fa-globe pull-left" aria-hidden="true">网址</span>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default btn-sm" id="detail-form-btn"><i class="fa fa-smile-o"></i>提交评论</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="reward" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">山无棱江水为竭，冬雷震震夏雨雪，才敢请君舍</h4>
            </div>
            <div class="modal-body">
                <div class="rewardType" align="center">
                    <ul class="list-unstyle list-inline">
                        <li style="margin-right: 20px;">
                            <input type="radio" name="type" id="alipay" onclick="PaymentUtils.change(0)" data-index="0" checked="checked" ><span style="margin-left: 5px;">支付宝</span>
                        </li>
                        <li style="margin-right: 20px;">
                            <input type="radio" name="type" id="wechat" onclick="PaymentUtils.change(1)" data-index="1"><span style="margin-left: 5px;">微信</span>
                        </li>
                    </ul>
                </div>
                <div id="qrcode-container" align="center" style="margin-top: 10px;"></div>
                <div style="width: 100%;color: #a3a3a3;font-size: 16px;font-family: 'Microsoft YaHei';text-align: center;">
                    转账时请备注“<strong>博客赞助</strong>”
                </div>
            </div>
        </div>
        <small class="font-bold"></small>
    </div>
    <small class="font-bold"> </small>
</div>
<div id="loading">
    <div class="filter"></div>
    <div class="loader">
        <div class="loading-1"></div>
        <div class="loading-2">Loading...</div>
    </div>
</div>
<div class="clear blog-footer">
    <div class="container">
        <div class="col-xs-12 col-sm-4 col-md-4 text-left fade-in">
            <h4>其他链接</h4>
            <ul class="list-unstyled list-inline">
                <li><a href="http://shang.qq.com/wpa/qunwpa?idkey=9f986e9b33b1de953e1ef9a96cdeec990affd0ac7855e00ff103514de2027b60" target="_blank" title="加入我的QQ交流群：190886500" data-toggle="tooltip" data-placement="bottom" rel="external nofollow"><i class="fa fa-qq"></i>QQ交流群</a></li>
                <li><a href="https://promotion.aliyun.com/ntms/act/ambassador/sharetouser.html?userCode=wylo59db" target="_blank" title="史上最全的阿里云优惠券" data-toggle="tooltip" data-placement="bottom" rel="external nofollow"><i class="fa fa-fire fa-fw"></i>阿里云券</a></li>
                <li><a href="https://mi.aliyun.com/shop/19462" target="_blank" title="闲置域名出售" data-toggle="tooltip" data-placement="bottom" rel="external nofollow"><i class="fa fa-globe fa-fw fa-spin red"></i>域名出售</a></li>
                <li><a href="${config.siteUrl}/sitemap.html" target="_blank" title="网站地图" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-map-o fa-fw"></i>网站地图</a></li>
                <li><a href="${config.siteUrl}/recommended" title="站长推荐" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-thumbs-o-up fa-fw"></i>站长推荐</a></li>
                <li><a href="${config.siteUrl}/updateLog" title="更新记录" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-file-o fa-fw"></i>更新记录</a></li>
                <li><a href="${config.siteUrl}/archives" title="归档目录" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-folder-o fa-fw"></i>归档目录</a></li>
                <li><a href="${config.siteUrl}/disclaimer" title="免责声明" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-file-o fa-fw"></i>免责声明</a></li>
                <li><a href="${config.siteUrl}/disclaimer" title="免责声明" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-file-o fa-fw"></i>免责声明</a></li>
            </ul>
        </div>
        <div class="col-xs-12 col-sm-4 col-md-4 text-left fade-in">
            <h4>关于本站</h4>
            <div style="padding: 4px;padding-left: 10px;">
                <p style="white-space: initial;">本站定位：个人技术类博客</p>
                <p style="white-space: initial;">本站作用：写博客、记日志、闲聊扯淡鼓捣技术。</p>
            </div>
        </div>
        <div class="col-xs-12 col-sm-4 col-md-4 text-left fade-in">
            <h4>问题交流</h4>
            <div class="row" style="padding: 4px;padding-left: 10px;">
                <div class="col-xs-12 col-sm-7 col-md-7" style="white-space: initial;">
                    <span>志同道合者欢迎进QQ交流群(190886500)</span>
                </div>
                <div class="col-xs-12 col-sm-5 col-md-5">
                    <a href="${config.staticWebSite}/img/qq_group.png" class="showImage" title="QQ群(190886500)二维码" rel="external nofollow">
                        <img data-original="${config.staticWebSite}/img/qq_group.png" alt="QQ群(190886500)二维码" class="img-responsive img-rounded auto-shake lazy-img" style="width: 100px;">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="footer">
    <div class="clear">
        <p>托管于<a href="https://promotion.aliyun.com/ntms/act/ambassador/sharetouser.html?userCode=wylo59db" target="_blank" title="阿里云-为了无法计算的价值" data-toggle="tooltip" data-placement="bottom" rel="external nofollow">阿里云</a> & <a href="https://www.qiniu.com/" target="_blank" title="七牛云-国内领先的企业级云服务商" data-toggle="tooltip" data-placement="bottom" rel="external nofollow">七牛云</a> · <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action" target="_blank" title="查看备案信息" data-toggle="tooltip" data-placement="bottom" rel="external nofollow">鲁ICP备17054970号-1</a></p>
        <p>Copyright&copy;2016-${.now?string("yyyy")} ${config.siteName} · Powered by <a href="https://gitee.com/yadong.zhang/DBlog" title="DBlog是一款简洁美观、自适应的Java博客系统..." data-toggle="tooltip" data-placement="right" target="_blank"><strong>DBlog</strong></a> · <a href="http://tongji.baidu.com/web/welcome/ico?s=590226d2f4ffbf051442eb75f37333c9" target="_blank" title="点击查看${config.siteName}网站统计详情" data-toggle="tooltip" data-placement="right" rel="external nofollow"><i class="fa fa-bar-chart-o fa-fw fa-spin"></i>网站统计</a></p>
    </div>
</footer>
<a class="to-top" title="点击返回顶部" data-toggle="tooltip" data-placement="bottom"></a>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery_lazyload/1.9.7/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-confirm/2.5.1/jquery-confirm.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.1/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/js-xss/0.3.3/xss.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/nprogress/0.2.0/nprogress.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/mustache.js/2.3.0/mustache.min.js"></script>
<script type="text/javascript">
    var appConfig = {
        wwwPath: '${config.siteUrl}',
        staticPath: '${config.staticWebSite}',
        qiuniuBasePath: '${config.qiuniuBasePath}',
        wxPraiseCode: '${config.wxPraiseCode}',
        zfbPraiseCode: '${config.zfbPraiseCode}',
    }
</script>
<script type="text/javascript" src="${config.staticWebSite}/js/wangEditor.min.js"></script>
<script type="text/javascript" src="${config.staticWebSite}/js/zhyd.core.js"></script>
<script type="text/javascript" src="${config.staticWebSite}/js/zhyd.js"></script>
<script type="text/javascript" src="${config.staticWebSite}/js/zhyd.comment.js"></script>