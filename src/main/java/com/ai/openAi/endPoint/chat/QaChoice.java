package com.ai.openAi.endPoint.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 简单问答模型返回的信息
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaChoice implements Serializable {

    /**
     * 回答内容
     */
    private String text;

    private int index;

    private Object logprobs;

    /**
     * 模型停止生成令牌的原因
     */
    @JsonProperty("finish_reason")
    private String finishReason;
}
