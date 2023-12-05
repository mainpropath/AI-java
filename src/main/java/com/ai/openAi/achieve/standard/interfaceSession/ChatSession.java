package com.ai.openAi.achieve.standard.interfaceSession;

import com.ai.openAi.endPoint.chat.req.ChatCompletionRequest;
import com.ai.openAi.endPoint.chat.req.QaCompletionRequest;
import com.ai.openAi.endPoint.chat.resp.ChatCompletionResponse;
import com.ai.openAi.endPoint.chat.resp.QaCompletionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.concurrent.CompletableFuture;

/**
 * @description openAi 聊天会话窗口
 */
public interface ChatSession {

    /**
     * 简单问答，用户自定义封装请求参数，此函数为简单问答的基础函数，其余的均在该函数上进行灵活拓展。
     *
     * @param apiHostByUser       用户自定义 host
     * @param apiKeyByUser        用户自定义密钥
     * @param apiUrlByUser        用户自定义请求地址
     * @param qaCompletionRequest 用户自定义封装的请求参数
     * @return 请求结果
     */
    QaCompletionResponse qaCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, QaCompletionRequest qaCompletionRequest);

    /**
     * 简单问答，流式返回。
     *
     * @param apiHostByUser       用户自定义 host
     * @param apiKeyByUser        用户自定义密钥
     * @param apiUrlByUser        用户自定义请求地址
     * @param qaCompletionRequest 用户自定义封装的请求参数
     * @param eventSourceListener 事件监听者
     * @return 事件源
     */
    EventSource qaCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, QaCompletionRequest qaCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    /**
     * 对话聊天，此函数为对话聊天的基础函数，其余的均在该函数上进行灵活拓展。
     *
     * @param apiHostByUser         用户自定义 host
     * @param apiKeyByUser          用户自定义密钥
     * @param apiUrlByUser          用户自定义请求地址
     * @param chatCompletionRequest 用户自定义封装的请求参数
     * @return 请求结果
     */
    ChatCompletionResponse chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ChatCompletionRequest chatCompletionRequest);

    /**
     * 对话聊天，流式返回，此函数为对话聊天流式返回的基础函数。
     *
     * @param apiHostByUser         用户自定义 host
     * @param apiKeyByUser          用户自定义密钥
     * @param apiUrlByUser          用户自定义请求地址
     * @param chatCompletionRequest 用户自定义封装的请求参数
     * @param eventSourceListener   事件监听者
     * @return 事件源
     */
    EventSource chatCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    /**
     * 对话聊天 & 流式反馈 & 一次反馈
     *
     * @param chatCompletionRequest 请求信息
     * @return 应答结果
     */
    CompletableFuture<String> chatCompletionsFuture(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ChatCompletionRequest chatCompletionRequest) throws InterruptedException, JsonProcessingException;

}
