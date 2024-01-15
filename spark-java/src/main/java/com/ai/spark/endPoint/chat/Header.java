package com.ai.spark.endPoint.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header {

    /**
     * 应用appid，从开放平台控制台创建的应用中获取
     */
    @JsonProperty("app_id")
    private String appId;

    /**
     * 每个用户的id，用于区分不同用户
     */
    private String uid;

}
