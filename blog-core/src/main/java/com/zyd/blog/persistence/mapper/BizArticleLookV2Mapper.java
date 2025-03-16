package com.zyd.blog.persistence.mapper;

import com.zyd.blog.persistence.beans.BizArticleLookV2;
import com.zyd.blog.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface BizArticleLookV2Mapper extends BaseMapper<BizArticleLookV2> {

    int increment(@Param("articleId") Long articleId);
}
