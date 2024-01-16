package com.ai.spark.achieve;

import com.ai.common.strategy.KeyStrategy;
import com.ai.spark.achieve.standard.api.SparkApiServer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.net.Proxy;
import java.util.List;

/**
 * @Description: 配置信息
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    /**
     * api 服务提供者
     */
    private SparkApiServer sparkApiServer;

    /**
     * api 请求客户端
     */
    private OkHttpClient okHttpClient;

    /**
     * api Key 集合
     */
    @NotNull
    private List<ApiData> keyList;

    /**
     * 请求地址
     */
    @NotNull
    private String apiHost;

    /**
     * 获取key的策略
     */
    private KeyStrategy keyStrategy;

    /**
     * 代理信息
     */
    private Proxy proxy;

}
