package com.ai.openAi;

import cn.hutool.json.JSONObject;
import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openAi.common.Constants;
import com.ai.openAi.endPoint.chat.Parameters;
import com.ai.openAi.endPoint.chat.msg.Content;
import com.ai.openAi.endPoint.chat.msg.DefaultMessage;
import com.ai.openAi.endPoint.chat.msg.ImgMessage;
import com.ai.openAi.endPoint.chat.req.*;
import com.ai.openAi.endPoint.chat.resp.ChatCompletionResponse;
import com.ai.openAi.endPoint.chat.resp.QaCompletionResponse;
import com.ai.openAi.endPoint.chat.tools.Tool;
import com.ai.openAi.endPoint.chat.tools.ToolFunction;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
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
        // 1. 创建配置类
        Configuration configuration = new Configuration();
        // 2. 设置请求地址，若有代理商或者代理服务器，可填写为代理服务器的请求路径
        configuration.setApiHost("https://api.openai.com");
        // 3. 设置鉴权所需的API Key,可设置多个。
        configuration.setKeyList(Arrays.asList("填入你的API Key"));
        // 4. 设置请求时 key 的使用策略，默认实现了：随机获取 和 固定第一个Key 两种方式。
        configuration.setKeyStrategy(new FirstKeyStrategy());
