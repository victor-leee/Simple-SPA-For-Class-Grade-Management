package cn.leetechweb.pj4.service;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.service
 * Time : 2020/6/3 23:03
 *
 * @author : Victor
 */

public interface BaseService <T>{
    /**
     * 新增数据接口
     * @param data  数据
     * @return  是否新增成功
     */
    boolean add(T data);

    /**
     * 删除数据，返回删除前的数据
     * @param data 数据
     * @return  data before deleted
     */
    T delete(T data);

    /**
     * 查找给定数据
     * @param data 数据
     * @return  查找的数据
     */
    T find(T data);

    /**
     * 根据data修改数据，并返回修改后的数据
     * @param data 数据
     * @return  修改后的数据
     */
    T update(T data);
}
