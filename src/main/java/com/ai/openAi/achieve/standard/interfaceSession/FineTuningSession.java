package com.ai.openAi.achieve.standard.interfaceSession;

import com.ai.openAi.common.CommonListResponse;
import com.ai.openAi.endPoint.fineTuning.FineTuningEvent;
import com.ai.openAi.endPoint.fineTuning.req.FineTuningRequest;
import com.ai.openAi.endPoint.fineTuning.req.ListFineTuningRequest;
import com.ai.openAi.endPoint.fineTuning.resp.FineTuningResponse;

/**
 * @Description: 微调会话窗口
 **/
public interface FineTuningSession {

    /**
     * 创建微调作业
     *
     * @param apiHostByUser           用户自定义 host
     * @param apiKeyByUser            用户自定义密钥
     * @param apiUrlByUser            用户自定义请求地址
     * @param fineTuningRequest 请求参数
     * @return 请求结果
     */
    FineTuningResponse createFineTuningJobCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, FineTuningRequest fineTuningRequest);

    /**
     * 列出微调作业
     *
     * @param apiHostByUser         用户自定义 host
     * @param apiKeyByUser          用户自定义密钥
     * @param apiUrlByUser          用户自定义请求地址
     * @param listFineTuningRequest 请求参数
     * @return 请求结果
     */
    CommonListResponse<FineTuningResponse> listFineTuningJobsCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ListFineTuningRequest listFineTuningRequest);

    /**
     * 列出微调作业
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param after         上一个分页请求中最后一个作业的标识符
     * @param limit         要检索的微调作业数
     * @return 请求结果
     */
    CommonListResponse<FineTuningResponse> listFineTuningJobsCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String after, Integer limit);

    /**
     * 检索微调作业
     *
     * @param apiHostByUser   用户自定义 host
     * @param apiKeyByUser    用户自定义密钥
     * @param apiUrlByUser    用户自定义请求地址
     * @param fineTuningJobId 微调作业的 ID
     * @return 请求结果
     */
    FineTuningResponse retrieveFineTuningJobCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fineTuningJobId);

    /**
     * 取消微调作业
     *
     * @param apiHostByUser   用户自定义 host
     * @param apiKeyByUser    用户自定义密钥
     * @param apiUrlByUser    用户自定义请求地址
     * @param fineTuningJobId 微调作业的 ID
     * @return 请求结果
     */
    FineTuningResponse cancelFineTuningJobCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fineTuningJobId);

    /**
     * 列出微调事件
     *
     * @param apiHostByUser   用户自定义 host
     * @param apiKeyByUser    用户自定义密钥
     * @param apiUrlByUser    用户自定义请求地址
     * @param fineTuningJobId 微调作业的 ID
     * @return 请求结果
     */
    CommonListResponse<FineTuningEvent> listFineTuningEventsCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fineTuningJobId);
}
