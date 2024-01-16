package com.ai.spark.endPoint.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chat {

    /**
     * 必传
     * 指定访问的领域,general指向V1.5版本,generalv2指向V2版本,generalv3指向V3版本 。注意：不同的取值对应的url也不一样！
     * 取值为[general,generalv2,generalv3]
     */
    private String domain;

    /**
     * 非必传
     * 核采样阈值。用于决定结果随机性，取值越高随机性越强即相同的问题得到的不同答案的可能性越高
     * 取值范围 (0，1] ，默认值0.5
     */
    private Double temperature;

    /**
     * 非必传
     * 模型回答的tokens的最大长度
     * V1.5取值为[1,4096]
     * V2.0取值为[1,8192]，默认为2048。
     * V3.0取值为[1,8192]，默认为2048。
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;

    /**
     * 非必传
     * 从k个候选中随机选择⼀个（⾮等概率）
     * 取值为[1，6],默认为4
     */
    @JsonProperty("top_k")
    private Integer topK;

    /**
     * 非必传
     * 用于关联用户会话，需要保障用户下的唯一性
     */
    @JsonProperty("chat_id")
    private String chatId;

    @Getter
    @AllArgsConstructor
    public enum General {
        general("general"),
        generalV2("generalv2"),
        generalV3("generalv3");
        private String msg;
    }

}
