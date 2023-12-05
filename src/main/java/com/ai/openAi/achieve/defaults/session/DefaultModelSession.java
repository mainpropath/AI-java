package com.ai.openAi.achieve.defaults.session;


import com.ai.openAi.achieve.Configuration;
import com.ai.openAi.achieve.standard.api.ApiServer;
import com.ai.openAi.achieve.standard.interfaceSession.ModelSession;
import com.ai.openAi.endPoint.models.ModelObject;
import com.ai.openAi.endPoint.models.resp.DeleteFineTuneModelResponse;

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
    private ApiServer apiServer;

    public DefaultModelSession(Configuration configuration) {
        this.configuration = configuration;
        this.apiServer = configuration.getApiServer();
    }

    @Override
    public List<ModelObject> listModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser) {
        return this.apiServer.listModelsCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser).blockingGet().getData();
    }

    @Override
    public ModelObject retrieveModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String model) {
        return this.apiServer.retrieveModelCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, model).blockingGet();
    }

    @Override
    public DeleteFineTuneModelResponse deleteFineTuneModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String model) {
        return this.apiServer.deleteFineTuneModelCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, model).blockingGet();
    }

}
