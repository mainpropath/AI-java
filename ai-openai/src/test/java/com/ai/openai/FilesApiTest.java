package com.ai.openai;

import com.ai.common.strategy.impl.FirstKeyStrategy;
import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.defaults.DefaultOpenAiSessionFactory;
import com.ai.openai.achieve.standard.OpenAiSessionFactory;
import com.ai.openai.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openai.endPoint.files.FileObject;
import com.ai.openai.endPoint.files.resp.DeleteFileResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

import static com.ai.common.exception.Constants.NULL;

/**
 * @Description: 测试文件相关接口功能
 **/
@Slf4j
public class FilesApiTest {

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
        configuration.setKeyStrategy(new FirstKeyStrategy<String>());
//        configuration.setKeyStrategy(new RandomKeyStrategy<String>());
        // 5. 设置代理，若不需要可不设置
        configuration.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
        // 6. 创建 session 工厂，制造不同场景的 session
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    /**
     * 测试列出文件
     */
    @Test
    public void test_list_file() {
        List<FileObject> fileObjects = aggregationSession.getFilesSession().listFilesCompletions(NULL, NULL, NULL);
        log.info("测试结果：{}", fileObjects);
    }

    /**
     * 测试上传文件
     */
    @Test
    public void test_upload_file() {
        File file = new File("src/main/resources/test1.txt");
        FileObject fileObject = aggregationSession.getFilesSession().uploadFileCompletions(NULL, NULL, NULL, file, "fine-tune");
        log.info("测试结果：{}", fileObject);
    }

    /**
     * 测试删除文件
     */
    @Test
    public void test_delete_file() {
        DeleteFileResponse deleteFileResponse = aggregationSession.getFilesSession().deleteFileCompletions(NULL, NULL, NULL, "file-B3CAfSS2ibv7cFmSbl5m1CPI");
        log.info("测试结果：{}", deleteFileResponse);
    }

    /**
     * 测试检索文件
     */
    @Test
    public void test_retrieve_file() {
        FileObject fileObject = aggregationSession.getFilesSession().retrieveFileCompletions(NULL, NULL, NULL, "file-B3CAfSS2ibv7cFmSbl5m1CPI");
        log.info("测试结果：{}", fileObject);
    }

    /**
     * 测试获取检索的文件内容
     */
    @Test
    public void test_retrieve_file_context() {
        ResponseBody responseBody = aggregationSession.getFilesSession().retrieveFileContextCompletions(NULL, NULL, NULL, "file-B3CAfSS2ibv7cFmSbl5m1CPI");
        log.info("测试结果：{}", responseBody);
    }

}
