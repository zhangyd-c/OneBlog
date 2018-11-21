package com.zyd.blog.business.service;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Resources;
import com.zyd.blog.business.vo.ResourceConditionVO;
import com.zyd.blog.framework.object.AbstractService;

import java.util.List;
import java.util.Map;

/**
 * 系统资源
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysResourcesService extends AbstractService<Resources, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Resources> findPageBreakByCondition(ResourceConditionVO vo);

    /**
     * 获取用户的资源列表
     *
     * @param map
     * @return
     */
    List<Resources> listUserResources(Map<String, Object> map);

    /**
     * 获取ztree使用的资源列表
     *
     * @param rid
     * @return
     */
    List<Map<String, Object>> queryResourcesListWithSelected(Long rid);

    /**
     * 获取资源的url和permission
     *
     * @return
     */
    List<Resources> listUrlAndPermission();

    /**
     * 获取所有可用的菜单资源
     *
     * @return
     */
    List<Resources> listAllAvailableMenu();

    /**
     * 获取用户关联的所有资源
     *
     * @param userId
     * @return
     */
    List<Resources> listByUserId(Long userId);
}
