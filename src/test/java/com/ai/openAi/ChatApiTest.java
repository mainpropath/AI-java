package com.ai.openAi;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.session.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.achieve.defaults.strategy.RandomKeyStrategy;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openAi.common.Constants;
import com.ai.openAi.endPoint.chat.Message;
import com.ai.openAi.endPoint.chat.req.ChatCompletionRequest;
import com.ai.openAi.endPoint.chat.req.QaCompletionRequest;
import com.ai.openAi.endPoint.chat.resp.ChatCompletionResponse;
import com.ai.openAi.endPoint.chat.resp.QaCompletionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.ai.openAi.common.Constants.NULL;

/**
 * @Description: 测试聊天接口相关接口功能
 */
@Slf4j
public class ChatApiTest {

    private AggregationSession aggregationSession;

    @Before
    public void test_OpenAiSessionFactory() {
        Configuration configuration = new Configuration();
//        configuration.setApiHost("https://api.openai.com");
        configuration.setApiHost("填入你的API 请求地址");
        configuration.setKeyList(Arrays.asList("填入你的API Key"));
        configuration.setKeyStrategy(new FirstKeyStrategy());
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    /**
     * 测试简单问答，相当于只有一轮问答。
     */
    @Test
    public void test_qa_completions() throws IOException {
        QaCompletionRequest qaCompletionRequest = QaCompletionRequest.buildBaseQaCompletionRequest("9*7=");
        QaCompletionResponse qaCompletionResponse = aggregationSession.getChatSession().qaCompletions(NULL, NULL, NULL, qaCompletionRequest);
        log.info("测试结果：{}", new ObjectMapper().writeValueAsString(qaCompletionResponse));
    }

    /**
     * 测试简单问答，流式返回结果。
     */
    @Test
    public void test_qa_completions_stream() throws InterruptedException, JsonProcessingException {
        QaCompletionRequest qaCompletionRequest = QaCompletionRequest.builder().prompt("讲一个笑话").stream(true).build();
        // 监听器监听返回的结果
        aggregationSession.getChatSession().qaCompletions(NULL, NULL, NULL, qaCompletionRequest, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                log.info("测试结果 id:{} type:{} data:{}", id, type, data);
            }

            @Override
            public void onFailure(EventSource eventSource, Throwable t, okhttp3.Response response) {
                log.error("失败 code:{} message:{}", response.code(), response.message());
            }
        });
        // 阻塞等待
        Thread.sleep(100000);
    }

    /**
     * 测试聊天对话，及多轮对话。
     */
    @Test
    public void test_chat_completions() {
        // 创建参数，上下文对话。
        // 第一次的问题
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.BuildBaseChatCompletionRequest("1+1=");
        // 第一次的回复
        chatCompletionRequest.getMessages().add(Message.builder().role(Constants.Role.ASSISTANT.getRoleName()).content("2").build());
        // 第二次的问题
        chatCompletionRequest.getMessages().add(Message.builder().role(Constants.Role.USER.getRoleName()).content("2+2=").build());
        // 询问第二次的问题的结果
        ChatCompletionResponse chatCompletionResponse = aggregationSession.getChatSession().chatCompletions(NULL, NULL, NULL, chatCompletionRequest);
        // 解析结果
        chatCompletionResponse.getChoices().forEach(e -> {
            log.info("测试结果：{}", e.getMessage());
        });
    }

    /**
     * 测试聊天对话，流式返回结果。
     */
    @Test
    public void test_chat_completions_stream() throws InterruptedException, JsonProcessingException {
        ChatCompletionRequest chatCompletion = ChatCompletionRequest.builder().stream(true)
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER.getRoleName()).content("1+1=").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getModuleName())
                .build();

        aggregationSession.getChatSession().chatCompletions(NULL, NULL, NULL, chatCompletion, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                log.info("测试结果 id:{} type:{} data:{}", id, type, data);
            }

            @Override
            public void onFailure(EventSource eventSource, Throwable t, Response response) {
                log.error("失败 code:{} message:{}", response.code(), response.message());
            }
        });
        // 阻塞等待
        Thread.sleep(100000);
    }

    /**
     * 测试 CompletableFuture 异步调用。
     */
    @Test
    public void test_chat_completions_future() throws JsonProcessingException, InterruptedException, ExecutionException {
        // 构造请求参数
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .stream(true)
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER.getRoleName()).content("1+1=").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getModuleName())
                .build();
        // 等待结果
        CompletableFuture<String> future = aggregationSession.getChatSession().chatCompletionsFuture(NULL, NULL, NULL, chatCompletion);
        log.info("测试结果：{}", future.get());
    }

}
