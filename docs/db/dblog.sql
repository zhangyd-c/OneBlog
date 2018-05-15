/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : dblog

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-04-23 10:38:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_article
-- ----------------------------
DROP TABLE IF EXISTS `biz_article`;
CREATE TABLE `biz_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '文章封面图片',
  `qrcode_path` varchar(255) DEFAULT NULL COMMENT '文章专属二维码地址',
  `content` longtext COMMENT '文章内容',
  `top` tinyint(1) DEFAULT '0' COMMENT '是否置顶',
  `type_id` bigint(20) unsigned NOT NULL COMMENT '类型',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态',
  `recommended` tinyint(1) unsigned DEFAULT '0' COMMENT '是否推荐',
  `original` tinyint(1) unsigned DEFAULT '1' COMMENT '是否原创',
  `description` varchar(300) DEFAULT NULL COMMENT '文章简介，最多200字',
  `keywords` varchar(200) DEFAULT NULL COMMENT '文章关键字，优化搜索',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of biz_article
-- ----------------------------
INSERT INTO `biz_article` VALUES ('1', 'chromedriver与chrome版本映射表(更新至v2.25)', '1', 'flyat/article/20180331122259.png', null, '<p>转载自 ：<a href=\"http://blog.csdn.net/huilan_same/article/details/51896672\" target=\"_blank\">selenium之 chromedriver与chrome版本映射表(更新至v2.25)</a>&nbsp; 。感谢原作者！</p><p>看到网上基本没有最新的chromedriver与chrome的对应关系表，便兴起整理了一份如下，希望对大家有用：</p><table><thead><tr><th>chromedriver版本</th><th>支持的Chrome版本</th></tr></thead><tbody><tr><td>v2.25</td><td>v53-55</td></tr><tr><td>v2.24</td><td>v52-54</td></tr><tr><td>v2.23</td><td>v51-53</td></tr><tr><td>v2.22</td><td>v49-52</td></tr><tr><td>v2.21</td><td>v46-50</td></tr><tr><td>v2.20</td><td>v43-48</td></tr><tr><td>v2.19</td><td>v43-47</td></tr><tr><td>v2.18</td><td>v43-46</td></tr><tr><td>v2.17</td><td>v42-43</td></tr><tr><td>v2.13</td><td>v42-45</td></tr><tr><td>v2.15</td><td>v40-43</td></tr><tr><td>v2.14</td><td>v39-42</td></tr><tr><td>v2.13</td><td>v38-41</td></tr><tr><td>v2.12</td><td>v36-40</td></tr><tr><td>v2.11</td><td>v36-40</td></tr><tr><td>v2.10</td><td>v33-36</td></tr><tr><td>v2.9</td><td>v31-34</td></tr><tr><td>v2.8</td><td>v30-33</td></tr><tr><td>v2.7</td><td>v30-33</td></tr><tr><td>v2.6</td><td>v29-32</td></tr><tr><td>v2.5</td><td>v29-32</td></tr><tr><td>v2.4</td><td>v29-32</td></tr></tbody></table>\n                                <p><strong>附：</strong>&nbsp;<br>所有chromedriver均可在下面链接中下载到：<a href=\"http://chromedriver.storage.googleapis.com/index.html\" target=\"_blank\" style=\"background-color: rgb(255, 255, 255);\">http://chromedriver.storage.googleapis.com/index.html</a></p><p><br></p>', '0', '3', '1', '1', null, 'java.lang.ClassNotFoundException: net.sf.ezmorph.Morpher \r\nCould not initialize class  net.sf.json.util.JSONUtils ', 'chromedriver', '2016-11-09 17:28:36', '2016-11-09 17:28:36');
INSERT INTO `biz_article` VALUES ('2', 'linux系统下解压war包', '1', 'flyat/article/20180331122637.png', null, '<p>网上很多人说用jar包解压，但jar命令解压时不能指定目录，推荐使用unzip解压war包。</p>\n                                <p>Linux系统中先检查是否可用unzip命令</p><pre style=\"max-width: 100%;\"><code class=\"java hljs\" codemark=\"1\">[root<span class=\"hljs-meta\">@VM</span>_83_104_centos ~]# unzip --help\n-bash: unzip: command not found</code></pre><p>需要先自行下载：<span style=\"font-size: inherit; background-color: rgb(248, 248, 248); color: rgb(51, 51, 51); font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; white-space: pre-wrap;\">yum install zip unzip</span></p><pre style=\"max-width: 100%;\"><code class=\"java hljs\" codemark=\"1\">[root<span class=\"hljs-meta\">@VM</span>_83_104_centos ~]# yum install zip unzip\nLoaded plugins: fastestmirror, langpacks\nepel                                                                                                                                                                    | <span class=\"hljs-number\">4.3</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\nextras                                                                                                                                                                  | <span class=\"hljs-number\">3.4</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\nmysql-connectors-community                                                                                                                                              | <span class=\"hljs-number\">2.5</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\nmysql-tools-community                                                                                                                                                   | <span class=\"hljs-number\">2.5</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\nmysql56-community                                                                                                                                                       | <span class=\"hljs-number\">2.5</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\nos                                                                                                                                                                      | <span class=\"hljs-number\">3.6</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\nupdates                                                                                                                                                                 | <span class=\"hljs-number\">3.4</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\n(<span class=\"hljs-number\">1</span>/<span class=\"hljs-number\">3</span>): epel/<span class=\"hljs-number\">7</span>/x86_64/group_gz                                                                                                                                           | <span class=\"hljs-number\">170</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\n(<span class=\"hljs-number\">2</span>/<span class=\"hljs-number\">3</span>): epel/<span class=\"hljs-number\">7</span>/x86_64/updateinfo                                                                                                                                         | <span class=\"hljs-number\">673</span> kB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\n(<span class=\"hljs-number\">3</span>/<span class=\"hljs-number\">3</span>): epel/<span class=\"hljs-number\">7</span>/x86_64/primary_db                                                                                                                                         | <span class=\"hljs-number\">4.3</span> MB  <span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>:<span class=\"hljs-number\">00</span>\nLoading mirror speeds from cached hostfile\nPackage zip-<span class=\"hljs-number\">3.0</span>-<span class=\"hljs-number\">10</span>.el7.x86_64 already installed and latest version\nPackage unzip-<span class=\"hljs-number\">6.0</span>-<span class=\"hljs-number\">15</span>.el7.x86_64 already installed and latest version\nNothing to do</code></pre><p>查看unzip命令的帮助</p><pre style=\"max-width: 100%;\"><code class=\"java hljs\" codemark=\"1\">[root<span class=\"hljs-meta\">@VM</span>_83_104_centos ~]# unzip --help\nUnZip <span class=\"hljs-number\">6.00</span> of <span class=\"hljs-number\">20</span> April <span class=\"hljs-number\">2009</span>, by Info-ZIP.  Maintained by C. Spieler.  Send\nbug reports using http:<span class=\"hljs-comment\">//www.info-zip.org/zip-bug.html; see README for details.</span>\n\nUsage: unzip [-Z] [-opts[modifiers]] file[.zip] [list] [-x xlist] [-d exdir]\n  Default action is to extract files in list, except those in xlist, to exdir;\n  file[.zip] may be a wildcard.  -Z =&gt; <span class=\"hljs-function\">ZipInfo <span class=\"hljs-title\">mode</span> <span class=\"hljs-params\">(<span class=\"hljs-string\">\"unzip -Z\"</span> <span class=\"hljs-keyword\">for</span> usage)</span>.\n\n  -p  extract files to pipe, no messages     -l  list <span class=\"hljs-title\">files</span> <span class=\"hljs-params\">(<span class=\"hljs-keyword\">short</span> format)</span>\n  -f  freshen existing files, create none    -t  test compressed archive data\n  -u  update files, create <span class=\"hljs-keyword\">if</span> necessary      -z  display archive comment only\n  -v  list verbosely/show version info       -T  timestamp archive to latest\n  -x  exclude files that <span class=\"hljs-title\">follow</span> <span class=\"hljs-params\">(in xlist)</span>   -d  extract files into exdir\nmodifiers:\n  -n  never overwrite existing files         -q  quiet <span class=\"hljs-title\">mode</span> <span class=\"hljs-params\">(-qq =&gt; quieter)</span>\n  -o  overwrite files WITHOUT prompting      -a  auto-convert any text files\n  -j  junk <span class=\"hljs-title\">paths</span> <span class=\"hljs-params\">(do not make directories)</span>   -aa treat ALL files as text\n  -U  use escapes <span class=\"hljs-keyword\">for</span> all non-ASCII Unicode  -UU ignore any Unicode fields\n  -C  match filenames <span class=\"hljs-keyword\">case</span>-insensitively     -L  <span class=\"hljs-title\">make</span> <span class=\"hljs-params\">(some)</span> names lowercase\n  -X  restore UID/GID info                   -V  retain VMS version numbers\n  -K  keep setuid/setgid/tacky permissions   -M  pipe through \"more\" pager\nSee \"unzip -hh\" or unzip.txt <span class=\"hljs-keyword\">for</span> more help.  Examples:\n  unzip data1 -x joe   </span>=&gt; extract all files except joe from zipfile data1.zip\n  unzip -p foo | more  =&gt; send contents of foo.zip via pipe into program more\n  unzip -fo foo ReadMe =&gt; quietly replace existing ReadMe <span class=\"hljs-keyword\">if</span> archive file newer\n[root<span class=\"hljs-meta\">@VM</span>_83_104_centos ~]#</code></pre><p>需要用到的指令：-o: -q:</p><blockquote><p>-o overwrite files WITHOUT prompting：不必先询问用户，unzip执&nbsp;行后覆盖原有文件。</p><p>-q quiet mode (-qq =&gt; quieter)：执行时不显示任何信息（静默）。</p><p>-d&nbsp;&nbsp;extract&nbsp;files&nbsp;into&nbsp;exdir：把压缩文件解压到指定目录下</p></blockquote><p>解压war文件到指定目录：将test.war文件解压到test目录下=<span style=\"font-size: inherit; background-color: rgb(248, 248, 248); color: rgb(51, 51, 51); font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; white-space: pre-wrap;\">unzip -oq test.war -d test</span></p><p>最后附上一个通过jar命令操作war包的方式：</p><p>压缩war包，把当前目录下的所有文件打包到xxx.war：<span style=\"font-size: inherit; background-color: rgb(248, 248, 248); color: rgb(51, 51, 51); font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; white-space: pre-wrap;\">jar -cvfM0 xxx.war ./</span></p><pre style=\"max-width: 100%;\"><code class=\"java hljs\" codemark=\"1\">-c   创建war包\n-v   显示过程信息\n-f    \n-M\n-<span class=\"hljs-number\">0</span>   这个是阿拉伯数字，只打包不压缩的意思</code></pre><p>解压war包，解压xxx.war到当前目录：<span style=\"background-color: rgb(248, 248, 248); color: rgb(51, 51, 51); font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: inherit; white-space: pre-wrap;\">jar -xvf game.war</span></p>', '0', '3', '1', '1', '1', '1.通过$.post(url,param,callback,dataType);发送Ajax请求时，如果不指定dataType，\r\n\r\n前台页面中要使用eval将返回数据转换成json格式eval(\"(\" + data + \")\");//以json格式获取数据\r\n\r\n但是在火狐浏览器中会返回的data为[object XMLDocument]', '解压war包，linux，uzip命令', '2016-11-09 22:24:45', '2016-11-09 22:24:45');
INSERT INTO `biz_article` VALUES ('3', '关于ajax请求 的处理方式', '1', 'flyat/article/20180331145644.png', null, '<p>1.通过<span style=\"background-color: rgb(248, 248, 248); color: rgb(51, 51, 51); font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: inherit; white-space: pre-wrap;\">$.post(url,param,callback,dataType);</span>发送Ajax请求时，如果不指定dataType，</p><p>前台页面中要使用eval将返回数据转换成json格式<span style=\"background-color: rgb(248, 248, 248); color: rgb(51, 51, 51); font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: inherit; white-space: pre-wrap;\">eval(\"(\" + data + \")\");//以json格式获取数据</span></p><p>但是在火狐浏览器中会返回的data为<b>[object&nbsp;XMLDocument]</b>，\r\n</p><p><b>处理办法：</b>在服务器端的getWriter.print()；前面加上一行代码</p><pre class=\"pre-scrollable\" style=\"max-width: 100%;\"><code class=\"java hljs\" codemark=\"1\">ServletActionContext.getResponse().setContentType(<span class=\"hljs-string\">\"text/html;charset=UTF-8\"</span>);</code></pre><p>指定其相应格式，\r\n</p><p>但是如果在每个服务器端的Ajax请求方法中都手动指定相应格式，不说代码冗余，影响系统系统，单纯从规范来说，也是不可取的，\r\n</p><p>那么，就有了第二种方法：\r\n</p><p>2.指定jsp页面请求Ajax时指定其数据类型：dataType为json\r\n</p><p>&nbsp;&nbsp;&nbsp;&nbsp;例：</p><pre class=\"pre-scrollable\" style=\"max-width: 100%;\"><code class=\"javascript hljs\" codemark=\"1\">$.post(url,param,<span class=\"hljs-function\"><span class=\"hljs-keyword\">function</span>(<span class=\"hljs-params\">data</span>)</span>{ },<span class=\"hljs-string\">\"json\"</span>);</code></pre><p>&nbsp;&nbsp;&nbsp;&nbsp;这样处理后，服务器端就不需要再指定其<span style=\"background-color: rgb(248, 248, 248); color: rgb(51, 51, 51); font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: inherit; white-space: pre-wrap;\">ServletActionContext.getResponse()</br>.setContentType(\"text/html;charset=UTF-8\");</span></p><p>3.也可以通过&nbsp;&nbsp;&nbsp;&nbsp;Ajax的“完整版”指定数据类型\r\n</p><pre class=\"pre-scrollable\" style=\"max-width: 100%;\"><code class=\"javascript hljs\" codemark=\"1\">$.ajax({\r\n    url: <span class=\"hljs-string\">\"\"</span> \r\n    dataType:<span class=\"hljs-string\">\"\"</span>\r\n    callback:\r\n});</code></pre>\r\n                                <p><br></p>', '0', '1', '1', '1', '1', '网上很多人说用jar包解压，但jar命令解压时不能指定目录，推荐使用unzip解压war包。\r\n\r\nLinux系统中先检查是否可用unzip命令', 'ajax请求', '2016-11-19 13:47:52', '2016-11-19 13:47:52');
INSERT INTO `biz_article` VALUES ('4', 'java.lang.ClassNotFoundException: net.sf.ezmorph.Morpher 异常', '1', 'flyat/cover/20180331122440.png', null, '<pre class=\"pre-scrollable\" style=\"max-width: 100%;\"><code class=\"html hljs xml\" codemark=\"1\">java.lang.ClassNotFoundException: net.sf.ezmorph.Morpher \nCould not initialize class  net.sf.json.util.JSONUtils </code></pre><p>出现以上等异常，可能是使用Json缺少以下几个包中的一个：&nbsp;<br><br>ezmorph-1.0.6.jar&nbsp;<br>commons-lang 2.4&nbsp;<br>commons-beanutils 1.7.0&nbsp;<br>commons-collections 3.2&nbsp;<br>commons-logging 1.1.1</p><p><br></p>', '0', '2', '1', '1', '1', '看到网上基本没有最新的chromedriver与chrome的对应关系表，便兴起整理了一份如下，希望对大家有用', 'ClassNotFoundException: net.sf.ezmorph.Morpher', '2016-11-19 13:49:16', '2016-11-19 13:49:16');

-- ----------------------------
-- Table structure for biz_article_look
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_look`;
CREATE TABLE `biz_article_look` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) unsigned NOT NULL COMMENT '文章ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '已登录用户ID',
  `user_ip` varchar(50) DEFAULT NULL COMMENT '用户IP',
  `look_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of biz_article_look
-- ----------------------------
INSERT INTO `biz_article_look` VALUES ('1', '4', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:16:24', '2018-04-23 10:16:24', '2018-04-23 10:16:24');
INSERT INTO `biz_article_look` VALUES ('2', '3', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:16:41', '2018-04-23 10:16:41', '2018-04-23 10:16:41');
INSERT INTO `biz_article_look` VALUES ('3', '2', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:16:55', '2018-04-23 10:16:55', '2018-04-23 10:16:55');
INSERT INTO `biz_article_look` VALUES ('4', '1', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:17:04', '2018-04-23 10:17:04', '2018-04-23 10:17:04');
INSERT INTO `biz_article_look` VALUES ('5', '4', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:17:39', '2018-04-23 10:17:39', '2018-04-23 10:17:39');
INSERT INTO `biz_article_look` VALUES ('6', '3', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:17:43', '2018-04-23 10:17:43', '2018-04-23 10:17:43');
INSERT INTO `biz_article_look` VALUES ('7', '3', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:18:17', '2018-04-23 10:18:17', '2018-04-23 10:18:17');
INSERT INTO `biz_article_look` VALUES ('8', '3', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:19:39', '2018-04-23 10:19:39', '2018-04-23 10:19:39');
INSERT INTO `biz_article_look` VALUES ('9', '3', null, '0:0:0:0:0:0:0:1', '2018-04-23 10:19:45', '2018-04-23 10:19:45', '2018-04-23 10:19:45');

-- ----------------------------
-- Table structure for biz_article_love
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_love`;
CREATE TABLE `biz_article_love` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) unsigned NOT NULL COMMENT '文章ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '已登录用户ID',
  `user_ip` varchar(50) DEFAULT NULL COMMENT '用户IP',
  `love_time` datetime DEFAULT NULL COMMENT '浏览时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of biz_article_love
