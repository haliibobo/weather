package com.halibobo.weather.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-25 14:12
 * @description describe what this class do
 */
public class ConfigSolver {

    private final static Config config = ConfigFactory.load("weather.conf").resolve();


    public static Config getConfig() {
        return config;
    }

    public static Config getKafkaConfig() {
        return config.getConfig("kafka");
    }

    public static Config getConfigMysql() {
        return config.getConfig("mysql");
    }
}
