package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.vo.ArticleConditionVO;
import com.zyd.blog.persistence.beans.BizArticle;
import com.zyd.blog.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface BizArticleMapper extends BaseMapper<BizArticle> {

    /**
     * 分页查询，关联查询文章标签、文章类型
     *
     * @param vo
     * @return
     */
    List<BizArticle> findPageBreakByCondition(ArticleConditionVO vo);

    /**
     * 统计指定文章的标签集合
     *
     * @param list
     * @return
     */
    List<BizArticle> listTagsByArticleId(List<Long> list);

    /**
     * 获取文章详情，关联查询文章标签、文章类型
     *
     * @param id
     * @return
     */
    BizArticle get(Long id);

    /**
     * 获取上一篇和下一篇(是否可以通过get时查出来？ BizArticle中关联两个BizArticle：prev & next)
     *
     * @param insertTime
     * @return
     */
    List<BizArticle> getPrevAndNextArticles(Date insertTime);

    /**
     * 获取热门文章
     *
     * @return
     */
    List<BizArticle> listHotArticle();

    /**
     * 是否存在文章
     *
     * @param id
     * @return
     */
    Integer isExist(Long id);

    /**
     * 批量修改status
     *
     * @param list
     * @param status
     * @return
     */
    int batchUpdateStatus(@Param("list") List<Long> list, @Param("status") boolean status);

}
