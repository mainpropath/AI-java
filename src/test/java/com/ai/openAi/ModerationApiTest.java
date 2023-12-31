package com.ai.openAi;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openAi.endPoint.moderations.req.ModerationRequest;
import com.ai.openAi.endPoint.moderations.resp.ModerationResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
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
     * 测试审核
     */
    @Test
    public void test_moderation() {
        ModerationRequest moderationRequest = ModerationRequest.BuildBaseModerationRequest("你好");
        ModerationResponse moderationResponse = aggregationSession.getModerationSession().moderationCompletions(NULL, NULL, NULL, moderationRequest);
        log.info("测试结果：{}", moderationResponse);
    }

}
