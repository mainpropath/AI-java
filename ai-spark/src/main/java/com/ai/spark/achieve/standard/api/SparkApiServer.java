package com.ai.spark.achieve.standard.api;


import com.ai.spark.endPoint.file.resp.FileUploadResponse;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

import java.util.Map;

import static com.ai.spark.common.Constants.*;
import static com.ai.spark.common.SparkDesk.FILE_UPLOAD_API_HOST;

/**
 * @Description: 讯飞星火 API接口
 */
public interface SparkApiServer {

    @Multipart
    @POST(FILE_UPLOAD_API_HOST)
    Single<FileUploadResponse> fileUpload(@Header(APP_ID) String appId, @Header(TIMESTAMP) String timestamp, @Header(SIGNATURE) String signature, @Part MultipartBody.Part file, @PartMap Map<String, RequestBody> requestBodyMap);
}
