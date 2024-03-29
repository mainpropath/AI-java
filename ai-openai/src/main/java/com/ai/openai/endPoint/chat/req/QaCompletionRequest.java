package com.ai.openai.endPoint.chat.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QaCompletionRequest implements Serializable {

    /**
     * 要使用的模型的 ID
     */
    @Builder.Default
    private String model = Model.GPT_3_5_TURBO.getModelName();

    /**
     * 问题描述
     */
    @NonNull
    private String prompt;

    /**
     * 默认值为 1
     * 在服务器端生成回答并返回“最佳”（每个令牌的对数概率最高的一个）。无法流式传输结果。
     */
    @JsonProperty("best_of")
    private Integer bestOf;

    /**
     * 介于 -2.0 和 2.0 之间的数字，默认值为 0
     * 正值会根据新标记在文本中的现有频率来惩罚新标记从而降低模型逐字重复同一行的可能性
     */
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;

    /**
     * 修改指定标记出现的可能性，默认值为 null
     */
    @JsonProperty("logit_bias")
    private Map logitBias;

    /**
     * 默认值为 null
     * 包括最有可能的令牌以及所选令牌的对数概率。
     */
    private Integer logprobs;

    /**
     * 输出字符串限制；0 ~ 4096
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;

    /**
     * 为每个提示生成的完成次数，默认值为 1
     */
    private Integer n;

    /**
     * 介于 -2.0 和 2.0 之间的数字，默认值为 0
     * 正值会根据新标记到目前为止是否出现在文本中来惩罚它们从而增加模型谈论新主题的可能性
     */
    @JsonProperty("presence_penalty")
    private Double presencePenalty;

    /**
     * 停止输出标识，默认值为 null
     * 最多 4 个序列，API 将停止生成更多令牌
     */
    private List<String> stop;

    /**
     * 是否为流式输出，默认值为 false
     */
    private Boolean stream;

    /**
     * 使用什么采样温度，介于 0 和 2 之间，默认值为 1
     * 较高的值（如 0.8）将使输出更加随机，而较低的值（如 0.2）将使其更具集中性和确定性
     */
    private Double temperature;

    /**
     * 默认值为 1
     * 温度采样的替代方法，称为核采样，其中模型考虑具有top_p概率质量的标记的结果。因此，0.1 表示仅考虑包含前 10% 概率质量的代币。
     */
    @JsonProperty("top_p")
    private Double topP = 1d;

    /**
     * 调用标识，避免重复调用
     */
    private String user;

    /**
     * 构造基础请求内容
     *
     * @param question 问题内容
     * @return 简单问答请求体
     */
    public static QaCompletionRequest baseBuild(String question) {
        return QaCompletionRequest.builder().prompt(question).build();
    }

    @Getter
    @AllArgsConstructor
    public enum Model {
        GPT_3_5_TURBO("gpt-3.5-turbo-instruct"), GPT_4("gpt-4"), GPT_4_32K("gpt-4-32k"),
        ;
        private String modelName;
    }
}
