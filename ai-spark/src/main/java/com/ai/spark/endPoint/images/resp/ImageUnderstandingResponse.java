package com.ai.spark.endPoint.images.resp;

import com.ai.spark.endPoint.images.ImageHeader;
import com.ai.spark.endPoint.images.ImageUnderstandingPayload;
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
public class ImageUnderstandingResponse {

    @JsonProperty("header")
    private ImageHeader imageHeader;

    @JsonProperty("payload")
    private ImageUnderstandingPayload imageUnderstandingPayload;

}
