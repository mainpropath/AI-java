package com.ai.openAi.achieve.standard.interfaceStrategy;


/**
 * @Description: API Key 获取策略
 */
public interface KeyStrategy<T, R> {
    R apply(T t);
}
