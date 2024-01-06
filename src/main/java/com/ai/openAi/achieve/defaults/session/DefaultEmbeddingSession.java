package com.ai.openAi.achieve.defaults.session;

import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.standard.api.ApiServer;
import com.ai.openAi.achieve.standard.interfaceSession.EmbeddingSession;
import com.ai.openAi.endPoint.embeddings.EmbeddingObject;
import com.ai.openAi.endPoint.embeddings.req.EmbeddingCompletionRequest;
import com.ai.openAi.endPoint.embeddings.resp.EmbeddingCompletionResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        EmbeddingCompletionResponse response = this.apiServer.createEmbeddingsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, embeddingCompletionRequest).blockingGet();
        List<EmbeddingObject> data = response.getData();
        List<String> input = embeddingCompletionRequest.getInput();
        IntStream.range(0, Math.min(data.size(), input.size()))
                .forEach(i -> data.get(i).setContent(input.get(i)));
        return response;
    }

}