-- ----------------------------

-- ----------------------------
-- Table structure for biz_article_tags
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_tags`;
CREATE TABLE `biz_article_tags` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) unsigned NOT NULL COMMENT '标签表主键',
  `article_id` bigint(20) unsigned NOT NULL COMMENT '文章ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of biz_article_tags
-- ----------------------------
INSERT INTO `biz_article_tags` VALUES ('1', '5', '1', '2018-04-23 10:14:33', '2018-04-23 10:14:33');
INSERT INTO `biz_article_tags` VALUES ('2', '1', '2', '2018-04-23 10:14:36', '2018-04-23 10:14:36');
INSERT INTO `biz_article_tags` VALUES ('3', '2', '3', '2018-04-23 10:14:38', '2018-04-23 10:14:38');
INSERT INTO `biz_article_tags` VALUES ('4', '2', '4', '2018-04-23 10:15:03', '2018-04-23 10:15:03');

-- ----------------------------
-- Table structure for biz_comment
-- ----------------------------
DROP TABLE IF EXISTS `biz_comment`;
CREATE TABLE `biz_comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sid` bigint(20) DEFAULT NULL COMMENT '被评论的文章或者页面的ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '评论人的ID',
  `pid` bigint(20) unsigned DEFAULT NULL COMMENT '父级评论的id',
  `qq` varchar(13) DEFAULT NULL COMMENT '评论人的QQ（未登录用户）',
  `nickname` varchar(13) DEFAULT NULL COMMENT '评论人的昵称（未登录用户）',
  `avatar` varchar(255) DEFAULT NULL COMMENT '评论人的头像地址',
  `email` varchar(100) DEFAULT NULL COMMENT '评论人的邮箱地址（未登录用户）',
  `url` varchar(200) DEFAULT NULL COMMENT '评论人的网站地址（未登录用户）',
  `status` enum('VERIFYING','APPROVED','REJECT','DELETED') DEFAULT 'VERIFYING' COMMENT '评论的状态',
  `ip` varchar(64) DEFAULT NULL COMMENT '评论时的ip',
  `lng` varchar(50) DEFAULT NULL COMMENT '经度',
  `lat` varchar(50) DEFAULT NULL COMMENT '纬度',
  `address` varchar(100) DEFAULT NULL COMMENT '评论时的地址',
  `os` varchar(64) DEFAULT NULL COMMENT '评论时的系统类型',
  `os_short_name` varchar(10) DEFAULT NULL COMMENT '评论时的系统的简称',
  `browser` varchar(64) DEFAULT NULL COMMENT '评论时的浏览器类型',
  `browser_short_name` varchar(10) DEFAULT NULL COMMENT '评论时的浏览器的简称',
  `content` varchar(2000) DEFAULT NULL COMMENT '评论的内容',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注（审核不通过时添加）',
  `support` int(10) unsigned DEFAULT '0' COMMENT '支持（赞）',
  `oppose` int(10) unsigned DEFAULT '0' COMMENT '反对（踩）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of biz_comment
-- ----------------------------

-- ----------------------------
-- Table structure for biz_tags
-- ----------------------------
DROP TABLE IF EXISTS `biz_tags`;
CREATE TABLE `biz_tags` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '书签名',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of biz_tags
-- ----------------------------
INSERT INTO `biz_tags` VALUES ('1', 'Linux', null, '2018-01-14 21:35:31', '2018-01-14 21:35:31');
INSERT INTO `biz_tags` VALUES ('2', 'Java', null, '2018-01-14 21:35:45', '2018-01-14 21:35:45');
INSERT INTO `biz_tags` VALUES ('3', 'Spring', null, '2018-01-14 21:35:52', '2018-01-14 21:35:52');
INSERT INTO `biz_tags` VALUES ('4', 'Spring Boot', null, '2018-01-14 21:36:01', '2018-01-14 21:36:01');
INSERT INTO `biz_tags` VALUES ('5', '其他', null, '2018-01-14 21:36:07', '2018-01-14 21:36:07');

-- ----------------------------
-- Table structure for biz_type
-- ----------------------------
DROP TABLE IF EXISTS `biz_type`;
CREATE TABLE `biz_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '文章类型名',
  `description` varchar(200) DEFAULT NULL COMMENT '类型介绍',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `available` tinyint(1) unsigned DEFAULT '1' COMMENT '是否可用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of biz_type
