package com.halibobo.weather.function;

import com.halibobo.weather.pojo.Weather;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.functions.RichReduceFunction;
import org.apache.flink.configuration.Configuration;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-24 15:24
 * @description describe what this class do
 */
public class WeatherReduceHander extends RichReduceFunction<Weather> {


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

    }



    @Override
    public Weather reduce(Weather weather1, Weather weather2) throws Exception {
        return weather1.getRtm().compareTo(weather2.getRtm())>=0?weather1:weather2;
    }
}
