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






