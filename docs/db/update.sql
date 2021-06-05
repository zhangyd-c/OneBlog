###################################  readme  ###################################
#
#  该文件中的sql已同步更新到了dblog.sql中，本文件存在的目的主要是记录数据库修改的内容
#  在每次更新时，凡是涉及到修改数据库的，如果是第一次使用该系统，则只需要导入dblog.sql后执行init_data.sql即可
#  如果你已经在使用dblog了，则只需要复制本文件中的相关sql去执行即可，避免了直接执行dblog.sql或者init_data.sql可能会覆盖本地修改内容的问题
#
###################################  readme  ###################################


# 20210423
ALTER TABLE `dblog`.`biz_article`
    ADD COLUMN `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章私密访问时的密钥' AFTER `comment`;

# 20210427
INSERT INTO `dblog`.`sys_resources` VALUES (76, '社会化登录配置管理', 'menu', '/socials', 'socials', 40, 7, 0, 1, '', now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (77, '新增社会化登录配置', 'button', NULL, 'social:add', 76, 2, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (78, '批量删除社会化登录配置', 'button', NULL, 'social:batchDelete', 76, 3, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (79, '编辑社会化登录配置', 'button', NULL, 'social:edit,social:get', 76, 4, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (80, '删除社会化登录配置', 'button', NULL, 'social:delete', 76, 5, 0, 1, NULL, now(), now());


DROP TABLE IF EXISTS `dblog`.`sys_social_config`;
CREATE TABLE `dblog`.`sys_social_config`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用ID',
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用密钥',
  `redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '认证成功后跳转的地址',
  `platform_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用名',
  `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方平台',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用 LOGO',
  `alipay_public_key` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付宝公钥',
  `union_id` bit(1) NULL DEFAULT NULL COMMENT '是否需要申请unionid',
  `stack_overflow_key` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Stack Overflow Key',
  `agent_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权方的网页应用ID',
  `scope` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权范围',
  `available` bit(1) NULL DEFAULT b'1' COMMENT '当前配置是否可用',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社会化登录应用' ROW_FORMAT = Dynamic;


# 20210606
ALTER TABLE `dblog`.`sys_config`
    MODIFY COLUMN `config_value` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配置项内容' AFTER `config_key`;
