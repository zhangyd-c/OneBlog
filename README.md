<p align="center">
	<img src="/docs/_media/logo.png" style="width: 300px"></img>
</p>
<p align="center">
	<strong>OneBlog 一个简洁美观、功能强大并且自适应的Java博客。使用springboot开发，前端使用Bootstrap。支持移动端自适应，配有完备的前台和后台管理功能。</strong>
</p>
<p align="center">
	<img src="https://img.shields.io/badge/Maven-3.3.1-green.svg" ></img>
	<a target="_blank" href="https://gitee.com/yadong.zhang/DBlog/blob/master/LICENSE">
		<img src="https://img.shields.io/badge/license-GPL%20v3-yellow.svg" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-blue.svg" ></img>
	</a>
	<img src="https://img.shields.io/badge/MySQL-5.6.4-red.svg" ></img>
	<img src="https://img.shields.io/badge/Redis-3.0.503-orange.svg" ></img>
</p>
<p align="center">
	<strong>开源地址：</strong> <a target="_blank" href='https://gitee.com/yadong.zhang/DBlog'>Gitee</a> | <a target="_blank" href='https://github.com/zhangyd-c/OneBlog'>Github</a>
</p>
<p align="center">
    <strong>QQ群：</strong>230017570
</p>

----

# 重要声明

**看文档！看文档！看文档！**，初次使用， 请先**看文档！**

