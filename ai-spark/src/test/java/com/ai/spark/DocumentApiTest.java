package com.ai.spark;

import com.ai.common.strategy.impl.FirstKeyStrategy;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.DefaultSparkSessionFactory;
import com.ai.spark.achieve.standard.SparkSessionFactory;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.endPoint.document.req.FileUploadRequest;
import com.ai.spark.endPoint.document.resp.DocumentSummaryResponse;
import com.ai.spark.endPoint.document.resp.FileUploadResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

public class DocumentApiTest {

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
        File file = new File("D:\\chatGPT-api\\AI-java\\doc\\test\\test_file_upload.pdf");
        FileUploadRequest request = FileUploadRequest.builder().file(file).build();
        FileUploadResponse fileUploadResponse = this.aggregationSession.getDocumentSession().fileUpload(request);
        System.out.println(fileUploadResponse);
//        FileUploadResponse(code=0, sid=28db14303e054046aabd2e96e7e65c51, desc=null, data=Data(fileId=1a477e7e9cb44e23ad4abd98076e3f70))
//        FileUploadResponse(code=0, sid=8e4f267415d84827a6ec7a1580e1ce64, desc=null, data=Data(fileId=004c3c6e79bc4d738a7e94a12697ea75))
    }

    // 文档总结和文档总结查询这两个接口其实只有请求路径不同，类似于异步的效果。
    // 调用文档总结接口，并不会直接返回结果，而是通知模型开始进行总结。
    // 然后调用文档总结查询接口查询结果，如果结果已经存在的情况下，不管是调用文档总结接口还是文档总结查询接口，返回的数据都是一样的。
    @Test
    public void test_document_summary_start() {
        DocumentSummaryResponse documentSummaryResponse = this.aggregationSession.getDocumentSession()
                .documentSummaryStart("004c3c6e79bc4d738a7e94a12697ea75");
        System.out.println(documentSummaryResponse);
    }

    @Test
    public void test_document_summary_query() {
        DocumentSummaryResponse documentSummaryResponse = this.aggregationSession.getDocumentSession()
                .documentSummaryQuery("004c3c6e79bc4d738a7e94a12697ea75");
        System.out.println(documentSummaryResponse);
    }
}