-- ----------------------------
INSERT INTO `biz_type` VALUES ('1', null, '前端技术', '主要收集、整理的基础前端类文章，包括JS、jQuery和CSS等Web开发所需的基础的文章总结', '1', 'fa fa-css3', '1', '2018-01-14 21:34:54', '2018-01-14 21:34:54');
INSERT INTO `biz_type` VALUES ('2', null, '后端技术', '网站中记录的后端类文章，包括Java、SSM、MySQL和其他在日常工作学习中所用的后端技术', '2', 'fa fa-coffee', '1', '2018-01-14 21:34:57', '2018-01-14 21:34:57');
INSERT INTO `biz_type` VALUES ('3', null, '其他文章', '记录网站建设以及日常工作、学习中的闲言碎语和个人笔记等文章', '3', 'fa fa-folder-open-o', '1', '2018-01-20 22:28:03', '2018-03-01 19:27:53');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `home_desc` varchar(255) DEFAULT NULL COMMENT '首页描述',
  `home_keywords` varchar(255) DEFAULT NULL COMMENT '首页关键字',
  `domain` varchar(255) DEFAULT NULL COMMENT '根域名',
  `site_url` varchar(255) DEFAULT NULL COMMENT '网站地址',
  `site_name` varchar(255) DEFAULT NULL COMMENT '站点名称',
  `site_desc` varchar(255) DEFAULT NULL COMMENT '站点描述',
  `site_favicon` varchar(255) DEFAULT NULL COMMENT '站点LOGO',
  `static_web_site` varchar(255) DEFAULT NULL COMMENT '资源文件（js、css等的路径）',
  `author_name` varchar(100) DEFAULT NULL COMMENT '站长名称',
  `author_email` varchar(100) DEFAULT NULL COMMENT '站长邮箱',
  `wx_code` varchar(255) DEFAULT NULL COMMENT '微信二维码地址',
  `qq` varchar(255) DEFAULT NULL COMMENT 'QQ',
  `weibo` varchar(255) DEFAULT NULL COMMENT '微博',
  `github` varchar(255) DEFAULT NULL COMMENT 'github',
  `maintenance` tinyint(1) unsigned DEFAULT NULL COMMENT '是否开启维护通知',
  `maintenance_data` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '系统维护时间',
  `comment` tinyint(1) unsigned DEFAULT NULL COMMENT '是否开启评论',
  `qiuniu_base_path` varchar(255) DEFAULT NULL COMMENT '七牛路径',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '张亚东博客是一个程序员的个人技术博客(微博ID:@七彩狼丿)，旨在分享工作和学习中用到的技术、遇到的疑难知识点，也为了备战自己的人生。心之所向，无所不能。', '张亚东博客,程序猿,程序员,后端开发,软件开发,WEB开发,Java攻城狮,全栈工程师,原创博客,个人原创网站,个人技术博客', 'zhyd.me', 'http://localhost:8443', '博客', '一个程序员的个人博客，心之所向，无所不能', 'https://static.zhyd.me/static/img/favicon.ico', 'http://localhost:8443', '张亚东', 'yadong.zhang0415#gmail.com', '', '843977358', 'http://weibo.com/211230415', 'https://github.com/zhangyd-c', '0', '2018-01-21 23:07:52', '1', 'http://p6fs0hjph.bkt.clouddn.com/', '2018-01-19 23:07:52', '2018-02-25 18:40:48');

