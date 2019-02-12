package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.entity.Link;
import com.zyd.blog.business.enums.ConfigKeyEnum;
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
        Map config = configService.getConfigs();
        String domain = (String) config.get(ConfigKeyEnum.DOMAIN.getKey());
        if (!(LinksUtil.hasLinkByHtml(url, domain))
                && !LinksUtil.hasLinkByChinaz(url, domain)) {
            throw new ZhydLinkException("贵站暂未添加本站友情链接！请先添加本站友链后重新提交申请！");
        }

        link.setSource(LinkSourceEnum.AUTOMATIC);
        link.setStatus(true);
        if (!StringUtils.isEmpty(link.getEmail())) {
            link.setEmail(HtmlUtil.html2Text(link.getEmail()));
        }
        if (!StringUtils.isEmpty(link.getFavicon())) {
            link.setFavicon(HtmlUtil.html2Text(link.getFavicon()));
        }
        if (!StringUtils.isEmpty(link.getName())) {
            link.setName(HtmlUtil.html2Text(link.getName()));
        }
        if (!StringUtils.isEmpty(link.getUrl())) {
            link.setUrl(HtmlUtil.html2Text(link.getUrl()));
        }
        if (!StringUtils.isEmpty(link.getDescription())) {
            link.setDescription(HtmlUtil.html2Text(link.getDescription()));
        }
        this.insert(link);
        log.info("友联自动申请成功,开始发送邮件通知...");
        mailService.send(link, TemplateKeyEnum.TM_LINKS);
        return true;
    }

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysLinkMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

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

    @Override
    public Link getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysLink entity = sysLinkMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Link(entity);
    }

    @Override
    public Link getOneByUrl(String url) {
        SysLink l = new SysLink();
        l.setUrl(url);
        l = sysLinkMapper.selectOne(l);
        return null == l ? null : new Link(l);
    }
}
