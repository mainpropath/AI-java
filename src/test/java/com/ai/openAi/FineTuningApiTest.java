package com.ai.openAi;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openAi.common.CommonListResponse;
import com.ai.openAi.endPoint.fineTuning.FineTuningEvent;
import com.ai.openAi.endPoint.fineTuning.req.FineTuningRequest;
import com.ai.openAi.endPoint.fineTuning.req.ListFineTuningRequest;
import com.ai.openAi.endPoint.fineTuning.resp.FineTuningResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

import static com.ai.openAi.common.Constants.NULL;

/**
 * @Description: 测试微调相关接口功能
 **/
@Slf4j
public class FineTuningApiTest {

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
     * 测试创建微调
     */
    @Test
    public void test_create_fine_tuning() {
        FineTuningRequest fineTuningRequest = FineTuningRequest.builder().trainingFile("file-BK7bzQj3FfZFXr7DbL6xJwfo").build();
        FineTuningResponse fineTuningResponse = aggregationSession.getFineTuningSession().createFineTuningJobCompletions(NULL, NULL, NULL, fineTuningRequest);
        log.info("返回结果：{}", fineTuningResponse);
    }

    /**
     * 测试列出微调作业
     */
    @Test
    public void test_list_fine_tuning_1() {
        ListFineTuningRequest listFineTuningRequest = ListFineTuningRequest.builder().build();
        CommonListResponse<FineTuningResponse> fineTuneJobListFineTuningResponse = aggregationSession.getFineTuningSession().listFineTuningJobsCompletions(NULL, NULL, NULL, listFineTuningRequest);
        log.info("返回结果：{}", fineTuneJobListFineTuningResponse);
    }

    /**
     * 测试列出微调作业
     */
    @Test
    public void test_list_fine_tuning_2() {
        CommonListResponse<FineTuningResponse> fineTuneJobListFineTuningResponse = aggregationSession.getFineTuningSession().listFineTuningJobsCompletions(NULL, NULL, NULL, null, null);
        log.info("返回结果：{}", fineTuneJobListFineTuningResponse);
    }

    /**
     * 测试检索微调作业
     */
    @Test
    public void test_retrieve_fine_tuning() {
        FineTuningResponse fineTuningResponse = aggregationSession.getFineTuningSession().retrieveFineTuningJobCompletions(NULL, NULL, NULL, "ft-AF1WoRqd3aJAHsqc9NY7iL8F");
        log.info("返回结果：{}", fineTuningResponse);
    }

    /**
     * 测试关闭微调作业
     */
    @Test
    public void test_cancel_fine_tuning() {
        FineTuningResponse fineTuningResponse = aggregationSession.getFineTuningSession().cancelFineTuningJobCompletions(NULL, NULL, NULL, "ft-AF1WoRqd3aJAHsqc9NY7iL8F");
        log.info("返回结果：{}", fineTuningResponse);
    }

    /**
     * 测试列出微调事件
     */
    @Test
    public void test_list_fine_tuning_events() {
        CommonListResponse<FineTuningEvent> fineTuningEventListFineTuningResponse = aggregationSession.getFineTuningSession().listFineTuningEventsCompletions(NULL, NULL, NULL, "ftjob-abc123");
        log.info("返回结果：{}", fineTuningEventListFineTuningResponse);

    }

}
