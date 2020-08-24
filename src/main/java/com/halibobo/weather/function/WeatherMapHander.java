package com.halibobo.weather.function;

import com.halibobo.weather.pojo.Weather;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-24 15:24
 * @description describe what this class do
 */
public class WeatherMapHander extends RichMapFunction<String, Weather> {

    private static final Logger logger = LoggerFactory.getLogger(WeatherMapHander.class);
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

    }

    @Override
    public Weather map(String weather) throws Exception {
        return Weather.fromString(weather);
    }
}
