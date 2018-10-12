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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/2.5.1/jquery-confirm.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.3/css/toastr.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/square/green.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-daterangepicker/2.1.24/daterangepicker.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/eonasdan-bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/zTree.v3/3.5.29/css/metroStyle/metroStyle.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/switchery/0.8.2/switchery.min.css" rel="stylesheet">

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