package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.service.BizArticleLookService;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.persistence.beans.BizArticleLookV2;
import com.zyd.blog.persistence.mapper.BizArticleLookV2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * 文章浏览记录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizArticleLookServiceImpl implements BizArticleLookService {

    @Autowired
    private BizArticleLookV2Mapper bizArticleLookV2Mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increment(Long articleId) {
        Assert.notNull(articleId, "articleId不可为空！");
        int res = bizArticleLookV2Mapper.increment(articleId);
        if (res == 0) {
            Example example = new Example(BizArticleLookV2.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("articleId", articleId);
            BizArticleLookV2 content = bizArticleLookV2Mapper.selectOneByExample(example);
            if (null == content) {
                content = new BizArticleLookV2();
                content.setArticleId(articleId);
                content.setLookCount(1);
                content.setUpdateTime(new Date());
                content.setCreateTime(new Date());
                bizArticleLookV2Mapper.insert(content);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByArticleId(Long articleId) {
        Example example = new Example(BizArticleLookV2.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        BizArticleLookV2 content = bizArticleLookV2Mapper.selectOneByExample(example);
        if (null == content) {
            throw new ZhydException("文章浏览记录已经不存在或者已被删除");
        }
        this.removeByPrimaryKey(content.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return bizArticleLookV2Mapper.deleteByPrimaryKey(primaryKey) > 0;
    }
}
