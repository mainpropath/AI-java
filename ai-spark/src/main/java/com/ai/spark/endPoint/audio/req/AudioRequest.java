package com.ai.spark.endPoint.audio.req;

import com.ai.spark.endPoint.audio.AudioHeader;
import com.ai.spark.endPoint.audio.AudioParameter;
import com.ai.spark.endPoint.audio.AudioPayload;
import com.ai.spark.endPoint.audio.Tts;
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
public class AudioRequest {

    /**
     * 协议头部，⽤于描述平台特性的参数
     */
    private AudioHeader header;

    /**
     * AI 能⼒功能参数，⽤于控制 AI 引擎特性的开关。
     */
    private AudioParameter parameter;

    private AudioPayload payload;

    public static AudioRequest baseBuild(Tts.Vcn vcn, String appId) {
        AudioRequest request = AudioRequest.builder()
                .header(AudioHeader.builder().appId(appId).status(0).build())
                .parameter(AudioParameter.builder().tts(Tts.builder().vcn(vcn.getName()).build()).build())
                .build();
        return request;
    }

}
