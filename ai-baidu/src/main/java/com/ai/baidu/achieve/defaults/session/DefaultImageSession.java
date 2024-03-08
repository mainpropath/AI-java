package com.ai.baidu.achieve.defaults.session;

import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.session.ImageSession;
import com.ai.baidu.endPoint.images.req.ImageRequest;
import com.ai.baidu.endPoint.images.resp.ImageResponse;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: 图像生成
 **/
public class DefaultImageSession extends Session implements ImageSession {

    public DefaultImageSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setBaiduApiServer(ensureNotNull(configuration.getBaiduApiServer(), "baiduApiServer"));
    }

    @Override
    public ImageResponse text2image(String accessToken, ImageRequest imageRequest) {
        String s = checkAccessToken(accessToken);
        return this.getBaiduApiServer().text2image(s, imageRequest).blockingGet();
    }
}
