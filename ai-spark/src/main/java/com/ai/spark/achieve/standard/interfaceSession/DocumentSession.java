package com.ai.spark.achieve.standard.interfaceSession;


import com.ai.spark.endPoint.document.req.FileUploadRequest;
import com.ai.spark.endPoint.document.resp.DocumentSummaryResponse;
import com.ai.spark.endPoint.document.resp.FileUploadResponse;

public interface DocumentSession {

    FileUploadResponse fileUpload(FileUploadRequest fileUploadRequest);

    FileUploadResponse fileUpload(String appId, String apiSecret, FileUploadRequest fileUploadRequest);

    DocumentSummaryResponse documentSummaryStart(String fileId);

    DocumentSummaryResponse documentSummaryStart(String appId, String apiSecret, String fileId);

    DocumentSummaryResponse documentSummaryQuery(String fileId);

    DocumentSummaryResponse documentSummaryQuery(String appId, String apiSecret, String fileId);


}
