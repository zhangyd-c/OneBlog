# DBlog简介
DBlog是一款简洁美观、自适应的Java博客系统。使用springboot开发，前端使用Bootstrap。支持移动端自适应，配有完备的前台和后台管理功能。  
网站预览    [https://www.zhyd.me](https://www.zhyd.me)

## 写在前面的话
ps: 虽然我知道，大部分人都是来了**直接下载源代码**后就潇洒的离开，并且只有等到下次**突然想到**“我天~~我得去看看DBlog这烂项目更新新功能了吗”的时候才会重新来到这儿，即使你重新来过，我估计你也只有两个选择：    

发现更新代码了 --> 下载源码后重复上面的步骤    
发现没更新代码 --> 直接关闭浏览器

虽然我知道现实就是如此的残酷，但我还是要以我萤虫之力对各位到来的同仁发出一声诚挚的嘶吼：

**如果喜欢，请多多分享！！多多Star！！fork可以，但还是请star一下！！**


### 开发环境

| 工具    | 版本或描述                |
| ----- | -------------------- |
| OS    | Windows 7            |
| JDK   | 1.7+                 |
| IDE   | IntelliJ IDEA 2017.3 |
| Maven | 3.3.1                |
| MySQL | 5.6.4                |

### 模块划分

| 模块         | 释义                      |
| ---------- | ----------------------- |
| blog-core  | 核心业务类模块，提供基本的数据操作、工具处理等 |
| blog-admin | 后台管理模块                  |
| blog-web   | 前台模块                    |


### 技术栈

- Springboot 1.5.9
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


### 使用方法

1. 使用IDE导入本项目
2. 新建数据库`CREATE DATABASE dblog;`
3. 导入数据库`docs/db/dblog.sql`
4. 修改(`resources/application.yml`)配置文件
   1. 数据库链接属性(可搜索`datasource`或定位到L.19) 
   2. redis配置(可搜索`redis`或定位到L.69)
   3. mail配置(可搜索`mail`或定位到L.89)
   4. 七牛云配置(可搜索`qiniu`或定位到L.135)
5. 运行项目(三种方式)
   1. 项目根目录下执行`mvn -X clean package -Dmaven.test.skip=true`编译打包，然后执行`java -jar target/blog-web.jar`
   2. 项目根目录下执行`mvn springboot:run`
   3. 直接运行`BlogWebApplication.java`
6. 浏览器访问`http://127.0.0.1:8443`


**后台用户**

_超级管理员_： 账号：root  密码：123456  （本地测试使用这个账号，admin没设置权限）

_普通管理员_： 账号：admin  密码：123456

更多详情，请参考【[Wiki](https://gitee.com/yadong.zhang/DBlog/wikis)】


你能看到这儿已经很不容易了，剩下的自己先摸索摸索吧，实在不行，加QQ群[190886500](http://shang.qq.com/wpa/qunwpa?idkey=9f986e9b33b1de953e1ef9a96cdeec990affd0ac7855e00ff103514de2027b60)，进群可以选择性的备注：~~欧巴群主我爱你~~(咳咳，鉴于部分群友的抗议，该备注就不用了)，麻烦大家换成：`我猜群主一定很帅`

### 图片预览

**前台页面**
![PC-首页](http://cdn.zhyd.me/pc-index.png?v=1.0)
![PC-文章详情页](http://cdn.zhyd.me/pc-detail.png?v=1.0)
![手机](http://cdn.zhyd.me/m.png?v=1.0)
**后台页面**
![首页](http://cdn.zhyd.me/admin-index.png)
![菜单](http://cdn.zhyd.me/admin-menu.png)
![文章列表](http://cdn.zhyd.me/admin-articles.png)
![发表文章](http://cdn.zhyd.me/admin-article2.png)
![角色列表](http://cdn.zhyd.me/admin-role.png)
![角色分配](http://cdn.zhyd.me/admin-role2.png)


 ### 生命不息，折腾不止！ 更多信息，请关注：
 1. [我的博客](https://www.zhyd.me)
 2. [我的微博](http://weibo.com/211230415)
 3. [我的头条号](http://www.toutiao.com/c/user/3286958681/)
 4. [我的mooc](http://www.imooc.com/u/1175248/articles)

 ### 有任何问题可以
- [给我留言](https://www.zhyd.me/guestbook)


### 开源协议

 [MIT](https://gitee.com/yadong.zhang/DBlog/blob/master/LICENSE)



