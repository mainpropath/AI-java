package com.ai.spark;


import com.ai.common.strategy.impl.FirstKeyStrategy;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.DefaultSparkSessionFactory;
import com.ai.spark.achieve.defaults.listener.ChatListener;
import com.ai.spark.achieve.defaults.listener.DocumentChatListener;
import com.ai.spark.achieve.standard.SparkSessionFactory;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.common.Usage;
import com.ai.spark.endPoint.chat.ChatText;
import com.ai.spark.endPoint.chat.req.ChatRequest;
import com.ai.spark.endPoint.chat.req.DocumentChatRequest;
import com.ai.spark.endPoint.chat.resp.ChatResponse;
import com.ai.spark.endPoint.chat.resp.DocumentChatResponse;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class ChatApiTest {

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

    /**
     * 测试聊天功能
     */
    @Test
    public void test_chat() {
        // 创建参数
        ChatRequest request = ChatRequest.baseBuild("讲一个笑话", "c8f362b8");
        // 设置参数并发起请求，监听事件信息
        aggregationSession.getChatSession().chat(new ChatListener(request) {
            @SneakyThrows
            @Override
            public void onChatError(ChatResponse chatResponse) {
                System.out.println(chatResponse);
            }

            @Override
            public void onChatOutput(ChatResponse chatResponse) {
                System.out.print(chatResponse.getChatPayload().getChoice().getTexts().get(0).getContent());
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
        // 等待会话结束
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试多轮聊天功能
     */
    @Test
    public void test_chat_multiple() {
        // 创建参数
        ChatRequest request = ChatRequest.baseBuild("1+1=", "c8f362b8");
        // 设置第一轮对话的结果
        ChatText chatText1 = ChatText.baseBuild(ChatText.Role.ASSISTANT, "2");
        // 设置第二轮对话的问题
        ChatText chatText2 = ChatText.baseBuild(ChatText.Role.USER, "2+2=");
        // 将对话过程注入到参数当中
        request.getChatPayload().getMessage().getChatTexts().add(chatText1);
        request.getChatPayload().getMessage().getChatTexts().add(chatText2);
        // 设置参数并发起请求，监听事件信息
        aggregationSession.getChatSession().chat(new ChatListener(request) {
            @SneakyThrows
            @Override
            public void onChatError(ChatResponse chatResponse) {
                System.out.println(chatResponse);
            }

            @Override
            public void onChatOutput(ChatResponse chatResponse) {
                System.out.print(chatResponse.getChatPayload().getChoice().getTexts().get(0).getContent());
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
        // 等待会话结束
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试文档对话功能
     */
    @Test
    public void test_document_chat() {
        // 构建参数
        DocumentChatRequest documentChatRequest = DocumentChatRequest.baseBuild("总结一下故事一说了什么？", Arrays.asList("c42a68fd31964d43b4f57eab11e9a833"));
        // 设置阐述并发起请求
        aggregationSession.getChatSession().documentChat(new DocumentChatListener(documentChatRequest) {
            @SneakyThrows
            @Override
            public void onChatError(DocumentChatResponse documentChatResponse) {
                System.err.println(documentChatResponse);
            }

            @Override
            public void onChatOutput(DocumentChatResponse documentChatResponse) {
                System.out.println(documentChatResponse);
            }

            @Override
            public void onChatEnd() {
                System.out.println("当前会话结束了");
            }
        });
        // 等待会话结束
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
