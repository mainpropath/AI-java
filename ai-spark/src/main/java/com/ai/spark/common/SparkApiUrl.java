package com.ai.spark.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 记录各个API的请求URL
 */
@Slf4j
public class SparkApiUrl {
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
    public final static String DOCUMENT_SUMMARY_START = "documentSummaryStart";
    public final static String DOCUMENT_SUMMARY_START_API_URL = "https://chatdoc.xfyun.cn/openapi/startSummary";

    // 查询文档总结结果链接
    public final static String DOCUMENT_SUMMARY_QUERY = "documentSummaryStart";
    public final static String DOCUMENT_SUMMARY_QUERY_API_URL = "https://chatdoc.xfyun.cn/openapi/fileSummary";

    // 文本嵌入接口
    public final static String EMBEDDING_P = "Embeddingp";
    public final static String EMBEDDING_P_API_URL = "https://cn-huabei-1.xf-yun.com/v1/private/sa8a05c27";
    public final static String EMBEDDING_Q = "Embeddingq";
    public final static String EMBEDDING_Q_API_URL = "https://cn-huabei-1.xf-yun.com/v1/private/s50d55a16";

    // 图片生成接口
    public final static String IMAGE_CREATE = "imageCreate";
    public final static String IMAGE_CREATE_API_URL = "https://spark-api.cn-huabei-1.xf-yun.com/v2.1/tti";

    // 图片理解接口
    public final static String IMAGE_UNDERSANDING = "imageUnderstanding";
    public final static String IMAGE_UNDERSANDING_API_URL = "https://spark-api.cn-huabei-1.xf-yun.com/v2.1/image";

    // 超拟人合成协议接口
    public final static String HYPERMIMETIC_SYNTHESIS = "hypermimeticSynthesis";
    public final static String HYPERMIMETIC_SYNTHESIS_API_URL = "wss://cbm01.cn-huabei-1.xf-yun.com/v1/private/medd90fec";

    public final static Map<String, String> urlMap = new HashMap<>();

    static {
        urlMap.put(GENERAL_V1, SPARK_API_HOST_WSS_V1_1_URL);
        urlMap.put(GENERAL_V2, SPARK_API_HOST_WSS_V2_1_URL);
        urlMap.put(GENERAL_V3, SPARK_API_HOST_WSS_V3_1_URL);
        urlMap.put(FILE_UPLOAD, FILE_UPLOAD_API_URL);
        urlMap.put(DOCUMENT_CHAT, DOCUMENT_CHAT_API_URL);
        urlMap.put(DOCUMENT_SUMMARY_START, DOCUMENT_SUMMARY_START_API_URL);
        urlMap.put(DOCUMENT_SUMMARY_QUERY, DOCUMENT_SUMMARY_QUERY_API_URL);
        urlMap.put(EMBEDDING_P, EMBEDDING_P_API_URL);
        urlMap.put(EMBEDDING_Q, EMBEDDING_Q_API_URL);
        urlMap.put(IMAGE_CREATE, IMAGE_CREATE_API_URL);
        urlMap.put(IMAGE_UNDERSANDING, IMAGE_UNDERSANDING_API_URL);
        urlMap.put(HYPERMIMETIC_SYNTHESIS, HYPERMIMETIC_SYNTHESIS_API_URL);
    }

    public static String getUrl(String key) {
        if (!urlMap.containsKey(key)) {
            log.error("No corresponding URL path found for {}", key);
            return null;
        }
        return urlMap.get(key);
    }

    @Getter
    @AllArgsConstructor
    public enum ApiUrl {
        general(SPARK_API_HOST_WSS_V1_1_URL),
        generalV2(SPARK_API_HOST_WSS_V2_1_URL),
        generalV3(SPARK_API_HOST_WSS_V3_1_URL),
        fileUpload(FILE_UPLOAD_API_URL),
        documentChat(DOCUMENT_CHAT_API_URL),
        documentSummaryStart(DOCUMENT_SUMMARY_START_API_URL),
        documentSummaryQuery(DOCUMENT_SUMMARY_QUERY_API_URL),
        embeddingp(EMBEDDING_P_API_URL),
        embeddingq(EMBEDDING_Q_API_URL),
        imageCreate(IMAGE_CREATE_API_URL),
        imageUnderstanding(IMAGE_UNDERSANDING_API_URL),
        hypermimeticSynthesis(HYPERMIMETIC_SYNTHESIS_API_URL);
        private String url;
    }

}
