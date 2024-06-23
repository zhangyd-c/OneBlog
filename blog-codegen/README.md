## oneblog 代码生成工具使用方式

1. 修改启动类的配置信息：`blog-codegen/src/main/java/com/zyd/blog/codegen/BlogCodegenApplication.java`，一般只需要修改数据库链接地址、数据库用户名和密码即可。
2. 运行启动类
3. 运行成功后会在 `blog-codegen` 目录下生成 `generator-output` 目录
4. 将 `generator-output` 目录中的文件按照目录格式 copy 到项目指定目录下
5. `generator-output/sql` 为数据库命令，执行后才可以在后台菜单中使用，按照 `update.sql` 文件中的说明进行修改，修改完成后 copy 到 MySQL 命令行中执行

注意：

1. `generator-output` 目录下会有一个和数据表名一致的驼峰式命名的文件夹，这个是 `blog-admin` 中的页面文件，将这个文件夹整个复制到 `blog-admin/src/main/resources/templates` 目录下即可
2. `generator-output/controller` 目录下有两个文件，一个是（`Rest` 开头的文件）定义了 `RestAPI` 的 `Controller`，另一个是用于跳转页面的（`Render` 开头的）。`Render`文件中只有一个声明好的 API 方法，将这个文件中的内容复制到 `blog-admin/src/main/java/com/zyd/blog/controller/RenderController.java` 中即可
3. **切记：非特殊情况，最终生成的所有文件的内容，不要随意修改，以防出现异常！**


## 定制模板

`blog-codegen` 中已经内置好了适用于本项目的模板，理论上不需要修改，如果开发者有特殊需求，请自行定制模板。

友情提示：模板是基于 `freemarker` 的，关于 Freemarker 的语法请参考 [springboot整合Freemark模板(修订-详尽版)](https://www.imooc.com/article/258641)

### 模板配置项

|       配置项        |        类型        |        示例        |        解释        |       备注        |         
|:---------------------------|:---------|:---------|:---------||:---------|    
| `classPrefix` | `String` | Light | 生成的类前缀，先去除 clearClassPrefix 再添加 classPrefix |  |    
| `clearClassPrefix` | `String` | Light | 需要去除的类前缀，先去除 clearClassPrefix 再添加 classPrefix |  |    
| `driver` | `String` | com.mysql.cj.jdbc.Driver |  |   |   
| `url` | `String` | jdbc:mysql://127.0.0.1:3306/light |  |  |    
| `db` | `String` | light | 选择的数据库，非必填，如果为空会从url中解析 |  |    
| `username` | `String` | root | 数据库用户名 |  |    
| `password` | `String` | root | 数据库密码 |  |    
| `basePackage` | `String` | me.zhyd.xx.core | 根包名 |     |    
| `beansPackage` | `String` | me.zhyd.light.core.db.persistence.beans | beans包名 |  |    
| `mapperPackage` | `String` | me.zhyd.xx.core.db.persistence.mapper | mapper接口包名 |  |    
| `outRootDir` | `String` | D:\project\generator\generator-output | 文件输出路径 |  |    
| `templatePath` | `String` | template/ | 模板路径，基于当前项目路径 |  |    
| `table` | `JSONObject` | object | 表信息 |  |


### `table` 配置项

|       配置项        |        类型        |        示例        |        解释        |       备注        |         
|:---------------------------|:---------|:---------|:---------||:---------|    
| `type` | `String` | BASE TABLE | 数据表类型 |  |    
| `db` | `String` | light | 数据库名 |  |    
| `tableName` | `String` | links | 数据表名 |  |   
| `coding` | `String` | utf8mb4_general_ci | 数据表编码 |  |    
| `createTime` | `String` | 2020-04-26 13:25:37 | 数据表创建时间 |  |    
| `engine` | `String` | InnoDB | 数据表引擎 |  |    
| `remark` | `String` |  | 数据表注释 |  |    
| `className` | `String` | LightLinks | 待生成的类名 |  |    
| `classNameFirstLower` | `String` | lightLinks | 首字母小写的类名 |  |    
| `classNameFirstUpper` | `String` | LightLinks | 首字母大写的类名 |  |    
| `columns` | `JSONArray` | array | 列信息 |  |

### `table.columns` 配置项

|       配置项        |        类型        |        示例        |        解释        |       备注        |         
|:---------------------------|:---------|:---------|:---------||:---------|    
| `tableName` | `String` | links | 表名 |  |    
| `keyType` | `String` | PRI | 键类型（PRI 主键） |  |    
| `columnName` | `String` | id | 列名 |  |     
| `columnNameFirstLower` | `String` | id | 列名首字母小写 |  |    
| `columnNameFirstUpper` | `String` | Id | 列名首字母大写 |  |    
| `notNull` | `Boolean` | true | 是否允许为空 |  |    
| `length` | `Integer` | 0 | 长度 |  |    
| `scale` | `Integer` | 0 | 精度，当为浮点数字类型时可用 |  |    
| `remark` | `String` |  | 列注释 |  |    
| `columnType` | `String` | bigint | 列对应的数据库数据类型 |  |    
| `jdbcType` | `String` | BIGINT | 列对应的JDBC数据类型 |  |    
| `javaType` | `String` | java.lang.String | 列对应的Java数据类型 |  |    
| `possibleShortJavaType` | `String` | String |  |  |    
| `extra` | `String` | auto_increment | 额外参数，auto_increment表自增 |  |

### 模板可用完整参数示例 JSON

```json
{
	"primaryKeyType": "String",
	"classPrefix": "",
	"basePackage": "com.zyd.blog",
	"mapperPackage": "com.zyd.blog.persistence.mapper",
	"templatePath": "template/",
	"url": "jdbc:mysql://127.0.0.1:3306/dblog?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true&useLegacyDatetimeCode=false",
	"clearClassPrefix": "",
	"password": "root",
	"driver": "com.mysql.cj.jdbc.Driver",
	"outRootDir": "D:\\project\\java\\gitee\\OneBlog\\blog-codegen\\generator-output",
	"db": "dblog",
	"table": {
		"className": "BizAd",
		"classNameFirstLower": "bizAd",
		"classNameFirstUpper": "BizAd",
		"coding": "utf8_general_ci",
		"columns": [{
			"columnName": "id",
			"columnNameFirstLower": "id",
			"columnNameFirstUpper": "Id",
			"columnType": "bigint",
			"extra": "auto_increment",
			"javaType": "java.lang.Long",
			"jdbcType": "BIGINT",
			"keyType": "PRI",
			"length": 0,
			"notNull": true,
			"possibleShortJavaType": "Long",
			"remark": "",
			"tableName": "biz_ad"
		}, {
			"columnName": "content",
			"columnNameFirstLower": "content",
			"columnNameFirstUpper": "Content",
			"columnType": "varchar",
			"extra": "",
			"javaType": "java.lang.String",
			"jdbcType": "VARCHAR",
			"keyType": "",
			"length": 300,
			"notNull": false,
			"possibleShortJavaType": "String",
			"remark": "广告内容（简介）",
			"tableName": "biz_ad"
		}],
		"createTime": "2021-10-27 16:56:13",
		"db": "dblog",
		"engine": "InnoDB",
		"remark": "广告",
		"tableName": "biz_ad",
		"type": "BASE TABLE"
	},
	"beansPackage": "com.zyd.blog.persistence.beans",
	"username": "root"
}
```
