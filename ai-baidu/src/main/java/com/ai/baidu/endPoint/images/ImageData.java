package com.ai.baidu.endPoint.images;

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
public class ImageData {

    /**
     * 固定值"image"
     */
    private String object;

    /**
     * 图片base64编码内容
     */
    @JsonProperty("b64_image")
    private String b64Image;

    /**
     * 序号
     */
    private Integer index;

}
