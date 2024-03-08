package com.ai.baidu.achieve.standard.session;

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

    /**
     * 获取文生图会话窗口
     */
    ImageSession getImageSession();

}
