package com.kaltsit.dataextractor.extractor;

import java.util.List;
import java.util.Map;

/**
 * @author wangcy
 * @date 2021/11/10 16:48
 */
public class TableColumnSqlBuilder extends AbstractSqlBuilder {

    public TableColumnSqlBuilder(String table, String[] columns, String where) {
        super(table, columns, where);
    }

    public String table() {
        return table;
    }

    public String select() {
        return super.select(table, columns, where);
    }

    public String insert(Map<String, Object> map, List<ColumnValueConverter> converters) {
        return super.insert(table, columns, map, converters);
    }
}
