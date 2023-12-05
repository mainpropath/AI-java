package com.ai.openAi.endPoint.fineTuning.req;

import com.ai.openAi.endPoint.fineTuning.HyperParameters;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class FineTuningRequest implements Serializable {

    /**
     * 微调的模型的名称
     */
    @Builder.Default
    private String model = Model.GPT_3_5_TURBO_1106.getModelName();

    /**
     * 包含训练数据的已上传文件的 ID
     */
    @NonNull
    @JsonProperty("training_file")
    private String trainingFile;

    /**
     * 用于微调作业的超参数
     */
    @JsonProperty("hyperparameters")
    private HyperParameters hyperParameters;

    /**
     * 最多 18 个字符的字符串，将添加到微调的模型名称中。
     */
    private String suffix;

    /**
     * 包含验证数据的上载文件的 ID
     */
    @JsonProperty("validation_file")
    private String validationFile;

    @Getter
    @AllArgsConstructor
    public enum Model {
        GPT_3_5_TURBO_1106("gpt-3.5-turbo-1106"), GPT_3_5_TURBO_0613("gpt-3.5-turbo-0613"), BABBAGE_002("babbage-002"), GPT_4_0613("gpt-4-0613"), DAVINCI_002("davinci-002"),
        ;
        private final String modelName;
    }
}
