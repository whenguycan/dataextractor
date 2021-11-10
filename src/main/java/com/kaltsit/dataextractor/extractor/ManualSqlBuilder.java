package com.kaltsit.dataextractor.extractor;

import java.util.List;
import java.util.Map;

/**
 * @author wangcy
 * @date 2021/11/10 16:55
 */
public class ManualSqlBuilder extends AbstractSqlBuilder {

    String select;

    public ManualSqlBuilder(String select, String table, String[] columns) {
        super(table, columns, "");
        this.select = select;
    }

    public String table() {
        return table;
    }

    public String select() {
        return select;
    }

    public String insert(Map<String, Object> map, List<ColumnValueConverter> converters) {
        return super.insert(table, columns, map, converters);
    }
}
