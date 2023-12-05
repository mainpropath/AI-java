package com.ai.openAi.achieve.standard.interfaceSession;

/**
 * @Description: 聚合各大场景的session
 **/
public interface AggregationSession {

    AudioSession getAudioSession();

    ChatSession getChatSession();

    FineTuningSession getFineTuningSession();

    EmbeddingSession getEmbeddingSession();

    FilesSession getFilesSession();

    ImageSession getImageSession();

    ModelSession getModelSession();

    ModerationSession getModerationSession();
}
