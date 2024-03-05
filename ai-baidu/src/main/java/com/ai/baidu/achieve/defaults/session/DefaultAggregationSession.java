package com.ai.baidu.achieve.defaults.session;

import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.interfaceSession.AggregationSession;
import com.ai.baidu.achieve.standard.interfaceSession.ChatSession;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: 聚合各个类型的session
 **/
public class DefaultAggregationSession implements AggregationSession {

    private Configuration configuration;

    private volatile ChatSession chatSession;

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

}
