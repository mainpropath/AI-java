package com.ai.spark.achieve.defaults.session;

import cn.hutool.http.ContentType;
import com.ai.common.utils.JsonUtils;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.listener.ImageUnderstandingListener;
import com.ai.spark.achieve.standard.interfaceSession.ImageSession;
import com.ai.spark.common.SparkApiUrl;
import com.ai.spark.common.utils.AuthUtils;
import com.ai.spark.endPoint.images.req.ImageCreateRequest;
import com.ai.spark.endPoint.images.req.ImageUnderstandingRequest;
import com.ai.spark.endPoint.images.resp.ImageCreateResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.ToString;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.WebSocket;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultImageSession extends Session implements ImageSession {

    public DefaultImageSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setSparkApiServer(ensureNotNull(configuration.getSparkApiServer(), "sparkApiServer"));
    }

    @Override
    @SneakyThrows
    public ImageCreateResponse imageCreate(ImageCreateRequest imageCreateRequest) {
        ApiData apiData = this.getConfiguration().getSystemApiData();
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
        String response = this.getConfiguration().getOkHttpClient().newCall(request).execute().body().string();
        // 将json映射到对象上
        return JsonUtils.fromJson(response, ImageCreateResponse.class);
    }

    @Override
    public <T extends ImageUnderstandingListener> WebSocket imageUnderstanding(ImageUnderstandingRequest imageUnderstandingRequest, ImageUnderstandingListener imageUnderstandingListener) {
        ApiData apiData = this.getConfiguration().getSystemApiData();
        return imageUnderstanding(apiData.getApiKey(), apiData.getApiSecret(), imageUnderstandingRequest, imageUnderstandingListener);
    }

    @Override
    @SneakyThrows
    public <T extends ImageUnderstandingListener> WebSocket imageUnderstanding(String apiKey, String apiSecret, ImageUnderstandingRequest imageUnderstandingRequest, ImageUnderstandingListener imageUnderstandingListener) {
        // 生成请求的URL
        String url = AuthUtils.replaceAllHttp(
                AuthUtils.getAuthUrl(AuthUtils.RequestMethod.GET.getMethod(), SparkApiUrl.ApiUrl.imageUnderstanding.getUrl(), apiKey, apiSecret)
        );
        // 发起请求返回结果
        return this.getConfiguration().getOkHttpClient().newWebSocket(new Request.Builder().url(url).build(), imageUnderstandingListener);
    }
}
