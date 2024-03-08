package com.ai.openai.achieve.defaults.session;


import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.session.ModerationSession;
import com.ai.openai.endPoint.moderations.req.ModerationRequest;
import com.ai.openai.endPoint.moderations.resp.ModerationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: OpenAI 审核类会话
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultModerationSession extends Session implements ModerationSession {


    public DefaultModerationSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setOpenaiApiServer(ensureNotNull(configuration.getOpenaiApiServer(), "openaiApiServer"));
    }

    @Override
    public ModerationResponse moderationCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ModerationRequest moderationRequest) {
        return this.getOpenaiApiServer().moderationCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, moderationRequest).blockingGet();
    }
}
