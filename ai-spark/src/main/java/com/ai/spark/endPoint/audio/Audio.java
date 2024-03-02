package com.ai.spark.endPoint.audio;

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
public class Audio {

    /**
     * ⾳频编码
     */
    private String encoding;

    /**
     * ⾳频编码
     */
    @JsonProperty("sample_rate")
    private Integer sampleRate;

    /**
     * 声道数
     */
    private Integer channels;

    /**
     * 位深
     */
    @JsonProperty("bit_depth")
    private Integer bitDepth;

    /**
     * 帧⼤⼩
     */
    @JsonProperty("frame_size")
    private Integer frameSize;

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
     * ⾳频数据
     * 最⼩尺⼨:0B, 最⼤尺⼨:10485760B
     */
    private String audio;

}
