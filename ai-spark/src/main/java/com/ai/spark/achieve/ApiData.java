package com.ai.spark.achieve;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 记录用户API信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiData {

    private String appId;
    private String apiKey;
    private String apiSecret;

}
