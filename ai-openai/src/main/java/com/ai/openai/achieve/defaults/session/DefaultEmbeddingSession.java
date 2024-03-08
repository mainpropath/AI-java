package com.ai.openai.achieve.defaults.session;

import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.session.EmbeddingSession;
import com.ai.openai.endPoint.embeddings.EmbeddingObject;
import com.ai.openai.endPoint.embeddings.req.EmbeddingCompletionRequest;
import com.ai.openai.endPoint.embeddings.resp.EmbeddingCompletionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: OpenAI 嵌入类会话
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultEmbeddingSession extends Session implements EmbeddingSession {

    public DefaultEmbeddingSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setOpenaiApiServer(ensureNotNull(configuration.getOpenaiApiServer(), "openaiApiServer"));
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
        EmbeddingCompletionResponse response = this.getOpenaiApiServer().createEmbeddingsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, embeddingCompletionRequest).blockingGet();
        List<EmbeddingObject> data = response.getData();
        List<String> input = embeddingCompletionRequest.getInput();
        IntStream.range(0, Math.min(data.size(), input.size()))
                .forEach(i -> data.get(i).setContent(input.get(i)));
        return response;
    }

}
