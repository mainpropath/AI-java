package com.ai.openai.achieve.defaults.session;

import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.session.ImageSession;
import com.ai.openai.endPoint.images.ImageObject;
import com.ai.openai.endPoint.images.req.CreateImageRequest;
import com.ai.openai.endPoint.images.req.ImageEditRequest;
import com.ai.openai.endPoint.images.req.ImageVariationRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: OpenAI 图片类会话
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultImageSession extends Session implements ImageSession {

    public DefaultImageSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setOpenaiApiServer(ensureNotNull(configuration.getOpenaiApiServer(), "openaiApiServer"));
    }

    @Override
    public List<ImageObject> createImageCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, CreateImageRequest createImageRequest) {
        return this.getOpenaiApiServer().createImageCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, createImageRequest).blockingGet().getData();
    }

    @Override
    public List<ImageObject> editImageCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, File image, File mask, ImageEditRequest imageEditRequest) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part imageMultipartBody = MultipartBody.Part.createFormData("image", image.getName(), imageBody);
        MultipartBody.Part maskMultipartBody = null;
        if (Objects.nonNull(mask)) {
            RequestBody maskBody = RequestBody.create(MediaType.parse("multipart/form-data"), mask);
            maskMultipartBody = MultipartBody.Part.createFormData("mask", image.getName(), maskBody);
        }
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("prompt", RequestBody.create(MediaType.parse("multipart/form-data"), imageEditRequest.getPrompt()));
        requestBodyMap.put("n", RequestBody.create(MediaType.parse("multipart/form-data"), imageEditRequest.getN().toString()));
        requestBodyMap.put("size", RequestBody.create(MediaType.parse("multipart/form-data"), imageEditRequest.getSize()));
        requestBodyMap.put("response_format", RequestBody.create(MediaType.parse("multipart/form-data"), imageEditRequest.getResponseFormat()));
        if (!(Objects.isNull(imageEditRequest.getUser()) || "".equals(imageEditRequest.getUser()))) {
            requestBodyMap.put("user", RequestBody.create(MediaType.parse("multipart/form-data"), imageEditRequest.getUser()));
        }
        return this.getOpenaiApiServer().editImageCompletion(
                apiHostByUser, apiKeyByUser, apiUrlByUser,
                imageMultipartBody,
                maskMultipartBody,
                requestBodyMap
        ).blockingGet().getData();
    }

    @Override
    public List<ImageObject> variationImageCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, File image, ImageVariationRequest imageVariationRequest) {
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("image", image.getName(), imageBody);
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("n", RequestBody.create(MediaType.parse("multipart/form-data"), imageVariationRequest.getN().toString()));
        requestBodyMap.put("size", RequestBody.create(MediaType.parse("multipart/form-data"), imageVariationRequest.getSize()));
        requestBodyMap.put("response_format", RequestBody.create(MediaType.parse("multipart/form-data"), imageVariationRequest.getResponseFormat()));
        if (!(Objects.isNull(imageVariationRequest.getUser()) || "".equals(imageVariationRequest.getUser()))) {
            requestBodyMap.put("user", RequestBody.create(MediaType.parse("multipart/form-data"), imageVariationRequest.getUser()));
        }
        return this.getOpenaiApiServer().variationImageCompletion(
                apiHostByUser, apiKeyByUser, apiUrlByUser,
                multipartBody,
                requestBodyMap
        ).blockingGet().getData();
    }

}
