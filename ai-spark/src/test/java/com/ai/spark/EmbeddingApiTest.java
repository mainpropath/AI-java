package com.ai.spark;


import com.ai.common.strategy.impl.FirstKeyStrategy;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.DefaultSparkSessionFactory;
import com.ai.spark.achieve.standard.SparkSessionFactory;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.endPoint.chat.ChatText;
import com.ai.spark.endPoint.embedding.req.EmbeddingRequest;
import com.ai.spark.endPoint.embedding.resp.EmbeddingResponse;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Base64;

import static com.ai.spark.endPoint.chat.ChatText.buildChatText;
import static com.ai.spark.endPoint.embedding.req.EmbeddingRequest.buildEmbeddingRequest;

public class EmbeddingApiTest {


    private AggregationSession aggregationSession;

    @Before
    public void before() {
        // 1. 创建配置类
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://spark-api.xf-yun.com");
        // 3. 设置鉴权所需的API Key,可设置多个。
        ApiData apiData = ApiData.builder()
                .apiKey("***********************")
                .apiSecret("***********************")
                .appId("***********************")
                .build();
        configuration.setKeyList(Arrays.asList(apiData));
        // 4. 设置请求时 key 的使用策略，默认实现了：随机获取 和 固定第一个Key 两种方式。
        configuration.setKeyStrategy(new FirstKeyStrategy<ApiData>());
//        configuration.setKeyStrategy(new RandomKeyStrategy<ApiData>());
        // 5. 设置代理，若不需要可不设置
//        configuration.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
        // 6. 创建 session 工厂，制造不同场景的 session
        SparkSessionFactory factory = new DefaultSparkSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    @Test
    public void test_embedding() {
        // TODO 请求不成功，还需要再测试
        ChatText chatText = buildChatText(ChatText.Role.USER, "这是一段文字");
        EmbeddingRequest request = buildEmbeddingRequest(chatText, "c8f362b8");
        EmbeddingResponse response = aggregationSession.getEmbeddingSession().embed(request);
        String text = response.getEmbeddingPayload().getFeature().getText();
        byte[] text_bytes = Base64.getDecoder().decode(text);
        ByteBuffer bb = ByteBuffer.wrap(text_bytes).order(ByteOrder.LITTLE_ENDIAN);
        float[] res = new float[text_bytes.length / 4];
        bb.asFloatBuffer().get(res);
    }

}
