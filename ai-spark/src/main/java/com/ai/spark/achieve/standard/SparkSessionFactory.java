package com.ai.spark.achieve.standard;


import com.ai.spark.achieve.standard.api.SparkApiServer;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import okhttp3.OkHttpClient;

public interface SparkSessionFactory {

    /**
     * 创建聚合过后的session
     *
     * @return 默认的聚合session
     */
    AggregationSession openAggregationSession();

    /**
     * 获取 httpClient
     *
     * @return 客户端
     */
    OkHttpClient createHttpClient();

    /**
     * 获取 Api 信息
     *
     * @param okHttpClient 客户端
     * @return api信息
     */
    SparkApiServer createSparkAiApiServer(OkHttpClient okHttpClient);

}
