package com.ai.openai.endPoint.embeddings.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingCompletionRequest implements Serializable {

    /**
     * 输入要嵌入的文本，编码为字符串或标记数组。
     * 若要在单个请求中嵌入多个输入，请传递字符串数组或令牌数组。
     */
    @NonNull
    private List<String> input;

    /**
     * 要使用的模型的 ID。
     */
    @Builder.Default
    private String model = Model.TEXT_EMBEDDING_ADA_002.getModelName();

    /**
     * 要返回嵌入的格式。
     */
    @JsonProperty("encoding_format")
    private String encodingFormat;

    /**
     * 代表最终用户的唯一标识符
     */
    private String user;

    /**
     * 构造基础请求参数
     *
     * @param input 文本
     * @return 请求参数
     */
    public static EmbeddingCompletionRequest baseBuild(String input) {
        return baseBuild(Arrays.asList(input));
    }

    /**
     * 构造基础请求参数
     *
     * @param inputList 文本数组
     * @return 请求参数
     */
    public static EmbeddingCompletionRequest baseBuild(List<String> inputList) {
        return EmbeddingCompletionRequest.builder().input(inputList).build();
    }

    @Getter
    @AllArgsConstructor
    public enum Model {
        TEXT_EMBEDDING_ADA_002("text-embedding-ada-002"),
        ;
        private final String modelName;
    }

}