-- ----------------------------
-- Table structure for sys_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_link`;
CREATE TABLE `sys_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(200) NOT NULL COMMENT '链接地址',
  `name` varchar(50) DEFAULT NULL COMMENT '链接名',
  `description` varchar(300) DEFAULT NULL COMMENT '链接介绍',
  `email` varchar(100) DEFAULT NULL COMMENT '友链站长邮箱',
  `qq` varchar(13) DEFAULT NULL COMMENT '友链站长QQ',
  `favicon` varchar(255) DEFAULT NULL,
  `status` tinyint(1) unsigned DEFAULT '1' COMMENT '状态',
  `home_page_display` tinyint(1) unsigned DEFAULT '1' COMMENT '是否首页显示',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `source` enum('ADMIN','AUTOMATIC') DEFAULT 'ADMIN' COMMENT '来源：管理员添加、自动申请',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_link
-- ----------------------------
INSERT INTO `sys_link` VALUES ('1', 'https://www.zhyd.me', '张亚东博客', '一个程序员的个人博客', 'yadong.zhang0415@gmail.com', null, 'https://static.zhyd.me/static/img/favicon.ico', '1', '1', null, 'ADMIN', '2016-11-16 23:32:03', '2018-01-23 11:27:19');
INSERT INTO `sys_link` VALUES ('13', 'http://tool.zhyd.me', '在线工具', '在线工具，只做最有用、最简单、最干净、最方便的工具网站。', 'yadong.zhang0415@gmail.com', null, 'https://static.zhyd.me/static/img/favicon.ico', '1', '1', null, 'ADMIN', '2017-05-04 11:22:29', '2017-05-09 09:11:35');
INSERT INTO `sys_link` VALUES ('35', 'http://music.zhyd.me', '我的音乐馆', '我的音乐馆', 'yadong.zhang0415@gmail.com', null, 'http://music.zhyd.me/images/favicon.ico', '1', '1', null, 'ADMIN', '2018-04-19 20:01:35', '2018-04-19 20:01:35');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL COMMENT '被通知的用户ID',
  `status` enum('RELEASE','NOT_RELEASE') DEFAULT 'NOT_RELEASE' COMMENT '通知状态',
  `title` varchar(200) DEFAULT NULL COMMENT '通知的标题',
  `content` varchar(2000) DEFAULT NULL COMMENT '通知的内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) unsigned DEFAULT '0',
  `sort` int(10) unsigned DEFAULT NULL,
  `external` tinyint(1) unsigned DEFAULT NULL COMMENT '是否外部链接',
  `available` tinyint(1) unsigned DEFAULT '0',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_resource_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '首页', 'menu', '/index', 'index', '0', '0', '0', '1', 'fa fa-home', '2017-12-20 16:40:06', '2018-01-14 21:02:05');
INSERT INTO `sys_resources` VALUES ('2', '用户管理', 'menu', '/users', 'users', '0', '1', null, '1', 'fa fa-users', '2017-12-22 13:56:15', '2017-12-22 13:56:15');
INSERT INTO `sys_resources` VALUES ('3', '系统配置', 'menu', null, null, '0', '7', null, '1', 'fa fa-cogs', '2017-12-20 16:40:06', '2017-12-20 16:40:08');
INSERT INTO `sys_resources` VALUES ('4', '资源管理', 'menu', '/resources', 'resources', '3', '1', null, '1', null, '2017-12-22 15:31:05', '2017-12-22 15:31:05');
INSERT INTO `sys_resources` VALUES ('5', '角色管理', 'menu', '/roles', 'roles', '3', '2', null, '1', null, '2017-12-22 15:31:27', '2017-12-22 15:31:27');
INSERT INTO `sys_resources` VALUES ('6', '测试页面', 'menu', null, null, '0', '100', null, '1', 'fa fa-desktop', '2017-12-22 15:31:27', '2017-12-22 15:31:27');
INSERT INTO `sys_resources` VALUES ('7', 'list', 'menu', '/list', 'anon', '6', '1', null, '1', null, '2017-12-22 15:31:27', '2017-12-22 15:31:27');
INSERT INTO `sys_resources` VALUES ('8', 'detail', 'menu', '/details', 'anon', '6', '2', null, '1', null, '2017-12-22 15:31:27', '2017-12-22 15:31:27');
INSERT INTO `sys_resources` VALUES ('9', 'ztree', 'menu', '/ztree', 'anon', '6', '3', null, '1', null, '2017-12-22 15:31:27', '2017-12-22 15:31:27');
INSERT INTO `sys_resources` VALUES ('10', 'icons', 'menu', '/icons', 'anon', '6', '4', null, '1', null, '2018-01-02 15:05:57', '2018-01-02 15:05:57');
INSERT INTO `sys_resources` VALUES ('11', '文章管理', 'menu', '', '', '0', '2', '0', '1', 'fa fa-list', '2018-01-13 21:23:00', '2018-01-13 21:23:00');
INSERT INTO `sys_resources` VALUES ('12', '文章列表', 'menu', '/articles', 'articles', '11', '1', '0', '1', '', '2018-01-13 21:45:50', '2018-01-13 21:51:47');
INSERT INTO `sys_resources` VALUES ('13', '发表文章', 'menu', '/article/publish', 'article:publish', '11', '2', '0', '1', '', '2018-01-13 21:48:36', '2018-01-13 21:51:53');
INSERT INTO `sys_resources` VALUES ('14', '分类列表', 'menu', '/article/types', 'article:types', '11', '3', '0', '1', '', '2018-01-13 21:49:36', '2018-01-13 21:51:58');
INSERT INTO `sys_resources` VALUES ('15', '标签列表', 'menu', '/article/tags', 'article:tags', '11', '4', '0', '1', '', '2018-01-13 21:50:10', '2018-01-13 21:51:41');
INSERT INTO `sys_resources` VALUES ('16', '友情链接', 'menu', '/links', 'links', '21', '3', '0', '1', null, '2018-01-13 21:54:21', '2018-01-13 21:54:21');
INSERT INTO `sys_resources` VALUES ('17', '评论管理', 'menu', '/comments', 'comments', '21', '4', '0', '1', null, '2018-01-14 20:25:29', '2018-01-14 20:29:56');
INSERT INTO `sys_resources` VALUES ('18', '模板管理', 'menu', '/templates', 'templates', '21', '3', '0', '1', null, '2018-01-14 20:26:28', '2018-01-14 20:28:10');
INSERT INTO `sys_resources` VALUES ('19', '更新记录', 'menu', '/updates', 'updates', '21', '5', '0', '1', null, '2018-01-15 17:46:23', '2018-01-15 17:49:46');
INSERT INTO `sys_resources` VALUES ('20', '公告管理', 'menu', '/notices', 'notices', '21', '6', '0', '1', null, '2018-01-15 17:54:14', '2018-01-15 17:54:14');
INSERT INTO `sys_resources` VALUES ('21', '网站管理', 'menu', '', null, '0', '2', '0', '1', 'fa fa-globe', '2018-01-31 11:25:13', '2018-01-31 11:25:13');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '角色名',
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'role:root', '超级管理员', '1', '2017-12-20 16:40:24', '2017-12-20 16:40:26');
INSERT INTO `sys_role` VALUES ('2', 'role:admin', '管理员', '1', '2017-12-22 13:56:39', '2017-12-22 13:56:39');

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) unsigned NOT NULL,
  `resources_id` bigint(20) unsigned NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('57', '1', '1', null, null);
