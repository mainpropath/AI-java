package com.ai.openai.achieve.standard;

import com.ai.openai.achieve.standard.interfaceSession.AggregationSession;

/**
 * @description 会话工厂
 */
public interface OpenAiSessionFactory {

    /**
     * 创建聚合过后的session
     *
     * @return 默认的聚合session
     */
    AggregationSession openAggregationSession();

}
