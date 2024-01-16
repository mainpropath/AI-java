package com.ai.spark.common;

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
public class UsageText {

    /**
     * 保留字段，可忽略
     */
    @JsonProperty("question_tokens")
    private Integer questionTokens;

    /**
     * 包含历史问题的总tokens大小
     */
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;

    /**
     * 回答的tokens大小
     */
    @JsonProperty("completion_tokens")
    private Integer completionTokens;

    /**
     * prompt_tokens和completion_tokens的和
     */
    @JsonProperty("total_tokens")
    private Integer totalTokens;

}
