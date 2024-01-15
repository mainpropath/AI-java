package com.ai.openai.interceptor;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import com.ai.openai.achieve.standard.interfaceStrategy.KeyStrategy;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import static com.ai.openai.common.Constants.*;

/**
 * @Description: OpenAI Key 拦截器
 **/
public class HeaderInterceptor implements Interceptor {

    /**
     * 系统设置的 openAI apiKey
     */
    private final List<String> apiKeyBySystem;

    /**
     * 系统设置的 api 请求地址
     */
    private final String apiHostBySystem;

    /**
     * key 获取策略
     */
    private KeyStrategy<List<String>, String> keyStrategy;

    public HeaderInterceptor(List<String> apiKeyBySystem, String apiHostBySystem, KeyStrategy keyStrategy) {
        this.apiKeyBySystem = apiKeyBySystem;
        this.apiHostBySystem = apiHostBySystem;
        this.keyStrategy = keyStrategy;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 1. 获取原始 Request
        Request originalReq = chain.request();
        // 2. 读取 apiKey；优先使用用户传递的 apiKey
        String apiKeyByUser = originalReq.header(API_KEY);
        String apiHostByUser = originalReq.header(API_HOST);
        String apiUrlByUser = originalReq.header(URL);
        String apiKey = apiKeyByUser == NULL ? keyStrategy.apply(apiKeyBySystem) : apiKeyByUser;
        // 3. 读取 apiUrl 和 apiHost，apiUrl 优先级大于 apiHost
        String apiUrl = apiUrlByUser == NULL ? apiHostByUser == NULL ? String.valueOf(originalReq.url()) : apiHostByUser + originalReq.url().url().getPath() : apiUrlByUser;
        // 4. 构建 Request
        Request request = originalReq.newBuilder()
                .url(apiUrl)
                .header(Header.AUTHORIZATION.getValue(), "Bearer " + apiKey)
                .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .method(originalReq.method(), originalReq.body())
                .build();
        // 5. 返回执行结果
        return chain.proceed(request);
    }
}
