<#include "/include/macros.ftl">
<@header>
    <style>
        .prod_title {
            margin: 5px 0;
        }
        .form-group {
            margin-bottom: 0;
        }
        #removerForm .item .alert {
            margin: 5px 0 0 15px;
        }
        #removerForm .item.bad .alert {
            float: right;
            margin-top: -30px;
            left: -15px!important;
            opacity: 1;
        }
        .x_title h2 {
            font-weight: 700;
        }
        .tips {
            font-size: 12px;
            color: #bbbbbb;
        }
    </style>
</@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">文章搬运工</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_content">
                <div class="row">
                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#multiple" id="multiple-tab" role="tab" data-toggle="tab" aria-expanded="true">抓取列表</a>
                            </li>
                            <li role="presentation">
                                <a href="#single" id="single-tab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-free-code-camp fa-fw red"></i>抓取单个文章</a>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade active in" id="multiple" aria-labelledby="multiple-tab">
                                <form id="removerForm" action="/remover/run" target="spiderFrame" method="post" class="form-horizontal form-label-left" novalidate>
                                    <#-- 左侧 -->
                                    <div class="col-md-6">
                                        <div class="x_panel">
                                            <div class="x_title">
                                                <h2><i class="fa fa-user-secret"></i> 基本配置</h2>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="x_content">
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="platform">博文平台 <span class="required">*</span></label>
                                                    <div class="col-md-6 col-sm-6 col-xs-6">
                                                        <select name="platform" id="platform" class="form-control" required="required">
                                                            <option value="">请选择</option>
                                                            <#list platforms as item>
                                                                <option value="${item.platform}">${item.platform} (${item.host})</option>
                                                            </#list>
                                                            <option value="">待续...</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-3 col-sm-3 col-xs-3">
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" class="square" name="convertImg"> 转图存片
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3"></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9 tips">
                                                        <i class="fa fa-exclamation-circle"></i> 勾选时默认将文章中的图片转存到自有云存储服务器中（需提前配置云存储服务器）
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="typeId">文章分类 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <select name="typeId" class="form-control typeId" required="required"></select>
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="uid">用户ID <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="uid" id="uid" class="form-control" required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3"></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9 tips">
                                                        <i class="fa fa-exclamation-circle"></i> 获取方式：
                                                        <a href="javascript:;" data-toggle="modal" data-target="#helpModal" data-img="/assets/images/spider/uid/imooc.png" data-title="慕课网“用户ID”获取方式">慕课网</a> |
                                                        <a href="javascript:;" data-toggle="modal" data-target="#helpModal" data-img="/assets/images/spider/uid/csdn.png" data-title="CSDN“用户ID”获取方式">CSDN</a> |
                                                        <a href="javascript:;" data-toggle="modal" data-target="#helpModal" data-img="/assets/images/spider/uid/iteye.png" data-title="ITeye“用户ID”获取方式">ITeye</a> |
                                                        <a href="javascript:;" data-toggle="modal" data-target="#helpModal" data-img="/assets/images/spider/uid/cnblogs.png" data-title="博客园“用户ID”获取方式">博客园</a>
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="exitWay">停止方式 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <select name="exitWay" id="exitWay" class="form-control" required="required" style="width: 50%;display: inline-block">
                                                            <#list exitWayList as exitWay>
                                                                <option value="${exitWay}" data-def-count="${exitWay.defaultCount}" <#if exitWay_index == 2>selected="selected"</#if>>${exitWay.desc}</option>
                                                            </#list>
                                                        </select>
                                                        <input class="form-control" style="width: 30%;display: inline-block" type="text" name="count" value="1">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3"></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9 tips">
                                                        <i class="fa fa-exclamation-circle"></i>
                                                        <ul class="list-unstyled">
                                                            <li><strong>默认：</strong>不做限制，抓取所有匹配到的文章，<strong class="red">慎用</strong></li>
                                                            <li><strong>持续时间：</strong>按照爬虫运行的时间，理想状态时1s抓取一条，受实际网速影响</li>
                                                            <li><strong>链接条数：</strong>按照指定的条数抓取，满足条数后程序自动停止</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="domain">网站根域名 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="domain" class="form-control" required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="cookie">Cookie </label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <textarea name="cookie" class="form-control"></textarea>
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3"></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9 tips">
                                                        <i class="fa fa-exclamation-circle"></i> 需要登录时设置。Cookie获取方式： <a href="javascript:HandlerInterceptor;" data-toggle="modal" data-target="#helpModal" data-img="/assets/images/spider/cookie/cookie.png" data-title="“Cookie”获取方式（通用）">以CSDN为例</a>
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="header">Header <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <textarea name="header" class="form-control" required="required"></textarea>
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3"></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9 tips">
                                                        <i class="fa fa-exclamation-circle"></i> Header主要是为了防止某些网站验证referer等信息防爬虫
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="entryUrls">程序入口 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <textarea name="entryUrls" class="form-control" required="required"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <#-- 右侧 -->
                                    <div class="col-md-6">
                                        <div class="x_panel">
                                            <div class="x_title">
                                                <h2><i class="fa fa-tasks"></i> 爬虫Xpath抓取规则</h2>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="x_content">
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="titleRegex">标题 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="titleRegex" class="form-control" required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="authorRegex">作者 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="authorRegex" class="form-control" required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="releaseDateRegex">发布日期 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="releaseDateRegex" class="form-control" required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="contentRegex">内容 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="contentRegex" class="form-control" required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="tagRegex">标签 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="tagRegex" class="form-control" required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="targetLinksRegex">待抓取的url <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="targetLinksRegex" class="form-control" required="required">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="x_panel">
                                            <div class="x_title">
                                                <h2><i class="fa fa-pagelines"></i> 爬虫其他配置项</h2>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="x_content">
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="charset">网站编码 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="text" name="charset" class="form-control" value="utf8" readonly required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="sleepTime">延迟 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="number" name="sleepTime" class="form-control" value="1000" readonly required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3"></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9 tips">
                                                        延迟和爬取速度以及被封的概率成正比！请慎用
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="retryTimes">重试次数 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="number" name="retryTimes" class="form-control" value="2" readonly required="required">
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="threadCount">线程个数 <span class="required">*</span></label>
                                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                                        <input type="number" name="threadCount" class="form-control" value="1" readonly required="required">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#declareModal"><i class="fa fa-truck"> GO！</i></button>
                                        <button type="reset" class="btn btn-default" id="resetBtn"><i class="fa fa-refresh"> 重置</i></button>
                                        <a type="button" class="btn btn-danger stopBtn hide"><i class="fa fa-stop-circle-o"> 停止</i></a>
                                        <a type="button" class="btn btn-info" id="showResultModal" style="display: none;"><i class="fa fa-eye"> 显示日志</i></a>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="single" aria-labelledby="single-tab">
                                <form id="removerSingleForm" action="/remover/single" target="spiderFrame" method="post" class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="url">转图存片</label>
                                        <div class="col-md-7 col-sm-7 col-xs-7">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" class="square" name="convertImg" checked="checked">
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="url">请输入文章链接 <span class="required">*</span></label>
                                        <div class="col-md-7 col-sm-7 col-xs-7">
                                            <div class="input-group">
                                                <input type="text" name="url" class="form-control" value="" placeholder="例如：https://www.baidu.com/article/1234567.html" required="required">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default" id="plus-btn" title="添加一条"><i class="fa fa-plus fa-fw"></i></button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="url-list"></div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="typeId">选择文章分类 <span class="required">*</span></label>
                                        <div class="col-md-7 col-sm-7 col-xs-7">
                                            <select name="typeId" class="form-control typeId" required="required"></select>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="url"></label>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <button type="button" class="btn btn-success" id="crawlSingle"><i class="fa fa-signing"> 就是它了！</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="helpModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="helpModalTitle"></h4>
            </div>
            <div class="modal-body">
                <a href="" class="showImage" title="" rel="external nofollow">
                    <img src="" alt="" id="helpModalImg" class="img-responsive img-rounded">
                </a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="declareModal" tabindex="-1" role="dialog" aria-labelledby="declareModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="declareModalTitle">声明</h4>
            </div>
            <div class="modal-body">
                <ul class="list-unstyled">
                    <li>1.本工具开发初衷只是用来迁移 <strong>自己的文章</strong> 所用，因此不可用该工具 <strong>恶意窃取</strong> 他人劳动成果！</li>
                    <li>2.因不听劝阻，使用该工具恶意窃取他们劳动成果而造成的一切不良后果，本人表示：坚决不背锅！</li>
                    <li>3.如果该工具不好用，你们绝对不能打我！</li>
                    <li>4.有问题、建议，请 <a href="https://gitee.com/yadong.zhang/DBlog/issues" target="_blank">提Issue</a>！</li>
                </ul>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-success" id="submitBtn"><i class="fa fa-hand-grab-o"> 知道了！</i></a>
                <a href="javascript:;" data-dismiss="modal"><i class="fa fa-close"> 算了吧</i></a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="resultModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
                <h4 class="modal-title" id="resultModalLabel">程序正在执行中...</h4>
            </div>
            <div class="modal-body">
                <div class="pageFormContent" id="pageFormContent" style="max-height: 300px;height: 300px;overflow-y: auto;">
                    <div id="spider-message" class="profile_title"></div>
                </div>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-danger stopBtn hide"><i class="fa fa-stop-circle-o"> 停止</i></a>
                <a type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></a>
            </div>
        </div>
    </div>
