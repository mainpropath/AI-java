package com.ai.spark;

import com.ai.common.strategy.impl.FirstKeyStrategy;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.DefaultSparkSessionFactory;
import com.ai.spark.achieve.standard.SparkSessionFactory;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.endPoint.file.req.FileUploadRequest;
import com.ai.spark.endPoint.file.resp.FileUploadResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

public class FileApiTest {

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
    public void test_file_upload() {
        File file = new File("D:\\chatGPT-api\\AI-java\\doc\\test\\test_file_upload.txt");
        FileUploadRequest request = FileUploadRequest.builder().file(file).build();
        FileUploadResponse fileUploadResponse = this.aggregationSession.getFileSession().fileUpload(request);
        System.out.println(fileUploadResponse);
    }
}
