package com.ai.openai.achieve.defaults.session;


import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.session.ModelSession;
import com.ai.openai.endPoint.models.ModelObject;
import com.ai.openai.endPoint.models.resp.DeleteFineTuneModelResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: OpenAI 模型类会话
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultModelSession extends Session implements ModelSession {

    public DefaultModelSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setOpenaiApiServer(ensureNotNull(configuration.getOpenaiApiServer(), "openaiApiServer"));
    }

    @Override
    public List<ModelObject> listModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser) {
        return this.getOpenaiApiServer().listModelsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser).blockingGet().getData();
    }

    @Override
    public ModelObject retrieveModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String model) {
        return this.getOpenaiApiServer().retrieveModelCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, model).blockingGet();
    }

    @Override
    public DeleteFineTuneModelResponse deleteFineTuneModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String model) {
        return this.getOpenaiApiServer().deleteFineTuneModelCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, model).blockingGet();
    }

}
