package com.ai.openai.endPoint.moderations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    /**
     * 内容是否违反 OpenAI 的使用政策
     */
    private boolean flagged;

    /**
     * 类别列表，以及它们是否被标记
     */
    private Categories categories;

    /**
     * 类别列表及其按模型预测的分数
     */
    @JsonProperty("category_scores")
    private CategoryScores categoryScores;

    /**
     * 原文内容
     */
    @JsonIgnore
    private String content;
}
