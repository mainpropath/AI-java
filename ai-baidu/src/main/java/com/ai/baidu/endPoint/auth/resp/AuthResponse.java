package com.ai.baidu.endPoint.auth.resp;

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
public class AuthResponse {

    @JsonProperty("refresh_token")
    private String refreshToken;

    /**
     * 有效期，Access Token的有效期。
     * 说明：单位是秒，有效期30天
     */
    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("session_key")
    private String sessionKey;

    /**
     * 访问凭证
     */
    @JsonProperty("access_token")
    private String accessToken;

    private String scope;

    @JsonProperty("session_secret")
    private String sessionSecret;

    /**
     * 错误码
     * 说明：响应失败时返回该字段，成功时不返回
     */
    private String error;

    /**
     * 错误描述信息，帮助理解和解决发生的错误
     * 说明：响应失败时返回该字段，成功时不返回
     */
    @JsonProperty("error_description")
    private String errorDescription;

}
