package com.ai.openai.endPoint.embeddings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddingObject implements Serializable {

    /**
     * 嵌入的内容
     */
    @JsonIgnore
    private String content;

    /**
     * 嵌入列表中嵌入的索引
     */
    private Integer index;

    /**
     * 嵌入向量，它是浮点数的列表
     */
    private double[] embedding;

    /**
     * 对象类型，始终为“embedding”。
     */
    private String object;

}
