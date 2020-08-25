package com.halibobo.weather.config;

public class KafkaConf {

    public final static String TOPIC = ConfigSolver.getKafkaConfig().getString("topic");
    public final static String APP = ConfigSolver.getKafkaConfig().getString("consumer");
    public final static String ZK_ADDRESS = ConfigSolver.getKafkaConfig().getString("zookeeper");
    public final static String BROKER_ADDRESS = ConfigSolver.getKafkaConfig().getString("brokerlist");
}
