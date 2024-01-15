package com.ai.spark.endPoint.chat.function;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FunctionText {

    /**
     * 用户输入命中后，会返回该名称
     */
    private String name;

    /**
     * 描述function功能即可，越详细越有助于大模型理解该function
     */
    private String description;

    @JsonProperty("parameters")
    private FunctionParameter functionParameter;

}
