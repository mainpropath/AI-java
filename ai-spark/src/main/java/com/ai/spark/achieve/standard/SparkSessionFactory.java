package com.ai.spark.achieve.standard;


import com.ai.spark.achieve.standard.interfaceSession.AggregationSession;

public interface SparkSessionFactory {

    /**
     * 创建聚合过后的session
     *
     * @return 默认的聚合session
     */
    AggregationSession openAggregationSession();

}
