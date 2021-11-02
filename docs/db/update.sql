###################################  readme  ###################################
#
#  该文件中的sql已同步更新到了dblog.sql中，本文件存在的目的主要是记录数据库修改的内容
#  在每次更新时，凡是涉及到修改数据库的，如果是第一次使用该系统，则只需要导入dblog.sql后执行init_data.sql即可
#  如果你已经在使用dblog了，则只需要复制本文件中的相关sql去执行即可，避免了直接执行dblog.sql或者init_data.sql可能会覆盖本地修改内容的问题
#
###################################  readme  ###################################


# 20211027
-- ----------------------------
-- Table structure for biz_ad
-- ----------------------------
DROP TABLE IF EXISTS `biz_ad`;
CREATE TABLE `biz_ad`  (
   `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
   `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告类型',
   `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位置',
   `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告标题',
   `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告内容',
   `picture` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告图片',
   `link` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告链接',
   `expiring_date` datetime(0) NULL DEFAULT NULL COMMENT '广告到期日',
   `show_number` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '展示次数',
   `click_number` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '点击次数',
   `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
   `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告' ROW_FORMAT = Compact;

INSERT INTO `dblog`.`sys_resources` VALUES (86, '广告管理', 'menu', '/bizAd', 'bizAds', 40, 7, 0, 1, '', now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (87, '新增广告', 'button', NULL, 'bizAd:add', 86, 2, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (88, '批量删除广告', 'button', NULL, 'bizAd:batchDelete', 86, 3, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (89, '编辑广告', 'button', NULL, 'bizAd:edit,bizAd:get', 86, 4, 0, 1, NULL, now(), now());
INSERT INTO `dblog`.`sys_resources` VALUES (90, '删除广告', 'button', NULL, 'bizAd:delete', 86, 5, 0, 1, NULL, now(), now());

# 20211028
ALTER TABLE `dblog`.`biz_article` ADD COLUMN `editor_type` varchar(10) NULL COMMENT '当前文章适用的编辑器类型' AFTER `cover_image`;
# 修改旧文章的编辑器类型
UPDATE `dblog`.`biz_article` SET `editor_type` = 'we' WHERE is_markdown is null || is_markdown = 0;
UPDATE `dblog`.`biz_article` SET `editor_type` = 'md' WHERE is_markdown = 1;


# 20211101
ALTER TABLE `dblog`.`biz_article` ADD COLUMN `required_auth` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '该文章是否登录后才可访问' AFTER `password`;
ALTER TABLE `dblog`.`biz_type` ADD COLUMN `position` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类在web端显示的位置，可选：nav、scrollmenu' AFTER `icon`;
UPDATE `dblog`.`biz_type` SET `position` = 'nav';
