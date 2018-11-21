package com.zyd.blog.controller;

import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Config;
import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.FileUtil;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        return ResultUtil.success(null, sysConfigService.get());
    }

    @RequiresRoles("role:root")
    @PostMapping("/edit")
    @BussinessLog("修改系统配置")
    public ResponseVO edit(Config config,
                           @RequestParam(required = false) MultipartFile wxPraiseCodeFile,
                           @RequestParam(required = false) MultipartFile zfbPraiseCodeFile) {
        config.setWxPraiseCode(FileUtil.uploadToQiniu(wxPraiseCodeFile, QiniuUploadType.QRCODE, true));
        config.setZfbPraiseCode(FileUtil.uploadToQiniu(zfbPraiseCodeFile, QiniuUploadType.QRCODE, true));
        if(null != wxPraiseCodeFile || null != zfbPraiseCodeFile){
            Config configDB = sysConfigService.get();
            FileUtil.removeQiniu(configDB.getWxPraiseCode(), configDB.getZfbPraiseCode());
        }
        try {
            sysConfigService.update(config);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("系统配置修改失败");
        }
        return ResultUtil.success("系统配置修改成功");
    }

}
