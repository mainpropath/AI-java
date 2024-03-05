package com.ai.baidu;

import com.ai.baidu.achieve.ApiData;
import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.defaults.DefaultBaiduSessionFactory;
import com.ai.baidu.achieve.standard.BaiduSessionFactory;
import com.ai.baidu.achieve.standard.interfaceSession.AggregationSession;
import com.ai.baidu.endPoint.chat.Message;
import com.ai.baidu.endPoint.chat.req.ChatRequest;
import com.ai.baidu.endPoint.chat.resp.ChatResponse;
import com.ai.common.strategy.impl.FirstKeyStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Description: 测试聊天接口相关接口功能
 */
@Slf4j
public class ChatApiTest {

    private AggregationSession aggregationSession;

    private Configuration configuration;

    @Before
    public void test_BaiduSessionFactory() {
        // 1. 创建配置类
        this.configuration = new Configuration();
        // 2. 设置请求地址，若有代理商或者代理服务器，可填写为代理服务器的请求路径
        configuration.setApiHost("https://aip.baidubce.com");
        // 3. 设置鉴权所需的API Key,可设置多个。
        ApiData apiData = ApiData.builder()
                .apiKey("**************************")
                .secretKey("**************************")
                .appId("**************************")
                .build();
        configuration.setKeyList(Arrays.asList(apiData));
        // 4. 设置请求时 key 的使用策略，默认实现了：随机获取 和 固定第一个Key 两种方式。
        configuration.setKeyStrategy(new FirstKeyStrategy<ApiData>());
//        configuration.setKeyStrategy(new RandomKeyStrategy<ApiData>());
        // 5. 设置代理，若不需要可不设置
//        configuration.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
        // 6. 创建 session 工厂，制造不同场景的 session
        BaiduSessionFactory factory = new DefaultBaiduSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    /**
     * 测试鉴权接口
     */
    @Test
    public void test_auth() {
        // 获取用户配置的ApiData
        ApiData systemApiData = configuration.getSystemApiData();
        // 鉴权
        String accessToken = configuration.getBaiduApiServer().getAccessToken(systemApiData.getApiKey(), systemApiData.getSecretKey());
        System.out.println(accessToken);
    }

    /**
     * 测试聊天功能
     */
    @Test
    public void test_chat() {
        // 发起聊天
        ChatResponse chat = aggregationSession
                .getChatSession()// 获取 chatSession
                .chat(null, ChatRequest.baseBuild(Message.Role.USER, "你能讲一个笑话吗？"));// 构造一个基础的聊天请求体
        System.out.println(chat.getResult());
    }

}
