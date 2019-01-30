package com.zyd.blog.controller;

import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.BaseConfig;
import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.core.websocket.server.ZydWebsocketServer;
import com.zyd.blog.core.websocket.util.WebSocketUtil;
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

import java.io.UnsupportedEncodingException;
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
    @Autowired
    private ZydWebsocketServer websocketServer;

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
        BaseConfig config = configService.getBaseConfig();
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("success", 1);
        resultMap.put("message", "上传成功");
        resultMap.put("filename", config.getQiniuBasePath() + filePath);
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

    /**
     * 发送消息通知
     *
     * @return
     */
    @RequiresPermissions("notice")
    @PostMapping("/notice")
    @BussinessLog("通过websocket向前台用户发送通知")
    public ResponseVO notice(String msg) throws UnsupportedEncodingException {
        WebSocketUtil.sendNotificationMsg(msg, websocketServer.getOnlineUsers());
        return ResultUtil.success("消息发送成功", articleService.listMaterial());
    }
}
