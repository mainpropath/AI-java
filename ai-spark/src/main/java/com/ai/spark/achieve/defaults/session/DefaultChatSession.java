package com.ai.spark.achieve.defaults.session;


import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.defaults.listener.ChatListener;
import com.ai.spark.achieve.standard.interfaceSession.ChatSession;
import com.ai.spark.common.SparkDesk;
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
        return this.chat(apply.getApiKey(), apply.getApiSecret(), chatListener);
    }

    @Override
    @SneakyThrows
    public <T extends ChatListener> WebSocket chat(String apiKey, String apiSecret, T chatListener) {
        String domain = chatListener.getChatRequest().getChatParameter().getChat().getDomain();
        String authUrl = AuthUtils.getAuthUrl(SparkDesk.getUrl(domain), apiKey, apiSecret);
        String url = authUrl.replaceAll("http://", "ws://").replaceAll("https://", "wss://");
        Request request = new Request.Builder().url(url).build();
        WebSocket webSocket = this.configuration.getOkHttpClient().newWebSocket(request, chatListener);
        return webSocket;
    }
}
