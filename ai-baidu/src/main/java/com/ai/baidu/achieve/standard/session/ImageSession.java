package com.ai.baidu.achieve.standard.session;

import com.ai.baidu.endPoint.images.req.ImageRequest;
import com.ai.baidu.endPoint.images.resp.ImageResponse;

/**
 * @Description: 图片会话窗口
 **/
public interface ImageSession {

    /**
     * 文生图接口
     *
     * @param accessToken  鉴权的 accessToken
     * @param imageRequest 请求参数
     * @return 请求结果
     */
    ImageResponse text2image(String accessToken, ImageRequest imageRequest);

    default ImageResponse text2image(ImageRequest imageRequest) {
        return text2image(null, imageRequest);
    }

}
