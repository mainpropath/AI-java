package com.ai.openai.achieve.defaults.session;


import cn.hutool.http.ContentType;
import com.ai.common.utils.JsonUtils;
import com.ai.core.exception.Constants;
import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.session.ChatSession;
import com.ai.openai.common.ApiUrl;
import com.ai.openai.endPoint.chat.ChatChoice;
import com.ai.openai.endPoint.chat.msg.DefaultMessage;
import com.ai.openai.endPoint.chat.req.DefaultChatCompletionRequest;
import com.ai.openai.endPoint.chat.req.FuncChatCompletionRequest;
import com.ai.openai.endPoint.chat.req.ImgChatCompletionRequest;
import com.ai.openai.endPoint.chat.req.QaCompletionRequest;
import com.ai.openai.endPoint.chat.resp.ChatCompletionResponse;
import com.ai.openai.endPoint.chat.resp.QaCompletionResponse;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;
import static com.ai.core.exception.Constants.*;

/**
 * @description OpenAI 对话类会话
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
        this.setOpenaiApiServer(ensureNotNull(configuration.getOpenaiApiServer(), "openaiApiServer"));
        this.setFactory(ensureNotNull(configuration.createRequestFactory(), "requestFactory"));
    }

    @Override
    public QaCompletionResponse qaCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, QaCompletionRequest qaCompletionRequest) {
        return this.getOpenaiApiServer().createQaCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, qaCompletionRequest).blockingGet();
    }

    @Override
    public EventSource qaCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, QaCompletionRequest qaCompletionRequest, EventSourceListener eventSourceListener) {
        Request request = new Request.Builder()
                .addHeader(API_HOST, apiHostByUser)
                .addHeader(API_KEY, apiKeyByUser)
                .addHeader(URL, apiUrlByUser)
                .url(this.getConfiguration().getApiHost().concat(ApiUrl.v1_completions.getUrl()))
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JsonUtils.toJson(qaCompletionRequest)))
                .build();
        return factory.newEventSource(request, eventSourceListener);
    }

    @Override
    public ChatCompletionResponse chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, DefaultChatCompletionRequest defaultChatCompletionRequest) {
        return this.getOpenaiApiServer().createChatCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, defaultChatCompletionRequest).blockingGet();
    }

    @Override
    public ChatCompletionResponse chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ImgChatCompletionRequest imgChatCompletionRequest) {
        return this.getOpenaiApiServer().createChatCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, imgChatCompletionRequest).blockingGet();
    }

    @Override
    public ChatCompletionResponse chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, FuncChatCompletionRequest funcChatCompletionRequest) {
        return this.getOpenaiApiServer().createChatCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, funcChatCompletionRequest).blockingGet();
    }

    @Override
    public EventSource chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, DefaultChatCompletionRequest defaultChatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        Request request = new Request.Builder()
                .addHeader(API_HOST, apiHostByUser)
                .addHeader(API_KEY, apiKeyByUser)
                .addHeader(URL, apiUrlByUser)
                .url(this.getConfiguration().getApiHost().concat(ApiUrl.v1_chat_completions.getUrl()))
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(defaultChatCompletionRequest)))
                .build();
        return factory.newEventSource(request, eventSourceListener);
    }

    @Override
    public CompletableFuture<String> chatCompletionsFuture(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, DefaultChatCompletionRequest defaultChatCompletionRequest) throws JsonProcessingException {
        CompletableFuture<String> future = new CompletableFuture<>();
        StringBuffer dataBuffer = new StringBuffer();
        chatCompletions(apiHostByUser, apiKeyByUser, apiUrlByUser, defaultChatCompletionRequest, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                if ("[DONE]".equalsIgnoreCase(data)) {
                    onClosed(eventSource);
                    future.complete(dataBuffer.toString());
                }
                ChatCompletionResponse chatCompletionResponse = JSON.parseObject(data, ChatCompletionResponse.class);
                List<ChatChoice> choices = chatCompletionResponse.getChoices();
                for (ChatChoice chatChoice : choices) {
                    DefaultMessage delta = chatChoice.getDelta();
                    if (Constants.Role.ASSISTANT.getRoleName().equals(delta.getRole())) continue;
                    // 应答完成
                    String finishReason = chatChoice.getFinishReason();
                    if ("stop".equalsIgnoreCase(finishReason)) {
                        onClosed(eventSource);
                        return;
                    }
                    // 发送信息
                    try {
                        dataBuffer.append(delta.getContent());
                    } catch (Exception e) {
                        future.completeExceptionally(new RuntimeException("Request closed before completion"));
                    }
                }
            }

            @Override
            public void onClosed(EventSource eventSource) {
                future.complete(dataBuffer.toString());
            }

            @Override
            public void onFailure(EventSource eventSource, Throwable t, Response response) {
                future.completeExceptionally(new RuntimeException("Request closed before completion"));
            }
        });
        return future;
    }

}
