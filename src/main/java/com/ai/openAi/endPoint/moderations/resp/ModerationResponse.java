package com.ai.openAi.endPoint.moderations.resp;

import com.ai.openAi.endPoint.moderations.Result;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModerationResponse {
    /**
     * 审核请求的唯一标识符
     */
    private String id;

    /**
     * 用于生成审核结果的模型
     */
    private String model;

    /**
     * 审核对象的列表
     */
    private List<Result> results;
}
