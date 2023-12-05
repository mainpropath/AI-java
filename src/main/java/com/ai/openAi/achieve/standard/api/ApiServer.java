package com.ai.openAi.achieve.standard.api;

import com.ai.openAi.endPoint.audio.req.TtsCompletionRequest;
import com.ai.openAi.endPoint.audio.resp.SttCompletionResponse;
import com.ai.openAi.endPoint.chat.req.ChatCompletionRequest;
import com.ai.openAi.endPoint.chat.req.QaCompletionRequest;
import com.ai.openAi.endPoint.chat.resp.ChatCompletionResponse;
import com.ai.openAi.endPoint.chat.resp.QaCompletionResponse;
import com.ai.openAi.common.CommonListResponse;
import com.ai.openAi.endPoint.embeddings.req.EmbeddingCompletionRequest;
import com.ai.openAi.endPoint.embeddings.resp.EmbeddingCompletionResponse;
import com.ai.openAi.endPoint.files.FileObject;
import com.ai.openAi.endPoint.files.resp.DeleteFileResponse;
import com.ai.openAi.endPoint.fineTuning.FineTuningEvent;
import com.ai.openAi.endPoint.fineTuning.req.FineTuningRequest;
import com.ai.openAi.endPoint.fineTuning.resp.FineTuningResponse;
import com.ai.openAi.endPoint.images.req.CreateImageRequest;
import com.ai.openAi.endPoint.images.resp.CreateImageResponse;
import com.ai.openAi.endPoint.models.ModelObject;
import com.ai.openAi.endPoint.models.resp.DeleteFineTuneModelResponse;
import com.ai.openAi.endPoint.moderations.req.ModerationRequest;
import com.ai.openAi.endPoint.moderations.resp.ModerationResponse;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

import static com.ai.openAi.common.Constants.*;

/**
 * @Description: OpenAI API接口
 **/
public interface ApiServer {

