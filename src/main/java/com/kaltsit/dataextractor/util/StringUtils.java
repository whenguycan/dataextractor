package com.kaltsit.dataextractor.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wangcy
 * @date 2021/9/25 8:52
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String CRLF = System.lineSeparator();

    public static String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern("_yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now());
    }

}
