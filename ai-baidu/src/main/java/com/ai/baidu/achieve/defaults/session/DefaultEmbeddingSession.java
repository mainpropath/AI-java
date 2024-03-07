package com.ai.baidu.achieve.defaults.session;

import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.function.EmbeddingSession;
import com.ai.baidu.endPoint.embedding.req.EmbeddingRequest;
import com.ai.baidu.endPoint.embedding.resp.EmbeddingResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
        return this.getBaiduApiServer().embedding(checkAccessToken(accessToken), embeddingRequest).blockingGet();
    }

}
