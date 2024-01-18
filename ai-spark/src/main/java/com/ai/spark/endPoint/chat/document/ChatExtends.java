package com.ai.spark.endPoint.chat.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatExtends {

    /**
     * wiki 大模型问答模板，在某些场景服务默认的 prompt 回答效果不好时，业务可以考虑通过自定义 prompt 来改善。<wikiquestion>替换的问题标识，<wikicontent>替换的文本内容标识
     */
    private String wikiPromptTpl;

    /**
     * wiki 结果分数阈值，低于这个阈值的结果丢弃。取值范围为(0,1] 参考值为：0.80非常宽松 0.82宽松 0.83标准0.84严格 0.86非常严格。服务会根据问题检索文件列表中内容相关的文段，该值设置的越高，可能丢弃的内容越多，但保留下来的内容越准确；但过高也可能导致无匹配内容
     */
    private Float wikiFilterScore;

    /**
     * 用户问题未匹配到文档内容时，是否使用大模型兜底回答问题
     */
    private Boolean sparkWhenWithoutEmbedding;

    /**
     * 大模型问答时的温度，取值范围 (0，1] ，temperature 越大，大模型回答随机度越高
     */
    private Float temperature;

}
