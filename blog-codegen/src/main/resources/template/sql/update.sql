<#assign po = table.classNameFirstLower>
# 新添加的业务默认都归到了 【网站管理（ID：40）】节点下
## 第一条的第6个列值（40）表示 【网站管理（ID：40）】 的 ID，一般不需要修改，如果需要修改，请自行操作
## 第一条的第1个列值为这个业务功能对应权限的根 ID，建议修改为 sys_resources 表最后一条记录的 ID + 1
INSERT INTO `${table.db}`.`sys_resources` VALUES (200, '${table.remark}管理', 'menu', '/${po}', '${po}s', 40, 7, 0, 1, '', now(), now());

## 剩余4条记录的 第6个列值填写上面一条父记录的 ID，参考本例应都为200
INSERT INTO `${table.db}`.`sys_resources` VALUES (201, '新增${table.remark}', 'button', NULL, '${po}:add', 200, 2, 0, 1, NULL, now(), now());
INSERT INTO `${table.db}`.`sys_resources` VALUES (202, '批量删除${table.remark}', 'button', NULL, '${po}:batchDelete', 200, 3, 0, 1, NULL, now(), now());
INSERT INTO `${table.db}`.`sys_resources` VALUES (203, '编辑${table.remark}', 'button', NULL, '${po}:edit,${po}:get', 200, 4, 0, 1, NULL, now(), now());
INSERT INTO `${table.db}`.`sys_resources` VALUES (204, '删除${table.remark}', 'button', NULL, '${po}:delete', 200, 5, 0, 1, NULL, now(), now());
