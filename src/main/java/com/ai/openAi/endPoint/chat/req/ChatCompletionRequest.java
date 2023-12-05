package com.ai.openAi.endPoint.chat.req;

import com.ai.openAi.endPoint.chat.Message;
import com.ai.openAi.common.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionRequest implements Serializable {

    /**
     * 构成对话的消息列表
     */
    @NonNull
    private List<Message> messages;

    /**
     * 要使用的模型的 ID
     */
    @Builder.Default
    private String model = Model.GPT_3_5_TURBO.getModuleName();

    /**
     * 介于 -2.0 和 2.0 之间的数字，默认值为 0
     * 正值会根据新标记在文本中的现有频率来惩罚新标记从而降低模型逐字重复同一行的可能性
     */
    @JsonProperty("frequency_penalty")
    private double frequencyPenalty;

    /**
     * 修改指定标记出现的可能性，默认值为 null
     */
    @JsonProperty("logit_bias")
    private Map logitBias;

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
    private double presencePenalty;

    /**
     * 停止输出标识，默认值为 null
     * 最多 4 个序列，API 将停止生成更多令牌
     */
    private List<String> stop;

    /**
     * 是否为流式输出，默认值为 false
     */
    private boolean stream;

    /**
     * 使用什么采样温度，介于 0 和 2 之间，默认值为 1
     * 较高的值（如 0.8）将使输出更加随机，而较低的值（如 0.2）将使其更具集中性和确定性
     */
    private double temperature;

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
     * @return 聊天对话请求体
     */
    public static ChatCompletionRequest BuildBaseChatCompletionRequest(String question) {
        return ChatCompletionRequest
                .builder()
                .messages(new ArrayList<>(Collections.singletonList(Message.builder().role(Constants.Role.USER.getRoleName()).content(question).build())))
                .build();
    }

    /**
     * 上下文对话时，添加问答内容
     *
     * @param message 问答内容
     */
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * 上下文对话时，添加问答内容
     *
     * @param role    角色
     * @param content 内容
     */
    public void addMessage(String role, String content) {
        this.addMessage(Message.builder().role(role).content(content).build());
    }

    @Getter
    @AllArgsConstructor
    public enum Model {
        GPT_3_5_TURBO("gpt-3.5-turbo"), GPT_4("gpt-4"), GPT_4_32K("gpt-4-32k"),
        ;
        private String moduleName;
    }
}

