package com.ai.spark.achieve.defaults.session;

import cn.hutool.http.ContentType;
import com.ai.common.utils.JsonUtils;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.interfaceSession.EmbeddingSession;
import com.ai.spark.common.SparkApiUrl;
import com.ai.spark.common.utils.AuthUtils;
import com.ai.spark.endPoint.embedding.req.EmbeddingRequest;
import com.ai.spark.endPoint.embedding.resp.EmbeddingResponse;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

public class DefaultEmbeddingSession implements EmbeddingSession {

    private Configuration configuration;

    public DefaultEmbeddingSession(Configuration configuration) {
        this.configuration = ensureNotNull(configuration, "configuration");
    }

    @Override
    public EmbeddingResponse embed(EmbeddingRequest embeddingRequest) {
        ApiData apiData = configuration.getSystemApiData();
        return this.embed(apiData.getApiKey(), apiData.getApiSecret(), embeddingRequest);
    }

    @Override
    @SneakyThrows
    public EmbeddingResponse embed(String apiKey, String apiSecret, EmbeddingRequest embeddingRequest) {
        String authUrl = AuthUtils.getAuthUrl(AuthUtils.RequestMethod.POST.getMethod(), SparkApiUrl.ApiUrl.embeddingp.getUrl(), apiKey, apiSecret);
        Request request = new Request.Builder().url(authUrl).post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JsonUtils.toJson(embeddingRequest))).build();
        String jsonResp = configuration.getOkHttpClient().newCall(request).execute().body().string();
        return JsonUtils.fromJson(jsonResp, EmbeddingResponse.class);
    }
}
