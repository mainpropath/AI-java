package com.ai.baidu.achieve.standard;

import com.ai.baidu.achieve.standard.api.BaiduApiServer;
import com.ai.baidu.achieve.standard.interfaceSession.AggregationSession;
import okhttp3.OkHttpClient;

/**
 * @Description: 会话工厂
 **/
public interface BaiduSessionFactory {

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
    BaiduApiServer createBaiduApiServer(OkHttpClient okHttpClient);

}
