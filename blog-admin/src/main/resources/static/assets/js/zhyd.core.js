/**
 *
 * 项目核心Js类，负责项目前端模板方面的初始化等操作
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018-04-25
 * @since 1.0
 */
var editor = null, simplemde = null;

var zhyd = window.zhyd || {
    combox: {
        init: function (mockComboxCallback) {
            $('select[target=combox]').each(function (e) {
                var $this = $(this);
                var url = $this.data("url");
                if (!url) {
                    return false;
                }
                var method = $this.data("method") || "get";
                $.ajax({
                    url: url,
                    type: method,
                    success: function (json) {
                        if (json && json.status == 200) {
                            var optionTpl = '<option value="">请选择</option>{{#data}}<option value="{{id}}">{{name}}</option>{{#nodes}}<option value="{{id}}">  > {{name}}</option>{{/nodes}}{{/data}}';
                            var html = Mustache.render(optionTpl, json);
                            $this.html(html);
                        }
                    }
                });
            });
            $('ul[target=combox], ol[target=combox]').each(function (e) {
                var $this = $(this);
                var url = $this.data("url");
                if (!url) {
                    return false;
                }
                var method = $this.data("method") || "get";
                $.ajax({
                    url: url,
                    type: method,
                    success: function (json) {
                        if (json && json.status == 200) {
                            var liTpl = '{{#data}}<li data-value="{{id}}">{{name}}</li>{{/data}}';
                            var html = Mustache.render(liTpl, json);
                            $this.html(html);

                            if ($.isFunction(mockComboxCallback)) {
                                mockComboxCallback();
                            }
                        }
                    }
                });
            })
        }
    },
    tagsInput: {
        init: function () {
            setTimeout(function () {
                $('select[target="tagsinput"], input[target="tagsinput"]').each(function () {
                    var $this = $(this);
                    var $bindBox = $this.data("bind-box");
                    if (!$bindBox || $bindBox.length <= 0) {
                        return;
                    }

                    $this.tagsinput({
                        itemValue: 'id',
                        itemText: 'name',
                        maxTags: 3,
                        maxChars: 20,
                        trimValue: true,
                        focusClass: 'focus'
                    });

                    function add() {
                        var thisId = $(this).data("value");
                        var thisText = $(this).text().trim();
                        console.log(thisText);
                        $this.tagsinput('add', {"id": thisId, "name": thisText}, {add: false});
                    }

                    $($bindBox).find("li").each(function () {
                        var $li = $(this);
                        $li.bind('click', add);
                    });
                    $(".bootstrap-tagsinput input").bind('keydown', function (event) {
                        var thisVal = $(this).val();
                        if (event.key == 'Enter' || event.keyCode == '13') {
                            $.post('/tag/add', {name: thisVal, description: thisVal}, function (response) {
                                if (response.status !== 200) {
                                    $.alert.error(response.message);
                                } else {
                                    var data = response.data;
                                    $this.tagsinput('add', {"id": data.id, "name": data.name}, {addNew: true});
                                }
                            });
                        }
                    });
                    $this.on('itemAdded', function (event) {
                        var tag = event.item;
                        if (event.options && event.options.addNew) {
                            $(".bootstrap-tagsinput input").val('');
                            $('<li data-value="' + tag.id + '">' + tag.name + '</li>').bind('click', add).appendTo($($bindBox));
                        }
                    });
                })
            }, 700);
        }
    },
    initTextSlider: function () {
        $.fn.textSlider = function (settings) {
            settings = jQuery.extend({
                speed: "normal",
                line: 2,
                timer: 3000
            }, settings);
            return this.each(function () {
                $.fn.textSlider.scllor($(this), settings);
            });
        };
        $.fn.textSlider.scllor = function ($this, settings) {
            var ul = $("ul:eq(0)", $this);
            var timerID;
            var li = ul.children();
            var liHight = $(li[0]).height();
            var upHeight = 0 - settings.line * liHight;//滚动的高度；
            var scrollUp = function () {
                ul.animate({marginTop: upHeight}, settings.speed, function () {
                    for (i = 0; i < settings.line; i++) {
                        ul.find("li:first", $this).appendTo(ul);
                    }
                    ul.css({marginTop: 0});
                });
            };
            var autoPlay = function () {
                timerID = window.setInterval(scrollUp, settings.timer);
            };
            var autoStop = function () {
                window.clearInterval(timerID);
            };
            //事件绑定
            ul.hover(autoStop, autoPlay).mouseout();
        };

        if ($("#scrolldiv")) {
            $("#scrolldiv").textSlider({line: 1, speed: 300, timer: 10000});
        }
    },
    wangEditor: {
        _instance: window.wangEditor,
        defaultConfig: {
            container: "#editor",
            textareaName: "content",
            uploadUrl: "",
            uploadFileName: "file",
            uploadType: "",
            customCss: {
                border: "1px solid #ccc;",
                height: "300px"
            },
            defaultHtml: "<p><br></p>"

        },
        init: function (options) {
            var config = $.extend(zhyd.wangEditor.defaultConfig, options);
            $(config.container).before($('<div id="toolbar-container"></div>'));
            if (config.textareaName) {
                $('<textarea class="wangeditor-textarea" id="' + config.textareaName + '" name="' + config.textareaName + '" style="display: none" required="required"></textarea>').insertAfter($(config.container));
            }
            var $contentBox = $('textarea[name=' + config.textareaName + ']');

            if (config.customCss) {
                // 自定义编辑器的样式
                for (var key in config.customCss) {
                    var value = config.customCss[key];
                    $(config.container).css(key, value);
                }
            }

            const { createEditor, createToolbar, Boot,IButtonMenu } = window.wangEditor

            const editorConfig = {
                placeholder: 'Type here...',
                onChange(editor) {
                    const html = editor.getHtml()
                    // 监控变化，同步更新到 textarea
                    $contentBox.val(html);
                    console.log('changed editor content', html)
                },
                customAlert(msg, type){
                    switch (type) {
                        case 'success':
                            $.alert.info(msg);
                            break
                        case 'info':
                            $.alert.info(msg);
                            break
                        case 'warning':
                            $.alert.error(msg);
                            break
                        case 'error':
                            $.alert.error(msg);
                            break
                        default:
                            $.alert.info(msg);
                            break
                    }
                },
                MENU_CONF: {
                    uploadImage: {
                        server: config.uploadUrl,
                        // form-data fieldName ，默认值 'wangeditor-uploaded-image'
                        fieldName: config.uploadFileName,

                        // 单个文件的最大体积限制，默认为 5M
                        maxFileSize: 5 * 1024 * 1024, // 5M

                        // 最多可上传几个文件，默认为 100
                        maxNumberOfFiles: 1,

                        // 选择文件时的类型限制，默认为 ['image/*'] 。如不想限制，则设置为 []
                        allowedFileTypes: ['image/*'],

                        // 自定义上传参数，例如传递验证的 token 等。参数会被添加到 formData 中，一起上传到服务端。
                        meta: {
                            uploadType: config.uploadType,
                        },

                        // 将 meta 拼接到 url 参数中，默认 false
                        metaWithUrl: false,

                        // 自定义增加 http  header
                        headers: {
                        },

                        // 跨域是否传递 cookie ，默认为 false
                        withCredentials: true,

                        // 超时时间，默认为 10 秒
                        timeout: 10 * 1000,

                        // 单个文件上传失败
                        onFailed(file, res) {   // TS 语法
                            console.log(`${file.name} 上传失败`, res)
                            $.alert.error(`${file.name} 上传失败`);
                        },

                        // 上传错误，或者触发 timeout 超时
                        onError(file, err, res) {  // TS 语法
                            console.log(`${file.name} 上传出错`, err, res)
                            $.alert.error(`${file.name} 上传出错, ${err}`);
                        },
                        // 自定义插入图片
                        customInsert(result, insertFn) {
                            // customInsert(res, insertFn) {                  // JS 语法
                            // res 即服务端的返回结果

                            // 从 res 中找到 url alt href ，然后插入图片
                            console.log('upload callback：', result);
                            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
                            if (result.status === 200) {
                                var imgFullPath = result.data;
                                const html = '<img src="' + imgFullPath + '" alt="" style="max-width: 100%;height: auto;border-radius: 6px;"/>';
                                editor.dangerouslyInsertHtml(html);
                            } else {
                                $.alert.error(result.message);
                            }
                        },
                    },
                    uploadVideo: {
                        server: '/api/unsupported_upload_video',
                        fieldName: 'unsupported',
                        timeout: 0, // 5 秒
                        onBeforeUpload(file) {
                            $.alert.error('不支持在线上传视频，请使用插入视频');
                            return false
                        }
                    }
                }
            }
            editor = createEditor({
                selector: config.container,
                html: config.defaultHtml,
                config: editorConfig,
                mode: 'default', // or 'simple'
            })

            const toolbar = createToolbar({
                editor,
                selector: '#toolbar-container',
                config: {
                    insertKeys: {
                        index: editor.getAllMenuKeys().length, // 插入的位置，基于当前的 toolbarKeys
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
                    return '#CUSTOM_AD_PLACEHOLDER#'
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

            // 注册全屏插件
            // 注册图片资源库
            zhyd.wangEditor.plugins.registerMaterial(editor, $contentBox);
            //
            // if (config.customCss) {
            //     // 自定义编辑器的样式
            //     for (var key in config.customCss) {
            //         var value = config.customCss[key];
            //         editor.$textContainerElem.css(key, value);
            //     }
            // }
        },
        plugins: {
            registerMaterial: function (editor, $contentBox) {
                $("div[id^='w-e-img']").unbind("click").click(function () {
                    setTimeout(function () {
                        // 删掉原来的input#file，防止触发原选中文件的函数
                        $(".w-e-up-img-container").find("input").remove();
                        // 重新绑定选中图片按钮的事件
                        $("div[id^='up-trigger'], div.w-e-up-btn").unbind("click").click(function () {
                            $.modal.material.open({multiSelect: true, selectable: 10}, function (selectedImageUrls) {
                                if(!selectedImageUrls) {
                                    return false;
                                }
                                for(var i = 0; i < selectedImageUrls.length; i ++){
                                    editor.txt.append('<img src="' + selectedImageUrls[i] + '" alt="" style="max-width: 100%;height: auto;border-radius: 6px;"/>');
                                    $contentBox.val(editor.txt.html());
                                }
                            })
                        })
                    }, 50);
                })


            }
        }
    },
    simpleMDE: {
        defaultConfig: {
            id: "mdEditor",
            uploadUrl: "",
            uploadType: "",
            uniqueId: "mdEditor_1"
        },
        init: function (options) {
            var $op = $.extend(zhyd.wangEditor.defaultConfig, options);
            zhyd.simpleMDE.plugins.registerAutosave();
            // Powered by https://github.com/sparksuite/simplemde-markdown-editor
            simplemde = new SimpleMDE({
                // textarea的DOM对象
                element: document.getElementById($op.id),
                // 自动下载FontAwesome，设为false为不下载(如果设为false则必须手动引入)
                autoDownloadFontAwesome: false,
                // 自动聚焦输入框
                autofocus: true,
                // 是否自动保存正在编写的文本
                autosave: {
                    // 启用自动保存功能
                    enabled: true,
                    // 自动保存的间隔，以毫秒为单位。默认为15000（15s）
                    delay: 15000,
                    // 唯一的字符串标识符(保证每个SimpleMDE编辑器的uniqueId唯一)
                    uniqueId: $op.uniqueId,
                    msg: "自动保存成功了"
                },
                placeholder: "请输入文本内容",
                // 如果设置为true，则会出现JS警报窗口，询问链接或图像URL(插入图片或链接弹窗)。默认为false
                promptURLs: false,
                renderingConfig: {
                    // 如果设置为true，将使用highlight.js高亮显示。默认为false
                    codeSyntaxHighlighting: true
                },
                showIcons: ["code", "table", "clean-block", "horizontal-rule"],
                tabSize: 4,
                status: false
            });
            zhyd.simpleMDE.plugins.registerFullscreen();
            zhyd.simpleMDE.plugins.registerUpload($op.uploadUrl, simplemde);
            zhyd.simpleMDE.plugins.registerMaterial();

            $(".editor-preview-side").addClass("markdown-body");
        },
        plugins: {
            registerAutosave: function () {
                // js实现aop切面编程，实时保存文章内容
                Function.prototype.after = function (afterfn) {
                    var __self = this;
                    //保存原函数的引用
                    return function () {
                        //返回包含了原函数和新函数的"代理"函数
                        afterfn.apply(this, arguments);//(1)
                        //执行新函数,且保证this不被劫持,新函数接受的参数
                        //也会被原封不动地传入原函数,新函数在原函数之前执行
                        return __self.apply(this, arguments);//(2)
                        //执行原函数并返回原函数的执行结果
                        //并且保证this不被劫持
                    }
                };
                var showMsg = function () {
                    var $div = $('<div></div>');
                    $div.css({
                        'position': 'absolute',
                        'right': '10px',
                        'top': 0,
                        'padding': '5px',
                        'font-size': '12px',
                        'color': '#ccc',
                        'opacity': 0
                    });
                    $div.html("自动保存完成");
                    $div.appendTo($(".CodeMirror"));
                    $div.animate({opacity: 1}, 1000, function () {
                        $div.animate({opacity: 0}, 1000, function () {
                            $(this).remove();
                        })
                    })
                };
                SimpleMDE.prototype.autosave = SimpleMDE.prototype.autosave.after(showMsg);
            },
            registerFullscreen: function () {
                var $fullscreen = $(".editor-toolbar a.fa-arrows-alt, .editor-toolbar a.fa-columns");
                $fullscreen.click(function () {
                    var $this = $(this);
                    if ($fullscreen.hasClass("active")) {
                        $(".CodeMirror, .CodeMirror-scroll").css('max-height', 'none');
                    } else {
                        $(".CodeMirror, .CodeMirror-scroll").css('max-height', '200px');
                    }
                });
            },
            registerUpload: function (uploadUrl, simplemde) {
                if (uploadUrl) {
                    inlineAttachment.editors.codemirror4.attach(simplemde.codemirror, {
                        uploadUrl: uploadUrl
                    });
                }
            },
            registerMaterial: function () {
                function getState(cm, pos) {
                    pos = pos || cm.getCursor("start");
                    var stat = cm.getTokenAt(pos);
                    if(!stat.type) return {};
                    var types = stat.type.split(" ");
                    var ret = {},
                        data, text;
                    for(var i = 0; i < types.length; i++) {
                        data = types[i];
                        if(data === "strong") {
                            ret.bold = true;
                        } else if(data === "variable-2") {
                            text = cm.getLine(pos.line);
                            if(/^\s*\d+\.\s/.test(text)) {
                                ret["ordered-list"] = true;
                            } else {
                                ret["unordered-list"] = true;
                            }
                        } else if(data === "atom") {
                            ret.quote = true;
                        } else if(data === "em") {
                            ret.italic = true;
                        } else if(data === "quote") {
                            ret.quote = true;
                        } else if(data === "strikethrough") {
                            ret.strikethrough = true;
                        } else if(data === "comment") {
                            ret.code = true;
                        } else if(data === "link") {
                            ret.link = true;
                        } else if(data === "tag") {
                            ret.image = true;
                        } else if(data.match(/^header(\-[1-6])?$/)) {
                            ret[data.replace("header", "heading")] = true;
                        }
                    }
                    return ret;
                }
                function insertHtml(imgUrl){
                    var cm = simplemde.codemirror;
                    var state = getState(cm);
                    var options = simplemde.options;

                    if(/editor-preview-active/.test(cm.getWrapperElement().lastChild.className))
                        return;
                    var text;
                    var start = options.insertTexts.image[0];
                    var end = options.insertTexts.image[1];
                    var startPoint = cm.getCursor("start");
                    var endPoint = cm.getCursor("end");
                    if(imgUrl) {
                        end = end.replace("#url#", imgUrl);
                    }
                    if(state.image) {
                        text = cm.getLine(startPoint.line);
                        start = text.slice(0, startPoint.ch);
                        end = text.slice(startPoint.ch);
                        cm.replaceRange(start + end, {
                            line: startPoint.line,
                            ch: 0
                        });
                    } else {
                        text = cm.getSelection();
                        var img = start + text + end;
                        cm.replaceSelection(img);
                        startPoint.ch += img.length;
                        if(startPoint !== endPoint) {
                            endPoint.ch += img.length;
                        }
                    }
                    cm.setSelection(startPoint, endPoint);
                    cm.focus();
                }
                try {
                    var insertImage = document.getElementsByClassName("editor-toolbar")[0].getElementsByTagName("a")[9];
                    insertImage.onclick = function (ev) {
                        $.modal.material.open({multiSelect: true, selectable: 10}, function (selectedImageUrls) {
                            if(!selectedImageUrls) {
                                return false;
                            }
                            for(var i = 0; i < selectedImageUrls.length; i ++){
                                insertHtml(selectedImageUrls[i]);
                            }
                        })
                    }
                } catch (e) {
                    console.error(e);
                }
            }
        }
    },
    tinymce: {
        defaultConfig: {
            selector: "tinymceEditor",
            uploadUrl: "",
            uploadFileName: "file",
            textareaName: "content",
        },
        init: function (options) {
            var $op = $.extend(zhyd.tinymce.defaultConfig, options);
            if ($op.textareaName) {
                $('<textarea class="wangeditor-textarea" id="' + $op.textareaName + '" name="' + $op.textareaName + '" style="display: none" required="required"></textarea>').insertAfter($($op.selector));
            }
            var $contentBox = $('textarea[name=' + $op.textareaName + ']');
            tinymce.init({
                selector: $op.selector,
                toolbar_mode: 'floating',
                // width: 600,
                height: 500,
                plugins: [
                    'powerpaste advlist autolink link image lists charmap print preview hr anchor pagebreak',
                    'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
                    'table emoticons template paste help'
                ],
                menubar: 'file edit view insert format tools table',
                toolbar: 'undo redo | styleselect | code | bold italic | alignleft aligncenter alignright alignjustify | ' +
                    'bullist numlist outdent indent | link image | preview media fullscreen | ' +
                    'forecolor backcolor emoticons | help',
                content_langs: [
                    { title: 'English', code: 'en' },
                    { title: 'Chinese', code: 'zh' }
                ],
                // language: 'zh_CN',
                // directionality: 'rtl',
                custom_undo_redo_levels: 10,
                images_upload_url: $op.uploadUrl,
                images_upload_credentials: true,
                automatic_uploads: false,
                images_upload_handler: example_image_upload_handler,
                init_instance_callback: function(editor) {
                    editor.on('SetContent', function(e) {
                        $contentBox.val(zhyd.tinymce.getHtml())
                    });
                    editor.on('Change', function(e) {
                        $contentBox.val(zhyd.tinymce.getHtml())
                    });
                }
            });
            function example_image_upload_handler (blobInfo, success, failure, progress) {
                var xhr, formData;
                xhr = new XMLHttpRequest();
                xhr.withCredentials = false;
                xhr.open('POST', $op.uploadUrl);
                xhr.upload.onprogress = function (e) {
                    progress(e.loaded / e.total * 100);
                };
                xhr.onload = function() {
                    var json;
                    if (xhr.status === 403) {
                        failure('HTTP Error: ' + xhr.status, { remove: true });
                        return;
                    }
                    if (xhr.status < 200 || xhr.status >= 300) {
                        failure('HTTP Error: ' + xhr.status);
                        return;
                    }
                    json = JSON.parse(xhr.responseText);
                    if (!json || typeof json.data != 'string') {
                        failure('Invalid JSON: ' + xhr.responseText);
                        return;
                    }
                    success(json.data);
                };
                xhr.onerror = function () {
                    failure('Image upload failed due to a XHR Transport error. Code: ' + xhr.status);
                };
                formData = new FormData();
                formData.append($op.uploadFileName, blobInfo.blob(), blobInfo.filename());
                xhr.send(formData);
            }
        },
        getHtml: function () {
            // 只有一个编辑器
            return tinymce.activeEditor.getContent();
            // 多个编辑器
            // return tinymce.editors[0].getContent();
            // 不带HTML标记的纯文本内容
            // var activeEditor = tinymce.activeEditor;
            // var editBody = activeEditor.getBody();
            // activeEditor.selection.select(editBody);
            // var text = activeEditor.selection.getContent( {'format' : 'text' });
        },
        setHtml: function (html) {
            // 只有一个编辑器
            return tinymce.activeEditor.setContent(html);
            // 多个编辑器
            // return tinymce.editors[0].setContent(html);
        }
    },
    mask: {
        _box: '<div class="mask {{maskType}}"><div class="masker"><i class="{{icon}}"></i></div><h3 class="text">{{text}}</h3></div>',
        _icon: {
            load: "fa fa-spinner fa-spin",
            lock: "fa fa-lock"
        },
        _open: function (container, msg, type) {
            var html = Mustache.render(this._box, {icon: this._icon[type], text: msg, maskType: type});
            $(container).append(html);
        },
        closeAll: function (container) {
            $(container).find("div.mask.load, div.mask.lock").remove();
        },
        init: function () {
            console.log("init mask...");
            $(".loading").each(function () {
                var $this = $(this);
                if (!$this.hasClass("locking")) {
                    zhyd.mask.loading($this, $this.data("mask"));
                }
            });
            $(".locking").each(function () {
                var $this = $(this);
                zhyd.mask.locking($this, $this.data("mask"));
            });
        },
        loading: function (container, msg) {
            this._open(container, msg || "Loading", "load");
        },
        locking: function (container, msg) {
            this._open(container, msg || "Locking", "lock");
        },
        closeLoading: function (container) {
            $(container).find("div.mask.load").remove();
        },
        closeLocking: function (container) {
            $(container).find("div.mask.lock").remove();
        }
    }
};

$(document).ready(function () {
    zhyd.initTextSlider();

    $("img.lazy-img").lazyload({
        placeholder: appConfig.staticPath + "/img/loading.gif",
        effect: "fadeIn",
        threshold: 100
    });
    $(window).bind("load", function () {
        var timeout = setTimeout(function () {
            $("img.lazy-img").trigger("sporty");
        }, 3000);
    });

    /**
     * 图片预览
     * 必须指定预览图片的容器，格式：data-preview-container = "#containerID"
     */
    $(".uploadPreview").each(function () {
        var $this = $(this);
        $this.uploadPreview({imgContainer: $this.data("preview-container")});
    });

    $("#updPassBtn").click(function () {
        var $form = $("#updPassForm");
        if (validator.checkAll($form)) {
            $form.ajaxSubmit({
                type: "POST",
                url: '/passport/updatePwd',
                success: function (json) {
                    $.alert.ajaxSuccess(json);
                    if (json.status == 200) {
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    }

                },
                error: $.alert.ajaxError
            });
        }
    });
    zhyd.combox.init(zhyd.tagsInput.init);
    zhyd.mask.init();

    /**
     * 针对shiro框架中， ajax请求时session过期后的页面跳转
     */
    $.ajaxSetup({
        complete: function (XMLHttpRequest, textStatus) {
            if (textStatus === "parsererror") {
                $.alert.error("会话已失效！请重新登录！", function () {
                    window.location.reload();
                });
            } else if (textStatus === "error") {
                $.alert.error("请求超时,请稍后再试！");
            }
        }
    });

    var notice = [
        '<strong class="red">Hi Boy! 前台首页的 “轮播”只会显示“推荐文章”哦</strong>',
        '要想百度搜索引擎快速收录文章，可以试试“推送”功能哦',
        '批量推送文章到百度可以一次提交多篇文章哦',
        '碰到页面显示和数据库内容不一致的情况，可以先考虑清下redis缓存哦',
        '使用过程中如果有不能解决的问题，请去提issue哈，在群里消息太多，有时候会看不到消息记录',
        '可以通过右上角“系统配置”-“文章编辑器”选择默认的文章发布编辑器'
    ];
    var $noticeBox = $("#notice-box");
    var tpl = '{{#data}}<li class="scrolltext-title">'
        + '{{&.}}'
        + '</li>{{/data}}';
    var html = Mustache.render(tpl, {"data": $.tool.shuffle(notice)});
    $noticeBox.html(html);

    /**
     * 切换编辑器
     */
    $(".changeEditor").click(function () {
        var $this = $(this);
        $.alert.confirm("确定要切换编辑器吗？切换后本页内容将可能会丢失？", function () {
            window.location.href = $this.data("href");
        })
    })

    $('#helpModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var img = button.data('img');
        var title = button.data('title');
        var modal = $(this);
        modal.find('#helpModalTitle').text(title);
        var $img = modal.find('#helpModalImg');
        $img.attr("src", img).attr("alt", title);
        $img.parent().attr("href", img).attr("title", title);
    })
});
