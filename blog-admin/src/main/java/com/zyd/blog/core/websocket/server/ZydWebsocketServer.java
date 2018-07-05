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
package com.zyd.blog.core.websocket.server;

import com.zyd.blog.core.websocket.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class ZydWebsocketServer {

    /**
     * 线程安全的socket集合
     */
    private static CopyOnWriteArraySet<Session> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 初始在线人数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        webSocketSet.add(session);
        int count = onlineCount.incrementAndGet();
        log.info("有链接加入，当前在线人数为: {}", count);

        WebSocketUtil.sendOnlineMsg(Integer.toString(count), webSocketSet);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        int count = onlineCount.decrementAndGet();
        log.info("有链接关闭,当前在线人数为: {}", count);
        WebSocketUtil.sendOnlineMsg(Integer.toString(count), webSocketSet);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *         客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("{}来自客户端的消息:{}", session.getId(), message);
    }

    /**
     * 获取在线用户数量
     *
     * @return
     */
    public int getOnlineUserCount() {
        return onlineCount.get();
    }

    /**
     * 获取在线用户的会话信息
     *
     * @return
     */
    public CopyOnWriteArraySet<Session> getOnlineUsers() {
        return webSocketSet;
    }
}
