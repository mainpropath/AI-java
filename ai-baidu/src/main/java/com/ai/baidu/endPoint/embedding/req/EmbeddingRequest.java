package com.ai.baidu.endPoint.embedding.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 嵌入请求体，官方文档链接：https://cloud.baidu.com/doc/WENXINWORKSHOP/s/alj562vvu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingRequest {

    /**
     * 必传
     * 输入文本以获取embeddings。说明：
     * （1）不能为空List，List的每个成员不能为空字符串
     * （2）文本数量不超过16
     * （3）每个文本token数不超过384且长度不超过1000个字符
     */
    private List<String> input;

    /**
     * 表示最终用户的唯一标识符
     */
    @JsonProperty("user_id")
    private String userId;

    public static EmbeddingRequest baseBuild(List<String> input) {
        return EmbeddingRequest.builder().input(input).build();
    }

}
