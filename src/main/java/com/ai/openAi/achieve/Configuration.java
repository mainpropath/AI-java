package com.ai.openAi.achieve;

import com.ai.openAi.achieve.standard.api.ApiServer;
import com.ai.openAi.achieve.standard.interfaceStrategy.KeyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Description: 配置信息
 **/
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    private ApiServer apiServer;

    private OkHttpClient okHttpClient;

    @NotNull
    private List<String> keyList;

    @NotNull
    private String apiHost;

    private KeyStrategy keyStrategy;

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

}
