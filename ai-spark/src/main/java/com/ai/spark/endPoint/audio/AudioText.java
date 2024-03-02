package com.ai.spark.endPoint.audio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class AudioText {

    /**
     * ⽂本编码
     */
    private String encoding;

    /**
     * ⽂本压缩格式
     */
    private String compress;

    /**
     * ⽂本格式
     */
    private String format;

    /**
     * 数据状态
     */
    private Integer status;

    /**
     * 数据序号
     */
    private Integer seq;

    /**
     * ⽂本数据
     */
    private String text;

}
