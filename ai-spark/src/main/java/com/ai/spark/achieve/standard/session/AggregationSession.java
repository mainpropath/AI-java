package com.ai.spark.achieve.standard.session;


public interface AggregationSession {

    ChatSession getChatSession();

    DocumentSession getDocumentSession();

    EmbeddingSession getEmbeddingSession();

    ImageSession getImageSession();

    AudioSession getAudioSession();

}
