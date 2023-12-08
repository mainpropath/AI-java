package com.ai.openAi.achieve.defaults;


import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.defaults.session.DefaultAggregationSession;
import com.ai.openAi.achieve.standard.OpenAiSessionFactory;
import com.ai.openAi.achieve.standard.api.ApiServer;
import com.ai.openAi.interceptor.HeaderInterceptor;
import com.ai.openAi.interceptor.ResponseInterceptor;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @description OpenAi API Factory 会话工厂
 */
@AllArgsConstructor
public class DefaultOpenAiSessionFactory implements OpenAiSessionFactory {

    private final Configuration configuration;

    /**
     * 获取 httpClient
     *
     * @return 客户端
     */
    public OkHttpClient createHttpClient() {
        // 1. 日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        // 2. 开启 Http 客户端
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HeaderInterceptor(configuration.getKeyList(), configuration.getApiHost(), configuration.getKeyStrategy()))
                .addInterceptor(new ResponseInterceptor())
                .connectTimeout(450, TimeUnit.SECONDS)
                .writeTimeout(450, TimeUnit.SECONDS)
                .readTimeout(450, TimeUnit.SECONDS);
        // 3. 检查是否需要代理
        if (configuration.getProxy() != null) {
            builder.proxy(configuration.getProxy());
        }
        return builder.build();
    }

    /**
     * 获取 Api 信息
     *
     * @param okHttpClient 客户端
     * @return api信息
     */
    public ApiServer createOpenAiApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build().create(ApiServer.class);
    }

    @Override
    public DefaultAggregationSession openAggregationSession() {
        OkHttpClient okHttpClient = createHttpClient();
        configuration.setOkHttpClient(okHttpClient);
        configuration.setApiServer(createOpenAiApi(okHttpClient));
        return new DefaultAggregationSession(configuration);
    }
}
