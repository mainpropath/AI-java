package com.ai.baidu.achieve;

import com.ai.baidu.achieve.standard.api.BaiduApiServer;
import com.ai.common.config.BaseConfiguration;
import lombok.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Description: baidu 相关配置信息
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
    private BaiduApiServer baiduApiServer;

    /**
     * api Key 集合
     */
    @NotNull
    private List<ApiData> keyList;

    public ApiData getSystemApiData() {
        return (ApiData) this.getKeyStrategy().apply(keyList);
    }

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(this.getOkHttpClient());
    }

}
