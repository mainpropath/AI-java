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
public class AudioPayload {

    /**
     * 待合成⽂本
     */
    private AudioText text;

    /**
     * ⽤户原始输⼊，场景化合成开启时必传，不开启为⾮必传
     */
    @JsonProperty("user_text")
    private AudioText userText;

    /**
     * 响应数据块
     */
    private Audio audio;

    /**
     * 响应数据块
     */
    private Pybuf pybuf;

}
