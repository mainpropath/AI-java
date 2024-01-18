package com.ai.spark.achieve.standard.interfaceSession;


import com.ai.spark.endPoint.embedding.req.EmbeddingRequest;
import com.ai.spark.endPoint.embedding.resp.EmbeddingResponse;

public interface EmbeddingSession {

    EmbeddingResponse embed(EmbeddingRequest embeddingRequest);

    EmbeddingResponse embed(String apiKey, String apiSecret, EmbeddingRequest embeddingRequest);

}
