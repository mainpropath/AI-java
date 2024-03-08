package com.ai.core.strategy.impl;

import com.ai.core.strategy.KeyStrategy;

import java.util.List;


public class FirstKeyStrategy<T> implements KeyStrategy<List<T>, T> {

    @Override
    public T apply(List<T> keyList) {
        return keyList.get(0);
    }
}
