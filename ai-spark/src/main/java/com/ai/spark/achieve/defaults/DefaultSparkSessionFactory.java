package com.ai.spark.achieve.defaults;

import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.session.DefaultAggregationSession;
import com.ai.spark.achieve.standard.SparkSessionFactory;
import com.ai.spark.achieve.standard.api.SparkApiServer;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.interceptor.BaseUrlInterceptor;
import com.ai.spark.interceptor.ResponseInterceptor;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class DefaultSparkSessionFactory implements SparkSessionFactory {

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
                .addInterceptor(new BaseUrlInterceptor())
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

    public SparkApiServer createSparkAiApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build().create(SparkApiServer.class);
    }

    @Override
    public AggregationSession openAggregationSession() {
        OkHttpClient okHttpClient = createHttpClient();
        configuration.setOkHttpClient(okHttpClient);
        configuration.setSparkApiServer(createSparkAiApi(okHttpClient));
        return new DefaultAggregationSession(configuration);
    }
}
