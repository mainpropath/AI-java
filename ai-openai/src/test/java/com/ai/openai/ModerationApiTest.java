package com.ai.openai;

import com.ai.core.strategy.impl.FirstKeyStrategy;
import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.defaults.DefaultOpenAiSessionFactory;
import com.ai.openai.achieve.standard.session.AggregationSession;
import com.ai.openai.endPoint.moderations.Categories;
import com.ai.openai.endPoint.moderations.Result;
import com.ai.openai.endPoint.moderations.req.ModerationRequest;
import com.ai.openai.endPoint.moderations.resp.ModerationResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

import static com.ai.core.exception.Constants.NULL;

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
        configuration.setKeyList(Arrays.asList("**************************"));
        // 4. 设置请求时 key 的使用策略，默认实现了：随机获取 和 固定第一个Key 两种方式。
        configuration.setKeyStrategy(new FirstKeyStrategy<String>());
//        configuration.setKeyStrategy(new RandomKeyStrategy<String>());
        // 5. 设置代理，若不需要可不设置
        configuration.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
        // 6. 创建 session 工厂，制造不同场景的 session
        DefaultOpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    /**
     * 测试审核
     */
    @Test
    public void test_moderation() {
        ModerationRequest moderationRequest = ModerationRequest.baseBuild("你好");
        ModerationResponse moderationResponse = aggregationSession.getModerationSession().moderationCompletions(NULL, NULL, NULL, moderationRequest);
        // 输出这个句子违规的地方
        for (Result res : moderationResponse.getResults()) {
            Categories categories = res.getCategories();
            Field[] declaredFields = categories.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                // 确保字段是布尔类型
                if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                    try {
                        field.setAccessible(true);// 设置可访问私有字段
                        if (field.getBoolean(categories)) System.out.println(field.getName());// 获取字段的值
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.err.println(moderationResponse);
//        ModerationResponse(id=modr-90qdTsE58uw0VxcsUd0PLASlTMakf, model=text-moderation-007, results=[Result(flagged=true, categories=Categories(hate=false, hateThreatening=false, harassment=false, harassmentThreatening=true, selfHarm=false, selfHarmIntent=false, selfHarmInstructions=false, sexual=false, sexualMinors=false, violence=true, violenceGraphic=false), categoryScores=CategoryScores(hate=0.0003277489449828863, hateThreatening=0.000023320022592088208, harassment=0.4399510324001312, harassmentThreatening=0.3999234139919281, selfHarm=0.00005479905303218402, selfHarmIntent=0.000011957466085732449, selfHarmInstructions=6.901122446834052E-7, sexual=0.000021965763153275475, sexualMinors=2.833799328527675E-7, violence=0.9975500702857971, violenceGraphic=0.00007630318577867001))])

        log.info("测试结果：{}", moderationResponse);
    }

}
