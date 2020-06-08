package cn.leetechweb.pj4.dao;

import cn.leetechweb.pj4.domain.Course;
import cn.leetechweb.pj4.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.dao
 * Time : 2020/6/4 1:08
 *
 * @author : Victor
 */

public interface CourseDao extends BaseDao<Course> {
    /**
     * 返回课程id和名字
     * @param data 残缺数据
     * @return  课程信息
     */
    @Override
    Course find(@Param("data") Course data);

    /**
     * 返回所有课程信息
     * @return 所有课程信息
     */
    List<Course> findAll();

    /**
     * 返回成绩在(min,max]之间的人数统计
     * @param min lower bound
     * @param max   upper bound
     * @param courseId 课程id
     * @return  人数统计
     */
    int findStudentCountBetweenGrades(@Param("min") int min, @Param("max") int max, @Param("courseId") String courseId);

    /**
     * 返回参加了该课程的学生列表
     * 默认按照总成绩降序排序
     * @param courseId 课程Id
     * @return  学生列表
     */
    List<Student> findCourseStudentList(@Param("courseId") String courseId);
}
