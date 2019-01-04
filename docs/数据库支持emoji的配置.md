## 一，改数据库编码
以下两种方案任选一
ps: 修改数据库配置可以一劳永逸，直接sql修改当机器重启时新加的设置会被还原

### 1.修改数据库配置文件
windows下的为my.ini(linux下的为my.cnf)
windows下的my.ini路径：C:\ProgramData\MySQL\MySQL Server 5.6

```bash
[client]
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqld]
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
```

## 2.修改数据库、表、字段的编码为utf8m64

```sql
-- 修改数据库:
ALTER DATABASE `dblog` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- 修改表:
ALTER TABLE `biz_article` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- 修改表字段:
ALTER TABLE `biz_article` CHANGE `content` content LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '文章内容';
ALTER TABLE `biz_article` CHANGE `content_md` content_md LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT 'markdown版的文章内容';
```

### 二、添加配置
```
spring:
    datasource:
        druid:
            connection-init-sqls: set names utf8mb4
```