package com.zyd.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.BizAdBo;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.business.service.BizAdService;
import com.zyd.blog.business.vo.BizAdConditionVO;
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
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2021/10/27 16:45
 * @since 1.8
 */
@RestController
@RequestMapping("/bizAd")
public class RestBizAdController {
    @Autowired
    private BizAdService bizAdService;

    @RequiresPermissions("bizAds")
    @PostMapping("/list")
    public PageResult list(BizAdConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<BizAdBo> pageInfo = bizAdService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("bizAd:add")
    @PostMapping(value = "/add")
    public ResponseVO add(BizAdBo bizAd) {
        BizAdBo entity = bizAdService.getByPosition(bizAd.getPositionEnum());
        if (null != entity) {
            return ResultUtil.error("当前广告位已存在广告，一个广告位仅支持一条广告");
        }
        bizAdService.insert(bizAd);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"bizAd:batchDelete", "bizAd:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            bizAdService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 条记录");
    }

    @RequiresPermissions("bizAd:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.bizAdService.getByPrimaryKey(id));
    }

    @RequiresPermissions("bizAd:edit")
    @PostMapping("/edit")
    public ResponseVO edit(BizAdBo bizAd) {
        try {

            BizAdBo entity = bizAdService.getByPrimaryKey(bizAd.getId());
            if (null == entity) {
                return ResultUtil.error("广告不存在！");
            }
            if (!entity.getPosition().equals(bizAd.getPosition())) {
                entity = bizAdService.getByPosition(bizAd.getPositionEnum());
                if (null != entity) {
                    return ResultUtil.error("当前广告位已存在广告，一个广告位仅支持一条广告");
                }
            }

            bizAdService.updateSelective(bizAd);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("数据修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
