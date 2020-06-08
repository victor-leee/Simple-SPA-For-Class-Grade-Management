package cn.leetechweb.pj4.utils;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.utils
 * Time : 2020/6/5 21:43
 *
 * @author : Victor
 */

public class StringUtils {
    public static boolean isBlank(String s){
        return s == null || "".equals(s.trim()) || "null".equals(s);
    }
}
