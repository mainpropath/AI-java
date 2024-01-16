package com.ai.spark.achieve.defaults.session;


import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.listener.ChatListener;
import com.ai.spark.achieve.standard.interfaceSession.ChatSession;
import com.ai.spark.common.utils.AuthUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.WebSocket;

@AllArgsConstructor
public class DefaultChatSession implements ChatSession {

    /**
     * 配置信息
     */
    private Configuration configuration;

    @Override
    @SneakyThrows
    public <T extends ChatListener> WebSocket chat(T chatListener) {
        ApiData apply = (ApiData) configuration.getKeyStrategy().apply(configuration.getKeyList());
        return this.chat(configuration.getApiHost(), apply.getApiKey(), apply.getApiSecret(), chatListener);
    }

    @Override
    public <T extends ChatListener> WebSocket chat(String question, T chatListener) {
        return null;
    }

    @Override
    @SneakyThrows
    public <T extends ChatListener> WebSocket chat(String hostUrl, String apiKey, String apiSecret, T chatListener) {
        String authUrl = AuthUtils.getAuthUrl(hostUrl, apiKey, apiSecret);
        String url = authUrl.replace("http://", "ws://").replace("https://", "wss://");
        Request request = new Request.Builder().url(url).build();
        WebSocket webSocket = this.configuration.getOkHttpClient().newWebSocket(request, chatListener);
        return webSocket;
    }
}
