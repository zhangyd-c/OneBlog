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






