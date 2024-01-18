package com.ai.spark.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SparkDesk {
    // 星火模型对话链接
    public final static String GENERAL_V1 = "general";
    public final static String GENERAL_V2 = "generalv2";
    public final static String GENERAL_V3 = "generalv3";
    public final static String SPARK_API_HOST_WS_V1_1_URL = "http://spark-api.xf-yun.com/v1.1/chat";
    public final static String SPARK_API_HOST_WSS_V1_1_URL = "https://spark-api.xf-yun.com/v1.1/chat";
    public final static String SPARK_API_HOST_WS_V2_1_URL = "http://spark-api.xf-yun.com/v2.1/chat";
    public final static String SPARK_API_HOST_WSS_V2_1_URL = "https://spark-api.xf-yun.com/v2.1/chat";
    public final static String SPARK_API_HOST_WS_V3_1_URL = "http://spark-api.xf-yun.com/v3.1/chat";
    public final static String SPARK_API_HOST_WSS_V3_1_URL = "https://spark-api.xf-yun.com/v3.1/chat";

    // 文档对话文件上传链接
    public final static String FILE_UPLOAD = "fileUpload";
    public final static String FILE_UPLOAD_API_URL = "https://chatdoc.xfyun.cn/openapi/fileUpload";

    // 文档对话链接
    public final static String DOCUMENT_CHAT = "documentChat";
    public final static String DOCUMENT_CHAT_API_URL = "wss://chatdoc.xfyun.cn/openapi/chat";

    // 文档总结链接
    public final static String DOCUMENT_SUMMARY = "documentSummary";
    public final static String DOCUMENT_SUMMARY_API_URL = "https://chatdoc.xfyun.cn/openapi/startSummary";

    @Getter
    @AllArgsConstructor
    public enum ApiRrl {
        general(SPARK_API_HOST_WSS_V1_1_URL),
        generalV2(SPARK_API_HOST_WSS_V2_1_URL),
        generalV3(SPARK_API_HOST_WSS_V3_1_URL),
        fileUpload(FILE_UPLOAD_API_URL),
        documentChat(DOCUMENT_CHAT_API_URL),
        documentSummary(DOCUMENT_SUMMARY_API_URL);
        private String url;
    }


    public final static Map<String, String> urlMap = new HashMap<>();

    static {
        urlMap.put(GENERAL_V1, SPARK_API_HOST_WSS_V1_1_URL);
        urlMap.put(GENERAL_V2, SPARK_API_HOST_WSS_V2_1_URL);
        urlMap.put(GENERAL_V3, SPARK_API_HOST_WSS_V3_1_URL);
        urlMap.put(FILE_UPLOAD, FILE_UPLOAD_API_URL);
        urlMap.put(DOCUMENT_CHAT, DOCUMENT_CHAT_API_URL);
    }

    public static String getUrl(String key) {
        if (!urlMap.containsKey(key)) {
            log.error("No corresponding URL path found for {}", key);
            return null;
        }
        return urlMap.get(key);
    }

}
