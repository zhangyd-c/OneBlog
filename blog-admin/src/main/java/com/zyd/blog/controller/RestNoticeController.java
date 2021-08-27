package com.zyd.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Notice;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.NoticeStatusEnum;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.business.service.SysNoticeService;
import com.zyd.blog.business.vo.NoticeConditionVO;
import com.zyd.blog.framework.object.PageResult;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import com.zyd.blog.util.SessionUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统通知-- 首页菜单下方滚动显示
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@RestController
@RequestMapping("/notice")
public class RestNoticeController {
    @Autowired
    private SysNoticeService noticeService;

    @RequiresPermissions("notices")
    @PostMapping("/list")
    public PageResult list(NoticeConditionVO vo) {
        PageInfo<Notice> pageInfo = noticeService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("notice:add")
    @PostMapping(value = "/add")
    @BussinessLog("添加公告通知")
    public ResponseVO add(Notice notice) {
        User user = SessionUtil.getUser();
        if (null != user) {
            notice.setUserId(user.getId());
        }
        noticeService.insert(notice);
        return ResultUtil.success("系统通知添加成功");
    }

    @RequiresPermissions(value = {"notice:batchDelete", "notice:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    @BussinessLog("删除公告通知")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            noticeService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个系统通知");
    }

    @RequiresPermissions("notice:get")
    @PostMapping("/get/{id}")
    @BussinessLog("获取公告通知详情")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.noticeService.getByPrimaryKey(id));
    }

    @RequiresPermissions("notice:edit")
    @PostMapping("/edit")
    @BussinessLog("编辑公告通知")
    public ResponseVO edit(Notice notice) {
        try {
            noticeService.updateSelective(notice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("系统通知修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

    @RequiresPermissions("notice:release")
    @PostMapping("/release/{id}")
    @BussinessLog("发布公告通知")
    public ResponseVO release(@PathVariable Long id) {
        try {
            Notice notice = new Notice();
            notice.setId(id);
            notice.setStatus(NoticeStatusEnum.RELEASE.toString());
            noticeService.updateSelective(notice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("通知发布失败，状态不变！");
        }
        return ResultUtil.success("该通知已发布，可去前台页面查看效果！");
    }

    @RequiresPermissions("notice:withdraw")
    @PostMapping("/withdraw/{id}")
    @BussinessLog("撤回公告通知")
    public ResponseVO withdraw(@PathVariable Long id) {
        try {
            Notice notice = new Notice();
            notice.setId(id);
            notice.setStatus(NoticeStatusEnum.NOT_RELEASE.toString());
            noticeService.updateSelective(notice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("通知撤回失败，状态不变！");
        }
        return ResultUtil.success("该通知已撤回，可修改后重新发布！");
    }

}
