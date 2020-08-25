package com.halibobo.weather.function;

import com.halibobo.weather.db.mysql.MysqlService;
import com.halibobo.weather.pojo.Weather;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
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
public class WeatherMysqlSinkHander extends RichSinkFunction<Weather> {

    private static final Logger logger = LoggerFactory.getLogger(WeatherMysqlSinkHander.class);

    private  Connection connection;
    private PreparedStatement statement;


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = MysqlService.getDataSource().getConnection();
        String sql = "INSERT INTO weather_info (uid,centername,cityX,cityY,cityname,fontColor,"
            + "humidity,pyName,state1,state2,stateDetailed,tem1,tem2,temNow,time,url"
            + ",windDir,windPower,windState) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
            + "ON DUPLICATE KEY UPDATE "
            + "fontColor = VALUES(fontColor),"
            + "humidity = VALUES(humidity),"
            + "state1 = VALUES(state1),"
            + "state2 = VALUES(state2),"
            + "stateDetailed = VALUES(stateDetailed),"
            + "tem1 = VALUES(tem1),"
            + "tem2 = VALUES(tem2),"
            + "temNow = VALUES(temNow),"
            + "time = VALUES(time),"
            + "windDir = VALUES(windDir),"
            + "windPower = VALUES(windPower),"
            + "windState = VALUES(windState);";
        statement = connection.prepareStatement(sql);

    }

    @Override
    public void invoke(Weather weather, Context context) throws Exception {

        try {
            statement.setString(1, weather.getPyName()+ "#" +weather.getRtm());
            statement.setString(2, weather.getCentername());
            statement.setString(3, weather.getCityX());
            statement.setString(4, weather.getCityY());
            statement.setString(5, weather.getCityname());
            statement.setString(6, weather.getFontColor());
            statement.setString(7, weather.getHumidity());
            statement.setString(8, weather.getPyName());
            statement.setString(9, weather.getState1());
            statement.setString(10, weather.getState2());
            statement.setString(11, weather.getStateDetailed());
            statement.setString(12, weather.getTem1());
            statement.setString(13, weather.getTem2());
            statement.setString(14, weather.getTemNow());
            statement.setString(15, weather.getTime());
            statement.setString(16, weather.getUrl());
            statement.setString(17, weather.getWindDir());
            statement.setString(18, weather.getWindPower());
            statement.setString(19, weather.getWindState());
            statement.addBatch();
            statement.executeBatch();
        }catch (Exception e){
            logger.error("<save  data from sink>:" + weather,e);
        }
    }
}
