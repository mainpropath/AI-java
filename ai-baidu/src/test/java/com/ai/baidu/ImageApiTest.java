package com.ai.baidu;


import com.ai.baidu.achieve.ApiData;
import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.defaults.DefaultBaiduSessionFactory;
import com.ai.baidu.achieve.standard.session.AggregationSession;
import com.ai.baidu.endPoint.images.ImageData;
import com.ai.baidu.endPoint.images.req.ImageRequest;
import com.ai.baidu.endPoint.images.resp.ImageResponse;
import com.ai.common.utils.ImageUtils;
import com.ai.core.strategy.impl.FirstKeyStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 图片相关功能点测试
 **/
public class ImageApiTest {

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
        DefaultBaiduSessionFactory factory = new DefaultBaiduSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    @Test
    public void test_text2image() {
        ImageRequest imageRequest = ImageRequest.baseBuild("画一个哆啦A梦");
        ImageResponse imageResponse = aggregationSession.getImageSession().text2image(imageRequest);
        // 得到结果当中的base64 图片字符串
        List<ImageData> data = imageResponse.getData();
        for (int i = 0; i < data.size(); i++) {
            ImageUtils.convertBase64StrToImage(data.get(i).getB64Image(), "D:\\chatGPT-api\\AI-java\\doc\\test\\test_baidu_create_image_" + i + ".png");
        }
    }

}
