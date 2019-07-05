<#include "include/macros.ftl">
<@header></@header>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <@breadcrumb>
            <ol class="breadcrumb">
                <li><a href="/">首页</a></li>
                <li class="active">系统配置</li>
            </ol>
        </@breadcrumb>
        <div class="x_panel">
            <div class="x_title">
                <h2>系统配置 </h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="col-md-12 col-sm-12 col-xs-12 profile_left">
                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#tab_basic" id="basic-tab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-info fa-fw"></i> 基本信息</a>
                            </li>
                            <li role="presentation">
                                <a href="#tab_seo" id="seo-tab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-sitemap fa-fw"></i> SEO</a>
                            </li>
                            <li role="presentation">
                                <a href="#tab_storage" id="storage-tab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-cube fa-fw"></i> 云存储</a>
                            </li>
                            <li role="presentation">
                                <a href="#tab_auth" id="auth-tab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-sign-in fa-fw"></i> 登录</a>
                            </li>
                            <li role="presentation">
                                <a href="#tab_comment" id="comment-tab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-commenting-o fa-fw"></i> 评论</a>
                            </li>
                            <li role="presentation">
                                <a href="#tab_article_editor" id="article-editor-tab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-edit fa-fw"></i> 文章编辑器</a>
                            </li>
                            <li role="presentation" class="">
                                <a href="#tab_contact" role="tab" id="contact-tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-id-card-o fa-fw"></i> 联系方式</a>
                            </li>
                            <li role="presentation">
                                <a href="#tab_praise" id="praise-tab" role="tab" data-toggle="tab" aria-expanded="true"><i class="fa fa-money fa-fw"></i> 赞赏码</a>
                            </li>
                            <li role="presentation" class="">
                                <a href="#tab_setting" role="tab" id="setting-tab" data-toggle="tab" aria-expanded="false"><i class="fa fa-tasks fa-fw"></i> 其他</a>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade active in" id="tab_basic"
                                 aria-labelledby="basic-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="siteDesc">站点简介 <i class="fa fa-question-circle" title="一句话简介"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="siteDesc" id="siteDesc" required="required" placeholder="一句话简介"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="domain">主域名 <i class="fa fa-question-circle" title="例如：https://www.zhyd.me的主域名就是zhyd.me"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="domain" id="domain" required="required" placeholder="例如: zhyd.me"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="siteUrl">博客地址 <i class="fa fa-question-circle" title="博客前台地址，例如: http://localhost:8443"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="siteUrl" id="siteUrl" required="required" placeholder="例如: http://localhost:8443"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="siteFavicon">站点图标
                                            <i class="fa fa-question-circle" title="favicon，浏览器标签网站标题左侧的图标"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="siteFavicon" id="siteFavicon" required="required" placeholder="例如：http://localhost:8443/favicon.ico"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="staticWebSite">资源文件域名
                                            <i class="fa fa-question-circle" title="js、css、img等文件的域名地址，如果是在本项目内，则与“博客地址”设置一样即可"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="staticWebSite" id="staticWebSite" required="required" placeholder="例如：http://localhost:8443"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="staticWebSite">管理系统地址
                                            <i class="fa fa-question-circle" title="博客后台管理系统的地址，例如: http://localhost:8085"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="cmsUrl" id="cmsUrl" required="required" placeholder="例如: http://localhost:8085"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="copyright">版权信息
                                            <i class="fa fa-question-circle" title="网站版权信息"></i> </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="copyright" id="copyright" placeholder="例如：Copyright &copy; ${.now?string("yyyy")} zhyd.me All Rights Reserved"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="copyright">网站备案号
                                            <i class="fa fa-question-circle" title="网站备案号"></i> </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="recordNumber" id="recordNumber" placeholder="例如：鲁ICP备17054970号-1"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="lazyloadPath">懒加载图片
                                            <i class="fa fa-question-circle" title="用于前台网站中对图片进行懒加载显示"></i> </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="lazyloadPath" id="lazyloadPath" placeholder="例如：${config.staticWebSite}/img/loading.gif"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="installdate">博客安装日期
                                            <i class="fa fa-question-circle" title="用于前台计算系统运行天数"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <div class='input-group date myDatepicker'>
                                                <input type='text' class="form-control" readonly="readonly" id="installdate" name="installdate"  required="required" placeholder="请选择系统安装日期"/>
                                                <span class="input-group-addon">
                                                   <span class="fa fa-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tab_seo" aria-labelledby="seo-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="siteName">首页标题 <i class="fa fa-question-circle" title="百度白皮书推荐的格式：关键词1_关键词2_关键词3_关键词4-品牌词"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="siteName" id="siteName" required="required" placeholder="请输入站点名"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="homeDesc">META描述 <i class="fa fa-question-circle" title="对keywords进行扩展描述，100~130字左右即可"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <textarea class="form-control col-md-7 col-xs-12" id="homeDesc" name="homeDesc" required="required" placeholder="请输入首页描述" rows="5"></textarea>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="homeKeywords">META关键词 <i class="fa fa-question-circle" title="网站关键字，半角逗号分割，不建议多，贴合网站主题"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <textarea class="form-control col-md-7 col-xs-12" id="homeKeywords" name="homeKeywords" required="required" placeholder="请输入首页关键字(半角逗号分隔)" rows="5"></textarea>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="baiduPushToken">百度推送Token <i class="fa fa-question-circle" title="方便百度引擎快速收录"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="baiduPushToken" id="baiduPushToken" required="required" placeholder="请输入百度推送Token，推送功能能加快百度搜索引擎对博文的索引速度"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <small>获取地址：<a href="https://ziyuan.baidu.com/linksubmit/index" target="_blank">点击获取百度推送Token</a></small>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="baiduPushCookie">百度推送Cookie <i class="fa fa-question-circle" title="请求API使用"></i> <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <textarea class="form-control col-md-7 col-xs-12" name="baiduPushCookie" id="baiduPushCookie" required="required" placeholder="请输入百度推送Cookie" rows="5"></textarea>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <small>帮助文档：<a href="http://t.cn/AiCIWi0Q" target="_blank">OneBlog-第三方配置参考-百度站长平台配置</a></small>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tab_storage" aria-labelledby="storage-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="alert alert-info" role="alert" style="color: white">
                                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                                        <i class="fa fa-info-circle fa-fw"></i>注意：系统<strong>暂不自持自动同步</strong>各个云存储空间中的文件，所以当切换云存储类型时可能会造成<strong>部分图片不可用</strong>的情况！请悉知！
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">存储类型 <span class="required">*</span></label>
                                        <div class="col-md-8 col-sm-8 col-xs-8">
                                            <div class="checkbox">
                                                <label for="storageType" style="margin-right: 10px"> <input type="radio" class="square" name="storageType" value="local" checked="checked"/> 本地 </label>
                                                <label for="storageType" style="margin-right: 10px"><input type="radio" class="square" name="storageType" value="qiniu"/> 七牛云 </label>
                                                <label for="storageType" style="margin-right: 10px"><input type="radio" class="square" name="storageType" value="aliyun"/> 阿里云OSS</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="storage-box" id="local">
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="localFileUrl">文件服务器域名 <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12" name="localFileUrl" id="localFileUrl" required="required" placeholder="请输入文件服务器域名，如：http://file.zhyd.me/"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="localFilePath">文件存储路径 <i class="fa fa-question-circle" title="Nginx服务中root后面对应的目录地址"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12" name="localFilePath" id="localFilePath" required="required" placeholder="请输入文件存储路径，如：/var/www/oneblog/upload/"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <small>本地文件服务器，需要手动<strong class="red">搭建文件服务器</strong>。作者推荐Nginx，<a class="pointer" data-toggle="modal" data-target="#storageNginxServerModal">获取nginx文件服务器配置</a> </small>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="storage-box hide" id="qiniu">
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="qiniuBucketName">Bucket 名称 <i class="fa fa-question-circle" title="存储空间名称"></i> <span  class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12"  name="qiniuBucketName" id="qiniuBucketName"  required="required" placeholder="请输入Bucket 名称"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="qiniuAccessKey">Access Key <i class="fa fa-question-circle" title="密钥获取地址：https://portal.qiniu.com/user/key"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12"  name="qiniuAccessKey" id="qiniuAccessKey" required="required" placeholder="请输入Access Key"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="qiniuSecretKey">Secret Key <i class="fa fa-question-circle" title="密钥获取地址：https://portal.qiniu.com/user/key"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12" name="qiniuSecretKey" id="qiniuSecretKey" required="required" placeholder="请输入Secret Key"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="qiniuBasePath">七牛云cdn域名 <i class="fa fa-question-circle" title="如果未自定义域名，则填写临时域名，格式：http://***.**.**/"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12" name="qiniuBasePath" id="qiniuBasePath" required="required" placeholder="请输入七牛域名，格式：http://***.**.**/"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="storage-box hide" id="aliyun">
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="aliyunBucketName">Bucket 名称 <i class="fa fa-question-circle" title="存储空间名称"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12" name="aliyunBucketName" id="aliyunBucketName" required="required" placeholder="请输入Bucket 名称"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="aliyunEndpoint">地域节点（EndPoint） <i class="fa fa-question-circle" title="地域节点，注意必须填写外网地址，非内网"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12"  name="aliyunEndpoint" id="aliyunEndpoint" required="required" placeholder="请输入endpoint，如：http://oss-cn-hangzhou.aliyuncs.com"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="aliyunFileUrl">Bucket 域名 <i class="fa fa-question-circle" title="默认为bucketName + endpoint，若使用自定义的域名，请修改"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12"  name="aliyunFileUrl" id="aliyunFileUrl" required="required" placeholder="默认为bucketName + endpoint，若使用自定义的域名，请修改"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="aliyunAccessKey">Access Key <i class="fa fa-question-circle" title="阿里云API密钥，获取地址：https://ak-console.aliyun.com/#/"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12" name="aliyunAccessKey" id="aliyunAccessKey" required="required" placeholder="请输入Access Key"/>
                                            </div>
                                        </div>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="aliyunAccessKeySecret">Access Key Secret <i class="fa fa-question-circle" title="阿里云API密钥，获取地址：https://ak-console.aliyun.com/#/"></i> <span class="required">*</span></label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" class="form-control col-md-7 col-xs-12" name="aliyunAccessKeySecret" id="aliyunAccessKeySecret" required="required" placeholder="请输入Access Key Secret"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tab_auth" aria-labelledby="auth-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="loginRetryNum">登录重试次数 <span class="required">*</span></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="loginRetryNum" id="loginRetryNum" required="required" placeholder="请输入登录重试次数，默认5"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="sessionTimeOut">Session有效期 <span class="required">*</span></label>
                                        <div class="col-md-3 col-sm-3 col-xs-3">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="sessionTimeOut" id="sessionTimeOut" required="required" placeholder="请输入Session有效期，默认1小时"/>
                                        </div>
                                        <div class="col-md-1 col-sm-1 col-xs-1">
                                            <select name="sessionTimeOutUnit" id="sessionTimeOutUnit" class="form-control" required="required" >
                                                <@zhydTag method="sessionTimeOutUnit">
                                                    <option value="">请选择</option>
                                                    <#list sessionTimeOutUnit as item>
                                                        <option value="${item}">${item}</option>
                                                    </#list>
                                                </@zhydTag>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tab_comment" aria-labelledby="comment-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12 aero" for="anonymous">允许匿名评论？
                                            <i class="fa fa-question-circle" title="【暂不可用】是否允许匿名评论，如果为否则必须需要登录。"></i>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12 fixed-radio-checkbox">
                                            <ul class="list-unstyled list-inline">
                                                <li><label for="anonymous" class="pointer"> <input type="radio" class="square" checked name="anonymous" value="1"> 开启 </label></li>
                                                <li><label for="anonymous" class="pointer"> <input type="radio" class="square" name="anonymous" value="0"> 关闭 </label></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment">开启留言板评论 <i class="fa fa-question-circle" title="控制留言板页面的评论框显示情况"></i></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12 fixed-radio-checkbox">
                                            <ul class="list-unstyled list-inline">
                                                <li><label for="comment" class="pointer"> <input type="radio" class="square" checked name="comment" value="1"> 开启 </label></li>
                                                <li><label for="comment" class="pointer"> <input type="radio" class="square" name="comment" value="0"> 关闭 </label></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="editorPlaceholder">Placeholder <i class="fa fa-question-circle" title="占位符，当没输入内容时显示该值"></i>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" id="editorPlaceholder" name="editorPlaceholder" placeholder="例如：说点什么吧">
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="editorAlert">警示语 <i class="fa fa-question-circle" title="评论框右下角显示的内容"></i> </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" id="editorAlert" name="editorAlert" placeholder="例如：讲文明、要和谐">
                                        </div>
                                    </div>

                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tab_article_editor" aria-labelledby="article-editor-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="articleEditor">默认文章编辑器 <i class="fa fa-question-circle" title="文章编辑器"></i></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12 fixed-radio-checkbox">
                                            <select name="articleEditor" id="articleEditor" class="form-control">
                                                <option value="md">Markdown编辑器</option>
                                                <option value="we">WangEditor编辑器</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tab_contact" aria-labelledby="contact-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="authorName">站长名称</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="authorName" id="authorName" placeholder="请输入站长名称"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="authorEmail">站长邮箱</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="authorEmail" id="authorEmail" placeholder="请输入站长邮箱"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="wxCode">微信二维码</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="wxCode" id="wxCode" placeholder="请输入微信二维码"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="qq">QQ</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="qq" id="qq" placeholder="请输入QQ"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="weibo">微博</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="weibo" id="weibo" placeholder="请输入微博"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="github">GitHub</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="github" id="github" placeholder="请输入GitHub"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tab_praise" aria-labelledby="praise-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="homeDesc">微信赞赏码 </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="file" class="form-control col-md-7 col-xs-12 uploadPreview" data-preview-container="#wxPraiseCodePreview" name="wxPraiseCode" id="wxPraiseCode"/>
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <div id="wxPraiseCodePreview" style="width: 200px;height: auto"></div>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="homeKeywords">支付宝赞赏码 </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="file" class="form-control col-md-7 col-xs-12 uploadPreview" data-preview-container="#zfbPraiseCodePreview" id="zfbPraiseCode" name="zfbPraiseCode"/>
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <div id="zfbPraiseCodePreview" style="width: 200px;height: auto"></div>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tab_setting" aria-labelledby="setting-tab">
                                <form class="form-horizontal form-label-left" novalidate>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="authorName">百度Api的AK <i class="fa fa-question-circle" title="用于通过百度地址接口获取用户当前的位置"></i></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" class="form-control col-md-7 col-xs-12" name="baiduApiAk" id="baiduApiAk" placeholder="请输入百度Api的AK"/>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <small>获取地址：<a href="http://lbsyun.baidu.com/apiconsole/key" target="_blank">点击获取百度Api AK</a></small>
                                        </div>
                                    </div>
                                    <div class="clear"></div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="maintenance">维护通知
                                            <i class="fa fa-question-circle" title="网站在更新前， 可以通过开启该功能，通知用户"></i> </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12 fixed-radio-checkbox">
                                            <ul class="list-unstyled list-inline">
                                                <li><label for="maintenance" class="pointer"> <input type="radio" class="square" checked name="maintenance" value="1"> 显示 </label> </li>
                                                <li><label for="maintenance" class="pointer"> <input type="radio" class="square" name="maintenance" value="0"> 关闭 </label></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="maintenanceDate">维护日期</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <div class='input-group date myDatepicker'>
                                                <input type='text' class="form-control" readonly="readonly" id="maintenanceDate" name="maintenanceDate"  placeholder="请输入维护日期"/>
                                                <span class="input-group-addon">
                                                   <span class="fa fa-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="maintenanceTime">维护用时</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <div class='input-group'>
                                                <input type='text' class="form-control" id="maintenanceTime" name="maintenanceTime"  placeholder="请输入维护大约需要的时间"/>
                                                <span class="input-group-addon">
                                                   分
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dynamicTitle">动态标题 <i class="fa fa-question-circle" title="当切换浏览器tab时，在原tab上的标题。比如https://www.zhyd.me上的“麻溜儿回来~~~”"></i></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type='text' class="form-control" id="dynamicTitle" name="dynamicTitle"  placeholder="请输入切换窗口时想要显示的标题，如：麻溜儿回来~~~"/>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="button" class="btn btn-primary saveBtn"><i class="fa fa-save"> 保存</i></button>
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
</div>
<div class="modal fade" id="storageNginxServerModal" tabindex="-1" role="dialog"
     aria-labelledby="storageNginxServerModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="storageNginxServerModalLabel">Nginx文件服务器配置</h4>
            </div>
            <div class="modal-body">
                <@zhydTag method="template" key="TM_NGINX_FILE_SERVER">
                    <#if template!>
                        <textarea class="form-control" rows="15" cols="">${template.refValue}</textarea>
                    <#else>
                        <textarea class="form-control" placeholder="暂无可参考的配置" disabled readonly></textarea>
                    </#if>
                </@zhydTag>

                <div class="item form-group">
                    <fieldset>
                        <legend style="padding-bottom: 0;"><h4>使用帮助<i class="fa fa-question-circle fa-fw"></i></h4>
                        </legend>
                        <dl>
                            <dt><i class="fa fa-info-circle fa-fw"></i>1. 替换配置文件中的指定内容</dt>
                            <dd><code>serverName</code> 改为自己的域名</dd>
                            <dd><code>serverPath</code> Nginx文件服务映射的服务器路径，同云存储中填写的“文件存储路径”</dd>
                            <dd><code>serverReferers</code> 防盗链的Referers，多个用空格分隔，支持通配符，比如：<code>*.zhyd.me zhyd.me</code></dd>
                            <dd><code>serverLogoPath</code> 触发防盗链后显示的默认图片，即当别人引用你网站中的图片时，会触发防盗链，对方网站中看到的就是 <code>serverLogoPath</code>对应的文件内容</dd>
                        </dl>
                        <dl>
                            <dt><i class="fa fa-info-circle fa-fw"></i>2. 添加Nginx配置</dt>
                            <dd>i. 将上方文本域修改后的内容保存为<code>**.conf</code>，放入到Nginx配置文件目录中</dd>
                            <dd>ii. 重启Nginx</dd>
                            <dd>iii. 尝试访问<code>serverName</code>检查Nginx是否配置成功</dd>
                        </dl>
                    </fieldset>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script type="text/javascript">
        $(function () {
            var oldStorageType, firstLoad = true;
            $.ajax({
                url: '/config/get',
                type: 'POST',
                success: function (json) {
                    var data = json.data;
                    oldStorageType = data.storageType;
                    $("#myTabContent").find("input, select, textarea").each(function () {
                        new Table().clearText($(this), this.type, data);
                    });
                    changeMaintenance(data.maintenance && data.maintenance == 1, data.maintenance);
                    data.zfbPraiseCode && $("#zfbPraiseCodePreview").html('<img src="' + data.zfbPraiseCode + '" alt="支付宝赞赏码" class="img-responsive img-rounded auto-shake">');
                    data.wxPraiseCode && $("#wxPraiseCodePreview").html('<img src="' + data.wxPraiseCode + '" alt="微信赞赏码" class="img-responsive img-rounded auto-shake">');
                }
            });

            $(".saveBtn").click(function () {
                var $this = $(this);
                var $form = $this.parents("form");
                if (validator.checkAll($form)) {
                    $form.ajaxSubmit({
                        type: "POST",
                        url: '/config/save',
                        success: function (json) {
                            $.alert.ajaxSuccess(json);
                        },
                        error: $.alert.ajaxError
                    });
                }
            });

            $("#tab_storage input[name=storageType]").on('ifChecked', function (event) {
                var $this = $(this);
                var thisValue = $this.val();
                if (!$("#" + thisValue).hasClass("hide")) {
                    return;
                }
                function changeStorageBox() {
                    $(".storage-box").each(function () {
                        var $box = $(this);
                        if ($box.attr("id") === thisValue) {
                            $box.removeClass("hide").find("input").removeAttr("disabled").removeAttr("readonly");
                        } else {
                            $box.addClass("hide").find("input").attr("disabled", "disabled").attr("readonly", "readonly");
                        }
                    });
                }
                if(firstLoad) {
                    changeStorageBox();
                    firstLoad = false;
                    oldStorageType = thisValue;
                } else {
                    if(oldStorageType !== thisValue) {
                        $.alert.confirm("您确定要切换云存储类型吗？切换后原文件将不可访问！", function () {
                            oldStorageType = thisValue;
                            changeStorageBox();
                        }, function () {
                            $("#tab_storage input[name=storageType]").each(function () {
                                var $this = $(this);
                                $this.iCheck((oldStorageType !== $this.val()) ? 'uncheck' : 'check');
                            });
                        });
                    }
                }
            });

            $("#tab_setting input[name=maintenance]").on('ifChanged', function (event) {
                changeMaintenance($(this).is(':checked'), $(this).val());
            });
            function changeMaintenance(checked, thisVal){
                if (checked && thisVal == 1) {
                    $("#maintenanceDate, #maintenanceTime").each(function () {
                        var $this = $(this);
                        var $label = $this.parents("div.form-group").find("label");
                        $this.attr("required", "required");
                        $label.append('<span class="required">*</span>');
                    })
                } else {
                    $("#maintenanceDate, #maintenanceTime").each(function () {
                        var $this = $(this);
                        var $span = $this.parents("div.form-group").find("label span");
                        $this.removeAttr("required");
                        $span.remove();
                    })
                }
            }

            $("#aliyunBucketName, #aliyunEndpoint").change(function () {
                var $fileUrl = $("#aliyunFileUrl");
                var aliyunBucketName = $("#aliyunBucketName").val();
                var aliyunEndpoint = $("#aliyunEndpoint").val();
                if(aliyunBucketName && aliyunEndpoint) {
                    $fileUrl.val("https://" + aliyunBucketName + "." + aliyunEndpoint + "/");
                }
            });

        });
    </script>
</@footer>