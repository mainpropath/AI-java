package com.ai.spark.achieve.standard.interfaceSession;


import com.ai.spark.achieve.defaults.listener.ChatListener;
import com.ai.spark.achieve.defaults.listener.DocumentChatListener;
import okhttp3.WebSocket;

public interface ChatSession {

    <T extends ChatListener> WebSocket chat(T chatListener);

    <T extends ChatListener> WebSocket chat(String apiKey, String apiSecret, T chatListener);

    <T extends DocumentChatListener> WebSocket documentChat(T documentChatListener);

    <T extends DocumentChatListener> WebSocket documentChat(String appId, String apiSecret, T documentChatListener);
}
