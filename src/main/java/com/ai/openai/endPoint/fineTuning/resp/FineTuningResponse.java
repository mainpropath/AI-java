package com.ai.openai.endPoint.fineTuning.resp;

import com.ai.openai.endPoint.fineTuning.HyperParameters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FineTuningResponse implements Serializable {

    /**
     * 对象标识符，可在 API 端点中引用
     */
    private String id;

    /**
     * 创建微调作业时的 Unix 时间戳
     */
    @JsonProperty("created_at")
    private Long createdAt;

    /**
     * 对于具有的微调作业，这将包含有关失败原因的详细信息
     */
//    private FineTuneError error;

    /**
     * 正在创建的微调模型的名称。如果微调作业仍在运行，则该值将为 null
     */
    @JsonProperty("fine_tuned_model")
    private String fineTunedModel;

    /**
     * 微调作业完成时的 Unix 时间戳（以秒为单位）。如果微调作业仍在运行，则该值将为 null
     */
    @JsonProperty("finished_at")
    private Long finishedAt;

    /**
     * 用于微调作业的超参数
     */
    @JsonProperty("hyperparameters")
    private HyperParameters hyperparameters;

    /**
     * 正在微调的基本模型
     */
    private String model;

    /**
     * 对象类型，始终为“fine_tuning.job”
     */
    private String object;

    /**
     * 拥有微调作业的组织
     */
    @JsonProperty("organization_id")
    private String organizationId;

    /**
     * 微调作业的编译结果文件 ID
     */
    @JsonProperty("result_files")
    private List<String> resultFiles;

    /**
     * 微调作业的当前状态
     */
    private String status;

    /**
     * 此微调作业处理的计费令牌总数
     */
    @JsonProperty("trained_tokens")
    private Integer trainedTokens;

    /**
     * 用于训练的文件 ID
     */
    @JsonProperty("training_file")
    private String trainingFile;

    /**
     * 用于验证的文件 ID
     */
    @JsonProperty("validation_file")
    private String validationFile;

}
