<#include "/blog-codegen/src/main/resources/template/java_copyright.include"/>
<#include "/blog-codegen/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.persistence.beans;

import ${basePackage}.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

<#include "/blog-codegen/src/main/resources/template/annotation.include"/>
@Data
@EqualsAndHashCode(callSuper = false)
public class ${table.className} extends AbstractDO{
	<#list table.columns as column>
	<#if column.columnNameFirstLower != 'id' && column.columnNameFirstLower != 'insertTime' && column.columnNameFirstLower != 'createTime' && column.columnNameFirstLower != 'updateTime'>
	private ${column.possibleShortJavaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
}
