<#include "/blog-codegen/src/main/resources/template/java_copyright.include"/>
<#include "/blog-codegen/src/main/resources/template/macro.include"/>
package ${basePackage}.business.entity;

import ${basePackage}.persistence.beans.${table.className};
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
<#assign po = table.classNameFirstLower>

<#include "/blog-codegen/src/main/resources/template/annotation.include"/>
public class ${table.className}Bo {
	private static final long serialVersionUID = 1L;
	private ${table.className} ${po};

	public ${table.className}Bo () {
		this.${po} = new ${table.className}();
	}

	public ${table.className}Bo (${table.className} ${po}) {
		this.${po} = ${po};
	}

	@JsonIgnore
	public ${table.className} get${table.className}(){
		return this.${po};
	}
	<#list table.columns as column>
	<#assign _javaType = column.possibleShortJavaType>

	public ${_javaType} get${column.columnNameFirstUpper}() {
		return this.${po}.get${column.columnNameFirstUpper}();
	}

	public void set${column.columnNameFirstUpper}(${_javaType} ${column.columnNameFirstLower}) {
		this.${po}.set${column.columnNameFirstUpper}(${column.columnNameFirstLower});
	}
	</#list>
}

