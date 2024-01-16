package com.ai.spark;


import com.ai.common.strategy.impl.FirstKeyStrategy;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.DefaultSparkSessionFactory;
import com.ai.spark.achieve.defaults.listener.ChatListener;
import com.ai.spark.achieve.standard.SparkSessionFactory;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.common.Usage;
import com.ai.spark.endPoint.chat.req.ChatRequest;
import com.ai.spark.endPoint.chat.resp.ChatResponse;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static com.ai.spark.common.SparkDesk.SPARK_API_HOST_WSS_V3_1;

public class ChatApiTest {

    private AggregationSession aggregationSession;

    @Before
    public void before() {
        // 1. 创建配置类
        Configuration configuration = new Configuration();
        // 2. 设置请求地址，若有代理商或者代理服务器，可填写为代理服务器的请求路径
        configuration.setApiHost(SPARK_API_HOST_WSS_V3_1);
        // 3. 设置鉴权所需的API Key,可设置多个。
        ApiData apiData = ApiData.builder()
                .apiKey("*****************")
                .apiSecret("*****************")
                .appid("*****************")
                .build();
        configuration.setKeyList(Arrays.asList(apiData));
        // 4. 设置请求时 key 的使用策略，默认实现了：随机获取 和 固定第一个Key 两种方式。
        configuration.setKeyStrategy(new FirstKeyStrategy<ApiData>());
//        configuration.setKeyStrategy(new RandomKeyStrategy());
        // 5. 设置代理，若不需要可不设置
//        configuration.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
        // 6. 创建 session 工厂，制造不同场景的 session
        SparkSessionFactory factory = new DefaultSparkSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    @Test
    public void test_chat() {
        ChatRequest chatRequest = ChatRequest.buildChatRequest("讲一个笑话", "c8f362b8");
        aggregationSession.getChatSession().chat(new ChatListener(chatRequest) {
            @SneakyThrows
            @Override
            public void onChatError(ChatResponse chatResponse) {
                System.err.println(chatResponse);
            }

            @Override
            public void onChatOutput(ChatResponse chatResponse) {
//                System.err.println(chatResponse);
                System.out.print(chatResponse.getPayload().getChoice().getTexts().get(0).getContent());
            }

            @Override
            public void onChatEnd() {
                System.out.println("当前会话结束了");
            }

            @Override
            public void onChatToken(Usage usage) {
                System.out.println("token 信息：" + usage);
            }
        });

        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
