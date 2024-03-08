package com.ai.openai.achieve.standard.session;

import com.ai.openai.endPoint.audio.req.SttCompletionRequest;
import com.ai.openai.endPoint.audio.req.TtsCompletionRequest;
import com.ai.openai.endPoint.audio.resp.SttCompletionResponse;
import retrofit2.Callback;

/**
 * @Description: openAi 音频会话窗口
 **/
public interface AudioSession {

    /**
     * 文字转语音
     *
     * @param apiHostByUser        用户自定义 host
     * @param apiKeyByUser         用户自定义密钥
     * @param apiUrlByUser         用户自定义请求地址
     * @param ttsCompletionRequest 语音接口请求参数
     * @param callback             回调函数
     */
    void ttsCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, TtsCompletionRequest ttsCompletionRequest, Callback callback);

    /**
     * 语音转文字
     *
     * @param apiHostByUser        用户自定义 host
     * @param apiKeyByUser         用户自定义密钥
     * @param apiUrlByUser         用户自定义请求地址
     * @param sttCompletionRequest 音频转文字接口请求参数
     * @return 请求结果
     */
    SttCompletionResponse sttCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, SttCompletionRequest sttCompletionRequest);

    /**
     * 翻译
     *
     * @param apiHostByUser        用户自定义 host
     * @param apiKeyByUser         用户自定义密钥
     * @param apiUrlByUser         用户自定义请求地址
     * @param sttCompletionRequest 音频转文字接口请求参数
     * @return 请求结果
     */
    SttCompletionResponse translationCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, SttCompletionRequest sttCompletionRequest);
}
