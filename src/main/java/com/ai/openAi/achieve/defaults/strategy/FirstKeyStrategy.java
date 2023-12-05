package com.ai.openAi.achieve.defaults.strategy;

import com.ai.openAi.achieve.standard.interfaceStrategy.KeyStrategy;

import java.util.List;


public class FirstKeyStrategy implements KeyStrategy<List<String>, String> {

    @Override
    public String apply(List<String> keyList) {
        return keyList.get(0);
    }
}
