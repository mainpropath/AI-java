package com.ai.spark.achieve.defaults.session;

import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.interfaceSession.*;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * 聚合各个场景的session
 */
public class DefaultAggregationSession implements AggregationSession {

    private Configuration configuration;

    private volatile ChatSession chatSession;

    private volatile DocumentSession documentSession;

    private volatile EmbeddingSession embeddingSession;

    private volatile ImageSession imageSession;

    private volatile AudioSession audioSession;

    public DefaultAggregationSession(Configuration configuration) {
        this.configuration = ensureNotNull(configuration, "configuration");
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

    @Override
    public ImageSession getImageSession() {
        if (imageSession == null) {
            synchronized (this) {
                if (imageSession == null) {
                    imageSession = new DefaultImageSession(configuration);
                }
            }
        }
        return imageSession;
    }

    @Override
    public AudioSession getAudioSession() {
        if (audioSession == null) {
            synchronized (this) {
                if (audioSession == null) {
                    audioSession = new DefaultAudioSession(configuration);
                }
            }
        }
        return audioSession;
    }

}