1. 本项目配有相关[帮助文档](https://docs.zhyd.me) 。文档中包括**基本的项目说明**、**shiro标签使用**、**七牛云配置**和一些常见的**异常问题解决方案**。使用时碰到问题请**优先**查阅【[帮助文档](https://docs.zhyd.me)】。因为现在好多朋友问的问题大部分都写在【[帮助文档](https://docs.zhyd.me)】中了，希望各位朋友能自己翻阅下相关资料，高效提问，避免重复问题！
2. **提问题**前请优先阅读【[如何向开源社区提问题](https://github.com/seajs/seajs/issues/545)】&【[提问的智慧](http://www.dianbo.org/9238/stone/tiwendezhihui.htm)】
3. **提问题**时请优先选择[Gitee Issues](https://gitee.com/yadong.zhang/DBlog/issues)（方便问题追踪和一对一解决），其次[我的博客-留言板](https://docs.zhyd.me/guestbook)，再次QQ群（QQ群消息较多，提问请注意节奏、时机），最次加我QQ好友直接提问（不推荐）
4. 本项目唯一官网：[https://docs.zhyd.me](https://docs.zhyd.me)  
5. 本项目开源地址：[Gitee](https://gitee.com/yadong.zhang/DBlog)    | [Github](https://github.com/zhangyd-c/OneBlog)
6. 本项目修改记录，详情请移步[这里](https://gitee.com/yadong.zhang/DBlog/blob/master/update.md)
7. 如果你想贡献代码，请先阅读[这篇文章](https://gitee.com/yadong.zhang/DBlog/blob/master/contribution.md)

----

# 写在前面的话
ps: 虽然我知道，大部分人都是来了**直接下载源代码**后就潇洒的离开，并且只有等到下次**突然想到**“我天~~我得去看看OneBlog这烂项目更新新功能了吗”的时候才会重新来到这儿，即使你重新来过，我估计你也只有两个选择：    

发现更新代码了 --> 下载源码后重复上面的步骤    
发现没更新代码 --> 直接关闭浏览器

虽然我知道现实就是如此的残酷，但我还是要以我萤虫之力对各位到来的同仁发出一声诚挚的嘶吼：

**如果喜欢，请多多分享！！多多Star！！**

----


# 功能简介

- **Docker一键部署**：支持 Docker 的方式一键启动服务
- **广告位管理**：支持五种广告位：首页开屏广告、侧边栏顶部、侧边栏底部、文章详情底部、评论框顶部，站长可以随时随意更换自己的广告链接，赚外快不成问题！
- **多种编辑器**：支持 wangEditor、Markdown 和 TinyMCE 等多种文章编辑器，可以自行选择
- **自动申请友情链接**：在线申请友情链接，无需站长手动配置，只需申请方添加完站长的连接后自行申请即可
- **百度推送**：支持百度推送功能，加速百度搜索引擎收录博文
- **评论系统**：自研的评论系统，支持显示用户地址、浏览器和 os 信息，后台可审核评论、开启匿名评论、回复和邮件通知评论
- **权限管理**：后台配备完善的 RBAC 权限管理，前台文章支持密码访问、登录访问等多种权限验证策略
- **完善的 SEO 方案**：自带robots、sitemap 等 seo 模板，实现自动生成 robots 和 sitemap
- **实时通讯**：管理员可向在线的用户实时发送消息
- **系统配置支持快速配置**：可通过后台手动修改诸如域名信息、SEO 优化、赞赏码、七牛云以及更新维护通知等
- **多种文件存储**：集成七牛云、阿里云OSS，实现文件云存储，同时支持本地文件存储
- **文章搬运工**：集成[blog-hunter](https://gitee.com/yadong.zhang/blog-hunter) 实现“文章搬运工”功能，支持一键同步imooc、csdn、iteye或者cnblogs上的文章，可抓取列表和单个文章
- **第三方授权登录**：集成 [JustAuthPlus（JAP）](https://gitee.com/fujieid/jap) 实现第三方授权登录
- **自定义网站内容**：管理员可自定义“关于本站”、“留言板”、“友情链接”、“免责声明”、“Footer”、“鼠标点击时的气泡文字”、“热门搜索的待选项”等内容
- **自定义页面**：管理员可添加自定义的页面
- **流控**：针对异常IP的连续大量访问，系统会自动封禁该IP。

----

# 模块划分

| 模块  | 释义 | 备注 |
| :------------: | :------------: | :------------: |
| blog-core | 核心业务类模块，提供基本的数据操作、工具处理等 | 该模块只是作为核心依赖包存在 |
| blog-codegen | 代码生成器 |
| blog-admin | 后台管理模块 | 该模块作为单独项目打包部署 |
| blog-web | 前台模块 | 该模块作为单独项目打包部署 |
| blog-file | 文件存储功能模块 | 支持local、七牛云和阿里云OSS |


# 技术栈

- docker
- docker-compose
- Springboot 2.3.4.RELEASE
- Apache Shiro 1.7.1
- Logback
- Redis
- Lombok
- Websocket
- MySQL、Mybatis、Mapper、Pagehelper
- Freemarker
- Bootstrap 3.3.0
- wangEditor
- Markdown
- jQuery 1.11.1、jQuery Lazyload 1.9.7、fancybox、iCheck
- 阿里云OSS
- 七牛云
- Nginx
- kaptcha
- webMagic
- ...


# 快速开始

## Docker Compose（推荐）

Compose 是用于定义和运行多容器 Docker 应用程序的工具。通过 Compose，您可以使用 YML 文件来配置应用程序需要的所有服务。然后，使用一个命令，就可以从 YML 文件配置中创建并启动所有服务。
使用之前需要先安装docker环境，建议版本为17.06.0-ce以上版本
1. 下载源码，安装maven环境，
打包项目 `mvn clean package -Dmaven.test.skip=true -Pdev`，放到服务器
2. 进入 `docs/docker` 目录
3. 按照注释修改 `.env` 文件
4. 执行 `docker-compose -p oneblog up -d`

## 源码方式

> `blog-web` 和 `blog-admin` 的运行方式一样

1. 使用IDE导入本项目
2. 新建数据库`CREATE DATABASE dblog;`
3. 导入数据库`docs/docker/mysql/dblog.sql`
4. 初始化数据库`docs/docker/mysql/init_data.sql`
5. 修改配置文件，mysql、redis、mail配置在`[blog-core]/resources/config/application-center.yml`配置文件中
6. 运行项目：直接运行 `blog-web/src/main/java/com/zyd/blog/BlogWebApplication.java` 或者 `blog-admin/src/main/java/com/zyd/blog/BlogAdminApplication.java`
7. 浏览器访问`http://127.0.0.1:{port}`

> 后台默认账号密码：root/123456

# Who is using?

烦请各位使用 OneBlog 的朋友，能留下你的网址 - [点这儿](https://gitee.com/yadong.zhang/DBlog/issues/ILIAQ)

----

# 预览

![web端首页-pc](docs/_media/1601f937.png)
![web端文章详情页](docs/_media/e922c529.png)
![web端文章详情页 - 私密文章](docs/_media/ff9a84a3.png)
![web端文章详情页 - 私密文章](docs/_media/cd37ba37.png)
![web端首页-mobile](https://images.gitee.com/uploads/images/2019/0129/191428_c76317e8_784199.png "web端首页-mobile")

![admin端首页](https://images.gitee.com/uploads/images/2019/0129/191117_221c6064_784199.png "admin端首页")
![admin端文章列表也](https://images.gitee.com/uploads/images/2019/0129/191135_21e4f61c_784199.png "admin端文章列表也")
![admin端发布文章页](https://images.gitee.com/uploads/images/2019/0129/191150_0d28d51a_784199.png "admin端发布文章页")
![admin端系统配置页](https://images.gitee.com/uploads/images/2019/0129/191203_cc6941e4_784199.png "admin端系统配置页")
![admin端文章搬运工](https://images.gitee.com/uploads/images/2019/0129/191214_5e8f3c34_784199.png "admin端文章搬运工")
![admin端文章搬运工](https://images.gitee.com/uploads/images/2019/0129/191237_d015fcda_784199.png "admin端文章搬运工")

----

# 交流

|  微信(备注:加群)  |  公众号  |
| :------------: | :------------: |
| <img src="https://gitee.com/yadong.zhang/static/raw/master/wx/wx.png" width="170"/> | <img src="https://gitee.com/yadong.zhang/static/raw/master/wx/wechat_account.jpg" width="200" /> |

 **QQ群** 

1. 1群[![](https://images.gitee.com/uploads/images/2019/0129/191256_a40bceba_784199.png)](https://shang.qq.com/wpa/qunwpa?idkey=3571c554a143eff1e15807de033a240196c6b493b25b903d1d37571cfb6040aa)
2. 2群[![](https://images.gitee.com/uploads/images/2019/0129/191256_a40bceba_784199.png)](https://shang.qq.com/wpa/qunwpa?idkey=38594b12fb0ec66459f0dba0316648a5c4bb71ea613289e5b57b8f44ad83ebc9)

# 赞助

> 为了让OneBlog更好用，作者码代码码的头发都没了！请作者买块生姜擦擦头皮，据说这样有助于生发~~

| 支付宝  | 微信  | 支付宝红包  |
| :------------: | :------------: | :------------: |
| <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/zfb_code.png" width="200"/> | <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/wx_code.png" width="200" /> | <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/zfb-code.png" width="200" /> |


# 生命不息，折腾不止！ 更多信息，请关注：
 1. [我的博客](https://docs.zhyd.me)
 2. [我的微博](http://weibo.com/211230415)
 3. [我的头条号](http://www.toutiao.com/c/user/3286958681/)
 4. [我的imooc](http://www.imooc.com/u/1175248/articles)
 
# 特别感谢

- 广大的开源爱好者
- 无私的网友
- [gentelella](https://github.com/puikinsh/gentelella): 一款开源的Bootstrap3后台管理模板
- [七牛云](https://portal.qiniu.com/signup?code=3l8yx2v0f21ci): 强大的对象存储、CDN等服务提供商
- [emoji表情列表](https://github.com/caiyongji/emoji-list#nature): emoji表情列表
- [blog-hunter](https://github.com/zhangyd-c/blog-hunter): 博客猎手，基于webMagic的博客爬取工具，支持慕课、csdn、iteye、cnblogs、掘金和V2EX等各大主流博客平台。
- [JustAuth](https://gitee.com/yadong.zhang/JustAuth): 史上最全的整合第三方登录的工具,目前已支持Github、Gitee、微博、钉钉、百度、Coding、腾讯云开发者平台、OSChina、支付宝、QQ、微信、淘宝、Google、Facebook、抖音、领英、小米、微软和今日头条等第三方平台的授权登录。 Login, so easy!
- [JAP](https://gitee.com/fujieid/jap): 一款开源的登录认证中间件，基于模块化设计，并且与业务高度解耦，使用起来非常灵活，开发者可以毫不费力地将 JAP 集成到任何 web 应用程序中，就像集成 JustAuth 一样，简单方便。
- 待续...

# 开源协议

[![license](https://img.shields.io/badge/license-GPL%20v3-yellow.svg)](https://gitee.com/yadong.zhang/DBlog/blob/master/LICENSE)
