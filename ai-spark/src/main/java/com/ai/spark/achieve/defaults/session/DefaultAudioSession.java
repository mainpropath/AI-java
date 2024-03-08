package com.ai.spark.achieve.defaults.session;

import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.session.AudioSession;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultAudioSession extends Session implements AudioSession {

    public DefaultAudioSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setSparkApiServer(ensureNotNull(configuration.getSparkApiServer(), "sparkApiServer"));
    }
}
