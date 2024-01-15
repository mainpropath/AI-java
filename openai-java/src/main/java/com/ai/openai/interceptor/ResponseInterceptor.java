package com.ai.openai.interceptor;

import cn.hutool.json.JSONUtil;
import com.ai.openai.common.CommonListResponse;
import com.ai.openai.common.Constants;
import com.ai.openai.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 1. 获取 req 和 resp
        Request original = chain.request();
        Response response = chain.proceed(original);
        // 2. 判断返回状态
        if (!response.isSuccessful() && response.body() != null) {
            // 2.1 获取返回的错误信息
            String errorMsg = response.body().string();
            CommonListResponse openAiResponse = JSONUtil.toBean(errorMsg, CommonListResponse.class);
            if (Constants.ERROR_MSG_MAP.containsKey(response.code())) {
                log.error(openAiResponse.getError().getMessage());
                throw new BaseException(openAiResponse.getError().getMessage());
            }
            log.error("--------> 请求异常：{}", errorMsg);
            if (Objects.nonNull(openAiResponse.getError())) {
                log.error(openAiResponse.getError().getMessage());
                throw new BaseException(openAiResponse.getError().getMessage());
            }
            throw new BaseException(Constants.ErrorMsg.RETRY_ERROR);
        }
        return response;
    }
}
