package com.ai.spark.endPoint.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatHeader {

    /**
     * 必传
     * 应用appid，从开放平台控制台创建的应用中获取
     */
    @JsonProperty("app_id")
    private String appId;

    /**
     * 非必传
     * 每个用户的id，用于区分不同用户
     */
    private String uid;

    // 以下是请求返回时所需参数
    /**
     * 错误码，0表示正常，非0表示出错；
     */
    private Integer code;

    /**
     * 会话是否成功的描述信息
     */
    private String message;

    /**
     * 会话的唯一id，用于讯飞技术人员查询服务端会话日志使用,出现调用错误时建议留存该字段
     */
    private String sid;

    /**
     * 会话状态，取值为[0,1,2]；0代表首次结果；1代表中间结果；2代表最后一个结果
     */
    private Integer status;

    @Getter
    @AllArgsConstructor
    public enum Code {
        SUCCESS(0),
        ;

        private final int value;
    }

}
