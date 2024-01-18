package com.ai.spark.achieve.defaults.session;

import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;
import com.ai.spark.achieve.standard.interfaceSession.ChatSession;
import com.ai.spark.achieve.standard.interfaceSession.DocumentSession;
import com.ai.spark.achieve.standard.interfaceSession.EmbeddingSession;


public class DefaultAggregationSession implements AggregationSession {

    private Configuration configuration;

    private volatile ChatSession chatSession;

    private volatile DocumentSession documentSession;

    private volatile EmbeddingSession embeddingSession;

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
    public DocumentSession getDocumentSession() {
        if (documentSession == null) {
            synchronized (this) {
                if (documentSession == null) {
                    documentSession = new DefaultDocumentSession(configuration);
                }
            }
        }
        return documentSession;
    }

    @Override
    public EmbeddingSession getEmbeddingSession() {
        if (embeddingSession == null) {
            synchronized (this) {
                if (embeddingSession == null) {
                    embeddingSession = new DefaultEmbeddingSession(configuration);
                }
            }
        }
        return embeddingSession;
    }
}
