package com.ai.openAi;

import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.endPoint.files.FileObject;
import com.ai.openAi.endPoint.files.resp.DeleteFileResponse;
import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.session.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.ai.openAi.common.Constants.NULL;

/**
 * @Description: 测试文件相关接口功能
 **/
@Slf4j
public class FilesApiTest {

    private AggregationSession aggregationSession;

    @Before
    public void test_OpenAiSessionFactory() {
        Configuration configuration = new Configuration();
//        configuration.setApiHost("https://api.openai.com");
        configuration.setApiHost("填入你的API 请求地址");
        configuration.setKeyList(Arrays.asList("填入你的API Key"));
        configuration.setKeyStrategy(new FirstKeyStrategy());
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
