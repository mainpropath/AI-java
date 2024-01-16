package com.ai.spark.interceptor;

import com.ai.common.strategy.KeyStrategy;
import com.ai.spark.achieve.ApiData;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;


public class UrlInterceptor implements Interceptor {

    /**
     * 系统设置的  apiKey
     */
    private final List<ApiData> apiKeyBySystem;

    /**
     * 系统设置的 api 请求地址
     */
    private final String apiHostBySystem;

    /**
     * key 获取策略
     */
    private KeyStrategy<List<String>, String> keyStrategy;

    public UrlInterceptor(List<ApiData> apiKeyBySystem, String apiHostBySystem, KeyStrategy keyStrategy) {
        this.apiKeyBySystem = apiKeyBySystem;
        this.apiHostBySystem = apiHostBySystem;
        this.keyStrategy = keyStrategy;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalReq = chain.request();
        return chain.proceed(originalReq);
    }
}
