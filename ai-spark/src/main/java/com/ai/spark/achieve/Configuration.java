package com.ai.spark.achieve;

import com.ai.common.config.BaseConfiguration;
import com.ai.spark.achieve.standard.api.SparkApiServer;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Description: 配置信息
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
    private SparkApiServer sparkApiServer;

    /**
     * api Key 集合
     */
    @NotNull
    private List<ApiData> keyList;

    public ApiData getSystemApiData() {
        return (ApiData) this.getKeyStrategy().apply(keyList);
    }

}
