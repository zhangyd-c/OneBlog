package com.zyd.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.dto.SysNoticeDTO;
import com.zyd.blog.business.entity.Notice;
import com.zyd.blog.business.vo.NoticeConditionVO;
import com.zyd.blog.framework.object.AbstractService;

import java.util.List;

/**
 * 系统通知
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysNoticeService extends AbstractService<Notice, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Notice> findPageBreakByCondition(NoticeConditionVO vo);

    /**
     * 获取已发布的通知列表
     *
     * @return
     */
    List<SysNoticeDTO> listRelease();
}
