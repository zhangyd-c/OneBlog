<#include "/blog-codegen/src/main/resources/template/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.business.service;


import ${basePackage}.framework.object.AbstractService;
import ${basePackage}.business.entity.${table.className}Bo;
import ${basePackage}.business.vo.${table.className}ConditionVO;
import com.github.pagehelper.PageInfo;

<#include "/blog-codegen/src/main/resources/template/annotation.include"/>
public interface ${table.className}Service extends AbstractService<${table.className}Bo, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<${table.className}Bo> findPageBreakByCondition(${table.className}ConditionVO vo);
}
