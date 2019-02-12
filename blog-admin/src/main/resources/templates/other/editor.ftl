<#include "/include/macros.ftl">
<@header>
    <style type="text/css">
        .toolbar {
            background-color: #f1f1f1;
            border: 1px solid #ccc;
        }
        .text {
            border: 1px solid #ccc;
            height: 200px;
        }
    </style>
</@header>
    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>wangEditor富文本编辑器用例
                        <small><a href="http://www.wangeditor.com/" target="_blank">http://www.wangeditor.com/</a></small>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="form-group row">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="name">菜单和编辑器区域分开 </label>
                        <div class="col-md-8 col-sm-8 col-xs-12">
                            <div id="toolbar" class="toolbar"></div>
                            <div style="padding: 5px 0; color: #ccc">中间隔离带</div>
                            <div id="div1" class="text" style="height: 100px">
                                <p>第一个 demo（菜单和编辑器区域分开）</p>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="name">普通的编辑器 </label>
                        <div class="col-md-8 col-sm-8 col-xs-12">
                            <div id="div2">
                                <p>第二个 demo（常规）</p>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group row">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="name">oneBlog系统定制的编辑器 </label>
                        <div class="col-md-8 col-sm-8 col-xs-12">
                            <div id="editor">
                                <p>第三个 demo（oneBlog系统单独定制，支持文件上传）</p>
                                <p>
                                    使用方式：
                                <pre><code># html<br>&lt;div id="editor"&gt;&lt;/div&gt;<br><br># js<br>$.wangEditor.init({<br>	container: "#editor",<br>	textareaName: "content",<br>	uploadUrl: "/api/uploadFile",<br>	uploadFileName: "file",<br>	uploadType: "goods",<br>	customCss: {<br>		"overflow-y": "scroll",<br>		"height": "100%",<br>		"max-height": "125px"<br>	}<br>})</code></pre>
                                </p>
                                <ul>
                                    <li>container: 编辑器的id，默认为editor</li>
                                    <li>textareaName: 自动生成的textarea组件的name，默认为content。可以自定义为表单中实际的参数name</li>
                                    <li>uploadUrl: 文件上传的api路径。如果不为空，则开启上传文件的功能</li>
                                    <li>uploadFileName: 文件上传时后台接收文件的参数名，默认为file</li>
                                    <li>uploadType: 当前上传文件的场景类型，<strong>最好根据实际业务取名</strong>，它会决定最终上传完成后的文件路径，比如在商品信息管理页中指定了uploadType = goods,那么最终上传完成后的文件路径就是：<code>oneblog/goods/{filename}.png</code>，默认为空</li>
                                    <li>customCss: 自定义的css，可以修改编辑器大小，默认为空。注：如果是修改高度，必须通过<code>max-height</code>参数修改，并且一定要加上：<code>"overflow-y": "scroll"</code>, <code>"height": "100%"</code>这两项配置，否则可能会使编辑器显示不正确</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script type="text/javascript">
        var E = window.wangEditor
        var editor1 = new E('#toolbar', '#div1');
        editor1.customConfig.zIndex = 10;
        editor1.create();

        var editor2 = new E('#div2');
        editor2.customConfig.zIndex = 10;
        editor2.create();
        $("#div2").find(".w-e-text-container").css("height","100px");

        // oneblog定制版的wangEditor
        zhyd.wangEditor.init({
            container: "#editor",
            textareaName: "content",
            uploadUrl: "/api/uploadFile",
            uploadFileName: "file",
            uploadType: "goods",
            customCss: {
                "overflow-y": "scroll",
                "height": "100%",
                "max-height": "600px"
            }
        })
    </script>
</@footer>