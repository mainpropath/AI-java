package com.ai.spark.endPoint.images.req;

import com.ai.spark.endPoint.chat.ChatText;
import com.ai.spark.endPoint.chat.Message;
import com.ai.spark.endPoint.images.ImageHeader;
import com.ai.spark.endPoint.images.ImagePayload;
import com.ai.spark.endPoint.images.ImageUnderstandingParameter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageUnderstandingRequest {

    @JsonProperty("header")
    private ImageHeader imageHeader;

    @JsonProperty("parameter")
    private ImageUnderstandingParameter imageUnderstandingParameter;

    @JsonProperty("payload")
    private ImagePayload imagePayload;

    public static ImageUnderstandingRequest baseBuild(String content, String appId, File image) {
        byte[] data = null;
        try (FileInputStream fileInputStream = new FileInputStream(image)) {
            data = new byte[fileInputStream.available()];
            fileInputStream.read(data); // 读取文件数据到数组中
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageStr = Base64.getEncoder().encodeToString(data); // 将byte数组进行base64编码
        ImageHeader imageHeader = ImageHeader.builder().appId(appId).build();
        ImageUnderstandingParameter imageUnderstandingParameter = ImageUnderstandingParameter.builder().build();
        ChatText chatText = ChatText.baseBuild(ChatText.Role.USER, content);
        chatText.setContentType(ChatText.ContentType.TEXT.getType());
        ChatText imgText = ChatText.baseBuild(ChatText.Role.USER, imageStr);
        imgText.setContentType(ChatText.ContentType.IMAGE.getType());
        Message message = Message.builder().chatTexts(new ArrayList<>(Arrays.asList(imgText, chatText))).build();
        ImagePayload imagePayload = ImagePayload.builder().message(message).build();
        return ImageUnderstandingRequest
                .builder()
                .imageHeader(imageHeader)
                .imagePayload(imagePayload)
                .imageUnderstandingParameter(imageUnderstandingParameter).build();

    }

}
