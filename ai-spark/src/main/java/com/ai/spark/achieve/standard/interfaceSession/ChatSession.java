package com.ai.spark.achieve.standard.interfaceSession;


import com.ai.spark.achieve.defaults.listener.ChatListener;
import okhttp3.WebSocket;

public interface ChatSession {

    <T extends ChatListener> WebSocket chat(T chatListener);

    <T extends ChatListener> WebSocket chat(String question, T chatListener);

    <T extends ChatListener> WebSocket chat(String hostUrl, String apiKey, String apiSecret, T chatListener);

}
