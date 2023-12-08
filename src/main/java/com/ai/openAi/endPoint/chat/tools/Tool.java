package com.ai.openAi.endPoint.chat.tools;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tool implements Serializable {

    /**
     * 目前只支持：function
     *
     * @see Type
     */
    private String type;

    private ToolFunction function;

    @Getter
    @AllArgsConstructor
    public enum Type {
        FUNCTION("function"),
        ;
        private final String name;
    }
}
