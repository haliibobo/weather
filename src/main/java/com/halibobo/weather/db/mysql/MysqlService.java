package com.halibobo.weather.db.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import com.halibobo.weather.config.ConfigSolver;
import com.typesafe.config.Config;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author lizibo
 *create user 'halibobo'@'%' identified by 'DDC7wq#v&Y4B'
 */
public class MysqlService {
    private static final Logger logger = LoggerFactory.getLogger(MysqlService.class);
    private static volatile  DataSource dataSource;


    private MysqlService(){

    }


    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (MysqlService.class){
                if (dataSource == null){
                    dataSource = fetchDataSource(ConfigSolver.getConfigMysql());
                }

            }
        }
    return dataSource;
    }

    private static DataSource fetchDataSource(Config config) {
        DruidDataSource druid = new DruidDataSource();
        try {
            // 1.名称
            druid.setName(config.getString("code"));
            // 2.驱动
            String driverClassName = config.getString("driver");
            druid.setDriverClassName(driverClassName);
            // 3.url
            druid.setUrl(config.getString("url"));
            // 4.用户名
            druid.setUsername(config.getString("user"));
            // 5.密码
            druid.setPassword(config.getString("pw"));
            // 6.验证测试sql
            String preferredTestQuery = config.getString("preferredTestQuery");

            druid.setValidationQuery(preferredTestQuery);

            // 12.建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
            druid.setTestWhileIdle(true);


            return druid;
        } catch (Exception e) {
            logger.error("DataSourceService.createDataSource:" + e, e);
        }
    return null;
    }

}