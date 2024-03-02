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
public class Pybuf {

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
     * 0:开始, 1:开始, 2:结束
     */
    private Integer status;

    /**
     * 数据序号
     * 最⼩值:0, 最⼤值:9999999
     */
    private Integer seq;

    /**
     * ⽂本数据
     * 最⼩尺⼨:0B, 最⼤尺⼨:1048576B
     */
    private String text;
}
