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
        const { createEditor, createToolbar, Boot,IButtonMenu } = window.wangEditor
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
                insertKeys: {
                    index: editor1.getAllMenuKeys().length, // 插入的位置，基于当前的 toolbarKeys
                    keys: ['insertArticleAd']
                }
            },
            mode: 'default', // or 'simple'
        })


        class ArticleAdButtonMenu {
            constructor() {
                this.title = '插入广告' // 自定义菜单标题
                // this.iconSvg = '<svg>...</svg>' // 可选
                this.tag = 'button'
            }
            // 获取菜单执行时的 value ，用不到则返回空 字符串或 false
            getValue(editor) {
                return '{{#custom_article_ad:all:*}}'
            }
            // 菜单是否需要激活（如选中加粗文本，“加粗”菜单会激活），用不到则返回 false
            isActive(editor) {
                return false
            }
            // 菜单是否需要禁用（如选中 H1 ，“引用”菜单被禁用），用不到则返回 false
            isDisabled(editor) {
                return false
            }
            // 点击菜单时触发的函数
            exec(editor, value) {
                if (this.isDisabled(editor)) {
                    return
                }
                $.toastr.success("广告标识插入成功，<span style='color: red;font-size: 700'>该标识内容不可修改</span>！修改会导致文章内广告显示异常");
                editor.insertText(value)
            }
        }
        Boot.registerMenu({
            key: 'insertArticleAd', // 定义 menu key ：要保证唯一、不重复（重要）
            factory() {
                return new ArticleAdButtonMenu() // 把 `YourMenuClass` 替换为你菜单的 class
            }
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
