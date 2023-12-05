package com.ai.openAi.achieve.standard.interfaceSession;


import com.ai.openAi.endPoint.moderations.req.ModerationRequest;
import com.ai.openAi.endPoint.moderations.resp.ModerationResponse;

/**
 * @Description: 审核会话窗口
 **/
public interface ModerationSession {
    /**
     * 审核
     *
     * @param apiHostByUser     用户自定义 host
     * @param apiKeyByUser      用户自定义密钥
     * @param apiUrlByUser      用户自定义请求地址
     * @param moderationRequest 请求参数
     * @return 审核结果
     */
    ModerationResponse moderationCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, ModerationRequest moderationRequest);
}
