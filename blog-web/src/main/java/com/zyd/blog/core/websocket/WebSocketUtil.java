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
package com.zyd.blog.core.websocket;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;

/**
 * websocket工具类，支持单条发送和批量发送
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
public class WebSocketUtil {

    private WebSocketUtil() {
        // 私有化构造方法，禁止new
    }

    /**
     * 向客户端发送消息
     *
     * @param message
     *         消息内容
     * @param session
     *         客户端session
     * @throws IOException
     */
    public static void sendMessage(String message, Session session) {
        try {
            session.getAsyncRemote().sendText(message);
        } catch (Exception e) {
            log.error("websocket-->向客户端发送数据发生异常", e);
        }
    }

    /**
     * 群发
     *
     * @param message
     *         消息内容
     * @param sessionSet
     *         客户端session列表
     * @throws IOException
     */
    public static void broadcast(String message, Set<Session> sessionSet) {
        // 多线程群发
        for (Session entry : sessionSet) {
            if (entry.isOpen()) {
                sendMessage(message, entry);
            } else {
                sessionSet.remove(entry);
            }
        }
    }
}
