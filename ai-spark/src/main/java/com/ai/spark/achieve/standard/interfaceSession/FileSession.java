package com.ai.spark.achieve.standard.interfaceSession;


import com.ai.spark.endPoint.file.req.FileUploadRequest;
import com.ai.spark.endPoint.file.resp.FileUploadResponse;

public interface FileSession {

    FileUploadResponse fileUpload(FileUploadRequest fileUploadRequest);

    FileUploadResponse fileUpload(String appId, String apiKey, String apiSecret, FileUploadRequest fileUploadRequest);

}
