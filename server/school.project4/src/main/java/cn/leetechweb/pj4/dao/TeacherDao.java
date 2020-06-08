package cn.leetechweb.pj4.dao;

import cn.leetechweb.pj4.domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.dao
 * Time : 2020/6/2 16:45
 *
 * @author : Victor
 */
@Repository
public interface TeacherDao extends BaseDao<Teacher>{
    /**
     * 添加教师信息
     * @param data 数据源
     */
    @Override
    void add(Teacher data);

    /**
     * 删除教师信息
     * @param data 数据源
     * @return  影响行数
     */
    @Override
    int delete(Teacher data);

    /**
     * 查找教师
     * @param data 残缺数据
     * @return  full teacher data
     */
    @Override
    Teacher find(Teacher data);

    /**
     * 更新教师数据
     * @param data 数据
     */
    @Override
    void update(Teacher data);

    /**
     * 添加所有教师信息到teacher表
     * @param teacherList 教师列表
     */
    void addAll(@Param("list") List<Teacher> teacherList);

    /**
     * 删除所有教师记录
     * @return 影响行数
     */
    int deleteAll();
}
