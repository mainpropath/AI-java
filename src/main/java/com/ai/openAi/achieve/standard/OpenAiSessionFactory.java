package com.ai.openAi.achieve.standard;

import com.ai.openAi.achieve.defaults.session.DefaultAggregationSession;

/**
 * @description 会话工厂
 */
public interface OpenAiSessionFactory {

    /**
     * 创建聚合过后的session
     *
     * @return 默认的聚合session
     */
    DefaultAggregationSession openAggregationSession();

}
