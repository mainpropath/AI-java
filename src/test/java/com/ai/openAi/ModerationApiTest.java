package com.ai.openAi;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.session.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openAi.endPoint.moderations.req.ModerationRequest;
import com.ai.openAi.endPoint.moderations.resp.ModerationResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.ai.openAi.common.Constants.NULL;

/**
 * @Description: 测试审核相关接口功能
 **/
@Slf4j
public class ModerationApiTest {

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
    public void test_moderation() {
        ModerationRequest moderationRequest = ModerationRequest.BuildBaseModerationRequest("你好");
        ModerationResponse moderationResponse = aggregationSession.getModerationSession().moderationCompletions(NULL, NULL, NULL, moderationRequest);
        log.info("测试结果：{}", moderationResponse);
    }

}
