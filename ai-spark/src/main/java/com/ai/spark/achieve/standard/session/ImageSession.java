package com.ai.spark.achieve.standard.session;


import com.ai.spark.achieve.defaults.listener.ImageUnderstandingListener;
import com.ai.spark.endPoint.images.req.ImageCreateRequest;
import com.ai.spark.endPoint.images.req.ImageUnderstandingRequest;
import com.ai.spark.endPoint.images.resp.ImageCreateResponse;
import okhttp3.WebSocket;

/**
 * 图片生成场景下的接口
 */
public interface ImageSession {

    /**
     * 图片创作接口，使用系统默认的ApiData
     *
     * @param imageCreateRequest 请求参数
     * @return 请求结果
     */
    ImageCreateResponse imageCreate(ImageCreateRequest imageCreateRequest);

    /**
     * 图片创作接口，使用自定义的ApiData
     *
     * @param apiKey             用户的ApiKey
     * @param apiSecret          用户的ApiSecret
     * @param imageCreateRequest 请求参数
     * @return 请求结果
     */
    ImageCreateResponse imageCreate(String apiKey, String apiSecret, ImageCreateRequest imageCreateRequest);

    /**
     * 图片理解
     *
     * @param imageUnderstandingRequest 请求参数
     * @return 请求结果
     */
    public <T extends ImageUnderstandingListener> WebSocket imageUnderstanding(ImageUnderstandingRequest imageUnderstandingRequest, ImageUnderstandingListener imageUnderstandingListener);

    /**
     * 图片理解
     *
     * @param apiKey                    用户的ApiKey
     * @param apiSecret                 用户的ApiSecret
     * @param imageUnderstandingRequest 请求参数
     * @return 请求结果
     */
    public <T extends ImageUnderstandingListener> WebSocket imageUnderstanding(String apiKey, String apiSecret, ImageUnderstandingRequest imageUnderstandingRequest, ImageUnderstandingListener imageUnderstandingListener);

}
