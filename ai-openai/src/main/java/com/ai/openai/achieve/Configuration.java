package com.ai.openai.achieve;

import com.ai.core.config.BaseConfiguration;
import com.ai.openai.achieve.standard.api.OpenaiApiServer;
import lombok.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Description: openai 相关配置信息
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Configuration extends BaseConfiguration {

    /**
     * api 服务提供者
     */
    private OpenaiApiServer openaiApiServer;

    /**
     * api Key 集合
     */
    @NotNull
    private List<String> keyList;

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(this.getOkHttpClient());
    }


    public String getSystemApiData() {
        return (String) this.getKeyStrategy().apply(keyList);
    }

}
