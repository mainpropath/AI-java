package com.ai.openai.achieve.standard.session;

import com.ai.openai.endPoint.embeddings.req.EmbeddingCompletionRequest;
import com.ai.openai.endPoint.embeddings.resp.EmbeddingCompletionResponse;

import java.util.List;

/**
 * @Description: 嵌入会话窗口
 **/
public interface EmbeddingSession {

    /**
     * 嵌入
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param inputList     文本数组
     * @return 请求结果
     */
    EmbeddingCompletionResponse embeddingCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, List<String> inputList);

    /**
     * 嵌入
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param input         文本
     * @return 请求结果
     */
    EmbeddingCompletionResponse embeddingCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String input);

    /**
     * 嵌入
     *
     * @param apiHostByUser              用户自定义 host
     * @param apiKeyByUser               用户自定义密钥
     * @param apiUrlByUser               用户自定义请求地址
     * @param embeddingCompletionRequest 请求参数
     * @return 请求结果
     */
    EmbeddingCompletionResponse embeddingCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, EmbeddingCompletionRequest embeddingCompletionRequest);
}
