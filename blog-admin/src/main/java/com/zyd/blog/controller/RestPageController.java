/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2021
 */
package com.zyd.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Page;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.business.service.BizPageService;
import com.zyd.blog.business.vo.PageConditionVO;
import com.zyd.blog.framework.object.PageResult;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yadong.zhang email:yadong.zhang(a)innodev.com.cn
 * @version 1.0
 * @date 2021/10/23 00:19
 * @since 1.0
 */
@RestController
@RequestMapping("/page")
public class RestPageController {
    @Autowired
    private BizPageService bizPageService;

    @RequiresPermissions("page")
    @PostMapping("/list")
    public PageResult list(PageConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<Page> pageInfo = bizPageService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("page:add")
    @PostMapping(value = "/add")
    public ResponseVO add(Page page) {
        Page dbPage = bizPageService.getByUrl(page.getUrl());
        if (dbPage != null) {
            return ResultUtil.error(page.getUrl() + " 已存在！");
        }
        bizPageService.insert(page);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"page:batchDelete", "page:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            bizPageService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 条记录");
    }

    @RequiresPermissions("page:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.bizPageService.getByPrimaryKey(id));
    }

    @RequiresPermissions("page:edit")
    @PostMapping("/edit")
    public ResponseVO edit(Page page) {
        try {
            Page dbPage = bizPageService.getByPrimaryKey(page.getId());
            if (null != dbPage && !dbPage.getUrl().equals(page.getUrl())) {
                dbPage = bizPageService.getByUrl(page.getUrl());
                if (dbPage != null) {
                    return ResultUtil.error(page.getUrl() + " 已存在！");
                }
            }
            bizPageService.updateSelective(page);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("数据修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
