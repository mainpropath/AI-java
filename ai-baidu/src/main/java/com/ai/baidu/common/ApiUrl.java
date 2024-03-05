package com.ai.baidu.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 各接口API路径
 */
@Getter
@AllArgsConstructor
public enum ApiUrl {

    ERNIE_Bot_4_0("/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro"),
    ;

    private String url;
}