INSERT INTO `sys_role_resources` VALUES ('58', '1', '2', null, null);
INSERT INTO `sys_role_resources` VALUES ('59', '1', '11', null, null);
INSERT INTO `sys_role_resources` VALUES ('60', '1', '12', null, null);
INSERT INTO `sys_role_resources` VALUES ('61', '1', '13', null, null);
INSERT INTO `sys_role_resources` VALUES ('62', '1', '14', null, null);
INSERT INTO `sys_role_resources` VALUES ('63', '1', '15', null, null);
INSERT INTO `sys_role_resources` VALUES ('64', '1', '21', null, null);
INSERT INTO `sys_role_resources` VALUES ('65', '1', '16', null, null);
INSERT INTO `sys_role_resources` VALUES ('66', '1', '18', null, null);
INSERT INTO `sys_role_resources` VALUES ('67', '1', '17', null, null);
INSERT INTO `sys_role_resources` VALUES ('68', '1', '19', null, null);
INSERT INTO `sys_role_resources` VALUES ('69', '1', '20', null, null);
INSERT INTO `sys_role_resources` VALUES ('70', '1', '3', null, null);
INSERT INTO `sys_role_resources` VALUES ('71', '1', '4', null, null);
INSERT INTO `sys_role_resources` VALUES ('72', '1', '5', null, null);
INSERT INTO `sys_role_resources` VALUES ('73', '1', '6', null, null);
INSERT INTO `sys_role_resources` VALUES ('74', '1', '7', null, null);
INSERT INTO `sys_role_resources` VALUES ('75', '1', '8', null, null);
INSERT INTO `sys_role_resources` VALUES ('76', '1', '9', null, null);
INSERT INTO `sys_role_resources` VALUES ('77', '1', '10', null, null);

