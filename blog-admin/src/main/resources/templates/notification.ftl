<#include "include/macros.ftl">
<@header></@header>
    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Websocket实现向用户发送通知 <small>当前在线人数: ${online!("0")}</small></h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal form-label-left" novalidate>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="domain">消息通知内容<span class="required">*</span></label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <textarea class="form-control col-md-7 col-xs-12" id="msg" name="msg" required="required" placeholder="请输入消息通知内容" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="comment"></label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <button type="button" class="btn btn-primary" id="saveBtn"><i class="fa fa-send"> 发送</i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script type="text/javascript">
        $("#saveBtn").click(function () {
            var $form = $(this).parents("form");
            var msg = $("#msg").val();
            $.post("/api/notice", {msg : msg}, function (json) {
                $.alert.ajaxSuccess(json);
            })
        });
    </script>

</@footer>