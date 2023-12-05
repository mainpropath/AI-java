package com.ai.openAi.endPoint.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelObject {

    /**
     * 模型标识符，可在 API 端点中引用。
     */
    private String id;

    /**
     * 对象类型，始终为“model”。
     */
    private String object;

    /**
     * 创建模型时的 Unix 时间戳（以秒为单位）。
     */
    private long created;

    /**
     * 拥有模型的组织。
     */
    @JsonProperty("owned_by")
    private String ownedBy;

}
