package com.ai.openAi.endPoint.chat;

import lombok.*;

/**
 * @Author: jianglinhong
 * @Description: TODO
 * @DateTime: 2023/12/8 14:22
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFormat {

    /**
     * 默认：text
     */
    private String type;

    @Getter
    @AllArgsConstructor
    public enum Type {
        JSON_OBJECT("json_object"),
        TEXT("text"),
        ;
        private final String name;
    }
}
