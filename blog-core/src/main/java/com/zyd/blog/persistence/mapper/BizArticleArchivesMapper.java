package com.zyd.blog.persistence.mapper;

import com.zyd.blog.persistence.beans.BizArticleArchives;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface BizArticleArchivesMapper {

    List<BizArticleArchives> listArchives();
}
