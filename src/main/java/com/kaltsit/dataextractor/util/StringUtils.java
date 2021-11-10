package com.kaltsit.dataextractor.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wangcy
 * @date 2021/9/25 8:52
 */
public class StringUtils {

    public static final String CRLF = System.lineSeparator();

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern("_yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now());
    }

    public static String join(String[] strs, String separator) {
        return join(strs, separator, "", "");
    }

    public static String join(String[] strs, String separator, String open, String close) {
        if(strs == null || strs.length == 0) {
            return null;
        }
        if(separator == null) {
            throw new RuntimeException("separator is null");
        }
        StringBuilder sb = new StringBuilder();
        for(String str : strs) {
            if(str == null) {
                sb.append(separator).append((String) null);
            }else {
                sb.append(separator).append(open).append(str).append(close);
            }
        }
        return sb.substring(separator.length());
    }

}
