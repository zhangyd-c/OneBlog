<#include "/include/macros.ftl">
<@header>
    <style type="text/css">
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
                    <h2>
                        编辑器用例
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="form-group row">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="name">原生 wangEditor</label>
                        <div class="col-md-8 col-sm-8 col-xs-12">
                            <div id="toolbar1" style="border: 1px solid #ccc;"></div>
                            <div id="editor1" class="text" style="height: 500px">
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="name">oneBlog系统定制的 wangEditor</label>
                        <div class="col-md-8 col-sm-8 col-xs-12">
                            <div id="editor">

                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="name">oneBlog系统定制的编辑器 TinyMCE</label>
                        <div class="col-md-8 col-sm-8 col-xs-12">
                            <div id="editor3"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<@footer>
    <script type="text/javascript">
        const { createEditor, createToolbar } = window.wangEditor
        const editor1 = createEditor({
            selector: '#editor1',
            html: '<p><br></p>',
            config: {
                placeholder: 'Type here...',
                onChange(editor) {
                    const html = editor.getHtml()
                    console.log('changed editor content', html)
                    // 也可以同步到 <textarea>
                }
            },
            mode: 'default', // or 'simple'
        })
        createToolbar({
            editor: editor1,
            selector: '#toolbar1',
            config: {
            },
            mode: 'default', // or 'simple'
        })

        // oneblog定制版的wangEditor
        zhyd.wangEditor.init({
            container: "#editor",
            textareaName: "content",
            uploadUrl: "/api/uploadFile",
            uploadFileName: "file",
            uploadType: "goods",
            defaultHtml: '',
            customCss: {
                "border": "1px solid #ccc",
                "height": "300px"
            }
        })

        zhyd.tinymce.init({
            selector: "#editor3",
            uploadUrl: "/api/uploadFile",
            uploadFileName: "file",
            textareaName: "content2",
        })

        setTimeout(function (){
            console.log(tinymce.activeEditor.getContent())
            console.log($('textarea[name=content2]').val())
        }, 10000)
    </script>
</@footer>
