package com.zyd.blog.persistence.mapper;

import com.zyd.blog.persistence.beans.BizStatistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 统计
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface BizStatisticsMapper {

    List<BizStatistics> listSpider();

    List<BizStatistics> listType();

    List<BizStatistics> listArticleLookCountByArticleIds(@Param("list") List<Long> articleIds);

    List<BizStatistics> listArticleLoveCountByArticleIds(@Param("list") List<Long> articleIds);

    List<BizStatistics> listArticleCommentCountByArticleIds(@Param("list") List<Long> articleIds);
}
