package com.kaltsit.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author wangcy
 * @date 2021/9/26 11:07
 */
public class JdbcUtils {

    public static JdbcTemplate getJdbcTemplate() {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(properties.getProperty("jdbc.driverClass"));
            dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
            dataSource.setUser(properties.getProperty("jdbc.username"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            return new JdbcTemplate(dataSource);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return null;
    }

}
