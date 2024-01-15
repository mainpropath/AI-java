package com.ai.openai.endPoint.fineTuning;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FineTuningEvent implements Serializable {

    private String object;

    private String id;

    /**
     * 创建微调作业时的 Unix 时间戳
     */
    @JsonProperty("created_at")
    private Long createdAt;

    private String level;

    private String message;

    private String type;

}
