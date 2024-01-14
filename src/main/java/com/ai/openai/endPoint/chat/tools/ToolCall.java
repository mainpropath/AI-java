package com.ai.openai.endPoint.chat.tools;

import lombok.*;

import java.io.Serializable;

/**
 * @Description: 函数式对话返回的函数信息
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolCall implements Serializable {

    private String id;

    private String type;

    private ToolCallFunction function;

    @Getter
    @AllArgsConstructor
    public enum Type {
        FUNCTION("function"),
        ;
        private final String name;
    }
}
