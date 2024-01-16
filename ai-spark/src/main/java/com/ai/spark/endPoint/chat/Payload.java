package com.ai.spark.endPoint.chat;

import com.ai.spark.common.Usage;
import com.ai.spark.endPoint.chat.function.Function;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload {

    private Message message;

    @JsonProperty("functions")
    private Function function;

    // 以下是请求返回时所需参数
    @JsonProperty("choices")
    private Choice choice;

    private Usage usage;

}
