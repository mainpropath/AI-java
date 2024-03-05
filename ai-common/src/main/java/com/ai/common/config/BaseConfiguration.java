package com.ai.common.config;

import com.ai.common.strategy.KeyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.net.Proxy;

/**
 * 各个模型配置基础类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseConfiguration {

    /**
     * api 请求客户端
     */
    public OkHttpClient okHttpClient;

    /**
     * 请求地址（很多情况下，这个apiHost都是一个摆设）
     */
    @NotNull
    public String apiHost;

    /**
     * 代理信息
     */
    public Proxy proxy;

    /**
     * 获取key的策略
     */
    public KeyStrategy keyStrategy;

}
