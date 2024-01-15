package com.ai.openai;

import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.defaults.DefaultOpenAiSessionFactory;
import com.ai.openai.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openai.achieve.standard.OpenAiSessionFactory;
import com.ai.openai.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openai.endPoint.images.ImageObject;
import com.ai.openai.endPoint.images.req.CreateImageRequest;
import com.ai.openai.endPoint.images.req.ImageEditRequest;
import com.ai.openai.endPoint.images.req.ImageVariationRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

import static com.ai.openai.common.Constants.NULL;

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
//        configuration.setKeyList(Arrays.asList("填入你的API Key"));
        configuration.setKeyList(Arrays.asList("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"));
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
        CreateImageRequest createImageRequest = CreateImageRequest.BuildBaseCreateImageRequest("富有科技感的房屋");
        List<ImageObject> imageObjectList = aggregationSession.getImageSession().createImageCompletions(NULL, NULL, NULL, createImageRequest);
        for (ImageObject imageObject : imageObjectList) {
            System.out.println(imageObject);
        }
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