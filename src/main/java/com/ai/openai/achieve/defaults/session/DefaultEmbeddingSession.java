package com.ai.openai.achieve.defaults.session;

import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.api.OpenaiApiServer;
import com.ai.openai.achieve.standard.interfaceSession.EmbeddingSession;
import com.ai.openai.endPoint.embeddings.EmbeddingObject;
import com.ai.openai.endPoint.embeddings.req.EmbeddingCompletionRequest;
import com.ai.openai.endPoint.embeddings.resp.EmbeddingCompletionResponse;

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
    private OpenaiApiServer openaiApiServer;

    public DefaultEmbeddingSession(Configuration configuration) {
        this.configuration = configuration;
        this.openaiApiServer = configuration.getOpenaiApiServer();
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
        EmbeddingCompletionResponse response = this.openaiApiServer.createEmbeddingsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, embeddingCompletionRequest).blockingGet();
        List<EmbeddingObject> data = response.getData();
        List<String> input = embeddingCompletionRequest.getInput();
        IntStream.range(0, Math.min(data.size(), input.size()))
                .forEach(i -> data.get(i).setContent(input.get(i)));
        return response;
    }

}
