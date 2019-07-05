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
    <div class="modal-dialog" role="document">
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
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col col-xs-12 col-md-12 col-lg-12">
                <p>
                    <ul class="list-unstyled list-inline">
                        <li>其他连接：</li>
                        <li><a href="${config.siteUrl}/sitemap.html" target="_blank" title="网站地图" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-map-o fa-fw"></i>网站地图</a></li>
                        <li><a href="${config.siteUrl}/recommended" title="站长推荐" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-thumbs-o-up fa-fw"></i>站长推荐</a></li>
                        <li><a href="${config.siteUrl}/updateLog" title="更新记录" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-file-o fa-fw"></i>更新记录</a></li>
                        <li><a href="${config.siteUrl}/archives" title="归档目录" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-folder-o fa-fw"></i>归档目录</a></li>
                        <li><a href="${config.siteUrl}/disclaimer" title="免责声明" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-file-o fa-fw"></i>免责声明</a></li>
                    </ul>
                </p>
                <p>托管于<a href="https://promotion.aliyun.com/ntms/act/ambassador/sharetouser.html?userCode=wylo59db" target="_blank" title="阿里云-为了无法计算的价值" data-toggle="tooltip" data-placement="bottom" rel="external nofollow">阿里云</a> & <a href="https://portal.qiniu.com/signup?code=3l8yx2v0f21ci" target="_blank" title="七牛云-国内领先的企业级云服务商" data-toggle="tooltip" data-placement="bottom" rel="external nofollow">七牛云</a><#if config.recordNumber!> · <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action" target="_blank" title="查看备案信息" data-toggle="tooltip" data-placement="bottom" rel="external nofollow">${config.recordNumber}</a></#if></p>
                <p><#if config.copyright!>${config.copyright} |</#if> Powered by <a href="https://gitee.com/yadong.zhang/DBlog" title="OneBlog是一款简洁美观、自适应的Java博客系统..." data-toggle="tooltip" data-placement="right" target="_blank"><i>OneBlog</i></a>  </p>
                <#if url?? && (url == "index")>
                    <div class="inline external-links">
                        <a>友情链接：</a>
                        <#if indexLinkList?? && (indexLinkList?size > 0)>
                            <#list indexLinkList as item>
                                <a href="${item.url}" target="_blank" title="${item.description!}" data-toggle="tooltip" data-placement="right">
                                    ${item.name!}
                                </a>
                            </#list>
                            <a href="${config.siteUrl}/links" title="更多链接" data-toggle="tooltip" data-placement="right"><i class="fa fa-plus"></i>更多链接</a>
                        <#else>
                            <a href="${config.siteUrl}/links" title="申请友链" data-toggle="tooltip" data-placement="right">申请友链</a>
                        </#if>
                    </div>
                </#if>
            </div>
            <div class="col col-md-4"></div>
        </div>
    </div>
</footer>
<a class="to-top" title="点击返回顶部" data-toggle="tooltip" data-placement="bottom"></a>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery@1.11.1/dist/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.0/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-lazyload@1.9.3/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-confirm@3.3.2/dist/jquery-confirm.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery.fancybox@2.1.5/source/jquery.fancybox.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrapvalidator@0.5.4/dist/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/xss@0.3.3/dist/xss.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/mustache@2.3.0/mustache.min.js"></script>
<script type="text/javascript">
    var appConfig = {
        siteName: '${config.siteName}',
        wwwPath: '${config.siteUrl}',
        cmsPath: '${config.cmsUrl}',
        staticPath: '${config.staticWebSite}',
        lazyloadPath: '${config.lazyloadPath}',
        fileStoragePath: '${config.fileStoragePath}',
        wxPraiseCode: '${config.wxPraiseCode}',
        zfbPraiseCode: '${config.zfbPraiseCode}',
        anonymous: '${config.anonymous}',
        editorPlaceholder: '${config.editorPlaceholder}',
        editorAlert: '${config.editorAlert}',
        dynamicTitle: '${config.dynamicTitle}'
    };

    var oauthConfig = {
        loginUserId: '${user.id}',
        loginUserName: '${user.username}',
        loginUserAvatar: '${user.avatar}'
    }
</script>
<script type="text/javascript" src="${config.staticWebSite}/js/zhyd.core.js"></script>
<script type="text/javascript" src="${config.staticWebSite}/js/zhyd.comment.js"></script>
<script type="text/javascript" src="${config.staticWebSite}/js/zhyd.js"></script>