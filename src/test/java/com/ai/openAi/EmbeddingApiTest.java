package com.ai.openAi;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openAi.endPoint.embeddings.req.EmbeddingCompletionRequest;
import com.ai.openAi.endPoint.embeddings.resp.EmbeddingCompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ai.openAi.common.Constants.NULL;

/**
 * @Description: 测试嵌入相关接口功能
 */
@Slf4j
public class EmbeddingApiTest {

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
     * 测试单个文本
     */
    @Test
    public void test_embedding() {
        EmbeddingCompletionResponse embeddingCompletionResponse = aggregationSession.getEmbeddingSession().embeddingCompletions(NULL, NULL, NULL, "你好");
        log.info("返回结果：{}", embeddingCompletionResponse);
    }


    /**
     * 测试多个文本嵌入
     */
    @Test
    public void test_embedding_list() {
        List<String> inputList = new ArrayList<>();
        inputList.add("你好");
        inputList.add("世界");
        EmbeddingCompletionResponse embeddingCompletionResponse = aggregationSession.getEmbeddingSession().embeddingCompletions(NULL, NULL, NULL, inputList);
        log.info("返回结果：{}", embeddingCompletionResponse);
    }

    @Test
    public void test_embedding_req() {
        List<String> inputList = new ArrayList<>();
        inputList.add("你好");
        inputList.add("世界");
        EmbeddingCompletionRequest embeddingCompletionRequest = EmbeddingCompletionRequest.BuildBaseEmbeddingCompletionRequest(inputList);
        EmbeddingCompletionResponse embeddingCompletionResponse = aggregationSession.getEmbeddingSession().embeddingCompletions(NULL, NULL, NULL, embeddingCompletionRequest);
        log.info("返回结果：{}", embeddingCompletionResponse);
    }

}
