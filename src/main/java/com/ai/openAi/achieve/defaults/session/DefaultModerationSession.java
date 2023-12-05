package com.ai.openAi.achieve.defaults.session;


import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.standard.api.ApiServer;
import com.ai.openAi.achieve.standard.interfaceSession.ModerationSession;
import com.ai.openAi.endPoint.moderations.req.ModerationRequest;
import com.ai.openAi.endPoint.moderations.resp.ModerationResponse;

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
    private ApiServer apiServer;

    public DefaultModerationSession(Configuration configuration) {
        this.configuration = configuration;
        this.apiServer = configuration.getApiServer();
    }

    @Override
    public ModerationResponse moderationCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ModerationRequest moderationRequest) {
        return this.apiServer.moderationCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, moderationRequest).blockingGet();
    }
}
