package com.kaltsit.util;

/**
 * @author wangcy
 * @date 2021/9/25 8:52
 */
public class StringUtils {

    public static final String CRLF = "\r\n";

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isAnyEmpty(String... ss) {
        if(ss == null || ss.length == 0) {
            return true;
        }
        for(String s : ss) {
            if(isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    public static String join(String[] strs, String separator) {
        return join(strs, separator, "", "", true);
    }

    public static String join(String[] strs, String separator, String open, String close, boolean escapeNull) {
        if(strs == null || strs.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(String str : strs) {
            if(str == null && escapeNull) {
                sb.append(separator).append((String) null);
            }else {
                sb.append(separator).append(open).append(str).append(close);
            }
        }
        return sb.substring(1);
    }

}
