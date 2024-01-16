package com.ai.openai.achieve;

import com.ai.common.strategy.KeyStrategy;
import com.ai.openai.achieve.standard.api.OpenaiApiServer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;

import java.net.Proxy;
import java.util.List;

/**
 * @Description: 配置信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    /**
     * api 服务提供者
     */
    private OpenaiApiServer openaiApiServer;

    /**
     * api 请求客户端
     */
    private OkHttpClient okHttpClient;

    /**
     * api Key 集合
     */
    @NotNull
    private List<String> keyList;

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

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

}
