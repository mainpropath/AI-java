package com.ai.baidu.achieve.defaults.session;

import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.session.EmbeddingSession;
import com.ai.baidu.endPoint.embedding.EmbeddingData;
import com.ai.baidu.endPoint.embedding.req.EmbeddingRequest;
import com.ai.baidu.endPoint.embedding.resp.EmbeddingResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.stream.IntStream;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: 百度嵌入操作
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultEmbeddingSession extends Session implements EmbeddingSession {

    public DefaultEmbeddingSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setBaiduApiServer(ensureNotNull(configuration.getBaiduApiServer(), "baiduApiServer"));
    }

    @Override
    public EmbeddingResponse embedding(String accessToken, EmbeddingRequest embeddingRequest) {
        EmbeddingResponse embeddingResponse = this.getBaiduApiServer().embedding(checkAccessToken(accessToken), embeddingRequest).blockingGet();
        List<EmbeddingData> data = embeddingResponse.getData();
        List<String> input = embeddingRequest.getInput();
        IntStream.range(0, Math.min(data.size(), input.size()))
                .forEach(i -> data.get(i).setContent(input.get(i)));
        return embeddingResponse;
    }

}
