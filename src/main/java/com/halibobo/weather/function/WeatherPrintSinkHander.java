package com.halibobo.weather.function;

import com.halibobo.weather.pojo.Weather;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
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
public class WeatherPrintSinkHander extends RichSinkFunction<Weather> {

    private static final Logger logger = LoggerFactory.getLogger(WeatherPrintSinkHander.class);


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

    }

    @Override
    public void invoke(Weather weather, SinkFunction.Context context) throws Exception {
        logger.warn("<fetch data from sink>:" + weather);
    }
}
