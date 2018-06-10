<@shiro.user>
<div class="top_nav">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <img src="/assets/images/loading.gif" alt=""><#if user?exists>${user.username?if_exists}<#else>管理员</#if>
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a data-toggle="modal" title="修改密码" data-target="#updPasswordModal">修改密码</a></li>
                        <@shiro.hasRole name="role:root">
                        <li>
                            <a href="/config">
                                <span>系统配置</span>
                            </a>
                        </li>
                        </@shiro.hasRole>
                        <li><a href="/passport/logout"><i class="fa fa-sign-out pull-right"></i> 退出系统</a></li>
                    </ul>
                </li>
                <@shiro.hasPermission name="comments">
                <li role="presentation" class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                        <i class="fa fa-envelope-o"></i>
                        <span class="badge bg-green noticeNum">0</span>
                    </a>
                    <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                        <li>
                            <div class="text-center">
                                <a>
                                    <strong>See All Alerts</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </div>
                        </li>
                    </ul>
                </li>
                </@shiro.hasPermission>
                <li>
                    <a href="${config.siteUrl?if_exists}" target="_blank">
                        <i class="fa fa-desktop"> 访问前台</i>
                    </a>
                </li>
                <li>
                    <a class="pointer" data-toggle="modal" data-target="#reward">
                        <i class="fa fa-cny"> 捐赠博主</i>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>

<div class="modal fade" id="reward" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">捐赠博主 <small>码代码很累的！天热了，请作者吃根冰棍儿吧！<i class="fa fa-smile-o"></i></small></h4>
            </div>
            <div class="modal-body">
                <div class="col-sm-12 col-md-12" style="text-align: center;margin: 0 auto;float: initial">
                    <a href="/assets/images/reward/zfb_code.png" class="showImage" title="支付宝收钱码" rel="external nofollow">
                        <img src="/assets/images/reward/zfb_code.png" alt="支付宝收钱码" class="img-rounded" style="width: 250px;height: auto;">
                    </a>
                    <a href="/assets/images/reward/wx_code.png" class="showImage" title="微信收钱码" rel="external nofollow">
                        <img src="/assets/images/reward/wx_code.png" alt="微信收钱码" class="img-rounded" style="width: 250px;height: auto">
                    </a>
                </div>
                <div style="width: 100%;color: #a3a3a3;font-size: 16px;font-family: 'Microsoft YaHei';text-align: center;margin-top: 10px">
                    转账时请备注“<strong>博客赞助</strong>”，并另附上您的称呼（方便博主统计）
                </div>
            </div>
        </div>
        <small class="font-bold"></small>
    </div>
    <small class="font-bold"> </small>
</div>

<#-- 修改密码Modal -->
<div class="modal fade" id="updPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">修改密码</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-horizontal form-label-left" role="form" id="updPassForm">
                    <input type="hidden" name="id" value="<#if user?exists>${user.id?c}</#if>">
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="password">旧密码 <span class="required">*</span></label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <input class="form-control col-md-7 col-xs-12" id="password" name="password" required="required" type="password" minlength="6" maxlength="15">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label for="newPassword" class="control-label col-md-3 col-sm-3 col-xs-3">新密码 <span class="required">*</span></label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <input id="newPassword" type="password" name="newPassword" data-validate-length="5,20" class="form-control col-md-7 col-xs-12" required="required" minlength="6" maxlength="15">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label for="newPasswordRepeat" class="control-label col-md-3 col-sm-3 col-xs-3">重复新密码 <span class="required">*</span></label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <input id="newPasswordRepeat" type="password" name="newPasswordRepeat" data-validate-linked="newPassword" class="form-control col-md-7 col-xs-12" required="required" minlength="6" maxlength="15">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"> 关闭</i></button>
                <button type="button" class="btn btn-primary" id="updPassBtn">修改密码</button>
            </div>
        </div>
    </div>
</div>
</@shiro.user>