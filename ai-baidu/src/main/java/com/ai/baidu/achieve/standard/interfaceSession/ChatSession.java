package com.ai.baidu.achieve.standard.interfaceSession;

import com.ai.baidu.endPoint.chat.req.ChatRequest;
import com.ai.baidu.endPoint.chat.resp.ChatResponse;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @Description: baidu 聊天会话窗口
 **/
public interface ChatSession {


    /**
     * 聊天接口，如果传入的 accessToken 为空，会自动根据用户设置的 key 按照 key 的获取策略进行自动鉴权。
     *
     * @param accessToken 鉴权的 accessToken
     * @param chatRequest 请求参数
     * @return 返回参数
     */
    ChatResponse chat(String accessToken, ChatRequest chatRequest);

    /**
     * 聊天接口流式返回
     *
     * @param accessToken         鉴权的 accessToken
     * @param chatRequest         请求参数
     * @param eventSourceListener 事件监听者
     * @return 事件源
     */
    EventSource chat(String accessToken, ChatRequest chatRequest, EventSourceListener eventSourceListener);

}
