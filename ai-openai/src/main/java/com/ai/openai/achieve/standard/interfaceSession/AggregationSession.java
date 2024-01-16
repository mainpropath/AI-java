package com.ai.openai.achieve.standard.interfaceSession;

/**
 * @Description: 聚合各大场景的session
 **/
public interface AggregationSession {

    /**
     * 获取音频会话窗口
     */
    AudioSession getAudioSession();

    /**
     * 获取对话会话窗口
     */
    ChatSession getChatSession();

    /**
     * 获取微调会话窗口
     */
    FineTuningSession getFineTuningSession();

    /**
     * 获取嵌入会话窗口
     */
    EmbeddingSession getEmbeddingSession();

    /**
     * 获取文件会话窗口
     */
    FilesSession getFilesSession();

    /**
     * 获取图片会话窗口
     */
    ImageSession getImageSession();

    /**
     * 获取模型会话窗口
     */
    ModelSession getModelSession();

    /**
     * 获取审核会话窗口
     */
    ModerationSession getModerationSession();
}
