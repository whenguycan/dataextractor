package com.kaltsit.dataextractor.extractor;

/**
 * @author wangcy
 * @date 2021/9/26 17:05
 */
public interface ColumnValueConverter {

    boolean accept(String column);

    String process(String value);

}
