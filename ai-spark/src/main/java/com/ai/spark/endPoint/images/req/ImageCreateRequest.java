package com.ai.spark.endPoint.images.req;

import com.ai.spark.endPoint.chat.ChatText;
import com.ai.spark.endPoint.chat.Message;
import com.ai.spark.endPoint.images.ImageHeader;
import com.ai.spark.endPoint.images.ImageParameter;
import com.ai.spark.endPoint.images.ImagePayload;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageCreateRequest {

    @JsonProperty("header")
    private ImageHeader imageHeader;

    @JsonProperty("parameter")
    private ImageParameter imageParameter;

    @JsonProperty("payload")
    private ImagePayload imagePayload;

    public static ImageCreateRequest baseBuild(String content, String appId) {
        return ImageCreateRequest
                .builder()
                .imageHeader(ImageHeader.builder().appId(appId).build())
                .imageParameter(ImageParameter.builder().build())
                .imagePayload(ImagePayload.builder().message(Message.builder().chatTexts(Arrays.asList(ChatText.baseBuild(ChatText.Role.USER, content))).build()).build())
                .build();
    }

}
