# 代码贡献说明

1. 首先非常欢迎和感谢对本开源项目发起PR的朋友
2. 本项目主要以【[阿里编程规约](https://yq.aliyun.com/attachment/download/?spm=a2c4e.11153940.blogcont69327.7.49d74b62D18YJE&id=5585)】作为规范进行开发，因此各位小伙伴有必要提前安装一个阿里编程规约插件。（[Eclipse安装插件介绍](https://blog.csdn.net/qq_19260029/article/details/78259804?winzoom=1) & [Idea安装插件介绍](https://blog.csdn.net/lafengwnagzi/article/details/78246277)）
3. 代码提交前，请务必**格式化**一下代码，并且保证新增或者修改的方法都有完整的参数说明
4. 请尽量编写相关单元测试

#### 贡献代码的方式

- `fork`本开源项目**到自己的仓库**，如`your/DBlog`，然后`clone`到本地（注意：是`clone`你`fork`后的**你的仓库**里的项目），并配置用户信息

  ```shell
  $ git clone https://gitee.com/your/DBlog.git
  $ cd DBlog
  $ git config user.name "your name"
  $ git config user.email "your email"
  ```

- 本地修改代码后，推送到自己的仓库

  ```shell
  $ git commit -am "change something"
  $ git push origin master
  ```

- 最后在你仓库里`fork`的项目里提交`Pull Request `即可


详情参考：[http://git.mydoc.io/?t=153749](http://git.mydoc.io/?t=153749)