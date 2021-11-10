package com.kaltsit.dataextractor.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author wangcy
 * @date 2021/11/10 16:59
 */
public class JdbcUtils {

    public static DataSource getDataSource(String driverClass, String jdbcUrl, String username, String password) {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(driverClass);
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw new RuntimeException("create jdbcTemplate failed");
        }
    }

}
