package com.ai.common.strategy;


/**
 * @Description: API Key 获取策略
 */
public interface KeyStrategy<T, R> {
    R apply(T t);
}
