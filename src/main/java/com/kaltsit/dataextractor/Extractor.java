package com.kaltsit.dataextractor;

import com.kaltsit.dataextractor.extractor.ColumnValueConverter;
import com.kaltsit.dataextractor.extractor.SqlBuilder;
import com.kaltsit.dataextractor.util.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

/**
 * 数据库表抽取工具
 * @author wangcy
 * @date 2021/9/25 8:45
 */
public class Extractor {

    File targetDir;

    DataSource dataSource;
    SqlBuilder sqlBuilder;
    List<ColumnValueConverter> converters = new ArrayList<>();

    Extractor() {

    }

    public static ExtractorBuilder builder() {
        return new ExtractorBuilder();
    }

    public void extract() {
        check();
        try {
            System.out.println("--> extract start");
            String charset = "UTF-8";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String select = sqlBuilder.select();
            List<Map<String, Object>> list = jdbcTemplate.queryForList(select);
            if(list.isEmpty()) {
                System.out.println("--> cannot find any lines by sql");
                return;
            }
            if(!targetDir.exists()) {
                targetDir.mkdirs();
            }
            String filename = sqlBuilder.table() + StringUtils.getCurrentTimestamp() + ".sql.txt";
            File file = new File(targetDir, filename);
            OutputStream os = new FileOutputStream(file);
            os.write(("## " + sqlBuilder.table()).getBytes(charset));
            os.write(StringUtils.CRLF.getBytes(charset));
            for(Map<String, Object> map : list) {
                os.write(sqlBuilder.insert(map, converters).getBytes(charset));
                os.write(StringUtils.CRLF.getBytes(charset));
            }
            os.flush();
            os.close();
            System.out.println("--> create file: " + file.getPath());
            System.out.println("--> extract complete");
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void check() {
        if(targetDir == null) {
            throw new RuntimeException("targetDir is null");
        }
        if(dataSource == null) {
            throw new RuntimeException("dataSource is null");
        }
        if(sqlBuilder == null) {
            throw new RuntimeException("sqlBuilder is null");
        }
    }

}
