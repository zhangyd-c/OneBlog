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
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.FileUtil;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 其他api性质的接口
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    private BizArticleService articleService;
    @Autowired
    private SysConfigService configService;

    /**
     * 上传文件到七牛云
     *
     * @param file
     * @return
     */
    @RequiresPermissions("article:publish")
    @PostMapping("/upload2Qiniu")
    public ResponseVO upload2Qiniu(@RequestParam("file") MultipartFile file) {
        String filePath = FileUtil.uploadToQiniu(file, QiniuUploadType.SIMPLE, false);
        return ResultUtil.success("图片上传成功", filePath);
    }

    @RequiresPermissions("article:publish")
    @PostMapping("/upload2QiniuForMd")
    public Object upload2QiniuForMd(@RequestParam("file") MultipartFile file) {
        String filePath = FileUtil.uploadToQiniu(file, QiniuUploadType.SIMPLE, false);
        Config config = configService.get();
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("success", 1);
        resultMap.put("message", "上传成功");
        resultMap.put("filename", config.getQiuniuBasePath() + filePath + "-pw");
        return resultMap;
    }

    /**
     * 发布文章选择图片时获取素材库
     *
     * @return
     */
    @RequiresPermissions("article:publish")
    @PostMapping("/material")
    public ResponseVO material() {
        return ResultUtil.success("", articleService.listMaterial());
    }
}
