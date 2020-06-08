package cn.leetechweb.pj4.dao;


/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.dao
 * Time : 2020/6/2 15:59
 *
 * @author : Victor
 */
public interface BaseDao<T> {
    /**
     * 新增数据接口
     * @param data 数据源
     */
    void add(T data);

    /**
     * 删除数据
     * @param data 数据源
     * @return  返回影响行数
     */
    int delete(T data);

    /**
     * 更新数据
     * @param data 数据
     */
    void update(T data);

    /**
     * 查找数据
     * @param data 残缺数据
     * @return  完整数据
     */
    T find(T data);
}
