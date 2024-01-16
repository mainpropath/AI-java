package com.ai.spark.endPoint.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Getter
    @AllArgsConstructor
    public enum Role {

        USER("user"),
        ASSISTANT("assistant"),
        ;

        private String RoleName;
    }

}
