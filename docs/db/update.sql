###################################  readme  ###################################
#
#  该文件中的sql已同步更新到了dblog.sql中，本文件存在的目的主要是记录数据库修改的内容
#  在每次更新时，凡是涉及到修改数据库的，如果是第一次使用该系统，则只需要导入dblog.sql即可
#  如果你已经在使用dblog了，则只需要复制本文件中的相关sql去执行即可，避免了直接执行dblog可能会覆盖本地修改内容的问题
#
###################################  readme  ###################################

ALTER TABLE `sys_role`
ADD COLUMN `name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '角色名' AFTER `id`;


# 2018-05-18 10:28 添加字段
ALTER TABLE `sys_config`
ADD COLUMN `baidu_push_token`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '百度推送的token' AFTER `qiuniu_base_path`,
ADD COLUMN `wx_praise_code`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '微信赞赏码' AFTER `baidu_push_token`,
ADD COLUMN `zfb_praise_code`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '支付宝赞赏码' AFTER `wx_praise_code`;
ALTER TABLE `sys_config`
ADD COLUMN `baidu_api_ak`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '百度api授权AK(获取地址：http://lbsyun.baidu.com/apiconsole/key)' AFTER `zfb_praise_code`;
ALTER TABLE `sys_config`
ADD COLUMN `qiniu_access_key`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '七牛ACCESS_KEY' AFTER `qiuniu_base_path`,
ADD COLUMN `qiniu_secret_key`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '七牛SECRET_KEY' AFTER `qiniu_access_key`,
ADD COLUMN `qiniu_bucket_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '七牛BUCKET_NAME' AFTER `qiniu_secret_key`;

# 2018-06-10 文章是否开启评论功能
ALTER TABLE `biz_article`
ADD COLUMN `comment`  tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否开启评论' AFTER `keywords`;
ALTER TABLE `sys_config`
MODIFY COLUMN `comment`  tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否开启评论' AFTER `maintenance_data`;

# 2018-06-10 markdown版本的编辑器
ALTER TABLE `biz_article`
ADD COLUMN `is_markdown`  tinyint(1) UNSIGNED NULL AFTER `qrcode_path`,
ADD COLUMN `content_md`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'markdown版的文章内容' AFTER `content`;

# 2018-07-05 config表中新增管理系统的url，增加推送消息的功能
ALTER TABLE `sys_config`
ADD COLUMN `cms_url`  varchar(255) NULL COMMENT '管理系统的url' AFTER `domain`;

INSERT INTO `sys_resources` VALUES ('71', '推送消息', 'menu', '/notice', 'notice', '40', NULL, '0', '1', '', '2018-07-05 11:30:24', '2018-07-05 11:30:24');
UPDATE `sys_config` SET `cms_url`='http://localhost:8085' WHERE (`id`='1')
-- 如果直接执行update语句，请手动清空redis缓存


# 2018-08-24 新增两条资源记录，更新资源表：重新排序，
INSERT INTO `dblog`.`sys_resources`(`id`, `name`, `type`, `url`, `permission`, `parent_id`, `sort`, `external`, `available`, `icon`, `create_time`, `update_time`) VALUES (72, '实验室', 'menu', '', '', 0, 5, 0, 1, 'fa fa-flask', '2018-07-30 11:28:50', '2018-08-24 14:11:37');
INSERT INTO `dblog`.`sys_resources`(`id`, `name`, `type`, `url`, `permission`, `parent_id`, `sort`, `external`, `available`, `icon`, `create_time`, `update_time`) VALUES (73, '文章搬运工', 'menu', '/remover', 'remover', 72, NULL, 0, 1, '', '2018-08-17 21:16:24', '2018-08-17 21:22:58');

