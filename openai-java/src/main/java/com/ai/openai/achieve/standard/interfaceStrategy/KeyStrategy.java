package com.ai.openai.achieve.standard.interfaceStrategy;


/**
 * @Description: API Key 获取策略
 */
public interface KeyStrategy<T, R> {
    R apply(T t);
}
