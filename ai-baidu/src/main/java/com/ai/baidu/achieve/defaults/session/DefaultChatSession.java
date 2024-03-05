package com.ai.baidu.achieve.defaults.session;

import com.ai.baidu.achieve.ApiData;
import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.api.BaiduApiServer;
import com.ai.baidu.achieve.standard.interfaceSession.ChatSession;
import com.ai.baidu.endPoint.chat.req.ChatRequest;
import com.ai.baidu.endPoint.chat.resp.ChatResponse;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @description baidu 对话类会话
 */
public class DefaultChatSession implements ChatSession {

    /**
     * 配置信息
     */
    private Configuration configuration;
    /**
     * OpenAI 接口
     */
    private BaiduApiServer baiduApiServer;

    public DefaultChatSession(Configuration configuration) {
        this.configuration = ensureNotNull(configuration, "configuration");
        this.baiduApiServer = ensureNotNull(configuration.getBaiduApiServer(), "baiduApiServer");
    }

    @Override
    public ChatResponse chat(String accessToken, ChatRequest request) {
        // 检查一下是否为null，为 null 根据 keyStrategy 获取一个用户设置的 key
        if (accessToken == null) {
            ApiData systemApiData = configuration.getSystemApiData();
            accessToken = baiduApiServer.getAccessToken(systemApiData.getApiKey(), systemApiData.getSecretKey());
        }
        return baiduApiServer.chat(accessToken, request).blockingGet();
    }
}
