package com.zyd.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Link;
import com.zyd.blog.business.vo.LinkConditionVO;
import com.zyd.blog.framework.exception.ZhydLinkException;
import com.zyd.blog.framework.object.AbstractService;

import java.util.List;
import java.util.Map;

/**
 * 友情链接
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysLinkService extends AbstractService<Link, Long> {

    Link getOneByUrl(String url);

    PageInfo<Link> findPageBreakByCondition(LinkConditionVO vo);

    /**
     * 查询可在首页显示的友情链接列表
     *
     * @return
     */
    List<Link> listOfIndex();

    /**
     * 查询可在内页显示的友情链接列表
     *
     * @return
     */
    List<Link> listOfInside();

    /**
     * 查询已禁用的友情链接列表
     *
     * @return
     */
    List<Link> listOfDisable();

    /**
     * 分组获取所有连接
     * {index:首页显示,inside:内页,disable:禁用}
     *
     * @return
     */
    Map<String, List<Link>> listAllByGroup();

    /**
     * 自动添加友链
     *
     * @param link
     * @return
     */
    boolean autoLink(Link link) throws ZhydLinkException;
}
