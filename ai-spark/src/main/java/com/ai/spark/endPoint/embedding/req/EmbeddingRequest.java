package com.ai.spark.endPoint.embedding.req;

import com.ai.common.utils.JsonUtils;
import com.ai.spark.endPoint.chat.ChatText;
import com.ai.spark.endPoint.embedding.EmbeddingHeader;
import com.ai.spark.endPoint.embedding.EmbeddingMessage;
import com.ai.spark.endPoint.embedding.EmbeddingParameter;
import com.ai.spark.endPoint.embedding.EmbeddingPayload;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Base64;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingRequest {

    @JsonProperty("header")
    private EmbeddingHeader embeddingHeader;

    @JsonProperty("parameter")
    private EmbeddingParameter embeddingParameter;

    @JsonProperty("payload")
    private EmbeddingPayload embeddingPayload;


    public static EmbeddingRequest baseBuild(ChatText text, String appId) {
        return EmbeddingRequest.builder()
                .embeddingHeader(EmbeddingHeader.builder().appId(appId).build())
                .embeddingParameter(EmbeddingParameter.builder().build())
                .embeddingPayload(EmbeddingPayload.builder().embeddingMessage(EmbeddingMessage
                        .builder()
                        .text(Base64.getEncoder().encodeToString(JsonUtils.toJson(text).getBytes()))
                        .build()).build()
                )
                .build();
    }
}
