package com.ai.spark.endPoint.audio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AudioHeader {

    /**
     * 在平台申请的appid信息，必传
     */
    @JsonProperty("app_id")
    private String appId;

    /**
     * 请求⽤户服务返回的uid，⽤户及设备级别个性化功能依赖此参数
     */
    private String uid;

    /**
     * 请求⽅确保唯⼀的设备标志，设备级别个性化功能依赖此参数
     */
    private String did;

    /**
     * 设备imei信息
     */
    private String imei;

    /**
     * 设备imsi信息
     */
    private String imsi;

    /**
     * 设备mac信息
     */
    private String mac;

    /**
     * ⽹络类型，可选值为wifi、2G、3G、4G、5G
     */
    @JsonProperty("net_type")
    private String netType;

    /**
     * 运营商信息，可选值为CMCC、CUCC、CTCC、other
     */
    @JsonProperty("net_isp")
    private String netIsp;

    /**
     * 客户端请求的会话唯⼀标识
     */
    @JsonProperty("request_id")
    private String requestId;

    /**
     * 个性化资源ID
     */
    @JsonProperty("res_id")
    private String resId;

    /**
     * 请求状态，可选值为：0-开始、1-继续、2-结束
     */
    private Integer status;

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
