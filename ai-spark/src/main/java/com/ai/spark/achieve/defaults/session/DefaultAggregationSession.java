package com.ai.spark.achieve.defaults.session;

import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.achieve.standard.interfaceSession.ChatSession;
import com.ai.spark.achieve.standard.interfaceSession.FileSession;


public class DefaultAggregationSession implements AggregationSession {

    private Configuration configuration;

    private volatile ChatSession chatSession;

    private volatile FileSession fileSession;

    public DefaultAggregationSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public ChatSession getChatSession() {
        if (chatSession == null) {
            synchronized (this) {
                if (chatSession == null) {
                    chatSession = new DefaultChatSession(configuration);
                }
            }
        }
        return chatSession;
    }

    @Override
    public FileSession getFileSession() {
        if (fileSession == null) {
            synchronized (this) {
                if (fileSession == null) {
                    fileSession = new DefaultFileSession(configuration);
                }
            }
        }
        return fileSession;
    }
}
