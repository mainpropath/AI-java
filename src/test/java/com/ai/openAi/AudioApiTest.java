package com.ai.openAi;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.session.DefaultOpenAiSessionFactory;
import com.ai.openAi.achieve.defaults.strategy.FirstKeyStrategy;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.interfaceSession.AggregationSession;
import com.ai.openAi.endPoint.audio.req.SttCompletionRequest;
import com.ai.openAi.endPoint.audio.req.TtsCompletionRequest;
import com.ai.openAi.endPoint.audio.resp.SttCompletionResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

import static com.ai.openAi.common.Constants.NULL;

/**
 * @Description: 测试语音相关接口功能
 **/
@Slf4j
public class AudioApiTest {

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
        // 5. 设置代理，若不需要可不设置
        configuration.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
        // 6. 创建 session 工厂，制造不同场景的 session
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        this.aggregationSession = factory.openAggregationSession();
    }

    /**
     * 测试文字转语音
     */
    @Test
    public void test_tts() throws InterruptedException {
        // 定义请求参数
        TtsCompletionRequest ttsCompletionRequest = TtsCompletionRequest.builder()
                .model(TtsCompletionRequest.Model.tts_1.getModuleName())// 设置使用的模型
                .input("你好，我是chatGPT")
                .voice(TtsCompletionRequest.Voice.alloy.getVoiceName())// 设置声音的样式
                .build();
        // 回传文件存放的路径
        File file = new File("src/main/resources/test.mp3");
        // 添加回调函数，发送请求
        aggregationSession.getAudioSession().ttsCompletions(NULL, NULL, NULL, ttsCompletionRequest, new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try (InputStream inputStream = response.body().byteStream();
                             OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
                            // 创建文件
                            if (!file.exists()) {
                                if (!file.getParentFile().exists()) file.getParentFile().mkdir();
                                file.createNewFile();
                            }
                            byte data[] = new byte[10240];
                            int len;
                            while ((len = inputStream.read(data, 0, 8192)) != -1) {
                                os.write(data, 0, len);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                }
        );
        // 阻塞等待
        Thread.sleep(100000);
    }

    /**
     * 测试语音转文字
     */
    @Test
    public void test_stt() {
        SttCompletionRequest sttCompletionRequest = SttCompletionRequest
                .builder()
                .file(new File("src/main/resources/test.mp3"))
                .build();
        SttCompletionResponse sttCompletionResponse = this.aggregationSession.getAudioSession().sttCompletions(NULL, NULL, NULL, sttCompletionRequest);
        log.info("测试结果：{}", sttCompletionResponse);
    }

    /**
     * 测试音频文件转文字后翻译为英文
     */
    @Test
    public void test_translation() {
        SttCompletionRequest sttCompletionRequest = SttCompletionRequest
                .builder()
                .file(new File("src/main/resources/test.mp3"))
                .build();
        SttCompletionResponse sttCompletionResponse = this.aggregationSession.getAudioSession().translationCompletions(NULL, NULL, NULL, sttCompletionRequest);
        log.info("测试结果：{}", sttCompletionResponse);
    }

}
