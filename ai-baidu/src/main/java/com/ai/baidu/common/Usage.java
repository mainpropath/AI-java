package com.ai.baidu.common;

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
public class Usage {

    /**
     * 问题tokens数
     */
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;

    /**
     * 回答tokens数
     */
    @JsonProperty("completion_tokens")
    private Integer completionTokens;

    /**
     * tokens总数
     */
    @JsonProperty("total_tokens")
    private Integer totalTokens;

}
