package com.ai.spark;

import com.ai.common.strategy.impl.FirstKeyStrategy;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.DefaultSparkSessionFactory;
import com.ai.spark.achieve.standard.SparkSessionFactory;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.endPoint.images.req.ImageCreateRequest;
import com.ai.spark.endPoint.images.resp.ImageCreateResponse;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageApiTest {

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
     * 测试图片生成功能
     */
    @Test
    public void test_image_create() throws IOException {
        // 创建请求参数
        ImageCreateRequest request = ImageCreateRequest.baseBuild("画一个大海", "c8f362b8");
        // 发起请求获取结果
        ImageCreateResponse imageCreateResponse = aggregationSession.getImageSession().imageCreate(request);
        // 得到结果当中的base64 图片字符串
        String content = imageCreateResponse.getImagePayload().getChoice().getTexts().get(0).getContent();
        // 转换为byte数组
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(content.substring(content.indexOf(",") + 1));
        // 读取byte数组，存放到指定文件路径
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        File outputFile = new File("D:\\chatGPT-api\\AI-java\\doc\\test\\test_create_image.png");
        ImageIO.write(bufferedImage, "png", outputFile);
    }


}
