package com.ai.openAi.endPoint.embeddings.resp;

import com.ai.openAi.common.Usage;
import com.ai.openAi.endPoint.embeddings.EmbeddingObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingCompletionResponse implements Serializable {

    /**
     * 参考示例返回：
     * {
     * "object": "list",
     * "data": [
     * {
     * "object": "embedding",
     * "embedding": [
     * 0.0023064255,
     * -0.009327292,
     * .... (1536 floats total for ada-002)
     * -0.0028842222,
     * ],
     * "index": 0
     * }
     * ],
     * "model": "text-embedding-ada-002",
     * "usage": {
     * "prompt_tokens": 8,
     * "total_tokens": 8
     * }
     * }
     */
    private String object;

    /**
     * 结果数组
     */
    private List<EmbeddingObject> data;

    private String model;

    private Usage usage;


}
