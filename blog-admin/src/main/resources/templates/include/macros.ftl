<#-- 公共顶部 -->
<#macro header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${config.siteName}后台管理系统</title>
    <link href="/assets/images/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/jquery-confirm/2.5.1/jquery-confirm.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/fancybox/2.1.5/jquery.fancybox.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/toastr.js/2.0.3/css/toastr.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/iCheck/1.0.2/skins/square/green.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/daterangepicker.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/zTree.v3/3.5.29/css/metroStyle/metroStyle.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/switchery/0.8.2/switchery.min.css" rel="stylesheet">
<#--
    <link href="https://cdn.bootcss.com/pnotify/3.2.1/pnotify.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/pnotify/3.2.1/pnotify.buttons.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/pnotify/3.2.1/pnotify.nonblock.css" rel="stylesheet">-->

    <link href="/assets/css/zhyd.core.css" rel="stylesheet">
    <#nested>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="/" class="site_title"><i class="fa fa-coffee"></i> <span>${config.siteName}</span></a>
                </div>
                <div class="clearfix"></div>
                <@shiro.user>
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="/assets/images/loading.gif" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span id="hello_msg">&nbsp;</span>
                        <h2>尊敬的管理员</h2>
                    </div>
                </div>
                </@shiro.user>
                <br />
            <#include "/layout/sidebar.ftl"/>
            </div>
        </div>
    <#include "/layout/setting.ftl"/>
        <div class="right_col" role="main">
</#macro>

<#-- 公共底部 -->
<#macro footer>
    <#include "/layout/footer.ftl"/>

    <#nested>

    </body>
</html>
</#macro>

<#macro breadcrumb>
    <div class="row">
        <div class="col col-md-9">
            <nav class="breadcrumb">
                <div class="notify"><i class="fa fa-bullhorn fa-fw"></i></div>
                <div id="scrolldiv">
                    <div class="scrolltext">
                        <ul class="list-unstyled" id="notice-box">
                            <li class="scrolltext-title">
                                <a href="javascript:void(0)" rel="bookmark">其实我们可以将所有的问题归结为两种：一种是没饭吃饿出来的；一种是吃饱了撑出来的。</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="col col-md-3 text-right">
            <#nested>
        </div>
    </div>
</#macro>