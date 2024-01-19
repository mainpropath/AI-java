package com.ai.spark.interceptor;

import com.ai.common.exception.BaseException;
import com.ai.common.exception.Constants;
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
        // 2. 排除webSocket连接，判断返回状态
        if (!"websocket".equalsIgnoreCase(response.header("Upgrade"))
                && !"Upgrade".equalsIgnoreCase(response.header("Connection"))
                && !response.isSuccessful()
                && response.body() != null) {
            // 2.1 获取返回的错误信息
            log.error(response.body().string());
            throw new BaseException(Constants.ErrorMsg.RETRY_ERROR);
        }
        return response;
    }
}
