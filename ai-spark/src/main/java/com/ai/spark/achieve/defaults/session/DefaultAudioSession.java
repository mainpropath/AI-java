package com.ai.spark.achieve.defaults.session;

import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.api.SparkApiServer;
import com.ai.spark.achieve.standard.interfaceSession.AudioSession;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;


public class DefaultAudioSession implements AudioSession {

    private Configuration configuration;

    private SparkApiServer sparkApiServer;

    public DefaultAudioSession(Configuration configuration) {
        this.configuration = ensureNotNull(configuration, "configuration");
        this.sparkApiServer = ensureNotNull(configuration.getSparkApiServer(), "sparkApiServer");
    }
}
