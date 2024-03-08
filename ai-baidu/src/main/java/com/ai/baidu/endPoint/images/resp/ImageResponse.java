package com.ai.baidu.endPoint.images.resp;

import com.ai.baidu.common.Usage;
import com.ai.baidu.endPoint.images.ImageData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageResponse implements Serializable {

    /**
     * 请求的id
     */
    private String id;

    /**
     * 回包类型。image：图像生成返回
     */
    private String object;

    /**
     * 时间戳
     */
    private Integer created;

    /**
     * 生成图片结果
     */
    private List<ImageData> data;

    /**
     * token统计信息，token数 = 汉字数+单词数*1.3 （仅为估算逻辑）
     */
    private Usage usage;

}
