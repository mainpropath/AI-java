package com.ai.spark.endPoint.chat;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Choice {

    /**
     * 文本响应状态，取值为[0,1,2]; 0代表首个文本结果；1代表中间文本结果；2代表最后一个文本结果
     */
    private Integer status;

    /**
     * 返回的数据序号，取值为[0,9999999]
     */
    private Integer seq;

    @JsonProperty("text")
    private List<ChatText> texts;

    @Getter
    public enum Status {
        START(0),
        ING(1),
        END(2),
        ;

        private final int value;

        Status(int value) {
            this.value = value;
        }
    }

}
