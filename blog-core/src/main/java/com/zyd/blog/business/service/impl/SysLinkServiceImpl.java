package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.entity.Config;
import com.zyd.blog.business.entity.Link;
import com.zyd.blog.business.enums.LinkSourceEnum;
import com.zyd.blog.business.enums.TemplateKeyEnum;
import com.zyd.blog.business.service.MailService;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.business.service.SysLinkService;
import com.zyd.blog.business.util.LinksUtil;
import com.zyd.blog.business.vo.LinkConditionVO;
import com.zyd.blog.framework.exception.ZhydLinkException;
import com.zyd.blog.persistence.beans.SysLink;
import com.zyd.blog.persistence.mapper.SysLinkMapper;
import com.zyd.blog.util.HtmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 友情链接
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class SysLinkServiceImpl implements SysLinkService {

    @Autowired
    private SysLinkMapper sysLinkMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private SysConfigService configService;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Link> findPageBreakByCondition(LinkConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysLink> list = sysLinkMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Link> boList = new ArrayList<>();
        for (SysLink sysLink : list) {
            boList.add(new Link(sysLink));
        }
        PageInfo bean = new PageInfo<SysLink>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * 查询可在首页显示的友情链接列表
     *
     * @return
     */
    @Override
    @RedisCache
    public List<Link> listOfIndex() {
        LinkConditionVO vo = new LinkConditionVO(1, 1);
        vo.setPageSize(100);
        PageInfo<Link> pageInfo = this.findPageBreakByCondition(vo);
        return pageInfo == null ? null : pageInfo.getList();
    }

    /**
     * 查询可在内页显示的友情链接列表
     *
     * @return
     */
    @Override
    @RedisCache
    public List<Link> listOfInside() {
        LinkConditionVO vo = new LinkConditionVO(1, 0);
        vo.setPageSize(100);
        PageInfo<Link> pageInfo = this.findPageBreakByCondition(vo);
        return pageInfo == null ? null : pageInfo.getList();
    }

    /**
     * 查询已禁用的友情链接列表
     *
     * @return
     */
    @Override
    @RedisCache
    public List<Link> listOfDisable() {
        LinkConditionVO vo = new LinkConditionVO(0, null);
        vo.setPageSize(100);
        PageInfo<Link> pageInfo = this.findPageBreakByCondition(vo);
        return pageInfo == null ? null : pageInfo.getList();
    }

    /**
     * 分组获取所有连接
     * {indexList:首页显示,insideList:内页,disableList:禁用}
     *
     * @return
     */
    @Override
    @RedisCache
    public Map<String, List<Link>> listAllByGroup() {
        // 首页连接
        List<Link> listOfIndex = this.listOfIndex();
        // 内页连接
        List<Link> listOfInside = this.listOfInside();
        // 已禁用的连接
        List<Link> listOfDisable = this.listOfDisable();
        Map<String, List<Link>> resultMap = new HashMap<>();
        resultMap.put("indexList", listOfIndex);
        resultMap.put("insideList", listOfInside);
        resultMap.put("disableList", listOfDisable);
        return resultMap;
    }

    /**
     * 自动添加友链
     *
     * @param link
     * @return
     */
    @Override
    @RedisCache(flush = true)
    public boolean autoLink(Link link) throws ZhydLinkException {
        String url = link.getUrl();
        Link bo = getOneByUrl(url);
        if (bo != null) {
            throw new ZhydLinkException("本站已经添加过贵站的链接！");
        }
        Config config = configService.get();
        if (!(LinksUtil.hasLinkByHtml(url, config.getDomain()))
                && !LinksUtil.hasLinkByChinaz(url, config.getDomain())) {
            throw new ZhydLinkException("贵站暂未添加本站友情链接！请先添加本站友链后重新提交申请！");
        }

        link.setSource(LinkSourceEnum.AUTOMATIC);
        link.setStatus(true);
        if(!StringUtils.isEmpty(link.getEmail())){
            link.setEmail(HtmlUtil.html2Text(link.getEmail()));
        }
        if(!StringUtils.isEmpty(link.getFavicon())){
            link.setFavicon(HtmlUtil.html2Text(link.getFavicon()));
        }
        if(!StringUtils.isEmpty(link.getName())){
            link.setName(HtmlUtil.html2Text(link.getName()));
        }
        if(!StringUtils.isEmpty(link.getUrl())){
            link.setUrl(HtmlUtil.html2Text(link.getUrl()));
        }
        if(!StringUtils.isEmpty(link.getDescription())){
            link.setDescription(HtmlUtil.html2Text(link.getDescription()));
        }
        this.insert(link);
        log.info("友联自动申请成功,开始发送邮件通知...");
        mailService.send(link, TemplateKeyEnum.TM_LINKS);
        return true;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public Link insert(Link entity) {
        Assert.notNull(entity, "Link不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        entity.setStatus(entity.isStatus());
        entity.setHomePageDisplay(entity.isHomePageDisplay());
        sysLinkMapper.insertSelective(entity.getSysLink());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    @RedisCache(flush = true)
    public void insertList(List<Link> entities) {
        Assert.notNull(entities, "Links不可为空！");
        List<SysLink> list = new ArrayList<>();
        for (Link entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            entity.setStatus(entity.isStatus());
            entity.setHomePageDisplay(entity.isHomePageDisplay());
            list.add(entity.getSysLink());
        }
        sysLinkMapper.insertList(list);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysLinkMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean update(Link entity) {
        Assert.notNull(entity, "Link不可为空！");
        entity.setUpdateTime(new Date());
        entity.setStatus(entity.isStatus());
        entity.setHomePageDisplay(entity.isHomePageDisplay());
        return sysLinkMapper.updateByPrimaryKey(entity.getSysLink()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean updateSelective(Link entity) {
        Assert.notNull(entity, "Link不可为空！");
        entity.setUpdateTime(new Date());
        entity.setStatus(entity.isStatus());
        entity.setHomePageDisplay(entity.isHomePageDisplay());
        return sysLinkMapper.updateByPrimaryKeySelective(entity.getSysLink()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public Link getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysLink entity = sysLinkMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Link(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    @RedisCache
    public Link getOneByEntity(Link entity) {
        Assert.notNull(entity, "Link不可为空！");
        SysLink bo = sysLinkMapper.selectOne(entity.getSysLink());
        return null == bo ? null : new Link(bo);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param url
     * @return
     */
    @Override
    public Link getOneByUrl(String url) {
        Link l = new Link();
        l.setUrl(url);
        return getOneByEntity(l);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    @RedisCache
    public List<Link> listAll() {
        List<SysLink> entityList = sysLinkMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Link> list = new ArrayList<>();
        for (SysLink entity : entityList) {
            list.add(new Link(entity));
        }
        return list;
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    @RedisCache
    public List<Link> listByEntity(Link entity) {
        Assert.notNull(entity, "Link不可为空！");
        List<SysLink> entityList = sysLinkMapper.select(entity.getSysLink());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Link> list = new ArrayList<>();
        for (SysLink po : entityList) {
            list.add(new Link(po));
        }
        return list;
    }
}
