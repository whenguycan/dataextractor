package com.kaltsit.dataextractor.extractor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangcy
 * @date 2021/9/26 17:07
 */
public class DefaultColumnValueConverter implements ColumnValueConverter {

    Set<String> columns = new HashSet<>();

    public DefaultColumnValueConverter(String... columns) {
        if(columns == null || columns.length == 0) {
            throw new RuntimeException("columns is empty");
        }
        this.columns.addAll(Arrays.asList(columns));
    }

    public boolean accept(String column) {
        return column != null && columns.contains(column);
    }

    public String process(String value) {
        return value == null ? null : String.format("'%s'", value);
    }
}
