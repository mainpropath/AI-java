package com.ai.openAi.endPoint.moderations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryScores implements Serializable {

    private BigDecimal hate;

    @JsonProperty("hate/threatening")
    private BigDecimal hateThreatening;

    /**
     * 对任何目标表达、煽动或宣扬骚扰性语言的内容。
     */
    @JsonProperty("harassment")
    private BigDecimal harassment;

    /**
     * 骚扰内容还包括对任何目标的暴力或严重伤害。
     */
    @JsonProperty("harassment/threatening")
    private BigDecimal harassmentThreatening;

    @JsonProperty("self-harm")
    private BigDecimal selfHarm;

    /**
     * 说话者表示他们正在或打算进行自残行为的内容，例如自杀、割伤和饮食失调。
     */
    @JsonProperty("self-harm/intent")
    private BigDecimal selfHarmIntent;

    /**
     * 鼓励进行自残行为（例如自杀、割伤和饮食失调）的内容，或者提供有关如何实施此类行为的说明或建议的内容。
     */
    @JsonProperty("self-harm/instructions")
    private BigDecimal selfHarmInstructions;

    private BigDecimal sexual;

    @JsonProperty("sexual/minors")
    private BigDecimal sexualMinors;

    private BigDecimal violence;

    @JsonProperty("violence/graphic")
    private BigDecimal violenceGraphic;

}
