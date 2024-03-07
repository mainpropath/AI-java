package com.ai.baidu.achieve.standard.function;

/**
 * @Description: 聚合各大场景的session
 **/
public interface AggregationSession {

    /**
     * 获取对话会话窗口
     */
    ChatSession getChatSession();

    /**
     * 获取嵌入会话窗口
     */
    EmbeddingSession getEmbeddingSession();
}
