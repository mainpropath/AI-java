package com.ai.baidu.endPoint.images.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文生图，官方文档路径：https://cloud.baidu.com/doc/WENXINWORKSHOP/s/Klkqubb9w#header%E5%8F%82%E6%95%B0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageRequest implements Serializable {

    /**
     * 必传
     * 提示词，即用户希望图片包含的元素。长度限制为1024字符，建议中文或者英文单词总数量不超过150个
     */
    private String prompt;

    /**
     * 反向提示词，即用户希望图片不包含的元素。长度限制为1024字符，建议中文或者英文单词总数量不超过150个
     */
    @JsonProperty("negative_prompt")
    private String negativePrompt;

    /**
     * 生成图片长宽，默认值 1024x1024，取值范围如下：
     * ["768x768", "768x1024", "1024x768", "576x1024", "1024x576", "1024x1024"]
     */
    private String size;

    /**
     * 生成图片数量，说明：
     * · 默认值为1
     * · 取值范围为1-4
     * · 单次生成的图片较多及请求较频繁可能导致请求超时
     */
    private Integer n;

    /**
     * 迭代轮次，说明：
     * · 默认值为20
     * · 取值范围为10-50
     */
    private Integer steps;

    /**
     * 采样方式，默认值：Euler a，可选值如下(释义参考)：
     * · Euler
     * · Euler a
     * · DPM++ 2M
     * · DPM++ 2M Karras
     * · LMS Karras
     * · DPM++ SDE
     * · DPM++ SDE Karras
     * · DPM2 a Karras
     * · Heun
     * · DPM++ 2M SDE
     * · DPM++ 2M SDE Karras
     * · DPM2
     * · DPM2 Karras
     * · DPM2 a
     * · LMS
     */
    @JsonProperty("sampler_index")
    private String samplerIndex;

    /**
     * 随机种子，说明：
     * · 不设置时，自动生成随机数
     * · 取值范围 [0, 4294967295]
     */
    private Integer seed;

    /**
     * 提示词相关性，说明：默认值为5，取值范围0-30
     */
    @JsonProperty("cfg_scale")
    private Double cfgScale;

    /**
     * 生成风格。说明：
     * （1）可选值：
     * · Base：基础风格
     * · 3D Model：3D模型
     * · Analog Film：模拟胶片
     * · Anime：动漫
     * · Cinematic：电影
     * · Comic Book：漫画
     * · Craft Clay：工艺黏土
     * · Digital Art：数字艺术
     * · Enhance：增强
     * · Fantasy Art：幻想艺术
     * · lsometric：等距风格
     * · Line Art：线条艺术
     * · Lowpoly：低多边形
     * · Neonpunk：霓虹朋克
     * · Origami：折纸
     * · Photographic：摄影
     * · Pixel Art：像素艺术
     * · Texture：纹理
     * （2）默认值为Base
     */
    private String style;

    /**
     * 表示最终用户的唯一标识符
     */
    @JsonProperty("user_id")
    private String userId;

    public static ImageRequest baseBuild(String prompt) {
        return ImageRequest.builder().prompt(prompt).build();
    }

}
