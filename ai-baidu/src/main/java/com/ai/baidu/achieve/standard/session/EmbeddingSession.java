package com.ai.baidu.achieve.standard.session;

import com.ai.baidu.endPoint.embedding.req.EmbeddingRequest;
import com.ai.baidu.endPoint.embedding.resp.EmbeddingResponse;

/**
 * @Description: 嵌入会话窗口
 **/
public interface EmbeddingSession {

    default EmbeddingResponse embedding(EmbeddingRequest embeddingRequest) {
        return embedding(null, embeddingRequest);
    }

    /**
     * 嵌入
     *
     * @param accessToken      鉴权的 accessToken
     * @param embeddingRequest 请求参数
     * @return 请求结果
     */
    EmbeddingResponse embedding(String accessToken, EmbeddingRequest embeddingRequest);

}
