package com.ai.baidu.achieve;

import com.ai.baidu.achieve.standard.api.BaiduApiServer;
import com.ai.common.config.BaseConfiguration;
import lombok.*;
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
        return (ApiData) keyStrategy.apply(keyList);
    }


}
