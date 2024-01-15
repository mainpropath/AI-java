package com.ai.openai.endPoint.chat;

import com.ai.openai.endPoint.chat.msg.DefaultMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 聊天接口模型返回的信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatChoice implements Serializable {

    private long index;

    /**
     * stream = true时，由流式模型响应生成的聊天完成增量
     */
    private DefaultMessage delta;

    /**
     * stream = false时，模型生成的聊天完成消息
     */
    private DefaultMessage message;

    /**
     * 模型停止生成令牌的原因
     */
    @JsonProperty("finish_reason")
    private String finishReason;

}
