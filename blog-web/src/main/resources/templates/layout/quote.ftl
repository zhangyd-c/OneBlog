    <link href="${config.siteFavicon}" rel="shortcut icon" type="image/x-icon">
    <#if config.webCssCdn?? && (config.webCssCdn?length > 0)>
        ${config.webCssCdn!}
    <#else >
        <link href="https://cdn.bootcdn.net/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.bootcdn.net/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css" rel="stylesheet">
        <link href="https://cdn.bootcdn.net/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" rel="stylesheet">
        <link href="https://cdn.bootcdn.net/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
        <link href="https://cdn.bootcdn.net/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
    </#if>
    <link href="${config.staticWebSite}/css/zhyd.core.css" rel="stylesheet" type="text/css">
    <link href="${config.staticWebSite}/css/zhyd.comment.css" rel="stylesheet" type="text/css">
