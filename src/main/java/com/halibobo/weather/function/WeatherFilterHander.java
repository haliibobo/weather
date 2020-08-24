package com.halibobo.weather.function;

import com.halibobo.weather.pojo.Weather;
import org.apache.flink.api.common.functions.RichFilterFunction;
import org.apache.flink.configuration.Configuration;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-24 15:24
 * @description describe what this class do
 */
public class WeatherFilterHander extends RichFilterFunction<Weather> {


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

    }

    @Override
    public boolean filter(Weather value) throws Exception {
        return value!=Weather.EMTPY;
    }
}
