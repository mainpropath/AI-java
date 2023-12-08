package com.ai.openAi.endPoint.chat.msg;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 图片输入
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImgMessage extends BaseMessage implements Serializable {

    /**
     * 图片对话内容
     */
    private List<Content> content;

    /**
     * 添加类型为 text 的对话内容
     */
    public void addTextContent(String text) {
        addContent(Content.builder().type(Content.Type.TEXT.getName()).text(text).build());
    }

    /**
     * 添加类型为 image_url 的对话内容
     */
    public void addImageContent(String imageUrl) {
        addContent(Content.builder()
                .text(Content.Type.IMAGE_URL.getName())
                .imageUrl(ImageUrl.builder().url(imageUrl).build())
                .build());
    }

    /**
     * 添加对话内容
     */
    public void addContent(Content content) {
        this.content.add(content);
    }

}
