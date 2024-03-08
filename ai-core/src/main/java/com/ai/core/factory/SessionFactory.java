package com.ai.core.factory;


import okhttp3.OkHttpClient;

public interface SessionFactory<Session, ApiServer> {

    /**
     * 创建聚合过后的session
     *
     * @return 默认的聚合session
     */
    Session openAggregationSession();

    /**
     * 获取 Api 信息
     *
     * @param okHttpClient 客户端
     * @return api信息
     */
    ApiServer createApiServer(OkHttpClient okHttpClient);

    /**
     * 获取 httpClient
     *
     * @return 客户端
     */
    OkHttpClient createHttpClient();

}
