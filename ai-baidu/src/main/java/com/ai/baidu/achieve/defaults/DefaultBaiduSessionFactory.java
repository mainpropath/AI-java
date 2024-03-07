package com.ai.baidu.achieve.defaults;

import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.defaults.session.DefaultAggregationSession;
import com.ai.baidu.achieve.standard.BaiduSessionFactory;
import com.ai.baidu.achieve.standard.api.BaiduApiServer;
import com.ai.baidu.achieve.standard.function.AggregationSession;
import com.ai.baidu.interceptor.ResponseInterceptor;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Description: baidu API Factory 会话工厂
 **/
@AllArgsConstructor
public class DefaultBaiduSessionFactory implements BaiduSessionFactory {

    private final Configuration configuration;

    @Override
    public OkHttpClient createHttpClient() {
        // 1. 日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        // 2. 开启 Http 客户端
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
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

    @Override
    public BaiduApiServer createBaiduApiServer(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build().create(BaiduApiServer.class);
    }

    @Override
    public AggregationSession openAggregationSession() {
        OkHttpClient okHttpClient = createHttpClient();
        configuration.setOkHttpClient(okHttpClient);
        configuration.setBaiduApiServer(createBaiduApiServer(okHttpClient));
        return new DefaultAggregationSession(configuration);
    }

}
