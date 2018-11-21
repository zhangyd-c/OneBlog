package com.zyd.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Comment;
import com.zyd.blog.business.vo.CommentConditionVO;
import com.zyd.blog.framework.exception.ZhydCommentException;

import java.util.List;
import java.util.Map;

/**
 * 评论
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface BizCommentService {

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    Comment insert(Comment entity);

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    void insertList(List<Comment> entities);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    boolean removeByPrimaryKey(Long primaryKey);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    boolean update(Comment entity);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    boolean updateSelective(Comment entity);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    Comment getByPrimaryKey(Long primaryKey);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    Comment getOneByEntity(Comment entity);

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    List<Comment> listAll();

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    List<Comment> listByEntity(Comment entity);

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Comment> findPageBreakByCondition(CommentConditionVO vo);

    /**
     * @param vo
     * @return
     */
    Map<String, Object> list(CommentConditionVO vo);

    /**
     * admin发表评论
     *
     * @param comment
     * @return
     */
    void commentForAdmin(Comment comment) throws ZhydCommentException;

    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    Comment comment(Comment comment) throws ZhydCommentException;

    /**
     * 查询近期评论
     *
     * @param pageSize
     * @return
     */
    List<Comment> listRecentComment(int pageSize);

    /**
     * 查询未审核的评论
     *
     * @param pageSize
     * @return
     */
    List<Comment> listVerifying(int pageSize);

    /**
     * 点赞
     *
     * @param id
     */
    void doSupport(Long id);

    /**
     * 点踩
     *
     * @param id
     */
    void doOppose(Long id);
}
