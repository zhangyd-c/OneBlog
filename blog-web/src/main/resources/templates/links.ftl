<#include "include/macros.ftl">
<@header title="友情链接 | ${config.siteName}"
    keywords="${config.siteName},友情链接交换"
    description="${config.siteName}，一个程序员的个人原创博客，友情链接交换"
    canonical="/links">
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
        <i class="fa fa-angle-right"></i>友情链接
    </nav>
    <div class="row">
        <@blogHeader title="友情链接"></@blogHeader>
        <div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <#--<h5 class="legend-title">简单的要求<small> - 简单点，说话的方式简单点</small></h5>-->
                <h4 class="bottom-line"><i class="fa fa-coffee fa-fw"></i><strong>简单的要求</strong><small> - 简单点，说话的方式简单点</small></h4>
                <div class="link-info">
                    <ul class="list-unstyled">
                        <li>本站定位：个人技术类博客</li>
                        <li>本站作用：写博客、记日志、闲聊扯淡鼓捣技术</li>
                        <li>本站 <em>优先</em> 选择<kbd>同类原创、内容相近</kbd>的博客或网站，您的站点内容可以为<kbd>技术类</kbd>、<kbd>IT科技</kbd>、<kbd>互联网</kbd>和<kbd>生活</kbd></li>
                        <li>特别提醒：<abbr title="禁止友链的网站">任何包含违反国家法律法规内容的网站、盈利类（赌博）网站，还有相亲类、卖表类的网站、垃圾站统统不做！！！</abbr></li>
                        <li>
                            <blockquote>
                                <ul class="list-unstyled">
                                    <li>复制添加本站链接：<strong>&lt;a href="${config.siteUrl}" title="${config.siteDesc}" target="_blank"&gt;${config.siteName}&lt;/a&gt;</strong></li>
                                    <li>复制添加本站图标：<strong>${config.siteFavicon}</strong></li>
                                </ul>
                            </blockquote>
                        </li>
                        <li><a class="btn btn-default btn-sm auto-link-btn" style="margin-top: 10px;" data-toggle="modal" data-target="#auto-link-modal"><i class="fa fa-hand-grab-o"></i>自助申请友链</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-sm-12 zhyd-container">
            <div class="zhyd-box zhyd-os">
                <div class="category">
                    <div><i class="fa fa-unlink fa-fw fa-2x"></i>取链记录</div>
                </div>
                <div class="col-sm-12 col-md-12" style="margin: 0 auto;float: initial">
                    <div class="h5"><i class="fa fa-plus-square showContent"> 点击查看</i></div>
                    <div class="disable-content" style="display: none;">
                        <table class="table table-hover table-condensed table-bordered">
                            <thead>
                            <th style="width: 80px;">网站图标</th>
                            <th style="width: 200px;">网站名</th>
                            <th>取链原因</th>
                            <th style="width: 150px;">取链时间</th>
                            </thead>
                            <#if link.disableList>
                                <#list link.disableList as item>
                                    <tr>
                                        <td><img src="${item.favicon}" alt="${item.name}" onerror="this.src='${config.staticWebSite}/img/user.png'" style="width: 20px;" rel="external nofollow"></td>
                                        <td>${item.name}</td>
                                        <td>${item.remark}</td>
                                        <td>${item.updateTime?string('yyyy-MM-dd')}</td>
                                    </tr>
                                </#list>
                            </#if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <#if link.indexList>
        <div class="col-sm-12 zhyd-container">
            <div class="zhyd-box zhyd-os">
                <div class="category">
                    <div><i class="fa fa-link fa-fw fa-2x"></i>首页友链 <small>(排名不分前后)</small></div>
                </div>
                <div class="col-sm-12 col-md-12" style="margin: 0 auto;float: initial">
                    <section class="links none-bg">
                        <ul class="list-unstyled list-inline">
                        <#list link.indexList as item>
                            <li>
                                <a href="${item.url}" target="_blank" title="${item.description}" data-toggle="tooltip" data-placement="bottom">
                                    <img src="${item.favicon}" alt="${item.name}" onerror="this.src='${config.staticWebSite}/img/user.png'">${item.name}
                                </a>
                            </li>
                        </#list>
                        </ul>
                    </section>
                </div>
            </div>
        </div>
        </#if>
        <#if link.insideList>
        <div class="col-sm-12 zhyd-container">
            <div class="zhyd-box zhyd-os">
                <div class="category">
                    <div><i class="fa fa-link fa-fw fa-2x"></i>内页友链 <small>(排名不分前后)</small></div>
                </div>
                <div class="col-sm-12 col-md-12" style="margin: 0 auto;float: initial">
                    <section class="links none-bg">
                        <ul class="list-unstyled list-inline">
                            <#list link.insideList as item>
                                <li>
                                    <a href="${item.url}" target="_blank" title="${item.description}" data-toggle="tooltip" data-placement="bottom">
                                        <img src="${item.favicon}" alt="${item.name}" onerror="this.src='${config.staticWebSite}/img/user.png'">${item.name}
                                    </a>
                                </li>
                            </#list>
                        </ul>
                    </section>
                </div>
            </div>
        </div>
        </#if>
        <#--<div class="col-sm-12 blog-main">
            <div class="blog-body expansion">
                <div id="comment-box" data-id="-2"></div>
            </div>
        </div>-->
    </div>
