package cn.leetechweb.pj4.dao;

import cn.leetechweb.pj4.domain.CourseClass;
import cn.leetechweb.pj4.domain.Student;
import cn.leetechweb.pj4.domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.dao
 * Time : 2020/6/2 0:10
 *
 * @author : Victor
 */
@Repository
public interface CourseClassDao extends BaseDao<CourseClass>{
    /**
     * 添加教学班级
     * @param data 数据源
     */
    @Override
    void add(@Param("data") CourseClass data);

    /**
     * 删除教学班级
     * @param data 数据源
     * @return  影响行数
     */
    @Override
    int delete(@Param("data") CourseClass data);

    /**
     * 查询教学班级
     * @param data 残缺数据
     * @return 教学班级详情
     */
    @Override
    CourseClass find(@Param("data") CourseClass data);

    /**
     * 更新教学班级数据
     * @param data 数据
     */
    @Override
    void update(@Param("data") CourseClass data);

    /**
     * 根据教学班级id返回该教学班级的所有学生
     * @param courseClass 教学班级
     * @return  学生列表
     */
    List<Student> selectCourseClassStudentList(@Param("courseClassId") CourseClass courseClass);

    /**
     * 根据课程班级查找该课程班级的教师信息
     * @param courseClass 课程班级
     * @return  返回该课程班级的教师
     */
    Teacher selectCourseClassTeacher(@Param("courseClassId") CourseClass courseClass);

    /**
     * 返回所有课程班级信息
     * @return 所有的课程班级信息
     */
    List<CourseClass> findAll();

    /**
     * 将该学生的成绩信息关联到courseClass上
     * @param student 学生实体
     * @param courseClass   课程班级实体
     */
    void addStudentGrade(@Param("student") Student student, @Param("courseClass") CourseClass courseClass);

    /**
     * 将学生列表成绩添加到课程实体上
     * @param studentList 学生列表
     * @param courseClass   课程实体
     */
    void addStudentGradeList(@Param("studentList") List<Student> studentList,
                             @Param("courseClass") CourseClass courseClass);

    /**
     * 例子 -> CQU_COURSE_CS_2 -> 返回1代表CQU_COURSE_CS_2已经存在一个教学班级
     * @param pattern 课程班级pattern
     * @return  已存在的同一课程的班级数量
     */
    int findSameCourseClassCount(@Param("courseClassPattern") String pattern);

    /**
     * 新增课程班级接口
     * @param courseClassList 课程班级列表
     */
    void addAll(@Param("courseClassList") List<CourseClass> courseClassList);

    /**
     * 将教师与课程班级关联
     * @param courseClass 课程班级
     */
    void addTeacher(@Param("courseClass") CourseClass courseClass);

    /**
     * 将课程班级的教师关联上
     * 如果教师已经关联，请不要使用这个方法
     * @param courseClassList 课程班级列表
     */
    void addTeacherList(@Param("courseClassList") List<CourseClass> courseClassList);

    /**
     * 删除所有课程班级
     * @return 影响行数
     */
    int deleteAll();

    /**
     * 返回信息包括名字和id
     * @return 课程班级信息最小化
     */
    List<CourseClass> findMinClassCourseInfo();
}
