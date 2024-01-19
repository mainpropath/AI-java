package com.ai.spark.endPoint.chat.req;

import com.ai.spark.endPoint.chat.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRequest {

    @JsonProperty("header")
    private ChatHeader chatHeader;
    @JsonProperty("parameter")
    private ChatParameter chatParameter;
    @JsonProperty("payload")
    private ChatPayload chatPayload;

    public static ChatRequest baseBuild(String question, String appId) {
        ChatHeader chatHeader = ChatHeader.builder().appId(appId).build();
        Chat chat = Chat.builder().domain(Chat.General.generalV3.getMsg()).build();
        ChatParameter chatParameter = ChatParameter.builder().chat(chat).build();
        ChatText chatText = ChatText.builder().role(ChatText.Role.USER.getRoleName()).content(question).build();
        Message message = Message.builder().chatTexts(new ArrayList<>(Arrays.asList(chatText))).build();
        ChatPayload chatPayload = ChatPayload.builder().message(message).build();
        return ChatRequest.builder().chatHeader(chatHeader).chatParameter(chatParameter).chatPayload(chatPayload).build();
    }

}
