package com.ai.spark.endPoint.chat.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentChatResponse {

    /**
     * 错误码 ，0 标识成功
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String content;

    /**
     * 文档引用，status=99 的时候返回；结构是个 Map，key=文件 id，value=引用的文段列表（对应 fileTrunks 的 index）
     */
    private String fileRefer;

    /**
     * 会话唯一标识
     */
    private String sid;

    /**
     * 会话状态，取值为[0,1,2,99]；0 代表首次结果；1 代表中间结果；2 代表最后一个结果；99 代表引用的文档及文段
     */
    private Integer status;

    @Getter
    @AllArgsConstructor
    public enum Code {
        SUCCESS(0),
        ;

        private final int value;
    }

    @Getter
    public enum Status {
        START(0),
        ING(1),
        END(2),
        DOCUMENT(99),
        ;

        private final int value;

        Status(int value) {
            this.value = value;
        }
    }

}
