package com.ai.openAi.endPoint.fineTuning;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Getter
@Slf4j
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class HyperParameters implements Serializable {

    /**
     * 每批样本数。较大的批量大小意味着模型参数 更新频率较低，但方差较低。
     */
    @JsonProperty("batch_size")
    private String batchSize;

    /**
     * 学习率的比例因子。较小的学习率可能有助于避免 过拟合。
     */
    @JsonProperty("learning_rate_multiplier")
    private String learningRateMultiplier;

    /**
     * 要训练模型的纪元数。一个纪元是指一个完整的周期 通过训练数据集。
     */
    @JsonProperty("n_epochs")
    private String nEpochs;

}
