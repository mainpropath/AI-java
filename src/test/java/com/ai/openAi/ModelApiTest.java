package com.ai.openAi;

import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.endPoint.models.ModelObject;
import com.ai.openAi.endPoint.models.resp.DeleteFineTuneModelResponse;
import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.session.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.ai.openAi.common.Constants.NULL;

/**
 * @Description: 测试模型相关接口功能
 **/
@Slf4j
public class ModelApiTest {

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
     * 测试列出模型接口
     */
    @Test
    public void test_list_model() {
        List<ModelObject> modelObjects = aggregationSession.getModelSession().listModelCompletions(NULL, NULL, NULL);
        log.info("返回结果：{}", modelObjects);
    }

    /**
     * 测试检索模型接口
     */
    @Test
    public void test_retrieve_model() {
        ModelObject modelObject = aggregationSession.getModelSession().retrieveModelCompletions(NULL, NULL, NULL, "gpt-3.5-turbo-instruct");
        log.info("返回结果：{}", modelObject);
    }


    /**
     * 测试删除微调模型接口
     */
    @Test
    public void test_delete_fine_tune_model() {
        DeleteFineTuneModelResponse deleteFineTuneModelResponse = aggregationSession.getModelSession().deleteFineTuneModelCompletions(NULL, NULL, NULL, "gpt-3.5-turbo-instruct");
        log.info("返回结果：{}", deleteFineTuneModelResponse);
    }


}
