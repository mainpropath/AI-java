package com.ai.openai.achieve.defaults.session;


import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.session.ModerationSession;
import com.ai.openai.endPoint.moderations.Result;
import com.ai.openai.endPoint.moderations.req.ModerationRequest;
import com.ai.openai.endPoint.moderations.resp.ModerationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.stream.IntStream;

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
        ModerationResponse response = getOpenaiApiServer().moderationCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, moderationRequest).blockingGet();
        List<String> inputs = moderationRequest.getInput();
        List<Result> results = response.getResults();
        // 将审核文本和审核结果进行对应
        IntStream.range(0, Math.min(results.size(), inputs.size()))
                .forEach(i -> results.get(i).setContent(inputs.get(i)));
        return response;
    }
}
