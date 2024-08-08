package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.dto.ArticleLookDto;
import com.zyd.blog.business.vo.ArticleLookConditionVO;
import com.zyd.blog.business.vo.CommentConditionVO;
import com.zyd.blog.persistence.beans.BizArticleLook;
import com.zyd.blog.persistence.beans.BizGroupCount;
import com.zyd.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface BizArticleLookMapper extends BaseMapper<BizArticleLook>{

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<BizArticleLook> findPageBreakByCondition(ArticleLookConditionVO vo);


    List<ArticleLookDto> findPageRecentLook(CommentConditionVO vo);

    /**
     * 根据id批量获取总数
     * @param idArr
     * @return
     */
    List<BizGroupCount> countGroupNumByIdArr(Long[] idArr);
}
