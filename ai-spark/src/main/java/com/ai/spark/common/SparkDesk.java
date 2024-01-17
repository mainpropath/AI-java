package com.ai.spark.common;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SparkDesk {
    public final static String GENERAL_V1 = "general";
    public final static String GENERAL_V2 = "generalv2";
    public final static String GENERAL_V3 = "generalv3";
    public final static String SPARK_API_HOST_WS_V1_1 = "http://spark-api.xf-yun.com/v1.1/chat";
    public final static String SPARK_API_HOST_WSS_V1_1 = "https://spark-api.xf-yun.com/v1.1/chat";
    public final static String SPARK_API_HOST_WS_V2_1 = "http://spark-api.xf-yun.com/v2.1/chat";
    public final static String SPARK_API_HOST_WSS_V2_1 = "https://spark-api.xf-yun.com/v2.1/chat";
    public final static String SPARK_API_HOST_WS_V3_1 = "http://spark-api.xf-yun.com/v3.1/chat";
    public final static String SPARK_API_HOST_WSS_V3_1 = "https://spark-api.xf-yun.com/v3.1/chat";

    public final static String FILE = "fileUpload";
    public final static String FILE_UPLOAD_API_HOST = "https://chatdoc.xfyun.cn/openapi/fileUpload";

    public final static String DOCUMENT_CHAT = "documentChat";
    public final static String DOCUMENT_CHAT_API_HOST = "wss://chatdoc.xfyun.cn/openapi/chat";


    public final static Map<String, String> urlMap = new HashMap<>();

    static {
        urlMap.put(GENERAL_V1, SPARK_API_HOST_WSS_V1_1);
        urlMap.put(GENERAL_V2, SPARK_API_HOST_WSS_V2_1);
        urlMap.put(GENERAL_V3, SPARK_API_HOST_WSS_V3_1);
        urlMap.put(FILE, FILE_UPLOAD_API_HOST);
        urlMap.put(DOCUMENT_CHAT, DOCUMENT_CHAT_API_HOST);
    }

    public static String getUrl(String key) {
        if (!urlMap.containsKey(key)) {
            log.error("No corresponding URL path found for {}", key);
            return null;
        }
        return urlMap.get(key);
    }

}
