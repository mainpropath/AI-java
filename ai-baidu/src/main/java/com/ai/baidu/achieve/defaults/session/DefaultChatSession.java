package com.ai.baidu.achieve.defaults.session;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.ai.baidu.achieve.ApiData;
import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.api.BaiduApiServer;
import com.ai.baidu.achieve.standard.interfaceSession.ChatSession;
import com.ai.baidu.common.ApiUrl;
import com.ai.baidu.endPoint.chat.req.ChatRequest;
import com.ai.baidu.endPoint.chat.resp.ChatResponse;
import com.ai.common.utils.JsonUtils;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

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
    /**
     * 工厂事件
     */
    private EventSource.Factory factory;

    public DefaultChatSession(Configuration configuration) {
        this.configuration = ensureNotNull(configuration, "configuration");
        this.baiduApiServer = ensureNotNull(configuration.getBaiduApiServer(), "baiduApiServer");
        this.factory = ensureNotNull(configuration.createRequestFactory(), "requestFactory");
    }

    private String checkAccessToken(String accessToken) {
        if (StrUtil.isEmpty(accessToken)) {
            ApiData systemApiData = configuration.getSystemApiData();
            accessToken = baiduApiServer.getAccessToken(systemApiData.getApiKey(), systemApiData.getSecretKey());
        }
        return accessToken;
    }

    @Override
    public ChatResponse chat(String accessToken, ChatRequest chatRequest) {
        // 检查一下是否为空，为空根据设置的 keyStrategy 获取一个用户设置的 key
        return baiduApiServer.chat(checkAccessToken(accessToken), chatRequest).blockingGet();
    }

    @Override
    public EventSource chat(String accessToken, ChatRequest chatRequest, EventSourceListener eventSourceListener) {
        // 将 accessToken 设置到请求路径上
        HttpUrl.Builder urlBuilder = HttpUrl.parse(configuration.getApiHost().concat(ApiUrl.ERNIE_Bot_4_0.getUrl())).newBuilder();
        urlBuilder.addQueryParameter("access_token", checkAccessToken(accessToken));
        // 发起请求
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JsonUtils.toJson(chatRequest)))
                .build();
        return factory.newEventSource(request, eventSourceListener);
    }
}
