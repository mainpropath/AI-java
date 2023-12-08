package com.ai.openAi.endPoint.chat;

import com.ai.openAi.endPoint.chat.msg.BaseMessage;
import com.ai.openAi.endPoint.chat.msg.DefaultMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 聊天接口模型返回的信息
 **/
@Data
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
