package com.kaltsit.converter;

/**
 * @author wangcy
 * @date 2021/9/26 17:07
 */
public class SimpleStringConverter implements Converter {

    String[] template;

    public SimpleStringConverter(String... template) {
        this.template = template;
    }

    public boolean access(String column) {
        if(template == null || template.length == 0) {
            return false;
        }
        boolean find = false;
        for(String s : template) {
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
