package com.ai.core.strategy.impl;

import cn.hutool.core.util.RandomUtil;
import com.ai.core.strategy.KeyStrategy;

import java.util.List;


public class RandomKeyStrategy<T> implements KeyStrategy<List<T>, T> {

    @Override
    public T apply(List<T> keyList) {
        return RandomUtil.randomEle(keyList);
    }

}
