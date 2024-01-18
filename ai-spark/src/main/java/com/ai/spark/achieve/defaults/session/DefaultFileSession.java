package com.ai.spark.achieve.defaults.session;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.ai.spark.achieve.ApiData;
import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.api.SparkApiServer;
import com.ai.spark.achieve.standard.interfaceSession.FileSession;
import com.ai.spark.common.utils.AuthUtils;
import com.ai.spark.endPoint.file.req.FileUploadRequest;
import com.ai.spark.endPoint.file.resp.FileUploadResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;


public class DefaultFileSession implements FileSession {

    private Configuration configuration;

    private SparkApiServer sparkApiServer;

    public DefaultFileSession(Configuration configuration) {
        this.configuration = configuration;
        this.sparkApiServer = configuration.getSparkApiServer();
    }

    @Override
    public FileUploadResponse fileUpload(FileUploadRequest fileUploadRequest) {
        ApiData apiData = configuration.getSystemApiData();
        return this.fileUpload(apiData.getAppId(), apiData.getApiKey(), apiData.getApiSecret(), fileUploadRequest);
    }

    @Override
    public FileUploadResponse fileUpload(String appId, String apiKey, String apiSecret, FileUploadRequest fileUploadRequest) {
        long ts = DateUtil.currentSeconds();
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), fileUploadRequest.getFile());
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", fileUploadRequest.getFile().getName(), fileBody);
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        if (StrUtil.isNotBlank(fileUploadRequest.getUrl())) {
            requestBodyMap.put(FileUploadRequest.Fields.url, RequestBody.create(MediaType.parse("multipart/form-data"), fileUploadRequest.getUrl()));
        }
        if (StrUtil.isNotBlank(fileUploadRequest.getFileName())) {
            requestBodyMap.put(FileUploadRequest.Fields.fileName, RequestBody.create(MediaType.parse("multipart/form-data"), fileUploadRequest.getFileName()));
        }
        if (StrUtil.isNotBlank(fileUploadRequest.getFileType())) {
            requestBodyMap.put(FileUploadRequest.Fields.fileType, RequestBody.create(MediaType.parse("multipart/form-data"), fileUploadRequest.getFileType()));
        }
        if (StrUtil.isNotBlank(fileUploadRequest.getCallbackUrl())) {
            requestBodyMap.put(FileUploadRequest.Fields.callbackUrl, RequestBody.create(MediaType.parse("multipart/form-data"), fileUploadRequest.getCallbackUrl()));
        }
        return sparkApiServer.fileUpload(appId, String.valueOf(ts), AuthUtils.getSignature(appId, apiSecret, ts), multipartBody, requestBodyMap).blockingGet();
    }
}
