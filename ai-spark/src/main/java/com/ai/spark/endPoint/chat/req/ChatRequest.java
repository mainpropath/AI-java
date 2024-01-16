package com.ai.spark.endPoint.chat.req;

import com.ai.spark.endPoint.chat.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRequest {

    private Header header;
    @JsonProperty("parameter")
    private ChatParameter chatParameter;
    private Payload payload;

    public static ChatRequest buildChatRequest(String question, String appId) {
        Header header = Header.builder().appId(appId).build();
        Chat chat = Chat.builder().domain(Chat.General.generalV3.getMsg()).build();
        ChatParameter chatParameter = ChatParameter.builder().chat(chat).build();
        ChatText chatText = ChatText.builder().role(ChatText.Role.USER.getRoleName()).content(question).build();
        Message message = Message.builder().chatTexts(Arrays.asList(chatText)).build();
        Payload payload = Payload.builder().message(message).build();
        return ChatRequest.builder().header(header).chatParameter(chatParameter).payload(payload).build();
    }

}
