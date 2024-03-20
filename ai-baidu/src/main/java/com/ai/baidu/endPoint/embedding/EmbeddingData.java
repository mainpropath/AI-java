package com.ai.baidu.endPoint.embedding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingData {

    /**
     * 固定值"embedding"
     */
    private String object;

    /**
     * embedding 内容
     */
    private double[] embedding;

    /**
     * 序号
     */
    private Integer index;

    /**
     * 原文
     */
    @JsonIgnore
    private String content;

}
