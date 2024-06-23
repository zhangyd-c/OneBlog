/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2021
 */
package com.zyd.blog.business.service;


import com.zyd.blog.framework.object.AbstractService;
import com.zyd.blog.business.entity.Page;
import com.zyd.blog.business.vo.PageConditionVO;
import com.github.pagehelper.PageInfo;

/**
 * @author yadong.zhang email:yadong.zhang(a)innodev.com.cn
 * @version 1.0
 * @date 2021/10/23 00:19
 * @since 1.0
 */
public interface BizPageService extends AbstractService<Page, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Page> findPageBreakByCondition(PageConditionVO vo);

    Page getByUrl(String url);
}
