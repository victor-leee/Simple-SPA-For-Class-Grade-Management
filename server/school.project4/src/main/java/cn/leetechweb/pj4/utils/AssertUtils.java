package cn.leetechweb.pj4.utils;

import java.util.List;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.utils
 * Time : 2020/6/4 0:40
 *
 * @author : Victor
 */

public class AssertUtils {
    /**
     * 如果list.size() <= minSize抛错
     * @param list list
     * @param minSize   最小大小
     */
    public static void assertMinListLength(List<?> list, int minSize){
        if(list.size() <= minSize){
            throw new RuntimeException("List : " + list + " size is below minSize");
        }
    }
}
