package com.ai.spark.achieve;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiData {

    private String appid;
    private String apiKey;
    private String apiSecret;

}
