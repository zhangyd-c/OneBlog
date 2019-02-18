package com.zyd.blog.controller;

import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 系统配置
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@RestController
@RequestMapping("/config")
public class RestConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @RequiresRoles("role:root")
    @PostMapping("/get")
    public ResponseVO get() {
        return ResultUtil.success(null, sysConfigService.getConfigs());
    }

    @RequiresRoles("role:root")
    @PostMapping("/save")
    @BussinessLog("修改系统配置")
    public ResponseVO save(@RequestParam Map<String, String> configs,
                           @RequestParam(required = false) MultipartFile wxPraiseCode,
                           @RequestParam(required = false) MultipartFile zfbPraiseCode) {
        try {
            sysConfigService.saveConfig(configs);
            sysConfigService.saveFile("wxPraiseCode", wxPraiseCode);
            sysConfigService.saveFile("zfbPraiseCode", zfbPraiseCode);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("系统配置修改失败");
        }
        return ResultUtil.success("系统配置修改成功");
    }

}
