package com.ai.openAi.achieve.defaults.session;


import cn.hutool.http.ContentType;
import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.standard.api.ApiServer;
import com.ai.openAi.achieve.standard.interfaceSession.ChatSession;
import com.ai.openAi.common.Constants;
import com.ai.openAi.endPoint.chat.ChatChoice;
import com.ai.openAi.endPoint.chat.msg.DefaultMessage;
import com.ai.openAi.endPoint.chat.req.DefaultChatCompletionRequest;
import com.ai.openAi.endPoint.chat.req.FuncChatCompletionRequest;
import com.ai.openAi.endPoint.chat.req.ImgChatCompletionRequest;
import com.ai.openAi.endPoint.chat.req.QaCompletionRequest;
import com.ai.openAi.endPoint.chat.resp.ChatCompletionResponse;
import com.ai.openAi.endPoint.chat.resp.QaCompletionResponse;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.ai.openAi.common.Constants.*;

/**
 * @description OpenAI 对话类会话
 */
public class DefaultChatSession implements ChatSession {

    /**
     * 配置信息
     */
    private Configuration configuration;
    /**
     * OpenAI 接口
     */
    private ApiServer apiServer;
    /**
     * 工厂事件
     */
    private EventSource.Factory factory;

    public DefaultChatSession(Configuration configuration) {
        this.configuration = configuration;
        this.apiServer = configuration.getApiServer();
        this.factory = configuration.createRequestFactory();
    }

    @Override
    public QaCompletionResponse qaCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, QaCompletionRequest qaCompletionRequest) {
        return this.apiServer.createQaCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, qaCompletionRequest).blockingGet();
    }

    @Override
    public EventSource qaCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, QaCompletionRequest qaCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        Request request = new Request.Builder()
                .addHeader(API_HOST, apiHostByUser)
                .addHeader(API_KEY, apiKeyByUser)
                .addHeader(URL, apiUrlByUser)
                .url(configuration.getApiHost().concat(ApiUrl.v1_completions.getCode()))
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(qaCompletionRequest)))
                .build();
        return factory.newEventSource(request, eventSourceListener);
    }

    @Override
    public ChatCompletionResponse chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, DefaultChatCompletionRequest defaultChatCompletionRequest) {
        return this.apiServer.createChatCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, defaultChatCompletionRequest).blockingGet();
    }

    @Override
    public ChatCompletionResponse chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ImgChatCompletionRequest imgChatCompletionRequest) {
        return this.apiServer.createChatCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, imgChatCompletionRequest).blockingGet();
    }

    @Override
    public ChatCompletionResponse chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, FuncChatCompletionRequest funcChatCompletionRequest) {
        return this.apiServer.createChatCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, funcChatCompletionRequest).blockingGet();
    }

    @Override
    public EventSource chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, DefaultChatCompletionRequest defaultChatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        Request request = new Request.Builder()
                .addHeader(API_HOST, apiHostByUser)
                .addHeader(API_KEY, apiKeyByUser)
                .addHeader(URL, apiUrlByUser)
                .url(configuration.getApiHost().concat(ApiUrl.v1_chat_completions.getCode()))
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
