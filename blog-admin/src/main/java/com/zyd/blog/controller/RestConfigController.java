/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.controller;

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
