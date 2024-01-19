package com.ai.spark.achieve.standard.interfaceSession;


import com.ai.spark.achieve.defaults.listener.ChatListener;
import com.ai.spark.achieve.defaults.listener.DocumentChatListener;
import okhttp3.WebSocket;

/**
 * 对话场景下的接口
 */
public interface ChatSession {

    /**
     * 聊天接口
     *
     * @param chatListener 对话监听器
     * @return 返回信息
     */
    <T extends ChatListener> WebSocket chat(T chatListener);

    /**
     * 聊天接口，自定义所使用的到的ApiKey和ApiSecret
     *
     * @param apiKey       用户的ApiKey
     * @param apiSecret    用户的ApiSecret
     * @param chatListener 对话监听器
     * @return 返回信息
     */
    <T extends ChatListener> WebSocket chat(String apiKey, String apiSecret, T chatListener);

    /**
     * 文档聊天接口
     *
     * @param documentChatListener 对话监听器
     * @return 返回信息
     */
    <T extends DocumentChatListener> WebSocket documentChat(T documentChatListener);

    /**
     * 聊天接口，自定义所使用的到的AppId和ApiSecret
     *
     * @param appId                用户的AppId
     * @param apiSecret            用户的ApiSecret
     * @param documentChatListener 对话监听器
     * @return 返回信息
     */
    <T extends DocumentChatListener> WebSocket documentChat(String appId, String apiSecret, T documentChatListener);

}
