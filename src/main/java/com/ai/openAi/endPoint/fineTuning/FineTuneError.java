package com.ai.openAi.endPoint.fineTuning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FineTuneError implements Serializable {

    /**
     * 错误信息
     */
    private String message;

    /**
     * 错误类型
     */
    private String type;

    /**
     * 错误参数
     */
    private String param;

    /**
     * 错误码
     */
    private String code;

}
