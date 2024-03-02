package com.ai.spark.achieve.standard.interfaceSession;


public interface AggregationSession {

    ChatSession getChatSession();

    DocumentSession getDocumentSession();

    EmbeddingSession getEmbeddingSession();

    ImageSession getImageSession();

    AudioSession getAudioSession();

}
