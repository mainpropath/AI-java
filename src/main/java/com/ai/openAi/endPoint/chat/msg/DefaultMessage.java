package com.ai.openAi.endPoint.chat.msg;

import com.ai.openAi.endPoint.chat.tools.ToolCall;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 默认问答内容
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultMessage extends BaseMessage implements Serializable {

    /**
     * 问答内容
     */
    private String content;

    /**
     * 函数式对话时，返回的函数信息
     */
    @JsonProperty("tool_calls")
    private List<ToolCall> toolCalls;

}
