package com.kaltsit.application;

import com.kaltsit.converter.Converter;
import com.kaltsit.util.JdbcUtils;
import com.kaltsit.util.SqlBuilder;
import com.kaltsit.util.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangcy
 * @date 2021/9/25 8:45
 */
public class Extractor {

    String table;
    String[] columns;
    String filepath;
    List<Converter> converters = new ArrayList<>();

    public Extractor(String table, String... columns) {
        this.table = table;
        this.columns = columns;
    }

    public Extractor addFilepath(String filepath) {
        this.filepath = filepath;
        return this;
    }

    public Extractor addConverter(Converter converter) {
        converters.add(converter);
        return this;
    }

    void check() {
        if(StringUtils.isEmpty(table)) {
            throw new RuntimeException("-->table is empty");
        }
        if(StringUtils.isAnyEmpty(columns)) {
            throw new RuntimeException("-->columns has empty value");
        }
        if(StringUtils.isEmpty(filepath)) {
            throw new RuntimeException("-->filepath is empty");
        }
    }

    public void execute() {
        check();
        System.out.println("-->");
        System.out.println("check pass");
        try {
            String charset = "UTF-8";
            JdbcTemplate jdbcTemplate = JdbcUtils.getJdbcTemplate();
            String sql = SqlBuilder.select(table, columns, null);
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
            File file = new File(filepath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream os = new FileOutputStream(file);
            os.write(StringUtils.CRLF.getBytes(charset));
            if(list != null) {
                for(Map<String, Object> map : list) {
                    os.write(SqlBuilder.insert(table, columns, map, converters).getBytes(charset));
                    os.write(StringUtils.CRLF.getBytes(charset));
                }
            }
            os.flush();
            os.close();
            System.out.println("-->");
            System.out.println("create file: " + filepath);
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
