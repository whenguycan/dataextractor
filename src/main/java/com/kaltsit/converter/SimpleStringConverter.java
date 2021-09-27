package com.kaltsit.converter;

/**
 * @author wangcy
 * @date 2021/9/26 17:07
 */
public class SimpleStringConverter implements Converter {

    String[] templates;

    public SimpleStringConverter(String... templates) {
        this.templates = templates;
    }

    public boolean access(String column) {
        if(templates == null || templates.length == 0) {
            return false;
        }
        boolean find = false;
        for(String s : templates) {
            if(s != null && s.equals(column)) {
                find = true;
                break;
            }
        }
        return find;
    }

    public String process(String value) {
        return value != null ? "'" + value + "'" : null;
    }
}
