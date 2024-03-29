package com.ai.openai.achieve.defaults.session;

import cn.hutool.core.util.StrUtil;
import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.session.AudioSession;
import com.ai.openai.endPoint.audio.req.SttCompletionRequest;
import com.ai.openai.endPoint.audio.req.TtsCompletionRequest;
import com.ai.openai.endPoint.audio.resp.SttCompletionResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: OpenAI 语音类会话
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultAudioSession extends Session implements AudioSession {

    public DefaultAudioSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setOpenaiApiServer(ensureNotNull(configuration.getOpenaiApiServer(), "openaiApiServer"));
    }

    @Override
    public void ttsCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, TtsCompletionRequest ttsCompletionRequest, Callback callback) {
        this.getOpenaiApiServer().createSpeechCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, ttsCompletionRequest).enqueue(callback);
    }

    private SttCompletionResponse sttBaseCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, SttCompletionRequest sttCompletionRequest, String type) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), sttCompletionRequest.getFile());
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", sttCompletionRequest.getFile().getName(), fileBody);
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        if (StrUtil.isNotBlank(sttCompletionRequest.getLanguage())) {
            requestBodyMap.put(SttCompletionRequest.Fields.language, RequestBody.create(MediaType.parse("multipart/form-data"), sttCompletionRequest.getLanguage()));
        }
        if (StrUtil.isNotBlank(sttCompletionRequest.getModel())) {
            requestBodyMap.put(SttCompletionRequest.Fields.model, RequestBody.create(MediaType.parse("multipart/form-data"), sttCompletionRequest.getModel()));
        }
        if (StrUtil.isNotBlank(sttCompletionRequest.getPrompt())) {
            requestBodyMap.put(SttCompletionRequest.Fields.prompt, RequestBody.create(MediaType.parse("multipart/form-data"), sttCompletionRequest.getPrompt()));
        }
        if (StrUtil.isNotBlank(sttCompletionRequest.getResponseFormat())) {
            requestBodyMap.put(SttCompletionRequest.Fields.responseFormat, RequestBody.create(MediaType.parse("multipart/form-data"), sttCompletionRequest.getResponseFormat()));
        }
        if (Objects.nonNull(sttCompletionRequest.getTemperature())) {
            requestBodyMap.put(SttCompletionRequest.Fields.temperature, RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(sttCompletionRequest.getTemperature())));
        }
        if ("translation".equals(type)) {
            return this.getOpenaiApiServer().createTranslationCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, multipartBody, requestBodyMap).blockingGet();
        }
        return this.getOpenaiApiServer().createTranscriptionCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, multipartBody, requestBodyMap).blockingGet();
    }

    @Override
    public SttCompletionResponse sttCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, SttCompletionRequest sttCompletionRequest) {
        return sttBaseCompletions(apiHostByUser, apiKeyByUser, apiUrlByUser, sttCompletionRequest, "stt");
    }

    @Override
    public SttCompletionResponse translationCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, SttCompletionRequest sttCompletionRequest) {
        return sttBaseCompletions(apiHostByUser, apiKeyByUser, apiUrlByUser, sttCompletionRequest, "translation");
    }


}
