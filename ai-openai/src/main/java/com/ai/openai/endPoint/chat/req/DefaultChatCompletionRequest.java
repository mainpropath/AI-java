package com.ai.openai.endPoint.chat.req;

import com.ai.common.exception.Constants;
import com.ai.openai.endPoint.chat.msg.DefaultMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 默认对话方式
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultChatCompletionRequest extends BaseChatCompletionRequest implements Serializable {

    /**
     * 构成对话的消息列表
     */
    @NonNull
    private List<DefaultMessage> messages;

    /**
     * 是否为流式输出，默认值为 false
     */
    private Boolean stream;

    /**
     * 构造基础的 DefaultChatCompletionRequest
     *
     * @param question 问题内容
     */
    public static DefaultChatCompletionRequest BuildDefaultChatCompletionRequest(String question) {
        return DefaultChatCompletionRequest
                .builder()
                .messages(new ArrayList<>(Collections.singletonList(DefaultMessage.builder().role(Constants.Role.USER.getRoleName()).content(question).build())))
                .build();
    }

    /**
     * 上下文对话时，添加问答内容
     *
     * @param message 问答内容
     */
    public void addMessage(DefaultMessage message) {
        this.messages.add(message);
    }

    /**
     * 上下文对话时，添加问答内容
     *
     * @param role    角色
     * @param content 内容
     */
    public void addMessage(String role, String content) {
        this.addMessage(DefaultMessage.builder().role(role).content(content).build());
    }

}
