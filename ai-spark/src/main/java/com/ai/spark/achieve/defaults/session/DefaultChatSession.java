package com.ai.spark.achieve.defaults.session;


import cn.hutool.core.date.DateUtil;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.listener.ChatListener;
import com.ai.spark.achieve.defaults.listener.DocumentChatListener;
import com.ai.spark.achieve.standard.session.ChatSession;
import com.ai.spark.common.SparkApiUrl;
import com.ai.spark.common.utils.AuthUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.ToString;
import okhttp3.Request;
import okhttp3.WebSocket;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;
import static com.ai.spark.common.SparkApiUrl.DOCUMENT_CHAT;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultChatSession extends Session implements ChatSession {

    public DefaultChatSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setSparkApiServer(ensureNotNull(configuration.getSparkApiServer(), "sparkApiServer"));
    }

    @Override
    @SneakyThrows
    public <T extends ChatListener> WebSocket chat(T chatListener) {
        // 默认情况下根据apiData获取策略得到创建时设置的参数
        ApiData apiData = this.getConfiguration().getSystemApiData();
        return this.chat(apiData.getApiKey(), apiData.getApiSecret(), chatListener);
    }

    @Override
    @SneakyThrows
    public <T extends ChatListener> WebSocket chat(String apiKey, String apiSecret, T chatListener) {
        // 获取到对应访问的domain，根据domain获取对应的请求地址
        String domain = chatListener.getChatRequest().getChatParameter().getChat().getDomain();
        // 生成请求的URL
        String url = AuthUtils.replaceAllHttp(
                AuthUtils.getAuthUrl(AuthUtils.RequestMethod.GET.getMethod(), SparkApiUrl.getUrl(domain), apiKey, apiSecret)
        );
        // 发起请求返回结果
        return this.getConfiguration().getOkHttpClient().newWebSocket(new Request.Builder().url(url).build(), chatListener);
    }

    @Override
    public <T extends DocumentChatListener> WebSocket documentChat(T documentChatListener) {
        ApiData apiData = this.getConfiguration().getSystemApiData();
        return documentChat(apiData.getAppId(), apiData.getApiSecret(), documentChatListener);
    }

    @Override
    public <T extends DocumentChatListener> WebSocket documentChat(String appId, String apiSecret, T documentChatListener) {
        // 得当当前时间戳，按秒计算
        long ts = DateUtil.currentSeconds();
        // 进行签名设置
        String url = SparkApiUrl.getUrl(DOCUMENT_CHAT) + "?"
                + "appId=" + appId
                + "&timestamp=" + ts
                + "&signature=" + AuthUtils.getSignature(appId, apiSecret, ts);
        // 发起请求返回结果
        return this.getConfiguration().getOkHttpClient().newWebSocket(new Request.Builder().url(url).build(), documentChatListener);
    }

}
