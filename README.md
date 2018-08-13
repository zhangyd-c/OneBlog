 **DBlog** 是一款简洁美观、自适应的Java博客系统。使用springboot开发，前端使用Bootstrap。支持移动端自适应，配有完备的前台和后台管理功能。    

![JDK](https://img.shields.io/badge/JDK-1.8-green.svg)
![Maven](https://img.shields.io/badge/Maven-3.3.1-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-5.6.4-green.svg)
![Redis](https://img.shields.io/badge/Redis-3.0.503-green.svg)
[![license](https://img.shields.io/badge/license-MIT-yellow.svg)](https://gitee.com/yadong.zhang/DBlog/blob/master/LICENSE)

----

# 重要声明

1. 本项目配有相关[帮助文档](https://gitee.com/yadong.zhang/DBlog/wikis)。文档中包括**基本的项目说明**、**shiro标签使用**、**七牛云配置**和一些常见的**异常问题解决方案**。使用时碰到问题请**优先**查阅【[帮助文档](https://gitee.com/yadong.zhang/DBlog/wikis)】。因为现在好多朋友问的问题大部分都写在【[帮助文档](https://gitee.com/yadong.zhang/DBlog/wikis)】中了，希望各位朋友能自己翻阅下相关资料，高效提问，避免重复问题！
2. **提问题**前请优先阅读【[如何向开源社区提问题](https://github.com/seajs/seajs/issues/545)】&【[提问的智慧](http://www.dianbo.org/9238/stone/tiwendezhihui.htm)】
3. **提问题**时请优先选择[Gitee Issues](https://gitee.com/yadong.zhang/DBlog/issues)（方便问题追踪和一对一解决），其次[我的博客-留言板](https://www.zhyd.me/guestbook)，再次[QQ群190886500](http://shang.qq.com/wpa/qunwpa?idkey=9f986e9b33b1de953e1ef9a96cdeec990affd0ac7855e00ff103514de2027b60)（QQ群消息较多，提问请注意节奏、时机），最次加我QQ好友直接提问（不推荐）
4. 本项目唯一官网：[https://www.zhyd.me](https://www.zhyd.me)  
5. 本项目开源地址：[Gitee](https://gitee.com/yadong.zhang/DBlog)    注： **Github上的项目已不准备更新** ，因此版本较老，请Github用户移步至[Gitee](https://gitee.com/yadong.zhang/DBlog) 
6. 本项目修改记录，详情请移步[这里](https://gitee.com/yadong.zhang/DBlog/blob/master/update.md)
7. 如果你想贡献代码，请先阅读[这篇文章](https://gitee.com/yadong.zhang/DBlog/blob/master/contribution.md)

----

# 写在前面的话
ps: 虽然我知道，大部分人都是来了**直接下载源代码**后就潇洒的离开，并且只有等到下次**突然想到**“我天~~我得去看看DBlog这烂项目更新新功能了吗”的时候才会重新来到这儿，即使你重新来过，我估计你也只有两个选择：    

发现更新代码了 --> 下载源码后重复上面的步骤    
发现没更新代码 --> 直接关闭浏览器

虽然我知道现实就是如此的残酷，但我还是要以我萤虫之力对各位到来的同仁发出一声诚挚的嘶吼：

**如果喜欢，请多多分享！！多多Star！！**

----

### 已经在用DBlog的网站 （ 排名按照留言先后顺序 ）
- [张亚东博客](https://www.zhyd.me)
- [攻城狮不是猫博客](http://www.jsdblog.com)
- [刘辉辉的博客](http://www.axxo.top)
- [乐赚淘](http://www.lezhuantao.com)
- [时光号](http://www.shiguanghao.cn/)
- [大杂烩 | 好好网](https://www.haohaowang.top)
- 更多待续...

烦请各位使用DBlog已经跑起来自己的博客的朋友，能留下你的网址（没别的意思，只是看看有多少人而已） - [点这儿](https://gitee.com/yadong.zhang/DBlog/issues/ILIAQ)



----

### 模块划分

| 模块         | 释义                      |
| ---------- | ----------------------- |
| blog-core  | 核心业务类模块，提供基本的数据操作、工具处理等 |
| blog-admin | 后台管理模块                  |
| blog-web   | 前台模块                    |


### 技术栈

- Springboot 2.0.1
- Apache Shiro 1.2.2
- Logback
- Redis
- Lombok
- Websocket
- MySQL、Mybatis、Mapper、Pagehelper
- Freemarker
- Bootstrap 3.3.0
- wangEditor
- jQuery 1.11.1、jQuery Lazyload 1.9.7、fancybox、iCheck
- 阿里云OSS
- kaptcha
- Qiniu
- ...

### 功能简介

- 支持wangEditor和Markdown两种富文本编辑器，可以自行选择
- 在线申请友情链接，无需站长手动配置，只需申请方添加完站长的连接后自行申请即可
- 支持将文件提交到百度站长收录平台，加快百度引擎的收录
- 自研评论系统
- 后台配备完善的权限管理
- 自带robots、sitemap等seo模板，实现自动生成robots和sitemap
- 集成七牛云，实现文件云存储
- 系统配置支持快速配置。可通过后台手动修改诸如域名信息、SEO优化、赞赏码、七牛云以及更新维护通知等。
- 管理员可向在线的用户发送实时消息（需用户授权 - 基于websocket实现，具体参考[DBlog建站之Websocket的使用](https://www.zhyd.me/article/111)）


### 使用方法

1. 使用IDE导入本项目
2. 新建数据库`CREATE DATABASE dblog;`
3. 导入数据库`docs/db/dblog.sql`
4. 修改配置文件
   1. 数据库链接属性(在`resources/application-{env}.yml`配置文件中搜索`datasource`或定位到L.7) 
   2. redis配置(在`resources/application.yml`配置文件中搜索`redis`或定位到L.65)
   3. mail配置(在`resources/application-{env}.yml`配置文件中搜索`mail`或定位到L.14)
   4. 【[七牛云](https://portal.qiniu.com/signup?code=3l8yx2v0f21ci)】配置(见sys_config表中qiniu_*开头的字段)    
      注：因为系统存在redis缓存，如果是第一次使用，可以直接修改sys_config表内容，如果不是第一次用，建议使用admin项目中的`系统配置`页面修改相关配置内容
5. 运行项目(三种方式)
   1. 项目根目录下执行`mvn -X clean package -Dmaven.test.skip=true -Ptest`编译打包（注：-Ptest中的test为环境标识），然后cd到blog-web目录下执行`java -jar target/blog-web.jar`
   2. 项目根目录下执行`mvn springboot:run`
   3. 直接运行`BlogWebApplication.java`
6. 浏览器访问`http://127.0.0.1:8443`


**后台默认账户**

_超级管理员_（超级管理员）： 账号：root  密码：123456  

_普通管理员_（普通管理员，无权限）： 账号：admin  密码：123456

_评论审核管理员_（只有评论审核的权限）： 账号：comment-admin  密码：123456

注：后台用户的创建，尽可能做到**权限最小化**


你能看到这儿已经很不容易了，剩下的自己先摸索摸索吧，实在不行，加QQ群[190886500](http://shang.qq.com/wpa/qunwpa?idkey=9f986e9b33b1de953e1ef9a96cdeec990affd0ac7855e00ff103514de2027b60)，进群可以选择性的备注：~~欧巴群主我爱你~~(咳咳，鉴于部分群友的抗议，该备注就不用了)，麻烦大家换成：`我猜群主一定很帅`


### 后续扩展
- [x] 1. 结合websocket+notification实现管理员向在线用户实时发送消息通知（隐藏掉浏览器的情况下一样会弹出，类似windowx桌面弹窗，需要用户授权）
- [ ] 2. 页面缓存
- [ ] 3. 数据统计
- [ ] 4. cc防护
...

### 图片预览

#### 前台页面

![PC-首页](https://gitee.com/uploads/images/2018/0627/161851_3eefd129_784199.png?v=1.0 "PC-首页")
![手机端](https://gitee.com/uploads/images/2018/0627/163121_6b6c551e_784199.png "手机端")

#### 后台页面

![PC-后台首页](https://gitee.com/uploads/images/2018/0627/162136_01f40c01_784199.png?v=1.0 "PC-后台首页")
![PC-文章列表页](https://gitee.com/uploads/images/2018/0627/162222_001e3342_784199.png?v=1.0 "PC-文章列表页")
![PC-发布文章页-markdown版](https://gitee.com/uploads/images/2018/0627/162317_5ea6c8d5_784199.png "PC-发布文章页-markdown版")
![PC-文章发布页-html版](https://gitee.com/uploads/images/2018/0627/162442_c98194c4_784199.png "PC-文章发布页-html版.png")

### 生命不息，折腾不止！ 更多信息，请关注：
 1. [我的博客](https://www.zhyd.me)
 2. [我的微博](http://weibo.com/211230415)
 3. [我的头条号](http://www.toutiao.com/c/user/3286958681/)
 4. [我的imooc](http://www.imooc.com/u/1175248/articles)

### 特别感谢

1. 广大的开源爱好者
2. 无私的网友

### 开源协议

[![license](https://img.shields.io/badge/license-MIT-yellow.svg)](https://gitee.com/yadong.zhang/DBlog/blob/master/LICENSE)