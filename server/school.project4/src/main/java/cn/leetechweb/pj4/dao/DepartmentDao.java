package cn.leetechweb.pj4.dao;

import cn.leetechweb.pj4.domain.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.dao
 * Time : 2020/6/4 16:58
 *
 * @author : Victor
 */
@Repository
public interface DepartmentDao extends BaseDao<Department> {

    /**
     * 返回department信息
     * @param data 残缺数据
     * @return  department信息
     */
    @Override
    Department find(@Param("dept") Department data);

    /**
     * 返回所有的department信息
     * @return Department list
     */
    List<Department> findAll();

    /**
     * 根据列数据查找完整department实体
     * @param data 列数据
     * @param column    列名称
     * @return  department实体
     */
    Department findByColumn(@Param("data") String data, @Param("column") String column);
}
