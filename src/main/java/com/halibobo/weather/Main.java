package com.halibobo.weather;


import com.halibobo.weather.config.KafkaConf;
import com.halibobo.weather.function.WeatherFilterHander;
import com.halibobo.weather.function.WeatherMapHander;
import com.halibobo.weather.function.WeatherMysqlSinkHander;
import com.halibobo.weather.function.WeatherReduceHander;
import com.halibobo.weather.pojo.Weather;
import java.util.Properties;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

public class Main {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = EnvUtil.getEnv();
        // kafka 连接参数
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConf.BROKER_ADDRESS);

        // 需指定group.id，值是管理端的app
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConf.APP);
        //props.put(ConsumerConfig.CLIENT_ID_CONFIG, KafkaConf.APP);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(KafkaConf.TOPIC, new SimpleStringSchema(), props);
        kafkaConsumer.setStartFromEarliest();
        //kafkaConsumer.setCommitOffsetsOnCheckpoints(true);

        env.addSource(kafkaConsumer).name("weather-source")
                .map(new WeatherMapHander()).name("weather-map")
                .filter(new WeatherFilterHander()).name("weather-filter")
                .keyBy(Weather::getPyName).reduce(new WeatherReduceHander())
                .addSink(new WeatherMysqlSinkHander()).name("weather-sink-mysql");

        //dataStream.print();

        env.execute("halibobo-weather");
    }
}
