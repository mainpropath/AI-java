package com.ai.spark.achieve.defaults.session;

import cn.hutool.http.ContentType;
import com.ai.common.utils.JsonUtils;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.interfaceSession.ImageSession;
import com.ai.spark.common.SparkApiUrl;
import com.ai.spark.common.utils.AuthUtils;
import com.ai.spark.endPoint.images.req.ImageCreateRequest;
import com.ai.spark.endPoint.images.resp.ImageCreateResponse;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

public class DefaultImageSession implements ImageSession {

    private Configuration configuration;

    public DefaultImageSession(Configuration configuration) {
        this.configuration = ensureNotNull(configuration, "configuration");
    }

    @Override
    @SneakyThrows
    public ImageCreateResponse imageCreate(ImageCreateRequest imageCreateRequest) {
        ApiData apiData = configuration.getSystemApiData();
        return this.imageCreate(apiData.getApiKey(), apiData.getApiSecret(), imageCreateRequest);
    }

    @Override
    @SneakyThrows
    public ImageCreateResponse imageCreate(String apiKey, String apiSecret, ImageCreateRequest imageCreateRequest) {
        // 鉴权，得到请求路径
        String authUrl = AuthUtils.getAuthUrl(AuthUtils.RequestMethod.POST.getMethod(), SparkApiUrl.ApiUrl.imageCreate.getUrl(), apiKey, apiSecret);
        // 创建请求，设置请求URL和json数据
        Request request = new Request.Builder().url(authUrl).post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JsonUtils.toJson(imageCreateRequest))).build();
        // 发起请求，获取返回的json字符串
        String response = configuration.getOkHttpClient().newCall(request).execute().body().string();
        // 将json映射到对象上
        return JsonUtils.fromJson(response, ImageCreateResponse.class);
    }
}