</div>
<!--评论弹框-->
<div class="modal fade bs-example-modal-sm" id="auto-link-modal" tabindex="-1" role="dialog" aria-labelledby="auto-link-modal-label">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="auto-link-modal-label"><i class="fa fa-spinner fa-fw fa-spin"></i>自助申请友情链接</h4>
            </div>
            <div class="modal-body" style="overflow: hidden">
                <div class="alert alert-warning" role="alert">
                    <i class="fa fa-lightbulb-o"></i>友情提示：提交前请先添加本站友链，确认添加后再次提交申请，系统将自动审核！
                </div>
                <form class="form-horizontal col-sm-12" role="form" action="/api/autoLink" method="post" id="autoLinkForm" style="padding-left: 15px;">
                    <div class="form-group has-info">
                        <div class="input-group input-logo">
                            <input type="text" class="form-control" name="name" placeholder="${config.siteName}" style="padding-left: 88px;">
                            <span class="fa fa-user pull-left" aria-hidden="true" style="width: 75px;">站点名称</span>
                        </div>
                    </div>
                    <div class="form-group has-info">
                        <div class="input-group input-logo">
                            <input type="text" class="form-control" name="url" placeholder="本站链接所在的网页(内页或首页)" style="padding-left: 88px;">
                            <span class="fa fa-map-marker pull-left" aria-hidden="true" style="width: 75px;">站点地址</span>
                        </div>
                    </div>
                    <div class="form-group has-info">
                        <div class="input-group input-logo">
                            <input type="text" class="form-control" name="description" placeholder="一个程序员的个人博客。心之所向，无所不能。" style="padding-left: 88px;">
                            <span class="fa fa-commenting pull-left" aria-hidden="true" style="width: 75px;">站点描述</span>
                        </div>
                    </div>
                    <div class="form-group has-info">
                        <div class="input-group input-logo">
                            <input type="text" class="form-control" name="email" placeholder="选填" style="padding-left: 88px;">
                            <span class="fa fa-envelope pull-left" aria-hidden="true" style="width: 75px;">站长邮箱</span>
                        </div>
                    </div>
                    <div class="form-group has-info">
                        <div class="input-group input-logo">
                            <input type="text" class="form-control" name="favicon" placeholder="选填" style="padding-left: 88px;">
                            <span class="fa fa-file-image-o pull-left" aria-hidden="true" style="width: 75px;">站点图标</span>
                        </div>
                    </div>
                    <div class="form-group has-info">
                        <button type="button" class="btn btn-default btn-sm autoLink"><i class="fa fa-smile-o"></i>提交申请</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<@footer>
    <script type="text/javascript">
        eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('$(k(){$("#n").g({0:"\\10\\R\\N\\M\\z",y:{l:"2 2-x",w:"2 2-v",u:"2 2-t"},s:{r:{3:{9:{0:"\\c\\d\\J\\q\\1\\e\\7\\f"}}},h:{3:{9:{0:"\\c\\d\\5\\4\\1\\e\\7\\f"},o:{0:"\\1\\8\\6\\j\\5\\4"}}},A:{3:{B:{0:"\\1\\8\\6\\j\\C\\D\\5\\4"}}},E:{3:{9:{0:"\\c\\d\\F\\G\\1\\e\\7\\f"}}},H:{3:{o:{0:"\\I\\p\\5\\4\\1\\8\\6"}}}}});$(".K").L(k(){i a=$("#n");a.g("O");P(Q.l(a)){i b=a.S("T");$.U({V:"W",h:b,X:a.Y(),Z:$.m.11,12:$.m.13})}})});',62,66,'message|u4e0d|fa|validators|u5740|u5730|u6cd5|u4e3a|u5408|notEmpty|||u7ad9|u70b9|u80fd|u7a7a|bootstrapValidator|url|var|u7684|function|valid|tool|autoLinkForm|uri|u6807|u79f0|name|fields|refresh|validating|remove|invalid|check|feedbackIcons|u6548|email|emailAddress|u90ae|u7bb1|description|u63cf|u8ff0|favicon|u56fe|u540d|autoLink|click|u65e0|u503c|validate|if|_form|u5165|attr|action|ajax|type|POST|data|serialize|success|u8f93|ajaxSuccess|error|ajaxError'.split('|'),0,{}))
    </script>
    <script src="https://v1.hitokoto.cn/?encode=js&c=d&select=%23hitokoto" defer></script>
</@footer>