-- ----------------------------
-- Table structure for sys_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_template`;
CREATE TABLE `sys_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ref_key` varchar(100) DEFAULT NULL COMMENT '键',
  `ref_value` text COMMENT '模板内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_template
-- ----------------------------
INSERT INTO `sys_template` VALUES ('1', 'TM_SITEMAP_XML', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">\r\n	<url>\r\n		<loc>${config.siteUrl}</loc>\r\n		<priority>1.0</priority>\r\n		<lastmod>${.now?string(\"yyyy-MM-dd\")}</lastmod>\r\n		<changefreq>daily</changefreq>\r\n	</url>\r\n	<url>\r\n		<loc>${config.siteUrl}/about</loc>\r\n		<priority>0.6</priority>\r\n		<lastmod>${.now?string(\"yyyy-MM-dd\")}</lastmod>\r\n		<changefreq>daily</changefreq>\r\n	</url>\r\n	<url>\r\n		<loc>${config.siteUrl}/links</loc>\r\n		<priority>0.6</priority>\r\n		<lastmod>${.now?string(\"yyyy-MM-dd\")}</lastmod>\r\n		<changefreq>daily</changefreq>\r\n	</url>\r\n	<url>\r\n		<loc>${config.siteUrl}/guestbook</loc>\r\n		<priority>0.6</priority>\r\n		<lastmod>${.now?string(\"yyyy-MM-dd\")}</lastmod>\r\n		<changefreq>daily</changefreq>\r\n	</url>\r\n	<url>\r\n		<loc>${config.siteUrl}/updateLog</loc>\r\n		<priority>0.6</priority>\r\n		<lastmod>${.now?string(\"yyyy-MM-dd\")}</lastmod>\r\n		<changefreq>daily</changefreq>\r\n	</url>\r\n	<url>\r\n		<loc>${config.siteUrl}/recommended</loc>\r\n		<priority>0.6</priority>\r\n		<lastmod>${.now?string(\"yyyy-MM-dd\")}</lastmod>\r\n		<changefreq>daily</changefreq>\r\n	</url>\r\n	<#list articleList as item>\r\n		<url>\r\n			<loc>${config.siteUrl}/article/${item.id}</loc>\r\n			<priority>0.6</priority>\r\n			<lastmod>${item.updateTime?string(\"yyyy-MM-dd\")}</lastmod>\r\n			<changefreq>daily</changefreq>\r\n		</url>\r\n	</#list>\r\n	<#list articleTypeList as item>\r\n	   <url>\r\n			<loc>${config.siteUrl}/type/${item.id}</loc>\r\n			<priority>0.6</priority>\r\n			<lastmod>${item.updateTime?string(\"yyyy-MM-dd\")}</lastmod>\r\n			<changefreq>daily</changefreq>\r\n		</url>\r\n	</#list>\r\n	\r\n	<#list articleTagsList as item>\r\n	   <url>\r\n			<loc>${config.siteUrl}/tag/${item.id}</loc>\r\n			<priority>0.6</priority>\r\n			<lastmod>${item.updateTime?string(\"yyyy-MM-dd\")}</lastmod>\r\n			<changefreq>daily</changefreq>\r\n		</url>\r\n	</#list>\r\n</urlset>', '2018-01-19 10:47:06', '2018-03-13 17:41:31');
INSERT INTO `sys_template` VALUES ('2', 'TM_SITEMAP_TXT', '${config.siteUrl}\r\n${config.siteUrl}/about\r\n${config.siteUrl}/links\r\n${config.siteUrl}/guestbook\r\n${config.siteUrl}/updateLog\r\n${config.siteUrl}/recommended\r\n<#list articleList as item>\r\n${config.siteUrl}/article/${item.id}\r\n</#list>\r\n<#list articleTypeList as item>\r\n${config.siteUrl}/type/${item.id}\r\n</#list>\r\n<#list articleTagsList as item>\r\n${config.siteUrl}/tag/${item.id}\r\n</#list>', '2018-01-19 10:47:12', '2018-02-28 12:11:59');
INSERT INTO `sys_template` VALUES ('3', 'TM_SITEMAP_HTML', '<!DOCTYPE html>\r\n<html lang=\"zh-CN\">\r\n<head>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\r\n    <title>${config.siteName} 网站地图</title>\r\n    <meta name=\"author\" content=\"SiteMapX.com\"/>\r\n    <meta name=\"robots\" content=\"index,follow\"/>\r\n    <style type=\"text/css\">\r\n        body {\r\n            color: #000000;\r\n            background: #ffffff;\r\n            margin: 20px;\r\n            font-family: Verdana, Arial, Helvetica, sans-serif;\r\n            font-size: 12px;\r\n        }\r\n\r\n        #myTable {\r\n            list-style: none;\r\n            margin: 10px 0px 10px 0px;\r\n            padding: 0px;\r\n            width: 100%;\r\n            min-width: 804px;\r\n        }\r\n\r\n        #myTable li {\r\n            list-style-type: none;\r\n            width: 100%;\r\n            min-width: 404px;\r\n            height: 20px;\r\n            line-height: 20px;\r\n        }\r\n\r\n        .pull-left{\r\n            float: left!important;\r\n        }\r\n        .pull-right{\r\n            float: right!important;\r\n        }\r\n\r\n        #myTable li .T1-h {\r\n            font-weight: bold;\r\n            min-width: 300px;\r\n        }\r\n\r\n        #myTable li .T2-h {\r\n            width: 200px;\r\n            font-weight: bold;\r\n        }\r\n\r\n        #myTable li .T3-h {\r\n            width: 200px;\r\n            font-weight: bold;\r\n        }\r\n\r\n        #myTable li .T4-h {\r\n            width: 100px;\r\n            font-weight: bold;\r\n        }\r\n\r\n        #myTable li .T1 {\r\n            min-width: 300px;\r\n        }\r\n\r\n        #myTable li .T2 {\r\n            width: 200px;\r\n        }\r\n\r\n        #myTable li .T3 {\r\n            width: 200px;\r\n        }\r\n\r\n        #myTable li .T4 {\r\n            width: 100px;\r\n        }\r\n\r\n        #footer {\r\n            padding: 2px;\r\n            margin: 0px;\r\n            font-size: 8pt;\r\n            color: gray;\r\n            min-width: 900px;\r\n        }\r\n\r\n        #footer a {\r\n            color: gray;\r\n        }\r\n\r\n        .myClear {\r\n            clear: both;\r\n        }\r\n\r\n        #nav, #content, #footer {padding: 8px; border: 1px solid #EEEEEE; clear: both; width: 95%; margin: auto; margin-top: 10px;}\r\n\r\n    </style>\r\n</head>\r\n<body>\r\n<h2 style=\"text-align: center; margin-top: 20px\">${config.siteName?if_exists} 网站地图 </h2>\r\n<div id=\"nav\"><a href=\"${config.siteUrl?if_exists}\"><strong>${config.siteName?if_exists}</strong></a> &raquo; <a href=\"${config.siteUrl?if_exists}/sitemap.html\">站点地图</a></div>\r\n<div id=\"content\">\r\n    <h3>最新文章</h3>\r\n    <ul id=\"myTable\">\r\n        <li>\r\n            <div class=\"T1-h pull-left\">URL</div>\r\n            <div class=\"T2-h pull-right\">Last Change</div>\r\n            <div class=\"T3-h pull-right\">Change Frequency</div>\r\n            <div class=\"T4-h pull-right\">Priority</div>\r\n        </li>\r\n        <div class=\"myClear\"></div>\r\n        <li>\r\n            <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}\" title=\"${config.siteName}\">${config.siteName} | 一个程序员的个人博客</a></div>\r\n            <div class=\"T2 pull-right\">${.now?string(\"yyyy-MM-dd\")}</div>\r\n            <div class=\"T3 pull-right\">daily</div>\r\n            <div class=\"T4 pull-right\">1</div>\r\n        </li>\r\n        <div class=\"myClear\"></div>\r\n        <li>\r\n            <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}/about\" title=\"${config.siteName}\">关于 | ${config.siteName}</a></div>\r\n            <div class=\"T2 pull-right\">${.now?string(\"yyyy-MM-dd\")}</div>\r\n            <div class=\"T3 pull-right\">daily</div>\r\n            <div class=\"T4 pull-right\">0.6</div>\r\n        </li>\r\n        <div class=\"myClear\"></div>\r\n        <li>\r\n            <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}/links\" title=\"${config.siteName}\">友情链接 | ${config.siteName}</a></div>\r\n            <div class=\"T2 pull-right\">${.now?string(\"yyyy-MM-dd\")}</div>\r\n            <div class=\"T3 pull-right\">daily</div>\r\n            <div class=\"T4 pull-right\">0.6</div>\r\n        </li>\r\n        <div class=\"myClear\"></div>\r\n        <li>\r\n            <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}/guestbook\" title=\"${config.siteName}\">留言板 | ${config.siteName}</a></div>\r\n            <div class=\"T2 pull-right\">${.now?string(\"yyyy-MM-dd\")}</div>\r\n            <div class=\"T3 pull-right\">daily</div>\r\n            <div class=\"T4 pull-right\">0.6</div>\r\n        </li>\r\n        <div class=\"myClear\"></div>\r\n        <li>\r\n            <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}/updateLog\" title=\"${config.siteName}\">网站更新记录 | ${config.siteName}</a></div>\r\n            <div class=\"T2 pull-right\">${.now?string(\"yyyy-MM-dd\")}</div>\r\n            <div class=\"T3 pull-right\">daily</div>\r\n            <div class=\"T4 pull-right\">0.6</div>\r\n        </li>\r\n		<div class=\"myClear\"></div>\r\n        <li>\r\n            <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}/recommended\" title=\"${config.siteName}\">站长推荐 | ${config.siteName}</a></div>\r\n            <div class=\"T2 pull-right\">${.now?string(\"yyyy-MM-dd\")}</div>\r\n            <div class=\"T3 pull-right\">daily</div>\r\n            <div class=\"T4 pull-right\">0.6</div>\r\n        </li>\r\n        <div class=\"myClear\"></div>\r\n        <#list articleList as item>\r\n            <li>\r\n                <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}/article/${item.id}\" title=\"${item.title}\">${item.title} | ${config.siteName}</a></div>\r\n                <div class=\"T2 pull-right\">${item.updateTime?string(\"yyyy-MM-dd\")}</div>\r\n                <div class=\"T3 pull-right\">daily</div>\r\n                <div class=\"T4 pull-right\">0.6</div>\r\n            </li>\r\n            <div class=\"myClear\"></div>\r\n        </#list>\r\n    </ul>\r\n</div>\r\n<div id=\"content\">\r\n    <h3>分类目录</h3>\r\n    <ul id=\"myTable\">\r\n        <#list articleTypeList as item>\r\n            <li>\r\n                <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}/type/${item.id}\" title=\"${item.name}\">${item.name} | ${config.siteName}</a></div>\r\n                <div class=\"T2 pull-right\">${item.updateTime?string(\"yyyy-MM-dd\")}</div>\r\n                <div class=\"T3 pull-right\">daily</div>\r\n                <div class=\"T4 pull-right\">0.6</div>\r\n            </li>\r\n            <div class=\"myClear\"></div>\r\n        </#list>\r\n    </ul>\r\n</div>\r\n<div id=\"content\">\r\n    <h3>标签目录</h3>\r\n    <ul id=\"myTable\">\r\n        <#list articleTagsList as item>\r\n            <li>\r\n                <div class=\"T1 pull-left\"><a href=\"${config.siteUrl}/tag/${item.id}\" title=\"${item.name}\">${item.name} | ${config.siteName}</a></div>\r\n                <div class=\"T2 pull-right\">${item.updateTime?string(\"yyyy-MM-dd\")}</div>\r\n                <div class=\"T3 pull-right\">daily</div>\r\n                <div class=\"T4 pull-right\">0.6</div>\r\n            </li>\r\n            <div class=\"myClear\"></div>\r\n        </#list>\r\n    </ul>\r\n</div>\r\n<div id=\"footer\">\r\n    该文件由<a href=\"${config.siteUrl}\" title=\"${config.siteName}\">${config.siteName}</a>网站自动生成。\r\n</div>\r\n</body>\r\n</html>', '2018-01-19 10:47:43', '2018-03-13 17:40:52');
INSERT INTO `sys_template` VALUES ('4', 'TM_ROBOTS', 'Crawl-delay: 5\r\nSitemap: https://www.zhyd.me/sitemap.txt\r\nSitemap: https://www.zhyd.me/sitemap.xml\r\nSitemap: https://www.zhyd.me/sitemap.html\r\n', '2018-01-19 10:48:27', '2018-01-19 10:48:27');
INSERT INTO `sys_template` VALUES ('5', 'TM_LINKS', '<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <title>友情链接操作通知</title>\r\n</head>\r\n<body>\r\n<div style=\"border-radius:5px;font-size:13px;width:680px;font-family:微软雅黑,\'Helvetica Neue\',Arial,sans-serif;margin:10px auto 0px;border:1px solid #eee;max-width:100%\">\r\n    <div style=\"width:100%;background:#2f889a;color:#ffffff;border-radius:5px 5px 0 0\">\r\n        <p style=\"font-size:15px;word-break:break-all;padding:20px 32px;margin:0\">\r\n            友情链接操作通知\r\n        </p>\r\n    </div>\r\n    <div style=\"margin:0px auto;width:90%\">\r\n        <p>站长<a href=\"${link.url?if_exists}\" target=\"_blank\">${link.name?if_exists}</a>，您好!</p>\r\n        <p>您于 ${link.createTime?string(\'yyyy-MM-dd HH:mm:ss\')} 提交的友链申请已通过系统审核。以下为您提交的信息，请确认（如有误，请联系我）！</p>\r\n        <p>\r\n        <ul>\r\n            <li>${link.name?if_exists}</li>\r\n            <li>${link.url?if_exists}</li>\r\n            <li>${link.description?if_exists}</li>\r\n            <li>${link.email?if_exists}</li>\r\n            <li>${link.qq?if_exists}</li>\r\n            <li><img src=\"${link.favicon?if_exists}\" width=\"100\" alt=\"LOGO\"></li>\r\n        </ul>\r\n        </p>\r\n        <p>本站会不定期检查连接有效性，如果因为贵站改版、服务到期等原因导致无法正常访问的，我会暂时停掉贵站友链，待贵站可以正常访问后，本站会继续启用贵站友链。</p>\r\n        <p>特别注意：以下情况，本站将在不做任何通知下，<strong>取消友链</strong>！</p>\r\n        <ul>\r\n            <li>私自取消本站友情链接</li>\r\n            <li>更换域名且未通知本站</li>\r\n            <li>网站内容长期不更新</li>\r\n            <li>友链上使用诸如nofollow之类的属性</li>\r\n        </ul>\r\n        <p>感谢您对 <a style=\"text-decoration:none;\" href=\"${config.siteUrl?if_exists}\" target=\"_blank\">${config.siteName?if_exists}</a> 的关注，如您有任何疑问，欢迎来我网站<a style=\"text-decoration:none;\" href=\"${config.siteUrl}/guestbook\" target=\"_blank\">留言</a>。</p>\r\n        <p>（注：此邮件由系统自动发出，请勿回复。）</p>\r\n    </div>\r\n    <div class=\"adL\">\r\n    </div>\r\n</div>\r\n</body>\r\n</html>', '2018-01-19 10:48:54', '2018-01-19 10:48:54');
INSERT INTO `sys_template` VALUES ('6', 'TM_COMMENT_AUDIT', '<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <title>评论审核结果通知</title>\r\n</head>\r\n<body>\r\n<div style=\"border-radius:5px;font-size:13px;width:680px;font-family:微软雅黑,\'Helvetica Neue\',Arial,sans-serif;margin:10px auto 0px;border:1px solid #eee;max-width:100%\">\r\n    <div style=\"width:100%;background:#2f889a;color:#ffffff;border-radius:5px 5px 0 0\">\r\n        <p style=\"font-size:15px;word-break:break-all;padding:20px 32px;margin:0\">\r\n            评论审核结果通知\r\n        </p>\r\n    </div>\r\n    <div style=\"margin:0px auto;width:90%\">\r\n        <p>${comment.nickname?if_exists}，您好!</p>\r\n        <p>\r\n            您于 ${comment.createTime?string(\'yyyy-MM-dd HH:mm:ss\')} 在文章《${config.siteUrl?if_exists}${comment.sourceUrl?if_exists}》 上发表的<span class=\"il\">评论</span>：\r\n        </p>\r\n        <div style=\"background:#efefef;margin:15px 0px;padding:1px 15px;border-radius:5px;font-size:14px;color:#333\">${comment.content}</div>\r\n        <#if comment.status == \'APPROVED\'>\r\n        <p>已通过管理员审核并显示。</p>\r\n        <p>\r\n            您可以点击 <a style=\"text-decoration:none;\" href=\"${config.siteUrl}${comment.sourceUrl}\" target=\"_blank\">链接</a>查看回复的完整內容。\r\n        </p>\r\n        <#elseif comment.status == \'REJECT\'>\r\n        <p>审核失败！失败原因：</p>\r\n        <p style=\"background:#efefef;margin:15px 0px;padding:1px 15px;border-radius:5px;font-size:14px;color:#333\">${comment.remark}</p>\r\n        <#elseif comment.status == \'DELETED\'>\r\n        <p>已被管理员删除！删除原因：</p>\r\n        <p style=\"background:#efefef;margin:15px 0px;padding:1px 15px;border-radius:5px;font-size:14px;color:#333\">${comment.remark}</p>\r\n        <#else>\r\n        <p>管理员正在审核中！审核通过后将给您及时发送通知！</p>\r\n        </#if>\r\n        <p>感谢您对 <a style=\"text-decoration:none;\" href=\"${config.siteUrl}\" target=\"_blank\">${config.siteName}</a> 的关注，如您有任何疑问，欢迎来我网站<a style=\"text-decoration:none;\" href=\"${config.siteUrl}/guestbook\" target=\"_blank\">留言</a>。</p>\r\n        <p>（注：此邮件由系统自动发出，请勿回复。）</p>\r\n    </div>\r\n    <div class=\"adL\">\r\n    </div>\r\n</div>\r\n</body>\r\n</html>', '2018-01-26 21:17:38', '2018-01-26 21:17:38');
INSERT INTO `sys_template` VALUES ('7', 'TM_COMMENT_REPLY', '<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <title>评论回复通知</title>\r\n</head>\r\n<body>\r\n<div style=\"border-radius:5px;font-size:13px;width:680px;font-family:微软雅黑,\'Helvetica Neue\',Arial,sans-serif;margin:10px auto 0px;border:1px solid #eee;max-width:100%\">\r\n    <div style=\"width:100%;background:#2f889a;color:#ffffff;border-radius:5px 5px 0 0\">\r\n        <p style=\"font-size:15px;word-break:break-all;padding:20px 32px;margin:0\">\r\n            评论回复通知\r\n        </p>\r\n    </div>\r\n    <div style=\"margin:0px auto;width:90%\">\r\n        <p>${comment.nickname}，您好!</p>\r\n        <p>\r\n            您于 ${comment.createTime?string(\'yyyy-MM-dd HH:mm:ss\')} 在文章《${config.siteUrl}${comment.sourceUrl}》 上发表的<span class=\"il\">评论</span>：\r\n        </p>\r\n        <div style=\"background:#efefef;margin:15px 0px;padding:1px 15px;border-radius:5px;font-size:14px;color:#333\">${comment.content}</div>\r\n        <p>\r\n            有了 <strong>新的回复</strong>！您可以点击 <a style=\"text-decoration:none;\" href=\"${config.siteUrl}${comment.sourceUrl}\" target=\"_blank\">链接</a>查看回复的完整內容。\r\n        </p>\r\n        <p>感谢您对 <a style=\"text-decoration:none;\" href=\"${config.siteUrl}\" target=\"_blank\">${config.siteName}</a> 的关注，如您有任何疑问，欢迎来我网站<a style=\"text-decoration:none;\" href=\"${config.siteUrl}/guestbook\" target=\"_blank\">留言</a>。</p>\r\n        <p>（注：此邮件由系统自动发出，请勿回复。）</p>\r\n    </div>\r\n    <div class=\"adL\">\r\n    </div>\r\n</div>\r\n</body>\r\n</html>', '2018-01-26 21:17:55', '2018-01-26 21:17:55');
INSERT INTO `sys_template` VALUES ('8', 'TM_LINKS_TO_ADMIN', '<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <title>友情链接操作通知</title>\r\n</head>\r\n<body>\r\n<div style=\"border-radius:5px;font-size:13px;width:680px;font-family:微软雅黑,\'Helvetica Neue\',Arial,sans-serif;margin:10px auto 0px;border:1px solid #eee;max-width:100%\">\r\n    <div style=\"width:100%;background:#2f889a;color:#ffffff;border-radius:5px 5px 0 0\">\r\n        <p style=\"font-size:15px;word-break:break-all;padding:20px 32px;margin:0\">\r\n            友情链接操作通知\r\n        </p>\r\n    </div>\r\n    <div style=\"margin:0px auto;width:90%\">\r\n        <p>有新的友情链接加入，信息如下</p>\r\n        <p>\r\n        <ul>\r\n            <li>${link.name?if_exists}</li>\r\n            <li>${link.url?if_exists}</li>\r\n            <li>${link.description?if_exists}</li>\r\n            <#if link.favicon?exists><li><img src=\"${link.favicon?if_exists}\" width=\"100\" alt=\"LOGO\"></li></#if>\r\n        </ul>\r\n        </p>\r\n        <p><a style=\"text-decoration:none;\" href=\"http://admin.zhyd.me\" target=\"_blank\">去后台继续审核</a>。</p>\r\n        <p>（注：此邮件由系统自动发出，请勿回复。）</p>\r\n    </div>\r\n    <div class=\"adL\">\r\n    </div>\r\n</div>\r\n</body>\r\n</html>', '2018-03-31 14:14:11', '2018-03-31 14:14:11');
INSERT INTO `sys_template` VALUES ('9', 'TM_NEW_COMMENT', '<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <title>新评论通知</title>\r\n</head>\r\n<body>\r\n<div style=\"border-radius:5px;font-size:13px;width:680px;font-family:微软雅黑,\'Helvetica Neue\',Arial,sans-serif;margin:10px auto 0px;border:1px solid #eee;max-width:100%\">\r\n    <div style=\"width:100%;background:#2f889a;color:#ffffff;border-radius:5px 5px 0 0\">\r\n        <p style=\"font-size:15px;word-break:break-all;padding:20px 32px;margin:0\">\r\n            新评论通知\r\n        </p>\r\n    </div>\r\n    <div style=\"margin:0px auto;width:90%\">\r\n        <p>\r\n            评论内容：\r\n        </p>\r\n        <div style=\"background:#efefef;margin:15px 0px;padding:1px 15px;border-radius:5px;font-size:14px;color:#333\"><#if comment?exists>${comment.content}<#else>**无评论内容**</#if></div>\r\n        <p>\r\n            <a style=\"text-decoration:none;\" href=\"${config.siteUrl}${comment.sourceUrl}\" target=\"_blank\">查看完整评论</a>\r\n        </p>\r\n        <p>（注：此邮件由系统自动发出，请勿回复。）</p>\r\n    </div>\r\n    <div class=\"adL\">\r\n    </div>\r\n</div>\r\n</body>\r\n</html>', '2018-03-31 14:19:04', '2018-03-31 14:19:04');

-- ----------------------------
-- Table structure for sys_update_recorde
-- ----------------------------
DROP TABLE IF EXISTS `sys_update_recorde`;
CREATE TABLE `sys_update_recorde` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `version` varchar(20) DEFAULT NULL COMMENT '更新版本',
  `description` varchar(2500) DEFAULT NULL COMMENT '更新记录备注',
  `recorde_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '项目更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_update_recorde
