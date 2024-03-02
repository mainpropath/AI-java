package com.ai.spark.endPoint.audio.resp;

import com.ai.spark.endPoint.audio.AudioHeader;
import com.ai.spark.endPoint.audio.AudioPayload;
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
public class AudioResponse {

    private AudioHeader header;

    private AudioPayload payload;

}
