package com.ai.spark.endPoint.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatText {

    /**
     * user表示是用户的问题，assistant表示AI的回复
     * 取值为[user,assistant]
     */
    private String role;

    /**
     * 用户和AI的对话内容
     * 所有content的累计tokens需控制8192以内
     */
    private String content;

    /**
     * 结果序号，取值为[0,10];
     */
    private Integer index;

    /**
     * 数据的类型
     */
    @JsonProperty("content_type")
    private String contentType;


    public static ChatText baseBuild(Role role, String content) {
        return ChatText.builder().role(role.getRoleName()).content(content).build();
    }

    @Getter
    @AllArgsConstructor
    public enum Role {

        USER("user"),
        ASSISTANT("assistant"),
        ;

        private String RoleName;
    }

    @Getter
    @AllArgsConstructor
    public enum ContentType {

        TEXT("text"),
        IMAGE("image"),
        ;

        private String type;
    }

}
