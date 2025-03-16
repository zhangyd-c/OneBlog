###################################  readme  ###################################
#
#  该文件中的sql已同步更新到了dblog.sql中，本文件存在的目的主要是记录数据库修改的内容
#  在每次更新时，凡是涉及到修改数据库的，如果是第一次使用该系统，则只需要导入dblog.sql后执行init_data.sql即可
#  如果你已经在使用dblog了，则只需要复制本文件中的相关sql去执行即可，避免了直接执行dblog.sql或者init_data.sql可能会覆盖本地修改内容的问题
#
###################################  readme  ###################################


# 20250301
-- ----------------------------
-- Table structure for biz_article_content
-- ----------------------------
DROP TABLE IF EXISTS `biz_article_content`;
CREATE TABLE `biz_article_content`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `article_id` bigint(20) NOT NULL COMMENT '管理的文章 ID',
    `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '富文本版的文章内容',
    `content_md` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT 'markdown 版的文章内容',
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_article_id`(`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;


-- 迁移 article 表大字段的 SQL
INSERT INTO biz_article_content ( id, article_id, content, content_md, create_time, update_time ) SELECT
    0,
    id,
    content,
    content_md,
    create_time,
    update_time
FROM
    biz_article

-- 迁移完成后删除旧字段（如果要求不停服迁移，则需要在新版服务升级后再执行以下脚本）
/*ALTER TABLE `biz_article`
    DROP COLUMN `content`,
    DROP COLUMN `content_md`;*/

-- 添加索引
ALTER TABLE `biz_article`
    ADD INDEX `idx_type_id`(`type_id`) USING BTREE,
    ADD INDEX `idx_recommended`(`recommended`) USING BTREE,
    ADD INDEX `idx_update_time`(`update_time`) USING BTREE;
ALTER TABLE `biz_article_love`
    ADD INDEX `idx_article_id`(`article_id`) USING BTREE;
ALTER TABLE `biz_article_tags`
    ADD INDEX `idx_article_id`(`article_id`) USING BTREE,
    ADD INDEX `idx_tag_id`(`tag_id`) USING BTREE;
ALTER TABLE `biz_comment`
    ADD INDEX `idx_sid`(`sid`) USING BTREE,
    ADD INDEX `idx_pid`(`pid`) USING BTREE;
ALTER TABLE `biz_article_look`
    ADD INDEX `idx_article_id`(`article_id`) USING BTREE;

-- article_look 数据汇总到独立的表中，和 article 表一对一
DROP TABLE IF EXISTS `biz_article_look_v2`;
CREATE TABLE `biz_article_look_v2`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文章ID',
    `look_count` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '次数',
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_article_id`(`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- 迁移汇总后的 article_look 表的 SQL
INSERT INTO biz_article_look_v2 ( id, article_id, look_count, create_time, update_time ) SELECT
    0,
    article_id,
    count( id ),
    now(),
    now()
FROM
    biz_article_look
GROUP BY
    article_id

