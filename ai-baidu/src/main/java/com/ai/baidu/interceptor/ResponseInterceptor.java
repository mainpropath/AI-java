package com.ai.baidu.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 返回信息拦截器
 */
@Slf4j
public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 1. 获取 req 和 resp
        Request original = chain.request();
        Response response = chain.proceed(original);
        if (!response.isSuccessful() && response.body() != null) {
            log.error("--------> 请求异常：{}", response.body().string());
        }
        return response;
    }
}
