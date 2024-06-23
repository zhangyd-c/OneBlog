<#include "/blog-codegen/src/main/resources/template/java_copyright.include"/>
<#include "/blog-codegen/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.business.entity.${table.className}Bo;
import ${basePackage}.business.enums.ResponseStatus;
import ${basePackage}.business.service.${table.className}Service;
import ${basePackage}.business.vo.${table.className}ConditionVO;
import ${basePackage}.framework.object.PageResult;
import ${basePackage}.framework.object.ResponseVO;
import ${basePackage}.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<#include "/blog-codegen/src/main/resources/template/annotation.include"/>
@RestController
@RequestMapping("/${table.classNameFirstLower}")
public class Rest${table.className}Controller {
    @Autowired
    private ${table.className}Service ${classNameLower}Service;

    @RequiresPermissions("${table.classNameFirstLower}s")
    @PostMapping("/list")
    public PageResult list(${table.className}ConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<${table.className}Bo> pageInfo = ${classNameLower}Service.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("${table.classNameFirstLower}:add")
    @PostMapping(value = "/add")
    public ResponseVO add(${table.className}Bo ${table.classNameFirstLower}) {
        ${classNameLower}Service.insert(${table.classNameFirstLower});
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"${table.classNameFirstLower}:batchDelete", "${table.classNameFirstLower}:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            ${classNameLower}Service.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 条记录");
    }

    @RequiresPermissions("${table.classNameFirstLower}:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.${classNameLower}Service.getByPrimaryKey(id));
    }

    @RequiresPermissions("${table.classNameFirstLower}:edit")
    @PostMapping("/edit")
    public ResponseVO edit(${table.className}Bo ${table.classNameFirstLower}) {
        try {
            ${classNameLower}Service.updateSelective(${table.classNameFirstLower});
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("数据修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
