package com.ai.openAi.achieve.defaults.strategy;

import cn.hutool.core.util.RandomUtil;
import com.ai.openAi.achieve.standard.interfaceStrategy.KeyStrategy;

import java.util.List;


public class RandomKeyStrategy implements KeyStrategy<List<String>, String> {

    @Override
    public String apply(List<String> keyList) {
        return RandomUtil.randomEle(keyList);
    }
}
