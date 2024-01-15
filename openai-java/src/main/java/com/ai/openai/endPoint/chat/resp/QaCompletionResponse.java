package com.ai.openai.endPoint.chat.resp;


import com.ai.openai.common.Usage;
import com.ai.openai.endPoint.chat.QaChoice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 简单问答返回信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QaCompletionResponse implements Serializable {

    /**
     * 完成的唯一标识符
     */
    private String id;

    /**
     * 模型为输入提示生成的完成选项列表
     */
    private List<QaChoice> choices;

    /**
     * 创建完成时的 Unix 时间戳（以秒为单位）。
     */
    private long created;

    /**
     * 用于完成的模型
     */
    private String model;

    /**
     * 对象类型，始终为“text_completion”
     */
    private String object;

    /**
     * 完成请求的使用情况统计信息
     */
    private Usage usage;

}
