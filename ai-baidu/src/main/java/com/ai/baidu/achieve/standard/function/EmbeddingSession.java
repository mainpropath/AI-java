package com.ai.baidu.achieve.standard.function;

import com.ai.baidu.endPoint.embedding.req.EmbeddingRequest;
import com.ai.baidu.endPoint.embedding.resp.EmbeddingResponse;

/**
 * @Description: 嵌入会话窗口
 **/
public interface EmbeddingSession {

    default EmbeddingResponse embedding(EmbeddingRequest embeddingRequest) {
        return embedding(null, embeddingRequest);
    }

    EmbeddingResponse embedding(String accessToken, EmbeddingRequest embeddingRequest);

}
