package com.ai.spark.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


@Slf4j
public class BaseUrlInterceptor implements Interceptor {
    // TODO 对路径进行拦截
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        return chain.proceed(request);
    }
}

