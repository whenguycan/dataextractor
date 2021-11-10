package com.kaltsit.dataextractor.extractor;

import java.util.List;
import java.util.Map;

/**
 * @author wangcy
 * @date 2021/11/10 16:44
 */
public interface SqlBuilder {

    String table();

    String select();

    String insert(Map<String, Object> map, List<ColumnValueConverter> converters);

}