</div>
<iframe src="" id="spiderFrame" name="spiderFrame" style="display: none"></iframe>
<@footer>
    <script>
        var $urlList = $("#url-list"),
            $plusBtn = $("#plus-btn");

        $plusBtn.on('click', function () {
            $urlList.append('<div class="item form-group"><label class="control-label col-md-3 col-sm-3 col-xs-3" for="url">请输入文章链接 <span class="required">*</span></label><div class="col-md-7 col-sm-7 col-xs-7"><div class="input-group"><input type="text" name="url" class="form-control" value="" placeholder="例如：https://www.baidu.com/article/1234567.html" required> <span class="input-group-btn"><button type="button" class="btn btn-danger minus-btn" title="删除该条"><i class="fa fa-minus fa-fw"></i></button></span></div></div></div>');

            function minus(){
                var $this = $(this);
                $this.parents(".form-group").remove();
            }
            $(".minus-btn").unbind("click", minus).on('click', minus);
        });

        var spiderConfig = ${spiderConfig};
        // 博文平台
        var $platform = $("#platform");
        // 各平台用户id
        var $uid = $("#uid");
        // 文章总页数
        var $totalPage = $("#totalPage");
        // 分割字符串的正则
        var reg = new RegExp('{\\w+}'), br = "\r\n";

        $("#platform, #uid, #totalPage").change(function () {
            autoCompleForm();
        });
        function  autoCompleForm() {
            var platform = $platform.val();
            if(!platform) {
                return;
            }
            var uid = $uid.val(),
                totalPage = $totalPage.val() | 1,
                curConfig = spiderConfig[platform];
            if(!curConfig) {
                $.toastr.warning("系统暂未内置[" + platform + "]平台的爬虫规则，请手动提取！")
                return;
            }
            $("#removerForm").find("input,textarea").each(function () {
                var $this = $(this);
                var thisName = $this.attr("name");
                var realText;
                $this.val((realText = parseText(curConfig, thisName, uid, totalPage)) ? realText : $this.val());
            });
        }
        function parseText(curConfig, thisName, uid, totalPage){
            var text = curConfig[thisName];
            if (typeof(text) === "undefined") {
                return text;
            }
            console.log("exec >> " + reg.exec(text));
            if(thisName === "header") {
                var header = "";
                for(var i in text) {
                    header += text[i].replaceAll("{uid}", uid) + br;
                }
                text = header.substr(0, header.length - br.length);
            } else if(thisName === "entryUrls") {
                var entryUrl = "";
                for(var j = 1; j <= totalPage; j ++) {
                    entryUrl += text[0].replaceAll("{uid}", uid).replaceAll("{curPage}", j) + br;
                }
                text = entryUrl.substr(0, entryUrl.length - br.length);
            } else if(reg.exec(text)) {
                text = text.replaceAll("{uid}", uid);
            }
            return text;
        }

        $("#submitBtn").click(function () {
            var $form = $("form#removerForm");
            if (validator.checkAll($form)) {
                $("#declareModal").modal('hide');
                changeBtnState(true)
                $("#resultModal").modal('show');
                $("#showResultModal").show();
                $form.submit();
                $("#spider-message").html("<p> 程序正在初始化...</p>");
            }
        });

        $("#showResultModal").click(function () {
            $("#resultModal").modal('show');
        });

        function changeBtnState(state) {
            if(state) {
                $("button[type=button], button[type=reset]").button('loading');
                $(".stopBtn").removeClass("hide");
            } else {
                $("button[type=button], button[type=reset]").button('reset');
                $(".stopBtn").addClass("hide");
            }
        }

        function printMessage(message){
            if(message == 'shutdown') {
                changeBtnState(false)
                return;
            }
            $("#spider-message").append("<p>" + message + "</p>");
            var $dom = document.getElementById("pageFormContent");
            $dom.scrollTop = $dom.scrollHeight;
        }

        $('#helpModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var img = button.data('img');
            var title = button.data('title');
            var modal = $(this);
            modal.find('#helpModalTitle').text(title);
            var $img = modal.find('#helpModalImg');
            $img.attr("src", img).attr("alt", title);
            $img.parent().attr("href", img).attr("title", title);
        })

        $.ajax({
            type: "post",
            url: "/type/listAll",
            success: function (json) {
                $.alert.ajaxSuccess(json);
                var data = '';
                if(data = json.data){
                    var tpl = '<option value="">选择分类</option>{{#data}}<option value="{{id}}">{{name}}</option>{{#nodes}}<option value="{{id}}">  -- {{name}}</option>{{/nodes}}{{/data}}';
                    var html = Mustache.render(tpl, json);
                    $("select.typeId").html(html);
                }
            },
            error: $.alert.ajaxError
        });

        $("#exitWay").on('change', function () {
            var $this = $(this);
            var $input = $this.next('input');
            if($this.val() !== 'DEFAULT') {
                $input.removeAttr('readonly');
            } else {
                $input.attr('readonly', 'readonly')
            }
            $input.val($this.find("option:selected").data("def-count"));
        });

        $(".stopBtn").on('click', function () {
            $.alert.confirm("确定停止当前进程?", function () {
                $.ajax({
                    type: "post",
                    url: "/remover/stop",
                    success: function (json) {
                        $.alert.ajaxSuccess(json);
                        changeBtnState(false)
                    },
                    error: $.alert.ajaxError
                });
            })
        })

        $("#crawlSingle").click(function () {
            var $form = $("form#removerSingleForm");
            if (validator.checkAll($form)) {
                changeBtnState(true)
                $("#resultModal").modal('show');
                $("#showResultModal").show();
                $form.submit();
                $("#spider-message").html("<p> 程序正在初始化...</p>");
            }
        });
    </script>
</@footer>