
package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.vo.BizAdConditionVO;
import com.zyd.blog.persistence.beans.BizAd;
import com.zyd.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 *
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2021/10/27 16:31
 * @since 1.8
 */
@Repository
public interface BizAdMapper extends BaseMapper<BizAd>{

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<BizAd> findPageBreakByCondition(BizAdConditionVO vo);
}
