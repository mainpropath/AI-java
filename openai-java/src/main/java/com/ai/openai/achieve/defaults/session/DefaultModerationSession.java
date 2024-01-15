package com.ai.openai.achieve.defaults.session;


import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.api.OpenaiApiServer;
import com.ai.openai.achieve.standard.interfaceSession.ModerationSession;
import com.ai.openai.endPoint.moderations.req.ModerationRequest;
import com.ai.openai.endPoint.moderations.resp.ModerationResponse;

/**
 * @Description: OpenAI 审核类会话
 **/
public class DefaultModerationSession implements ModerationSession {
    /**
     * 配置信息
     */
    private Configuration configuration;
    /**
     * OpenAI 接口
     */
    private OpenaiApiServer openaiApiServer;

    public DefaultModerationSession(Configuration configuration) {
        this.configuration = configuration;
        this.openaiApiServer = configuration.getOpenaiApiServer();
    }

    @Override
    public ModerationResponse moderationCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ModerationRequest moderationRequest) {
        return this.openaiApiServer.moderationCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, moderationRequest).blockingGet();
    }
}
