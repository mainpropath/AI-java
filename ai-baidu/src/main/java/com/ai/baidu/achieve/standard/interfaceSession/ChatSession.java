package com.ai.baidu.achieve.standard.interfaceSession;

import com.ai.baidu.endPoint.chat.req.ChatRequest;
import com.ai.baidu.endPoint.chat.resp.ChatResponse;

/**
 * @Description: baidu 聊天会话窗口
 **/
public interface ChatSession {


    /**
     * 聊天接口
     *
     * @param accessToken 鉴权的 accessToken
     * @param request     请求参数
     * @return 返回参数
     */
    ChatResponse chat(String accessToken, ChatRequest request);

}
