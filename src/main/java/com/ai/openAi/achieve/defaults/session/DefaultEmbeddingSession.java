package com.ai.openAi.achieve.defaults.session;

import com.ai.openAi.endPoint.embeddings.req.EmbeddingCompletionRequest;
import com.ai.openAi.endPoint.embeddings.resp.EmbeddingCompletionResponse;
import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.standard.api.ApiServer;
import com.ai.openAi.achieve.standard.interfaceSession.EmbeddingSession;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: OpenAI 嵌入类会话
 **/
public class DefaultEmbeddingSession implements EmbeddingSession {

    /**
     * 配置信息
     */
    private Configuration configuration;
    /**
     * OpenAI 接口
     */
    private ApiServer apiServer;

    public DefaultEmbeddingSession(Configuration configuration) {
        this.configuration = configuration;
        this.apiServer = configuration.getApiServer();
    }

    @Override
    public EmbeddingCompletionResponse embeddingCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, List<String> inputList) {
        return this.embeddingCompletions(apiHostByUser, apiKeyByUser, apiUrlByUser, EmbeddingCompletionRequest.builder().input(inputList).build());
    }

    @Override
    public EmbeddingCompletionResponse embeddingCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String input) {
        return this.embeddingCompletions(apiHostByUser, apiKeyByUser, apiUrlByUser, EmbeddingCompletionRequest.builder().input(Arrays.asList(input)).build());
    }

    @Override
    public EmbeddingCompletionResponse embeddingCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, EmbeddingCompletionRequest embeddingCompletionRequest) {
        return this.apiServer.createEmbeddingsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, embeddingCompletionRequest).blockingGet();
    }

}
