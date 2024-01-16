package com.ai.openai.achieve.defaults.session;


import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.api.OpenaiApiServer;
import com.ai.openai.achieve.standard.interfaceSession.ModelSession;
import com.ai.openai.endPoint.models.ModelObject;
import com.ai.openai.endPoint.models.resp.DeleteFineTuneModelResponse;

import java.util.List;

/**
 * @Description: OpenAI 模型类会话
 **/
public class DefaultModelSession implements ModelSession {

    /**
     * 配置信息
     */
    private Configuration configuration;
    /**
     * OpenAI 接口
     */
    private OpenaiApiServer openaiApiServer;

    public DefaultModelSession(Configuration configuration) {
        this.configuration = configuration;
        this.openaiApiServer = configuration.getOpenaiApiServer();
    }

    @Override
    public List<ModelObject> listModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser) {
        return this.openaiApiServer.listModelsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser).blockingGet().getData();
    }

    @Override
    public ModelObject retrieveModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String model) {
        return this.openaiApiServer.retrieveModelCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, model).blockingGet();
    }

    @Override
    public DeleteFineTuneModelResponse deleteFineTuneModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String model) {
        return this.openaiApiServer.deleteFineTuneModelCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, model).blockingGet();
    }

}