    /**
     * 简单问答接口
     *
     * @param apiHostByUser       用户自定义 host
     * @param apiKeyByUser        用户自定义密钥
     * @param apiUrlByUser        用户自定义请求地址
     * @param qaCompletionRequest 用户自定义封装的请求参数
     * @return 请求结果
     */
    @POST("/v1/completions")
    Single<QaCompletionResponse> createQaCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Body QaCompletionRequest qaCompletionRequest);

    /**
     * 对话聊天接口
     *
     * @param apiHostByUser         用户自定义 host
     * @param apiKeyByUser          用户自定义密钥
     * @param apiUrlByUser          用户自定义请求地址
     * @param chatCompletionRequest 用户自定义封装的请求参数
     * @return 请求结果
     */
    @POST("/v1/chat/completions")
    Single<ChatCompletionResponse> createChatCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Body ChatCompletionRequest chatCompletionRequest);


    /**
     * 输入文本生成音频
     *
     * @param apiHostByUser        用户自定义 host
     * @param apiKeyByUser         用户自定义密钥
     * @param apiUrlByUser         用户自定义请求地址
     * @param ttsCompletionRequest 用户自定义封装的请求参数
     * @return 请求结果
     */
    @POST("/v1/audio/speech")
    @Streaming
    Call<ResponseBody> createSpeechCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Body TtsCompletionRequest ttsCompletionRequest);

    /**
     * 发送音频文件，解析成文本
     *
     * @param apiHostByUser  用户自定义 host
     * @param apiKeyByUser   用户自定义密钥
     * @param apiUrlByUser   用户自定义请求地址
     * @param file           音频文件
     * @param requestBodyMap 请求参数
     * @return 请求结果
     */
    @Multipart
    @POST("/v1/audio/transcriptions")
    Single<SttCompletionResponse> createTranscriptionCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> requestBodyMap);

    /**
     * 发送音频文件，解析并翻译为英文
     *
     * @param apiHostByUser  用户自定义 host
     * @param apiKeyByUser   用户自定义密钥
     * @param apiUrlByUser   用户自定义请求地址
     * @param file           音频文件
     * @param requestBodyMap 请求参数
     * @return 请求结果
     */
    @Multipart
    @POST("/v1/audio/translations")
    Single<SttCompletionResponse> createTranslationCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> requestBodyMap);

    /**
     * 创建嵌入
     *
     * @param apiHostByUser              用户自定义 host
     * @param apiKeyByUser               用户自定义密钥
     * @param apiUrlByUser               用户自定义请求地址
     * @param embeddingCompletionRequest 请求参数
     * @return 请求结果
     */
    @POST("/v1/embeddings")
    Single<EmbeddingCompletionResponse> createEmbeddingsCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Body EmbeddingCompletionRequest embeddingCompletionRequest);

    /**
     * 创建微调作业
     *
     * @param apiHostByUser           用户自定义 host
     * @param apiKeyByUser            用户自定义密钥
     * @param apiUrlByUser            用户自定义请求地址
     * @param fineTuningRequest 请求参数
     * @return 请求结果
     */
    @POST("/v1/fine_tuning/jobs")
    Single<FineTuningResponse> createFineTuningJobCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Body FineTuningRequest fineTuningRequest);

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
    @GET("/v1/fine_tuning/jobs")
    Single<CommonListResponse<FineTuningResponse>> listFineTuningJobsCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Query("after") String after, @Query("limit") Integer limit);

    /**
     * 检索微调作业
     *
     * @param apiHostByUser   用户自定义 host
     * @param apiKeyByUser    用户自定义密钥
     * @param apiUrlByUser    用户自定义请求地址
     * @param fineTuningJobId 微调作业的 ID
     * @return 请求结果
     */
    @GET("/v1/fine_tuning/jobs/{fine_tuning_job_id}")
    Single<FineTuningResponse> retrieveFineTuningJobCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Path("fine_tuning_job_id") String fineTuningJobId);

    /**
     * 取消微调作业
     *
     * @param apiHostByUser   用户自定义 host
     * @param apiKeyByUser    用户自定义密钥
     * @param apiUrlByUser    用户自定义请求地址
     * @param fineTuningJobId 微调作业的 ID
     * @return 请求结果
     */
    @POST("/v1/fine_tuning/jobs/{fine_tuning_job_id}/cancel")
    Single<FineTuningResponse> cancelFineTuningJobCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Path("fine_tuning_job_id") String fineTuningJobId);

    /**
     * 列出微调事件
     *
     * @param apiHostByUser   用户自定义 host
     * @param apiKeyByUser    用户自定义密钥
     * @param apiUrlByUser    用户自定义请求地址
     * @param fineTuningJobId 微调作业的 ID
     * @return 请求结果
     */
    @GET("/v1/fine_tuning/jobs/{fine_tuning_job_id}/events")
    Single<CommonListResponse<FineTuningEvent>> listFineTuningEventsCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Path("fine_tuning_job_id") String fineTuningJobId);

    /**
     * 返回属于用户组织的文件列表
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @return 文件列表
     */
    @GET("/v1/files")
    Single<CommonListResponse<FileObject>> listFilesCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser);

    /**
     * 上传文件
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param file          要上载的 File 对象（不是文件名）
     * @param purpose       上传文件的预期用途
     * @return 上载的 File 对象
     */
    @Multipart
    @POST("/v1/files")
    Single<FileObject> uploadFileCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Part MultipartBody.Part file, @Part("purpose") RequestBody purpose);

    /**
     * 删除文件
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param fileId        文件ID
     * @return 删除状态
     */
    @DELETE("/v1/files/{file_id}")
    Single<DeleteFileResponse> deleteFileCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Path("file_id") String fileId);

    /**
     * 检索文件
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param fileId        文件ID
     * @return 文件信息
     */
    @GET("/v1/files/{file_id}")
    Single<FileObject> retrieveFileCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Path("file_id") String fileId);


    /**
     * 获取文件内容
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param fileId        文件ID
     * @return 文件内容
     */
    @Streaming
    @GET("/v1/files/{file_id}/content")
    Single<ResponseBody> retrieveFileContentCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Path("file_id") String fileId);

    /**
     * 生成图片
     *
     * @param apiHostByUser      用户自定义 host
     * @param apiKeyByUser       用户自定义密钥
     * @param apiUrlByUser       用户自定义请求地址
     * @param createImageRequest 生成图片的请求参数
     * @return 图片信息
     */
    @POST("/v1/images/generations")
    Single<CreateImageResponse> createImageCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Body CreateImageRequest createImageRequest);

    /**
     * 编辑图片
     *
     * @param apiHostByUser  用户自定义 host
     * @param apiKeyByUser   用户自定义密钥
     * @param apiUrlByUser   用户自定义请求地址
     * @param image          被编辑的图片
     * @param mask           一个额外的图像，其完全透明的区域（例如，alpha为零）指示应该编辑的位置。必须是有效的 PNG 文件，小于 4MB，并且尺寸与image相同。
     * @param requestBodyMap 请求参数
     * @return 图片信息
     */
    @Multipart
    @POST("/v1/images/edits")
    Single<CreateImageResponse> editImageCompletion(@Header(API_HOST) String apiHostByUser,
                                                    @Header(API_KEY) String apiKeyByUser,
                                                    @Header(URL) String apiUrlByUser,
                                                    @Part() MultipartBody.Part image, @Part() MultipartBody.Part mask, @PartMap() Map<String, RequestBody> requestBodyMap);

    /**
     * 创建给定图像的变体
     *
     * @param apiHostByUser  用户自定义 host
     * @param apiKeyByUser   用户自定义密钥
     * @param apiUrlByUser   用户自定义请求地址
     * @param image          被编辑的图片
     * @param requestBodyMap 请求参数
     * @return 图片信息
     */
    @Multipart
    @POST("/v1/images/variations")
    Single<CreateImageResponse> variationImageCompletion(@Header(API_HOST) String apiHostByUser,
                                                         @Header(API_KEY) String apiKeyByUser,
                                                         @Header(URL) String apiUrlByUser, @Part() MultipartBody.Part image,
                                                         @PartMap() Map<String, RequestBody> requestBodyMap);

    /**
     * 列出模型
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @return 模型信息
     */
    @GET("/v1/models")
    Single<CommonListResponse<ModelObject>> listModelsCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser);

    /**
     * 检索模型信息
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param model         模型ID
     * @return 模型信息
     */
    @GET("/v1/models/{model}")
    Single<ModelObject> retrieveModelCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Path("model") String model);

    /**
     * 删除微调模型
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param model         模型ID
     * @return 删除状态
     */
    @DELETE("/v1/models/{model}")
    Single<DeleteFineTuneModelResponse> deleteFineTuneModelCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Path("model") String model);


    /**
     * 审核
     *
     * @param apiHostByUser     用户自定义 host
     * @param apiKeyByUser      用户自定义密钥
     * @param apiUrlByUser      用户自定义请求地址
     * @param moderationRequest 审核请求参数
     * @return 审核结果
     */
    @POST("/v1/moderations")
    Single<ModerationResponse> moderationCompletion(@Header(API_HOST) String apiHostByUser, @Header(API_KEY) String apiKeyByUser, @Header(URL) String apiUrlByUser, @Body ModerationRequest moderationRequest);


}
