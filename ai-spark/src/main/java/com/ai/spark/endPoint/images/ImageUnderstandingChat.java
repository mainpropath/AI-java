package com.ai.spark.endPoint.images;

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
public class ImageUnderstandingChat {

    @Builder.Default
    private String domain = "general";

    private String auditing;

    private Double temperature;

    @JsonProperty("top_k")
    private Integer topK;

    @JsonProperty("max_tokens")
    private Integer maxTokens;

}
