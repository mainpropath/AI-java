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
            ApiData systemApiData = configuration.getSystemApiData();
            accessToken = baiduApiServer.getAccessToken(systemApiData.getApiKey(), systemApiData.getSecretKey());
        }
        return accessToken;
    }


}
