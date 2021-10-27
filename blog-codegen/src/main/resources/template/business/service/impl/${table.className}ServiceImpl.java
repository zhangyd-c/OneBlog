<#include "/blog-codegen/src/main/resources/template/java_copyright.include"/>
<#include "/blog-codegen/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.business.entity.${table.className}Bo;
import ${basePackage}.business.service.${table.className}Service;
import ${basePackage}.business.vo.${table.className}ConditionVO;
import ${basePackage}.persistence.beans.${table.className};
import ${basePackage}.persistence.mapper.${table.className}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

<#include "/blog-codegen/src/main/resources/template/annotation.include"/>
@Service
public class ${table.className}ServiceImpl implements ${table.className}Service{

    @Autowired
    private ${table.className}Mapper ${classNameLower}Mapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<${table.className}Bo> findPageBreakByCondition(${table.className}ConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<${table.className}> list = ${classNameLower}Mapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<${table.className}Bo> boList = new ArrayList<>();
        for (${table.className} ${className?uncap_first} : list){
            boList.add(new ${table.className}Bo(${className?uncap_first}));
        }
        PageInfo bean = new PageInfo<${table.className}>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.className}Bo insert(${table.className}Bo entity) {
        Assert.notNull(entity, "${table.className}不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        ${classNameLower}Mapper.insertSelective(entity.get${table.className}());
        return entity;
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return ${classNameLower}Mapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(${table.className}Bo entity) {
        Assert.notNull(entity, "${table.className}不可为空！");
        entity.setUpdateTime(new Date());
        return ${classNameLower}Mapper.updateByPrimaryKeySelective(entity.get${table.className}()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public ${table.className}Bo getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        ${table.className} entity = ${classNameLower}Mapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new ${table.className}Bo(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<${table.className}Bo> listAll() {
        List<${table.className}> entityList = ${classNameLower}Mapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<${table.className}Bo> list = new ArrayList<>();
        for (${table.className} entity : entityList) {
            list.add(new ${table.className}Bo(entity));
        }
        return list;
    }
}
