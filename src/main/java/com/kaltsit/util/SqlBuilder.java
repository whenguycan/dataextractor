package com.kaltsit.util;

import com.kaltsit.converter.Converter;

import java.util.List;
import java.util.Map;

/**
 * @author wangcy
 * @date 2021/9/26 9:59
 */
public class SqlBuilder {

    public static String select(String table, String[] columns, String where) {
        String sql = String.format("select %s from %s", StringUtils.join(columns, ","), table);
        if(!StringUtils.isEmpty(where)) {
            sql += " where " + where;
        }
        return sql;
    }

    public static String insert(String table, String[] columns, Map<String, Object> map, List<Converter> converters) {
        String[] values = new String[columns.length];
        for(int i=0,len=columns.length; i<len; i++) {
            String column = columns[i];
            Object value = map.get(column);
            if(value == null) {
                values[i] = null;
            }else {
                values[i] = value.toString();
                for(Converter converter : converters) {
                    if(converter.access(column)) {
                        values[i] = converter.process(value.toString());
                        break;
                    }
                }
            }
        }
        return String.format("insert into %s (%s) values (%s);", table, StringUtils.join(columns, ","), StringUtils.join(values, ","));
    }

}
