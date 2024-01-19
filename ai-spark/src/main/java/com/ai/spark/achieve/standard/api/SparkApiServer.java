package com.ai.spark.achieve.standard.api;


import com.ai.spark.endPoint.document.resp.DocumentSummaryResponse;
import com.ai.spark.endPoint.document.resp.FileUploadResponse;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

import java.util.Map;

import static com.ai.spark.common.Constants.*;
import static com.ai.spark.common.SparkApiUrl.FILE_UPLOAD_API_URL;

/**
 * @Description: 讯飞星火 API接口
 */
public interface SparkApiServer {

    /**
     * 文件上传接口
     *
     * @param appId          用户appId
     * @param timestamp      当前时间戳（秒）
     * @param signature      生成的签名
     * @param file           需要上传的文件
     * @param requestBodyMap 其他字段
     * @return 文件上传返回参数
     */
    @Multipart
    @POST(FILE_UPLOAD_API_URL)
    Single<FileUploadResponse> fileUpload(@Header(APP_ID) String appId, @Header(TIMESTAMP) String timestamp, @Header(SIGNATURE) String signature, @Part MultipartBody.Part file, @PartMap Map<String, RequestBody> requestBodyMap);

    /**
     * 文档总结，文档总结结果查询两个接口二合一，参数都一样，请求路径不同
     *
     * @param url       请求地址
     * @param appId     用户appId
     * @param timestamp 当前时间戳（秒）
     * @param signature 生成的签名
     * @param fileId    文件ID
     * @return 请求结果信息
     */
    @Multipart
    @POST
    Single<DocumentSummaryResponse> documentSummary(@Url String url, @Header(APP_ID) String appId, @Header(TIMESTAMP) String timestamp, @Header(SIGNATURE) String signature, @Part("fileId") RequestBody fileId);


}
