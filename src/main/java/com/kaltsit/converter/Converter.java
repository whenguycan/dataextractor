package com.kaltsit.converter;

/**
 * @author wangcy
 * @date 2021/9/26 17:05
 */
public interface Converter {

    boolean access(String column);

    String process(String value);

}
