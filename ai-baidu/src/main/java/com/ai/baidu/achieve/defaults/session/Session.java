package com.ai.baidu.achieve.defaults.session;

import cn.hutool.core.util.StrUtil;
import com.ai.baidu.achieve.ApiData;
import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.api.BaiduApiServer;
import lombok.Data;


@Data
public class Session {

    /**
     * 配置信息
     */
    private Configuration configuration;
    /**
     * OpenAI 接口
     */
    private BaiduApiServer baiduApiServer;

    public String checkAccessToken(String accessToken) {
        if (StrUtil.isEmpty(accessToken)) {
            // 先随机获取一个apidata，看是否设置了accessToken，如果没有设置，获取token设置进去，然后返回。
            ApiData systemApiData = configuration.getSystemApiData();
            if (StrUtil.isNotEmpty(systemApiData.getAccessToken())) {
                return systemApiData.getAccessToken();
            }
            accessToken = baiduApiServer.getAccessToken(systemApiData.getApiKey(), systemApiData.getSecretKey());
            systemApiData.setAccessToken(accessToken);
        }
        return accessToken;
    }


}
