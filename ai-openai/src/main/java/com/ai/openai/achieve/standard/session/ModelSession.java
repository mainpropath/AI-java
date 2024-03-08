package com.ai.openai.achieve.standard.session;


import com.ai.openai.endPoint.models.ModelObject;
import com.ai.openai.endPoint.models.resp.DeleteFineTuneModelResponse;

import java.util.List;

/**
 * @Description: 模型会话窗口
 **/
public interface ModelSession {

    /**
     * 列出模型
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @return 模型列表
     */
    List<ModelObject> listModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser);

    /**
     * 检索模型
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param model         模型ID
     * @return 模型信息
     */
    ModelObject retrieveModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String model);

    /**
     * 删除微调模型
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param model         模型ID
     * @return 删除状态
     */
    DeleteFineTuneModelResponse deleteFineTuneModelCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String model);
}