UPDATE `dblog`.`sys_resources` SET `name` = '用户管理', `type` = 'menu', `url` = '', `permission` = '', `parent_id` = 0, `sort` = 4, `external` = 0, `available` = 1, `icon` = 'fa fa-users', `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-08-24 14:11:29' WHERE `id` = 1;
UPDATE `dblog`.`sys_resources` SET `name` = '用户列表', `type` = 'menu', `url` = '/users', `permission` = 'users', `parent_id` = 1, `sort` = 1, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 2;
UPDATE `dblog`.`sys_resources` SET `name` = '新增用户', `type` = 'button', `url` = NULL, `permission` = 'user:add', `parent_id` = 2, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 3;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除用户', `type` = 'button', `url` = NULL, `permission` = 'user:batchDelete', `parent_id` = 2, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 4;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑用户', `type` = 'button', `url` = NULL, `permission` = 'user:edit,user:get', `parent_id` = 2, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 5;
UPDATE `dblog`.`sys_resources` SET `name` = '删除用户', `type` = 'button', `url` = NULL, `permission` = 'user:delete', `parent_id` = 2, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 6;
UPDATE `dblog`.`sys_resources` SET `name` = '分配用户角色', `type` = 'button', `url` = NULL, `permission` = 'user:allotRole', `parent_id` = 2, `sort` = 6, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 7;
UPDATE `dblog`.`sys_resources` SET `name` = '权限管理', `type` = 'menu', `url` = '', `permission` = '', `parent_id` = 0, `sort` = 3, `external` = 0, `available` = 1, `icon` = 'fa fa-cogs', `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-08-24 14:11:10' WHERE `id` = 8;
UPDATE `dblog`.`sys_resources` SET `name` = '资源管理', `type` = 'menu', `url` = '/resources', `permission` = 'resources', `parent_id` = 8, `sort` = 1, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 9;
UPDATE `dblog`.`sys_resources` SET `name` = '新增资源', `type` = 'button', `url` = NULL, `permission` = 'resource:add', `parent_id` = 9, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 10;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除资源', `type` = 'button', `url` = NULL, `permission` = 'resource:batchDelete', `parent_id` = 9, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 11;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑资源', `type` = 'button', `url` = NULL, `permission` = 'resource:edit,resource:get', `parent_id` = 9, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 12;
UPDATE `dblog`.`sys_resources` SET `name` = '删除资源', `type` = 'button', `url` = NULL, `permission` = 'resource:delete', `parent_id` = 9, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 13;
UPDATE `dblog`.`sys_resources` SET `name` = '角色管理', `type` = 'menu', `url` = '/roles', `permission` = 'roles', `parent_id` = 8, `sort` = 2, `external` = 0, `available` = 1, `icon` = '', `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 14;
UPDATE `dblog`.`sys_resources` SET `name` = '新增角色', `type` = 'button', `url` = NULL, `permission` = 'role:add', `parent_id` = 14, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 15;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除角色', `type` = 'button', `url` = NULL, `permission` = 'role:batchDelete', `parent_id` = 14, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 16;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑角色', `type` = 'button', `url` = NULL, `permission` = 'role:edit,role:get', `parent_id` = 14, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 17;
UPDATE `dblog`.`sys_resources` SET `name` = '删除角色', `type` = 'button', `url` = NULL, `permission` = 'role:delete', `parent_id` = 14, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 18;
UPDATE `dblog`.`sys_resources` SET `name` = '分配角色资源', `type` = 'button', `url` = NULL, `permission` = 'role:allotResource', `parent_id` = 14, `sort` = 6, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 19;
UPDATE `dblog`.`sys_resources` SET `name` = '文章管理', `type` = 'menu', `url` = '', `permission` = '', `parent_id` = 0, `sort` = 1, `external` = 0, `available` = 1, `icon` = 'fa fa-list', `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-08-24 14:10:25' WHERE `id` = 20;
UPDATE `dblog`.`sys_resources` SET `name` = '文章列表', `type` = 'menu', `url` = '/articles', `permission` = 'articles', `parent_id` = 20, `sort` = 1, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 21;
UPDATE `dblog`.`sys_resources` SET `name` = '发表文章', `type` = 'button', `url` = NULL, `permission` = 'article:publish', `parent_id` = 21, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 22;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除文章', `type` = 'button', `url` = NULL, `permission` = 'article:batchDelete', `parent_id` = 21, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 23;
UPDATE `dblog`.`sys_resources` SET `name` = '批量推送文章', `type` = 'button', `url` = NULL, `permission` = 'article:batchPush', `parent_id` = 21, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 24;
UPDATE `dblog`.`sys_resources` SET `name` = '推送文章', `type` = 'button', `url` = NULL, `permission` = 'article:push', `parent_id` = 21, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 25;
UPDATE `dblog`.`sys_resources` SET `name` = '置顶文章', `type` = 'button', `url` = NULL, `permission` = 'article:top', `parent_id` = 21, `sort` = 6, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 26;
UPDATE `dblog`.`sys_resources` SET `name` = '推荐文章', `type` = 'button', `url` = NULL, `permission` = 'article:recommend', `parent_id` = 21, `sort` = 7, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 27;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑文章', `type` = 'button', `url` = NULL, `permission` = 'article:edit,article:get', `parent_id` = 21, `sort` = 8, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 28;
UPDATE `dblog`.`sys_resources` SET `name` = '删除文章', `type` = 'button', `url` = NULL, `permission` = 'article:delete', `parent_id` = 21, `sort` = 9, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 29;
UPDATE `dblog`.`sys_resources` SET `name` = '分类列表', `type` = 'menu', `url` = '/article/types', `permission` = 'types', `parent_id` = 20, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 30;
UPDATE `dblog`.`sys_resources` SET `name` = '添加分类', `type` = 'button', `url` = NULL, `permission` = 'type:add', `parent_id` = 30, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 31;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除分类', `type` = 'button', `url` = NULL, `permission` = 'type:batchDelete', `parent_id` = 30, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 32;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑分类', `type` = 'button', `url` = NULL, `permission` = 'type:edit,type:get', `parent_id` = 30, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 33;
UPDATE `dblog`.`sys_resources` SET `name` = '删除分类', `type` = 'button', `url` = NULL, `permission` = 'type:delete', `parent_id` = 30, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 34;
UPDATE `dblog`.`sys_resources` SET `name` = '标签列表', `type` = 'menu', `url` = '/article/tags', `permission` = 'tags', `parent_id` = 20, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 35;
UPDATE `dblog`.`sys_resources` SET `name` = '添加标签', `type` = 'button', `url` = NULL, `permission` = 'tag:add', `parent_id` = 35, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 36;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除标签', `type` = 'button', `url` = NULL, `permission` = 'tag:batchDelete', `parent_id` = 35, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 37;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑标签', `type` = 'button', `url` = NULL, `permission` = 'tag:edit,tag:get', `parent_id` = 35, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 38;
UPDATE `dblog`.`sys_resources` SET `name` = '删除标签', `type` = 'button', `url` = NULL, `permission` = 'tag:delete', `parent_id` = 35, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 39;
UPDATE `dblog`.`sys_resources` SET `name` = '网站管理', `type` = 'menu', `url` = '', `permission` = '', `parent_id` = 0, `sort` = 2, `external` = 0, `available` = 1, `icon` = 'fa fa-globe', `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-08-24 14:10:45' WHERE `id` = 40;
UPDATE `dblog`.`sys_resources` SET `name` = '友情链接', `type` = 'menu', `url` = '/links', `permission` = 'links', `parent_id` = 40, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 41;
UPDATE `dblog`.`sys_resources` SET `name` = '添加友情链接', `type` = 'button', `url` = NULL, `permission` = 'link:add', `parent_id` = 41, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 42;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除友情链接', `type` = 'button', `url` = NULL, `permission` = 'link:batchDelete', `parent_id` = 41, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 43;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑友情链接', `type` = 'button', `url` = NULL, `permission` = 'link:edit,link:get', `parent_id` = 41, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 44;
UPDATE `dblog`.`sys_resources` SET `name` = '删除友情链接', `type` = 'button', `url` = NULL, `permission` = 'link:delete', `parent_id` = 41, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 45;
UPDATE `dblog`.`sys_resources` SET `name` = '评论管理', `type` = 'menu', `url` = '/comments', `permission` = 'comments', `parent_id` = 40, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 46;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除评论', `type` = 'button', `url` = NULL, `permission` = 'comment:batchDelete', `parent_id` = 46, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 47;
UPDATE `dblog`.`sys_resources` SET `name` = '回复评论', `type` = 'button', `url` = NULL, `permission` = 'comment:reply', `parent_id` = 46, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 48;
UPDATE `dblog`.`sys_resources` SET `name` = '审核评论', `type` = 'button', `url` = NULL, `permission` = 'comment:audit', `parent_id` = 46, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 49;
UPDATE `dblog`.`sys_resources` SET `name` = '删除评论', `type` = 'button', `url` = NULL, `permission` = 'comment:delete', `parent_id` = 46, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 50;
UPDATE `dblog`.`sys_resources` SET `name` = '模板管理', `type` = 'menu', `url` = '/templates', `permission` = 'templates', `parent_id` = 40, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 51;
UPDATE `dblog`.`sys_resources` SET `name` = '添加模板', `type` = 'button', `url` = NULL, `permission` = 'template:add', `parent_id` = 51, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 52;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除模板', `type` = 'button', `url` = NULL, `permission` = 'template:batchDelete', `parent_id` = 51, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 53;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑模板', `type` = 'button', `url` = NULL, `permission` = 'template:edit,template:get', `parent_id` = 51, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 54;
UPDATE `dblog`.`sys_resources` SET `name` = '删除模板', `type` = 'button', `url` = NULL, `permission` = 'template:delete', `parent_id` = 51, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 55;
UPDATE `dblog`.`sys_resources` SET `name` = '更新日志', `type` = 'menu', `url` = '/updates', `permission` = 'updateLogs', `parent_id` = 40, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 56;
UPDATE `dblog`.`sys_resources` SET `name` = '添加更新日志', `type` = 'button', `url` = NULL, `permission` = 'updateLog:add', `parent_id` = 51, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 57;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除更新日志', `type` = 'button', `url` = NULL, `permission` = 'updateLog:batchDelete', `parent_id` = 51, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 58;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑更新日志', `type` = 'button', `url` = NULL, `permission` = 'updateLog:edit,updateLog:get', `parent_id` = 51, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 59;
UPDATE `dblog`.`sys_resources` SET `name` = '删除更新日志', `type` = 'button', `url` = NULL, `permission` = 'updateLog:delete', `parent_id` = 51, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 60;
UPDATE `dblog`.`sys_resources` SET `name` = '公告管理', `type` = 'menu', `url` = '/notices', `permission` = 'notices', `parent_id` = 40, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 61;
UPDATE `dblog`.`sys_resources` SET `name` = '添加公告', `type` = 'button', `url` = NULL, `permission` = 'notice:add', `parent_id` = 61, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 62;
UPDATE `dblog`.`sys_resources` SET `name` = '批量删除公告', `type` = 'button', `url` = NULL, `permission` = 'notice:batchDelete', `parent_id` = 61, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 63;
UPDATE `dblog`.`sys_resources` SET `name` = '编辑公告', `type` = 'button', `url` = NULL, `permission` = 'notice:edit,notice:get', `parent_id` = 61, `sort` = 4, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 64;
UPDATE `dblog`.`sys_resources` SET `name` = '删除公告', `type` = 'button', `url` = NULL, `permission` = 'notice:delete', `parent_id` = 61, `sort` = 5, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 65;
UPDATE `dblog`.`sys_resources` SET `name` = '发布公告', `type` = 'button', `url` = NULL, `permission` = 'notice:release', `parent_id` = 61, `sort` = 6, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 66;
UPDATE `dblog`.`sys_resources` SET `name` = '撤回公告', `type` = 'button', `url` = NULL, `permission` = 'notice:withdraw', `parent_id` = 61, `sort` = 7, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 67;
UPDATE `dblog`.`sys_resources` SET `name` = '测试页面', `type` = 'menu', `url` = '', `permission` = '', `parent_id` = 0, `sort` = 6, `external` = 0, `available` = 1, `icon` = 'fa fa-desktop', `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-08-24 14:11:42' WHERE `id` = 68;
UPDATE `dblog`.`sys_resources` SET `name` = 'icons图标', `type` = 'menu', `url` = '/icons', `permission` = 'icons', `parent_id` = 68, `sort` = 2, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 69;
UPDATE `dblog`.`sys_resources` SET `name` = 'shiro测试', `type` = 'menu', `url` = '/shiro', `permission` = 'shiro', `parent_id` = 68, `sort` = 3, `external` = 0, `available` = 1, `icon` = NULL, `create_time` = '2018-05-22 10:08:05', `update_time` = '2018-05-22 10:08:05' WHERE `id` = 70;
UPDATE `dblog`.`sys_resources` SET `name` = '推送消息', `type` = 'menu', `url` = '/notice', `permission` = 'notice', `parent_id` = 40, `sort` = NULL, `external` = 0, `available` = 1, `icon` = '', `create_time` = '2018-06-26 16:16:51', `update_time` = '2018-06-26 16:16:51' WHERE `id` = 71;


# 2018-10-10 新增sys_log表
-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '已登录用户ID',
  `type` enum('SYSTEM','VISIT','ERROR') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'SYSTEM' COMMENT '日志类型（系统操作日志，用户访问日志，异常记录日志）',
  `log_level` enum('ERROR','WARN','INFO') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'INFO' COMMENT '日志级别',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志内容（业务操作）',
  `spider_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爬虫类型（当访问者被鉴定为爬虫时该字段表示爬虫的类型）',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户的ip',
  `ua` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户的user_agent',
  `os` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时的系统类型',
  `browser` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时的浏览器类型',
  `request_url` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的路径',
  `referer` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求来源地址',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


