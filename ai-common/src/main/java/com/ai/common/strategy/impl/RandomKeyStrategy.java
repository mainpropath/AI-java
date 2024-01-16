package com.ai.common.strategy.impl;

import cn.hutool.core.util.RandomUtil;
import com.ai.common.strategy.KeyStrategy;

import java.util.List;


public class RandomKeyStrategy<T> implements KeyStrategy<List<T>, T> {

    @Override
    public T apply(List<T> keyList) {
        return RandomUtil.randomEle(keyList);
    }

}
