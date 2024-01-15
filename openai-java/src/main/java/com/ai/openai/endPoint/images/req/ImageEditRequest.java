package com.ai.openai.endPoint.images.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageEditRequest {

    /**
     * 必选项：描述文字，最多1000字符
     */
    @NonNull
    private String prompt;

    /**
     * 为每个提示生成的完成次数。
     */
    @Builder.Default
    private Integer n = 1;

    /**
     * 256x256
     * 512x512
     * 1024x1024
     */
    @Builder.Default
    private String size = SizeEnum.size_1024.getName();

    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = ResponseFormat.URL.getName();

    private String user;

    /**
     * 构造基础请求参数
     *
     * @param prompt 提示词
     * @return 请求参数
     */
    public static ImageEditRequest BuildBaseImageEditRequest(String prompt) {
        return ImageEditRequest.builder().prompt(prompt).build();
    }

    @Getter
    @AllArgsConstructor
    public enum SizeEnum implements Serializable {
        size_1024_1792("1024x1792"),
        size_1792_1024("1792x1024"),
        size_1024("1024x1024"),
        size_512("512x512"),
        size_256("256x256"),
        ;
        private final String name;

    }

    @AllArgsConstructor
    @Getter
    public enum ResponseFormat implements Serializable {
        URL("url"),
        B64_JSON("b64_json"),
        ;

        private final String name;
    }

}
