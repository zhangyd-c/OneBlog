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
package com.zyd.blog.spider.util;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;

/**
 * WriterUtil
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2017/11/18 11:48
 * @since 1.0
 */
@Slf4j
public class WriterUtil {

    private PrintWriter writer;

    public WriterUtil() {
    }

    public WriterUtil(PrintWriter writer) {
        this.writer = writer;
    }

    public WriterUtil print(String... msgs) {
        if (null == writer) {
            for (String msg : msgs) {
                log.info(msg);
            }
            return this;
        }
        for (String msg : msgs) {
            log.info(msg);
            writer.print("<script>parent.printMessage('" + msg + "');</script>");
            if(null != writer) {
                writer.flush();
            }
        }

        return this;
    }

    public void shutdown() {
        print("exit...", "shutdown");
        if (null != writer) {
            writer.close();
            writer = null;
        }
    }
}
