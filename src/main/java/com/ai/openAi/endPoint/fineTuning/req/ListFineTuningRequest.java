package com.ai.openAi.endPoint.fineTuning.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ListFineTuningRequest implements Serializable {

    /**
     * 上一个分页请求中最后一个作业的标识符
     */
    private String after;

    /**
     * 要检索的微调作业数
     */
    private Integer limit;

}
