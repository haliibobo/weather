package com.halibobo.weather.config;

public class KafkaConf {

    /**
     * Timeout expired while fetching topic metadata
     * kafka service.properties 修改 host.name 相关
     * 看kafka客户端与服务端版本是否一致
     */

    public final static String TOPIC = ConfigSolver.getKafkaConfig().getString("topic");
    public final static String APP = ConfigSolver.getKafkaConfig().getString("consumer");
    public final static String ZK_ADDRESS = ConfigSolver.getKafkaConfig().getString("zookeeper");
    public final static String BROKER_ADDRESS = ConfigSolver.getKafkaConfig().getString("brokerlist");
}
