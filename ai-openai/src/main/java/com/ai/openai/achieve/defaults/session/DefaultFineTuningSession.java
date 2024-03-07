package com.ai.openai.achieve.defaults.session;

import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.interfaceSession.FineTuningSession;
import com.ai.openai.common.CommonListResponse;
import com.ai.openai.endPoint.fineTuning.FineTuningEvent;
import com.ai.openai.endPoint.fineTuning.req.FineTuningRequest;
import com.ai.openai.endPoint.fineTuning.req.ListFineTuningRequest;
import com.ai.openai.endPoint.fineTuning.resp.FineTuningResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: OpenAI 微调类会话
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultFineTuningSession extends Session implements FineTuningSession {

    public DefaultFineTuningSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setOpenaiApiServer(ensureNotNull(configuration.getOpenaiApiServer(), "openaiApiServer"));
    }

    @Override
    public FineTuningResponse createFineTuningJobCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, FineTuningRequest fineTuningRequest) {
        return this.getOpenaiApiServer().createFineTuningJobCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fineTuningRequest).blockingGet();
    }

    @Override
    public CommonListResponse<FineTuningResponse> listFineTuningJobsCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ListFineTuningRequest listFineTuningRequest) {
        return this.listFineTuningJobsCompletions(apiHostByUser, apiKeyByUser, apiUrlByUser, listFineTuningRequest.getAfter(), listFineTuningRequest.getLimit());
    }

    @Override
    public CommonListResponse<FineTuningResponse> listFineTuningJobsCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String after, Integer limit) {
        return this.getOpenaiApiServer().listFineTuningJobsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, after, limit).blockingGet();
    }

    @Override
    public FineTuningResponse retrieveFineTuningJobCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fineTuningJobId) {
        return this.getOpenaiApiServer().retrieveFineTuningJobCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fineTuningJobId).blockingGet();
    }

    @Override
    public FineTuningResponse cancelFineTuningJobCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fineTuningJobId) {
        return this.getOpenaiApiServer().cancelFineTuningJobCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fineTuningJobId).blockingGet();
    }

    @Override
    public CommonListResponse<FineTuningEvent> listFineTuningEventsCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fineTuningJobId) {
        return this.getOpenaiApiServer().listFineTuningEventsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fineTuningJobId).blockingGet();
    }

}
