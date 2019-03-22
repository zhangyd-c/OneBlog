package com.zyd.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Log;
import com.zyd.blog.business.enums.PlatformEnum;
import com.zyd.blog.business.vo.LogConditionVO;
import com.zyd.blog.framework.object.AbstractService;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/01/09 17:40
 * @since 1.0
 */
public interface SysLogService extends AbstractService<Log, Integer> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Log> findPageBreakByCondition(LogConditionVO vo);

    void asyncSaveSystemLog(PlatformEnum platform, String bussinessName);
}
