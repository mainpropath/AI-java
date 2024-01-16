package com.ai.spark.endPoint.chat.function;

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
public class FunctionParameter {

    /**
     * 参数类型
     */
    private String type;

    /**
     * 该内容由用户定义，命中该方法时需要返回哪些参数
     */
    private Object properties;

    /**
     * 该内容由用户定义，命中方法时必须返回的字段
     */
    private List<String> required;

}
