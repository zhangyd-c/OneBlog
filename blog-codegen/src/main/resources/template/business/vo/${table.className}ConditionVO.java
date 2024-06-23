<#include "/blog-codegen/src/main/resources/template/java_copyright.include"/>
<#include "/blog-codegen/src/main/resources/template/macro.include"/>
package ${basePackage}.business.vo;

import ${basePackage}.framework.object.BaseConditionVO;
import ${basePackage}.business.entity.${table.className}Bo;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#assign po = table.classNameFirstLower>

<#include "/blog-codegen/src/main/resources/template/annotation.include"/>
@Data
@EqualsAndHashCode(callSuper = false)
public class ${table.className}ConditionVO extends BaseConditionVO {

}

