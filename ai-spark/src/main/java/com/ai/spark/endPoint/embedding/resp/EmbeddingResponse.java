package com.ai.spark.endPoint.embedding.resp;

import com.ai.spark.endPoint.embedding.EmbeddingHeader;
import com.ai.spark.endPoint.embedding.EmbeddingPayload;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingResponse {

    @JsonProperty("header")
    private EmbeddingHeader embeddingHeader;

    @JsonProperty("payload")
    private EmbeddingPayload embeddingPayload;
}
