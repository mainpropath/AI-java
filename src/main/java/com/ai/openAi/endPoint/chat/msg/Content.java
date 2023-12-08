package com.ai.openAi.endPoint.chat.msg;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @Description: 图片问答信息
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content {

    /**
     * 输入类型
     */
    private String type;

    /**
     * 问题内容
     */
    private String text;

    /**
     * 图片地址
     */
    @JsonProperty("image_url")
    private ImageUrl imageUrl;

    /**
     * 构建 text 类型的 content
     */
    public static Content BuildTextContent(String text) {
        return Content.builder().type(Type.TEXT.getName()).text(text).build();
    }

    /**
     * 构建 image_url 类型的 content
     */
    public static Content BuildImageUrlContent(String imageUrl) {
        return Content.builder().type(Type.IMAGE_URL.getName()).text(imageUrl).build();
    }

    /**
     * 输入类型
     */
    @Getter
    @AllArgsConstructor
    public enum Type {
        TEXT("text"),
        IMAGE_URL("image_url"),
        ;
        private final String name;
    }
}
