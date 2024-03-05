package com.ai.openai.achieve.standard;

import com.ai.openai.achieve.standard.api.OpenaiApiServer;
import com.ai.openai.achieve.standard.interfaceSession.AggregationSession;
import okhttp3.OkHttpClient;

/**
 * @description 会话工厂
 */
public interface OpenAiSessionFactory {

    /**
     * 创建聚合过后的session
     *
     * @return 默认的聚合session
     */
    AggregationSession openAggregationSession();

    /**
     * 获取 Api 信息
     *
     * @param okHttpClient 客户端
     * @return api信息
     */
    OpenaiApiServer createOpenAiApiServer(OkHttpClient okHttpClient);

    /**
     * 获取 httpClient
     *
     * @return 客户端
     */
    OkHttpClient createHttpClient();
}
