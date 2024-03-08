package com.ai.baidu.achieve.defaults.session;

import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.session.AggregationSession;
import com.ai.baidu.achieve.standard.session.ChatSession;
import com.ai.baidu.achieve.standard.session.EmbeddingSession;
import com.ai.baidu.achieve.standard.session.ImageSession;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: 聚合各个类型的session
 **/
public class DefaultAggregationSession implements AggregationSession {

    private Configuration configuration;

    private volatile ChatSession chatSession;

    private volatile EmbeddingSession embeddingSession;

    private volatile ImageSession imageSession;

    public DefaultAggregationSession(Configuration configuration) {
        this.configuration = ensureNotNull(configuration, "configuration");
    }

    @Override
    public ChatSession getChatSession() {
        if (chatSession == null) {
            synchronized (this) {
                if (chatSession == null) {
                    chatSession = new DefaultChatSession(configuration);
                }
            }
        }
        return chatSession;
    }

    @Override
    public EmbeddingSession getEmbeddingSession() {
        if (embeddingSession == null) {
            synchronized (this) {
                if (embeddingSession == null) {
                    embeddingSession = new DefaultEmbeddingSession(configuration);
                }
            }
        }
        return embeddingSession;
    }

    @Override
    public ImageSession getImageSession() {
        if (imageSession == null) {
            synchronized (this) {
                if (imageSession == null) {
                    imageSession = new DefaultImageSession(configuration);
                }
            }
        }
        return imageSession;
    }

}