//        configuration.setKeyStrategy(new RandomKeyStrategy());
        // 5. 设置代理，若不需要可不设置
        configuration.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
        // 6. 创建 session 工厂，制造不同场景的 session
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    /**
     * 测试简单问答，相当于只有一轮问答。
     */
    @Test
    public void test_qa_completions() {
        QaCompletionRequest qaCompletionRequest = QaCompletionRequest.buildBaseQaCompletionRequest("9*7=");
        QaCompletionResponse qaCompletionResponse = aggregationSession.getChatSession().qaCompletions(NULL, NULL, NULL, qaCompletionRequest);
        log.info("测试结果：{}", qaCompletionResponse);
    }

    /**
     * 测试简单问答，流式返回结果。
     */
    @Test
    public void test_qa_completions_stream() throws InterruptedException, JsonProcessingException {

        QaCompletionRequest qaCompletionRequest = QaCompletionRequest.builder()
                .prompt("讲一个笑话")
                .stream(true) // 设置流式返回
                .build();
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
        new CountDownLatch(1).await();
    }

    /**
     * 测试多轮对话
     */
    @Test
    public void test_chat_completions() {
        // 创建参数，上下文对话。
        // 第一次的问题
        DefaultChatCompletionRequest defaultChatCompletionRequest = DefaultChatCompletionRequest.BuildDefaultChatCompletionRequest("1+1=");
        // 第一次的回复
        defaultChatCompletionRequest.addMessage(Constants.Role.ASSISTANT.getRoleName(), "2");
        // 第二次的问题
        defaultChatCompletionRequest.addMessage(Constants.Role.USER.getRoleName(), "2+2=");
        // 询问第二次的问题的结果
        ChatCompletionResponse chatCompletionResponse = aggregationSession.getChatSession().chatCompletions(NULL, NULL, NULL, defaultChatCompletionRequest);
        // 解析结果
        chatCompletionResponse.getChoices().forEach(e -> {
            log.info("测试结果：{}", e.getMessage());
        });
    }

    /**
     * 测试函数对话，创建一个函数获取天气信息
     * 下面的请求参数根据官方案例转换而来
     */
    @Test
    public void test_func_chat_completions() {
        // 定义第一个属性，地址信息
        JSONObject location = new JSONObject();
        location.putOpt("type", "string");
        location.putOpt("description", "The city and state, e.g. San Francisco, CA");
        // 定义第二个属性，时间信息
        JSONObject unit = new JSONObject();
        unit.putOpt("type", "string");
        unit.putOpt("enum", Arrays.asList("celsius", "fahrenheit"));
        // 定义 properties，及将函数属性组合起来
        JSONObject properties = new JSONObject();
        properties.putOpt("location", location);
        properties.putOpt("unit", unit);
        // 定义 parameters
        Parameters parameters = Parameters.builder().type("object")
                .properties(properties)
                .required(Arrays.asList("location"))
                .build();
        // 构造函数信息
        ToolFunction toolFunction = ToolFunction.builder()
                .name("get_current_weather")
                .description("Get the current weather in a given location")
                .parameters(parameters).build();
        // 构造工具
        Tool tool = Tool.builder()
                .type(Tool.Type.FUNCTION.getName())
                .function(toolFunction).build();
        // 构造请求参数
        FuncChatCompletionRequest funcChatCompletionRequest = FuncChatCompletionRequest.BuildFuncChatCompletionRequest("What is the weather like in Boston?");
        funcChatCompletionRequest.setTools(Arrays.asList(tool));
        funcChatCompletionRequest.setToolChoice("auto");
        // 获取请求结果
        ChatCompletionResponse chatCompletionResponse = aggregationSession.getChatSession().chatCompletions(NULL, NULL, NULL, funcChatCompletionRequest);
        log.info("测试结果：{}", chatCompletionResponse);
    }

    /**
     * 测试图片对话
     */
    @Test
    public void test_img_chat_completions() {
        // 构造对话内容
        Content textContent = Content.BuildTextContent("这张图片当中有什么？");
        Content imgContent = Content.BuildImageUrlContent("https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Gfp-wisconsin-madison-the-nature-boardwalk.jpg/2560px-Gfp-wisconsin-madison-the-nature-boardwalk.jpg");
        // 构造 msg 内容
        ImgMessage imgMessage = ImgMessage.builder().role(Constants.Role.USER.getRoleName()).content(Arrays.asList(textContent, imgContent)).build();
        // 构造请求参数，chatGPT 4 支持图片对话
        ImgChatCompletionRequest imgChatCompletionRequest = ImgChatCompletionRequest.builder().model(BaseChatCompletionRequest.Model.GPT_4_VISION_PREVIEW.getModuleName()).messages(Arrays.asList(imgMessage)).build();
        // 获取结果
        ChatCompletionResponse chatCompletionResponse = aggregationSession.getChatSession().chatCompletions(NULL, NULL, NULL, imgChatCompletionRequest);
        log.info("测试结果：{}", chatCompletionResponse);
    }

    /**
     * 测试聊天对话，流式返回结果。
     */
    @Test
    public void test_chat_completions_stream() throws InterruptedException, JsonProcessingException {
        // 建造者模式构造参数
        DefaultChatCompletionRequest defaultChatCompletionRequest = DefaultChatCompletionRequest.builder()
                .stream(true)// 开启流式返回
                .messages(Collections.singletonList(DefaultMessage.builder().role(Constants.Role.USER.getRoleName()).content("1+1=").build()))
                .model(BaseChatCompletionRequest.Model.GPT_3_5_TURBO.getModuleName())
                .build();

        aggregationSession.getChatSession().chatCompletions(NULL, NULL, NULL, defaultChatCompletionRequest, new EventSourceListener() {
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
        new CountDownLatch(1).await();
    }

    /**
     * 测试 CompletableFuture 异步调用。
     */
    @Test
    public void test_chat_completions_future() throws JsonProcessingException, InterruptedException, ExecutionException {
        // 构造请求参数
        DefaultChatCompletionRequest defaultChatCompletionRequest = DefaultChatCompletionRequest
                .builder()
                .stream(true)
                .messages(Collections.singletonList(DefaultMessage.builder().role(Constants.Role.USER.getRoleName()).content("1+1=").build()))
                .model(BaseChatCompletionRequest.Model.GPT_3_5_TURBO.getModuleName())
                .build();
        // 等待结果
        CompletableFuture<String> future = aggregationSession.getChatSession().chatCompletionsFuture(NULL, NULL, NULL, defaultChatCompletionRequest);
        log.info("测试结果：{}", future.get());
    }

}
