package com.ai.baidu.endPoint.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    /**
     * 当前支持以下：
     * user: 表示用户
     * assistant: 表示对话助手
     */
    private String role;

    /**
     * 对话内容
     */
    private String content;

    /**
     * message作者
     */
    private String name;

    public static Message baseBuild(Role role, String content) {
        return Message.builder().role(role.getRoleName()).content(content).build();
    }

    @Getter
    @AllArgsConstructor
    public enum Role {

        USER("user"),
        ASSISTANT("assistant"),
        ;

        private String RoleName;
    }

}
