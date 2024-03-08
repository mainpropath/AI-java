package com.ai.baidu.achieve.standard.api;

import com.ai.baidu.endPoint.auth.resp.AuthResponse;
import com.ai.baidu.endPoint.chat.req.ChatRequest;
import com.ai.baidu.endPoint.chat.resp.ChatResponse;
import com.ai.baidu.endPoint.embedding.req.EmbeddingRequest;
import com.ai.baidu.endPoint.embedding.resp.EmbeddingResponse;
import com.ai.baidu.endPoint.images.req.ImageRequest;
import com.ai.baidu.endPoint.images.resp.ImageResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
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

    /**
     * 聊天接口
     *
     * @param accessToken 鉴权的 accessToken
     * @param chatRequest 请求参数
     * @return 返回数据
     */
    @POST("/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro")
    Single<ChatResponse> chat(@Query("access_token") String accessToken, @Body ChatRequest chatRequest);

    /**
     * embedding 接口
     *
     * @param accessToken      鉴权的 accessToken
     * @param embeddingRequest 请求参数
     * @return 返回数据
     */
    @POST("/rpc/2.0/ai_custom/v1/wenxinworkshop/embeddings/embedding-v1")
    Single<EmbeddingResponse> embedding(@Query("access_token") String accessToken, @Body EmbeddingRequest embeddingRequest);

    /**
     * 文生图接口
     *
     * @param accessToken  鉴权的 accessToken
     * @param imageRequest 请求参数
     * @return 返回数据
     */
    @POST("/rpc/2.0/ai_custom/v1/wenxinworkshop/text2image/sd_xl")
    Single<ImageResponse> text2image(@Query("access_token") String accessToken, @Body ImageRequest imageRequest);

}
