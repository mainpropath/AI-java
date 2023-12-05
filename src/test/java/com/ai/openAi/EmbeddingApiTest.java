package com.ai.openAi;

import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.endPoint.embeddings.req.EmbeddingCompletionRequest;
import com.ai.openAi.endPoint.embeddings.resp.EmbeddingCompletionResponse;
import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.session.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

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
        Configuration configuration = new Configuration();
//        configuration.setApiHost("https://api.openai.com");
        configuration.setApiHost("填入你的API 请求地址");
        configuration.setKeyList(Arrays.asList("填入你的API Key"));
        configuration.setKeyStrategy(new FirstKeyStrategy());
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    @Test
    public void test_embedding() {
        EmbeddingCompletionResponse embeddingCompletionResponse = aggregationSession.getEmbeddingSession().embeddingCompletions(NULL, NULL, NULL, "你好");
        log.info("返回结果：{}", embeddingCompletionResponse);
    }


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
