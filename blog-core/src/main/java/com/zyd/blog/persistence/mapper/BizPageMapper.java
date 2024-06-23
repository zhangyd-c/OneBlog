package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.vo.PageConditionVO;
import com.zyd.blog.persistence.beans.BizPage;
import com.zyd.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface BizPageMapper extends BaseMapper<BizPage> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<BizPage> findPageBreakByCondition(PageConditionVO vo);
}
