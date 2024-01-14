package com.ai.openai.achieve.standard.interfaceSession;

import com.ai.openai.endPoint.images.ImageObject;
import com.ai.openai.endPoint.images.req.CreateImageRequest;
import com.ai.openai.endPoint.images.req.ImageEditRequest;
import com.ai.openai.endPoint.images.req.ImageVariationRequest;

import java.io.File;
import java.util.List;

/**
 * @Description: 图片会话窗口
 **/
public interface ImageSession {
    /**
     * @param apiHostByUser      用户自定义 host
     * @param apiKeyByUser       用户自定义密钥
     * @param apiUrlByUser       用户自定义请求地址
     * @param createImageRequest 请求参数
     * @return 图片信息
     */
    List<ImageObject> createImageCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, CreateImageRequest createImageRequest);


    /**
     * 编辑图像
     *
     * @param apiHostByUser    用户自定义 host
     * @param apiKeyByUser     用户自定义密钥
     * @param apiUrlByUser     用户自定义请求地址
     * @param image            要编辑的图像
     * @param mask             一个额外的图像，其完全透明的区域（例如，alpha为零）指示应该编辑的位置。必须是有效的 PNG 文件，小于 4MB，并且尺寸与image相同
     * @param imageEditRequest 请求参数
     * @return 图片信息
     */
    List<ImageObject> editImageCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, File image, File mask, ImageEditRequest imageEditRequest);

    /**
     * 创建图像变体
     *
     * @param apiHostByUser         用户自定义 host
     * @param apiKeyByUser          用户自定义密钥
     * @param apiUrlByUser          用户自定义请求地址
     * @param image                 要编辑的图像
     * @param imageVariationRequest 请求参数
     * @return 图片信息
     */
    List<ImageObject> variationImageCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, File image, ImageVariationRequest imageVariationRequest);
}
