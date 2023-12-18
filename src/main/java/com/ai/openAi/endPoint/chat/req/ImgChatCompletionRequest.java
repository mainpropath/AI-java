package com.ai.openAi.endPoint.chat.req;

import com.ai.openAi.endPoint.chat.msg.ImgMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 输入图片对话
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImgChatCompletionRequest extends BaseChatCompletionRequest implements Serializable {

    /**
     * 构成对话的消息列表
     */
    @NonNull
    private List<ImgMessage> messages;

}
