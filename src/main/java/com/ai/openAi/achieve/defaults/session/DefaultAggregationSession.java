package com.ai.openAi.achieve.defaults.session;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.standard.interfaceSession.*;

/**
 * @Description: 聚合各个类型的session
 **/
public class DefaultAggregationSession implements AggregationSession {

    private Configuration configuration;

    private volatile AudioSession audioSession;

    private volatile ChatSession chatSession;

    private volatile EmbeddingSession embeddingSession;

    private volatile FineTuningSession fineTuningSession;

    private volatile FilesSession filesSession;

    private volatile ImageSession imageSession;

    private volatile ModelSession modelSession;

    private volatile ModerationSession moderationSession;

    public DefaultAggregationSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
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
    public FineTuningSession getFineTuningSession() {
        if (fineTuningSession == null) {
            synchronized (this) {
                if (fineTuningSession == null) {
                    fineTuningSession = new DefaultFineTuningSession(configuration);
                }
            }
        }
        return fineTuningSession;
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
    public FilesSession getFilesSession() {
        if (filesSession == null) {
            synchronized (this) {
                if (filesSession == null) {
                    filesSession = new DefaultFilesSession(configuration);
                }
            }
        }
        return filesSession;
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
    public ModelSession getModelSession() {
        if (modelSession == null) {
            synchronized (this) {
                if (modelSession == null) {
                    modelSession = new DefaultModelSession(configuration);
                }
            }
        }
        return modelSession;
    }

    @Override
    public ModerationSession getModerationSession() {
        if (moderationSession == null) {
            synchronized (this) {
                if (moderationSession == null) {
                    moderationSession = new DefaultModerationSession(configuration);
                }
            }
        }
        return moderationSession;
    }

}
