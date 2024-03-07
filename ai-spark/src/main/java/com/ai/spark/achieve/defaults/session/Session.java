package com.ai.spark.achieve.defaults.session;

import com.ai.spark.achieve.Configuration;
import com.ai.spark.achieve.standard.api.SparkApiServer;
import lombok.Data;

@Data
public class Session {

    private Configuration configuration;

    private SparkApiServer sparkApiServer;

}
