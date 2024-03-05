package com.ai.baidu.achieve.standard.api;

import com.ai.baidu.endPoint.auth.resp.AuthResponse;
import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @Description: baidu API接口
 **/
public interface BaiduApiServer {

    /**
     * 鉴权接口
     *
     * @param grantType 类型
     * @param apiKey    百度千帆大模型平台 apiKey
     * @param secretKey 百度千帆大模型平台 secretKey
     * @return 返回参数
     */
    @POST("/oauth/2.0/token")
    Single<AuthResponse> auth(@Query("grant_type") String grantType, @Query("client_id") String apiKey, @Query("client_secret") String secretKey);

    /**
     * 获取鉴权的 accessToken
     *
     * @param apiKey    百度千帆大模型平台 apiKey
     * @param secretKey 百度千帆大模型平台 secretKey
     * @return 鉴权的 accessToken
     */
    default String getAccessToken(String apiKey, String secretKey) {
        return getAccessToken("client_credentials", apiKey, secretKey);
    }

    /**
     * 获取鉴权的 accessToken
     *
     * @param grantType 类型
     * @param apiKey    百度千帆大模型平台 apiKey
     * @param secretKey 百度千帆大模型平台 secretKey
     * @return 鉴权的 accessToken
     */
    default String getAccessToken(String grantType, String apiKey, String secretKey) {
        return this.auth(grantType, apiKey, secretKey).blockingGet().getAccessToken();
    }

}
