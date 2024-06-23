<#include "/blog-codegen/src/main/resources/template/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.persistence.mapper;

import ${basePackage}.business.vo.${table.className}ConditionVO;
import ${basePackage}.persistence.beans.${table.className};
import ${basePackage}.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

<#include "/blog-codegen/src/main/resources/template/annotation.include"/>
@Repository
public interface ${table.className}Mapper extends BaseMapper<${table.className}>{

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<${table.className}> findPageBreakByCondition(${table.className}ConditionVO vo);
}
