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

import javax.websocket.Session;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket工具类，支持单条发送和批量发送
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
public class WebSocketUtil {

    /**
     * 向客户端发送消息
     *
     * @param message
     *         消息内容
     * @param session
     *         客户端session
     * @throws IOException
     */
    public static void sendMessage(String message, Session session) throws IOException {
        session.getAsyncRemote().sendText(message);
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
    public static void broadcast(String message, CopyOnWriteArraySet<Session> sessionSet) {
        Iterator<Session> it = sessionSet.iterator();
        // 多线程群发
        while (it.hasNext()) {
            Session entry = it.next();
            if (entry.isOpen()) {
                try {
                    sendMessage(message, entry);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                sessionSet.remove(entry);
            }
        }
    }
}
