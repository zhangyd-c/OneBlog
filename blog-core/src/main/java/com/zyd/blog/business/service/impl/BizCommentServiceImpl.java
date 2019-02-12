package com.zyd.blog.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.dto.BizCommentDTO;
import com.zyd.blog.business.entity.Comment;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.CommentStatusEnum;
import com.zyd.blog.business.enums.ConfigKeyEnum;
import com.zyd.blog.business.enums.TemplateKeyEnum;
import com.zyd.blog.business.service.BizCommentService;
import com.zyd.blog.business.service.MailService;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.business.vo.CommentConditionVO;
import com.zyd.blog.framework.exception.ZhydCommentException;
import com.zyd.blog.framework.holder.RequestHolder;
import com.zyd.blog.persistence.beans.BizComment;
import com.zyd.blog.persistence.mapper.BizCommentMapper;
import com.zyd.blog.util.*;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 评论
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class BizCommentServiceImpl implements BizCommentService {

    @Autowired
    private BizCommentMapper bizCommentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

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
    public PageInfo<Comment> findPageBreakByCondition(CommentConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizComment> list = bizCommentMapper.findPageBreakByCondition(vo);
        List<Comment> boList = this.getComments(list);
        if (boList == null) {
            return null;
        }
        PageInfo bean = new PageInfo<BizComment>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * @param vo
     * @return
     */
    @Override
    public Map<String, Object> list(CommentConditionVO vo) {
        PageInfo pageInfo = findPageBreakByCondition(vo);
        Map<String, Object> map = new HashMap<>();
        if (pageInfo != null) {
            map.put("commentList", convert2DTO(pageInfo.getList()));
            map.put("total", pageInfo.getTotal());
            map.put("hasNextPage", pageInfo.isHasNextPage());
            map.put("nextPage", pageInfo.getNextPage());
        }
        return map;
    }

    private List<BizCommentDTO> convert2DTO(List<Comment> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<BizCommentDTO> dtoList = new LinkedList<>();
        for (Comment comment : list) {
            BizCommentDTO dto = BeanConvertUtil.doConvert(comment, BizCommentDTO.class);
            dto.setParentDTO(BeanConvertUtil.doConvert(comment.getParent(), BizCommentDTO.class));
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * admin发表评论
     *
     * @param comment
     * @return
     */
    @Override
    @RedisCache(flush = true)
    public void commentForAdmin(Comment comment) throws ZhydCommentException {
        Map config = configService.getConfigs();
        User user = SessionUtil.getUser();
        comment.setQq(user.getQq());
        comment.setEmail(user.getEmail());
        comment.setNickname(user.getNickname());
        comment.setAvatar(user.getAvatar());
        comment.setUrl((String) config.get(ConfigKeyEnum.SITE_URL.getKey()));
        comment.setUserId(user.getId());
        comment.setStatus(CommentStatusEnum.APPROVED.toString());
        comment.setPid(comment.getId());
        comment.setId(null);
        this.comment(comment);
    }

    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    @Override
    @RedisCache(flush = true)
    public Comment comment(Comment comment) throws ZhydCommentException {
        if (StringUtils.isEmpty(comment.getNickname())) {
            throw new ZhydCommentException("必须输入昵称");
        }
        String content = comment.getContent();
        if (!XssKillerUtil.isValid(content)) {
            throw new ZhydCommentException("请不要使用特殊标签");
        }
        content = XssKillerUtil.clean(content.trim()).replaceAll("(<p><br></p>)|(<p></p>)", "");
        if (StringUtils.isEmpty(content) || "\n".equals(content)) {
            throw new ZhydCommentException("说点什么吧");
        }
        // 过滤非法属性和无用的空标签
        comment.setContent(content);
        comment.setNickname(HtmlUtil.html2Text(comment.getNickname()));
        comment.setQq(HtmlUtil.html2Text(comment.getQq()));
        comment.setAvatar(HtmlUtil.html2Text(comment.getAvatar()));

        List<String> avatars = configService.getRandomUserAvatar();
        if (StringUtils.isEmpty(comment.getAvatar()) && !CollectionUtils.isEmpty(avatars)) {
            Collections.shuffle(avatars);
            int randomIndex = new Random().nextInt(avatars.size());
            comment.setAvatar(avatars.get(randomIndex));
        }

        comment.setEmail(HtmlUtil.html2Text(comment.getEmail()));
        comment.setUrl(HtmlUtil.html2Text(comment.getUrl()));
        HttpServletRequest request = RequestHolder.getRequest();
        String ua = request.getHeader("User-Agent");
        UserAgent agent = UserAgent.parseUserAgentString(ua);
        Browser browser = agent.getBrowser();
        String browserInfo = browser.getName();
//        comment.setBrowserShortName(browser.getShortName());// 此处需开发者自己处理
        Version version = agent.getBrowserVersion();
        if (version != null) {
            browserInfo += " " + version.getVersion();
        }
        comment.setBrowser(browserInfo);
        OperatingSystem os = agent.getOperatingSystem();
        comment.setOs(os.getName());
//        comment.setOsShortName(os.getShortName());// 此处需开发者自己处理
        comment.setIp(IpUtil.getRealIp(request));
        String address = "";
        Map config = configService.getConfigs();
        try {
            String locationJson = RestClientUtil.get(UrlBuildUtil.getLocationByIp(comment.getIp(), (String) config.get(ConfigKeyEnum.BAIDU_API_AK.getKey())));
            JSONObject localtionContent = JSONObject.parseObject(locationJson).getJSONObject("content");
            JSONObject addressDetail = localtionContent.getJSONObject("address_detail");
            String city = addressDetail.getString("city");
            String district = addressDetail.getString("district");
            String street = addressDetail.getString("street");
            address = addressDetail.getString("province") + (StringUtils.isEmpty(city) ? "" : city) +
                    (StringUtils.isEmpty(district) ? "" : district) +
                    (StringUtils.isEmpty(street) ? "" : street);
            JSONObject point = localtionContent.getJSONObject("point");
            comment.setLat(point.getString("y"));
            comment.setLng(point.getString("x"));
            comment.setAddress(address);
        } catch (Exception e) {
            comment.setAddress("未知");
            log.error("获取地址失败", e);
        }
        if (StringUtils.isEmpty(comment.getStatus())) {
            comment.setStatus(CommentStatusEnum.VERIFYING.toString());
        }
        this.insert(comment);
        this.sendEmail(comment);
        return comment;
    }


    private void sendEmail(Comment comment) {
        // 发送邮件通知，此处如发生异常不应阻塞当前的业务流程
        // 可以进行日志记录等操作
        try {
            if (null != comment.getPid() && 0 != comment.getPid()) {
                // 给被评论的用户发送通知
                Comment commentDB = this.getByPrimaryKey(comment.getPid());
                mailService.send(commentDB, TemplateKeyEnum.TM_COMMENT_REPLY, false);
            } else {
                mailService.sendToAdmin(comment);
            }
        } catch (Exception e) {
            log.error("发送评论通知邮件时发生异常", e);
        }
    }

    /**
     * 查询近期评论
     *
     * @param pageSize
     * @return
     */
    @Override
    @RedisCache
    public List<Comment> listRecentComment(int pageSize) {
        CommentConditionVO vo = new CommentConditionVO();
        vo.setPageSize(pageSize);
        vo.setStatus(CommentStatusEnum.APPROVED.toString());
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizComment> list = bizCommentMapper.findPageBreakByCondition(vo);
        return getComments(list);
    }

    private List<Comment> getComments(List<BizComment> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Comment> boList = new ArrayList<>();
        for (BizComment bizComment : list) {
            boList.add(new Comment(bizComment));
        }
        return boList;
    }

    /**
     * 查询未审核的评论
     *
     * @param pageSize
     * @return
     */
    @Override
    public List<Comment> listVerifying(int pageSize) {
        CommentConditionVO vo = new CommentConditionVO();
        vo.setPageSize(pageSize);
        vo.setStatus(CommentStatusEnum.VERIFYING.toString());
        PageInfo pageInfo = findPageBreakByCondition(vo);
        return null == pageInfo ? null : pageInfo.getList();
    }

    /**
     * 点赞
     *
     * @param id
     */
    @Override
    @RedisCache(flush = true)
    public void doSupport(Long id) {
        String key = IpUtil.getRealIp(RequestHolder.getRequest()) + "_doSupport_" + id;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            throw new ZhydCommentException("一个小时只能点一次赞哈~");
        }
        bizCommentMapper.doSupport(id);
        operations.set(key, id, 1, TimeUnit.HOURS);
    }

    /**
     * 点踩
     *
     * @param id
     */
    @Override
    @RedisCache(flush = true)
    public void doOppose(Long id) {
        String key = IpUtil.getRealIp(RequestHolder.getRequest()) + "_doOppose_" + id;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            throw new ZhydCommentException("一个小时只能踩一次哈~又没什么深仇大恨");
        }
        bizCommentMapper.doOppose(id);
        operations.set(key, id, 1, TimeUnit.HOURS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public Comment insert(Comment entity) {
        Assert.notNull(entity, "Comment不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizCommentMapper.insertSelective(entity.getBizComment());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return bizCommentMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean updateSelective(Comment entity) {
        Assert.notNull(entity, "Comment不可为空！");
        entity.setUpdateTime(new Date());
        return bizCommentMapper.updateByPrimaryKeySelective(entity.getBizComment()) > 0;
    }

    @Override
    public Comment getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizComment entity = bizCommentMapper.getById(primaryKey);
        return null == entity ? null : new Comment(entity);
    }
}
