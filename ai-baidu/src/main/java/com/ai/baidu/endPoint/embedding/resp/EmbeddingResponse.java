package com.ai.baidu.endPoint.embedding.resp;

import com.ai.baidu.common.Usage;
import com.ai.baidu.endPoint.embedding.EmbeddingData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 嵌入返回体，官方文档链接：https://cloud.baidu.com/doc/WENXINWORKSHOP/s/alj562vvu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingResponse {

    /**
     * 本轮对话的id
     */
    private String id;

    /**
     * 回包类型，固定值“embedding_list”
     */
    private String Object;

    /**
     * 时间戳
     */
    private Integer created;

    /**
     * embedding信息，data成员数和文本数量保持一致
     */
    private List<EmbeddingData> data;

    /**
     * token统计信息，token数 = 汉字数+单词数*1.3 （仅为估算逻辑）
     */
    private Usage usage;


}
