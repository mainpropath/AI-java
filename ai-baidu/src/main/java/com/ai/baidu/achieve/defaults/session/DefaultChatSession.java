package com.ai.baidu.achieve.defaults.session;

import cn.hutool.http.ContentType;
import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.session.ChatSession;
import com.ai.baidu.common.ApiUrl;
import com.ai.baidu.endPoint.chat.req.ChatRequest;
import com.ai.baidu.endPoint.chat.resp.ChatResponse;
import com.ai.common.utils.JsonUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultChatSession extends Session implements ChatSession {

    /**
     * 工厂事件
     */
    private EventSource.Factory factory;

    public DefaultChatSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setBaiduApiServer(ensureNotNull(configuration.getBaiduApiServer(), "baiduApiServer"));
        this.factory = ensureNotNull(configuration.createRequestFactory(), "requestFactory");
    }

    @Override
    public ChatResponse chat(String accessToken, ChatRequest chatRequest) {
        // 检查一下是否为空，为空根据设置的 keyStrategy 获取一个用户设置的 key
        return this.getBaiduApiServer().chat(checkAccessToken(accessToken), chatRequest).blockingGet();
    }

    @Override
    public EventSource chat(String accessToken, ChatRequest chatRequest, EventSourceListener eventSourceListener) {
        // 将 accessToken 设置到请求路径上
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.getConfiguration().getApiHost().concat(ApiUrl.ERNIE_Bot_4_0.getUrl())).newBuilder();
        urlBuilder.addQueryParameter("access_token", checkAccessToken(accessToken));
        // 发起请求
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JsonUtils.toJson(chatRequest)))
                .build();
        return factory.newEventSource(request, eventSourceListener);
    }
}
