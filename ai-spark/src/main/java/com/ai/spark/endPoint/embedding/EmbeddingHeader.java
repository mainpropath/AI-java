package com.ai.spark.endPoint.embedding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingHeader {

    /**
     * 必传
     * 在平台申请的app id信息
     */
    @JsonProperty("app_id")
    private String appId;

    /**
     * 非必传
     * 每个用户的id，用于区分不同用户
     */
    private String uid;

    /**
     * 发送状态标识，3为一次性发完
     */
    @Builder.Default
    private Integer status = 3;

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


}
