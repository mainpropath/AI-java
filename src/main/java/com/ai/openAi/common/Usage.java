package com.ai.openAi.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usage {

    /**
     * 请求消耗的token
     */
    @JsonProperty("prompt_tokens")
    private int promptTokens;

    /**
     * 回答消耗的token
     */
    @JsonProperty("completion_tokens")
    private int completionTokens;

    /**
     * 总共消耗的token
     */
    @JsonProperty("total_tokens")
    private int totalTokens;
}
