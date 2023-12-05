package com.ai.openAi.endPoint.audio.resp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SttCompletionResponse implements Serializable {
    /**
     * 转换之后得到的文本
     */
    private String text;
}
