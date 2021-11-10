package com.kaltsit.dataextractor.extractor;

import com.kaltsit.dataextractor.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wangcy
 * @date 2021/11/10 16:50
 */
public abstract class AbstractSqlBuilder implements SqlBuilder {

    String table;
    String[] columns;
    String where;

    protected AbstractSqlBuilder(String table, String[] columns, String where) {
        this.table = table;
        this.columns = columns;
        this.where = where;
    }

    protected String select(String table, String[] columns, String where) {
        String sql = String.format("select %s from %s", StringUtils.join(columns, ","), table);
        if(StringUtils.isNotEmpty(where)) {
            sql += " where " + where;
        }
        return sql;
    }

    protected String insert(String table, String[] columns, Map<String, Object> map, List<ColumnValueConverter> converters) {
        String[] values = new String[columns.length];
        for(int i=0,len=columns.length; i<len; i++) {
            String column = columns[i];
            Object value = map.get(column);
            if(value == null) {
                values[i] = null;
            }else {
                values[i] = value.toString();
                for(ColumnValueConverter converter : converters) {
                    if(converter.accept(column)) {
                        values[i] = converter.process(value.toString());
                        break;
                    }
                }
            }
        }
        return String.format("insert into %s (%s) values (%s);", table, StringUtils.join(columns, ","), StringUtils.join(values, ","));
    }

}
