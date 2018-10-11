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


import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.consts.DateConst;
import com.zyd.blog.business.service.RemoverService;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.spider.model.BaseModel;
import com.zyd.blog.util.DateUtil;
import com.zyd.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Remover：搬运工(英语渣渣，实在想不出好玩的名字了)
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/8/14 14:37
 * @since 1.0
 */
@RestController
@RequestMapping("/remover")
public class RestRemoverController {

    @Autowired
    private RemoverService removerService;

    @PostMapping("/run")
    @ResponseBody
    @BussinessLog("运行文章搬运工")
    public void run(Long typeId, BaseModel model, HttpServletResponse response) throws IOException, InterruptedException {
        removerService.run(typeId, model, response.getWriter());
    }

    @PostMapping("/stop")
    @ResponseBody
    @BussinessLog("停止文章搬运工")
    public ResponseVO stop() {
        try {
            removerService.stop();
        } catch (Exception e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success("程序已停止运行，当前时间 " + DateUtil.date2Str(new Date(), DateConst.YYYY_MM_DD_HH_MM_SS_EN));
    }

}
