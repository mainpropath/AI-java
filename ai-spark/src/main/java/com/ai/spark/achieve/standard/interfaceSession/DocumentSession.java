package com.ai.spark.achieve.standard.interfaceSession;


import com.ai.spark.endPoint.document.req.FileUploadRequest;
import com.ai.spark.endPoint.document.resp.DocumentSummaryResponse;
import com.ai.spark.endPoint.document.resp.FileUploadResponse;

public interface DocumentSession {

    FileUploadResponse fileUpload(FileUploadRequest fileUploadRequest);

    FileUploadResponse fileUpload(String appId, String apiKey, String apiSecret, FileUploadRequest fileUploadRequest);

    DocumentSummaryResponse documentSummary(String fileId);

    DocumentSummaryResponse documentSummary(String appId, String apiKey, String apiSecret, String fileId);


}
