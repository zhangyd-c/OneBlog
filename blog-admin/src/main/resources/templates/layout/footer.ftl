<script type="text/javascript">
    var appConfig = {
        fileStoragePath: '${config.fileStoragePath}',
        wwwPath: '${config.siteUrl}',
        cmsPath: '${config.cmsUrl}',
        staticPath: '${config.staticWebSite}'
    }
</script>
<#if config.adminJsCdn?? && (config.adminJsCdn?length > 0)>
    ${config.adminJsCdn!}
<#else >
    <script type="text/javascript" src="/assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery-confirm.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.fancybox.min.js"></script>
    <script type="text/javascript" src="/assets/js/mustache.min.js"></script>
    <script type="text/javascript" src="/assets/js/xss.min.js"></script>
    <script type="text/javascript" src="/assets/js/nprogress.min.js"></script>
    <script type="text/javascript" src="/assets/js/toastr.min.js"></script>
    <script type="text/javascript" src="/assets/js/icheck.min.js"></script>
    <script type="text/javascript" src="/assets/js/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="/assets/js/bootstrap-table-zh-CN.min.js"></script>

    <script type="text/javascript" src="/assets/js/moment.min.js"></script>
    <script type="text/javascript" src="/assets/js/daterangepicker.min.js"></script>
    <script type="text/javascript" src="/assets/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="/assets/js/bootstrap-progressbar.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.ztree.excheck.min.js"></script>
    <script type="text/javascript" src="/assets/js/switchery.min.js"></script>
    <script type="text/javascript" src="/assets/js/wangeditor-index.min.js" async></script>
    <script type="text/javascript" src="/assets/js/bootstrap-tagsinput.min.js"></script>
</#if>
<#if config.tinyMCEKey?exists>
<script src="https://cdn.tiny.cloud/1/${config.tinyMCEKey!}/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<#else>
<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js"></script>
</#if>
<#--
<script src="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.buttons.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pnotify/3.2.1/pnotify.nonblock.js"></script>-->

<script src="/assets/js/bootstrap-treetable.js" type="text/javascript"></script>
<script src="/assets/js/validator.js"></script>
<script src="/assets/js/ajaxfileupload.js"></script>
<script src="/assets/js/jquery-form.js"></script>
<script src="/assets/js/zhyd.tool.js"></script>
<script src="/assets/js/zhyd.upload-preview.js"></script>
<script src="/assets/js/gentelella.core.js"></script>
<script src="/assets/js/zhyd.core.js"></script>
<script src="/assets/js/zhyd.table.js"></script>