-- ----------------------------
INSERT INTO `sys_update_recorde` VALUES ('1', '1.0.1', '第一版', '2018-01-19 22:16:40', '2018-01-19 22:16:40', '2018-01-19 22:16:40');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `nickname` varchar(30) DEFAULT '' COMMENT '昵称',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` tinyint(2) unsigned DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `user_type` enum('ROOT','ADMIN','USER') DEFAULT 'ADMIN' COMMENT '超级管理员、管理员、普通用户',
  `company` varchar(100) DEFAULT NULL COMMENT '公司',
  `blog` varchar(255) DEFAULT NULL COMMENT '个人博客地址',
  `location` varchar(255) DEFAULT NULL COMMENT '地址',
  `source` enum('GITHUB','WEIBO','QQ','ZHYD') DEFAULT 'ZHYD' COMMENT '用户来源(默认ZHYD=本网站注册用户)',
  `privacy` tinyint(2) DEFAULT NULL COMMENT '隐私（1：公开，0：不公开）',
  `notification` tinyint(2) unsigned DEFAULT NULL COMMENT '通知：(1：通知显示消息详情，2：通知不显示详情)',
  `score` int(10) unsigned DEFAULT '0' COMMENT '金币值',
  `experience` int(10) unsigned DEFAULT '0' COMMENT '经验值',
  `reg_ip` varchar(30) DEFAULT NULL COMMENT '注册IP',
  `last_login_ip` varchar(30) DEFAULT NULL COMMENT '最近登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `login_count` int(10) unsigned DEFAULT '0' COMMENT '登录次数',
  `remark` varchar(100) DEFAULT NULL COMMENT '用户备注',
  `status` int(1) unsigned DEFAULT NULL COMMENT '用户状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'root', 'CGUx1FN++xS+4wNDFeN6DA==', '超级管理员', '15151551516', '843977358@qq.com', '843977358', null, null, 'https://static.zhyd.me/static/img/favicon.ico', 'ROOT', null, null, null, 'ZHYD', null, null, '0', '0', null, null, '2018-04-10 15:26:23', '159', null, '1', '2018-01-02 09:32:15', '2018-04-10 15:26:23');
INSERT INTO `sys_user` VALUES ('2', 'admin', 'gXp2EbyZ+sB/A6QUMhiUJQ==', '管理员', '15151551516', '843977358@qq.com', '843977358', null, null, null, 'ADMIN', null, null, null, 'ZHYD', '1', '1', '0', '0', '0:0:0:0:0:0:0:1', null, null, '0', null, '1', '2018-01-02 15:56:34', '2018-01-05 18:18:05');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '2018-01-02 10:47:27', '2018-01-02 10:47:27');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2', '2018-01-05 18:21:02', '2018-01-05 18:21:02');
