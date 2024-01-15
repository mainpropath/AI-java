package com.ai.spark.endPoint.chat.req;

import com.ai.spark.endPoint.chat.ChatParameter;
import com.ai.spark.endPoint.chat.Header;
import com.ai.spark.endPoint.chat.Payload;
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
public class ChatRequest {

    private Header header;
    @JsonProperty("parameter")
    private ChatParameter chatParameter;
    private Payload payload;

}
