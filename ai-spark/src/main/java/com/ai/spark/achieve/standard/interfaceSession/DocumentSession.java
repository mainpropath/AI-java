package com.ai.spark.achieve.standard.interfaceSession;


import com.ai.spark.endPoint.document.req.FileUploadRequest;
import com.ai.spark.endPoint.document.resp.DocumentSummaryResponse;
import com.ai.spark.endPoint.document.resp.FileUploadResponse;

/**
 * 文档场景下的接口
 */
public interface DocumentSession {

    /**
     * 文件上传
     *
     * @param fileUploadRequest 上传的信息
     * @return 返回信息
     */
    FileUploadResponse fileUpload(FileUploadRequest fileUploadRequest);

    /**
     * 文件上传
     *
     * @param appId             用户的AppId
     * @param apiSecret         用户的ApiSecret
     * @param fileUploadRequest 上传的信息
     * @return 返回信息
     */
    FileUploadResponse fileUpload(String appId, String apiSecret, FileUploadRequest fileUploadRequest);

    /**
     * 发起文档总结接口
     *
     * @param fileId 文件ID
     * @return 返回信息
     */
    DocumentSummaryResponse documentSummaryStart(String fileId);

    /**
     * 发起文档总结接口
     *
     * @param appId     用户的AppId
     * @param apiSecret 用户的ApiSecret
     * @param fileId    文件ID
     * @return 返回信息
     */
    DocumentSummaryResponse documentSummaryStart(String appId, String apiSecret, String fileId);

    /**
     * 查询文档总结结果
     *
     * @param fileId 文件ID
     * @return 返回信息
     */
    DocumentSummaryResponse documentSummaryQuery(String fileId);

    /**
     * 查询文档总结结果
     *
     * @param appId     用户的AppId
     * @param apiSecret 用户的ApiSecret
     * @param fileId    文件ID
     * @return 返回信息
     */
    DocumentSummaryResponse documentSummaryQuery(String appId, String apiSecret, String fileId);

}
