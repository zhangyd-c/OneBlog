###################################  readme  ###################################
#
#  该文件中的sql已同步更新到了dblog.sql中，本文件存在的目的主要是记录数据库修改的内容
#  在每次更新时，凡是涉及到修改数据库的，如果是第一次使用该系统，则只需要导入dblog.sql后执行init_data.sql即可
#  如果你已经在使用dblog了，则只需要复制本文件中的相关sql去执行即可，避免了直接执行dblog.sql或者init_data.sql可能会覆盖本地修改内容的问题
#
###################################  readme  ###################################


# 20211023
-- ----------------------------
-- Table structure for biz_page
-- ----------------------------
DROP TABLE IF EXISTS `biz_page`;
CREATE TABLE `biz_page`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自定义页面的url（不包含域名部分）',
    `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '自定义页面的内容',
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


# 20211023
INSERT INTO `dblog`.`sys_resources` VALUES (81, '自定义页面管理', 'menu', '/page', 'page', 40, 7, 0, 1, '', now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (82, '新增自定义页面', 'button', NULL, 'page:add', 81, 2, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (83, '批量删除自定义页面', 'button', NULL, 'page:batchDelete', 81, 3, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (84, '编辑自定义页面', 'button', NULL, 'page:edit,page:get', 81, 4, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (85, '删除自定义页面', 'button', NULL, 'page:delete', 81, 5, 0, 1, NULL, now(), now());
