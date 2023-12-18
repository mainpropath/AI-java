package com.ai.openAi.endPoint.chat.resp;

import com.ai.openAi.endPoint.chat.ChatChoice;
import com.ai.openAi.common.Usage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @description 聊天请求结果信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatCompletionResponse implements Serializable {

    /**
     * 完成的唯一标识符
     */
    private String id;

    /**
     * 对话信息
     */
    private List<ChatChoice> choices;

    /**
     * 对象
     */
    private String object;

    /**
     * 创建完成时的 Unix 时间戳（以秒为单位）。
     */
    private long created;

    /**
     * 用于完成的模型
     */
    private String model;

    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

    /**
     * 结束原因
     */
    @JsonProperty("finish_reason")
    private String finishReason;

    /**
     * 完成请求的使用情况统计信息
     */
    private Usage usage;

}
