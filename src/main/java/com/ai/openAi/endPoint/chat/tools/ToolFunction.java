package com.ai.openAi.endPoint.chat.tools;

import com.ai.openAi.endPoint.chat.Parameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolFunction implements Serializable {

    /**
     * 要调用的函数的名称。必须是 a-z、A-Z、0-9 或包含下划线和短划线，最大长度为 64。
     */
    private String name;

    /**
     * 对函数所执行操作的描述，模型使用它来选择何时以及如何调用该函数。
     */
    private String description;

    /**
     * 函数接受的参数，描述为 JSON 架构对象。
     */
    private Parameters parameters;

}
