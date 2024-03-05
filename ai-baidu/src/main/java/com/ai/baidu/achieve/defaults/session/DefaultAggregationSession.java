package com.ai.baidu.achieve.defaults.session;

import com.ai.baidu.achieve.Configuration;
import com.ai.baidu.achieve.standard.interfaceSession.AggregationSession;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: 聚合各个类型的session
 **/
public class DefaultAggregationSession implements AggregationSession {

    private Configuration configuration;

    public DefaultAggregationSession(Configuration configuration) {
        this.configuration = ensureNotNull(configuration, "configuration");
    }

}
