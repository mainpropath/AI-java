package com.ai.openAi;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openAi.endPoint.images.ImageObject;
import com.ai.openAi.endPoint.images.req.CreateImageRequest;
import com.ai.openAi.endPoint.images.req.ImageEditRequest;
import com.ai.openAi.endPoint.images.req.ImageVariationRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

import static com.ai.openAi.common.Constants.NULL;

/**
 * @Description: 测试图片相关接口功能
 **/
@Slf4j
public class ImageApiTest {
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
     * 测试图片生成
     */
    @Test
    public void test_create_image() {
        CreateImageRequest createImageRequest = CreateImageRequest.BuildBaseCreateImageRequest("森林里有一只小熊，小熊在吃蜂蜜。");
        List<ImageObject> imageObjectList = aggregationSession.getImageSession().createImageCompletions(NULL, NULL, NULL, createImageRequest);
        log.info("测试结果：{}", imageObjectList);
    }

    /**
     * 测试编辑图片
     */
    @Test
    public void test_edit_image() {
        File file = new File("doc/test/test_edit_image.png");
        ImageEditRequest imageEditRequest = ImageEditRequest.BuildBaseImageEditRequest("给小熊的背后加上一只梅花鹿。");
        List<ImageObject> imageObjectList = aggregationSession.getImageSession().editImageCompletions(NULL, NULL, NULL, file, null, imageEditRequest);
        log.info("测试结果：{}", imageObjectList);
    }

    /**
     * 测试创建图片变体
     */
    @Test
    public void test_variation_image() {
        File file = new File("doc/test/test_edit_image.png");
        ImageVariationRequest imageVariationRequest = ImageVariationRequest.builder().build();
        List<ImageObject> imageObjectList = aggregationSession.getImageSession().variationImageCompletions(NULL, NULL, NULL, file, imageVariationRequest);
        log.info("测试结果：{}", imageObjectList);
    }
}
