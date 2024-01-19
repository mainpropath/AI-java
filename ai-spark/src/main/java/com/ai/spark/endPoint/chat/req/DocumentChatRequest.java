package com.ai.spark.endPoint.chat.req;

import com.ai.spark.endPoint.chat.ChatText;
import com.ai.spark.endPoint.chat.document.ChatExtends;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentChatRequest {

    private ChatExtends chatExtends;

    /**
     * 必传
     * 提问问题检索的文件 id 列表
     */
    private List<String> fileIds;

    /**
     * 问答内容列表，按时间正序，最后一条为最新提问
     */
    @JsonProperty("messages")
    private List<ChatText> chatTexts;

    public static DocumentChatRequest baseBuild(String question, List<String> fileIds) {
        ChatText chatText = ChatText.builder().role(ChatText.Role.USER.getRoleName()).content(question).build();
        return DocumentChatRequest.builder()
                .fileIds(fileIds)
                .chatTexts(Arrays.asList(chatText))
                .build();
    }

}
