package cn.leetechweb.pj4.dao;

import cn.leetechweb.pj4.DTO.RankDTO;
import cn.leetechweb.pj4.domain.CourseClass;
import cn.leetechweb.pj4.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.dao
 * Time : 2020/6/2 0:09
 *
 * @author : Victor
 */
@Repository
public interface StudentDao extends BaseDao<Student> {
    /**
     * 新增学生数据
     * @param data 数据源
     */
    @Override
    void add(@Param("data") Student data);

    /**
     * 删除学生
     * @param data 数据源
     * @return  影响行数
     */
    @Override
    int delete(@Param("data") Student data);

    /**
     * 查找学生完整数据t
     * 这个接口不会返回学生的成绩数据，需要查询成绩数据请使用findStudentPlus接口
     * @param data 残缺数据
     * @return full data
     */
    @Override
    Student find(@Param("data") Student data);

    /**
     * 更新学生数据
     * 按照stu_id(primary key)作更新
     * @param data 数据
     */
    @Override
    void update(@Param("data") Student data);

    /**
     * 查询学生完整信息 + 在某个课程班级的成绩
     * @param student   学生实体
     * @param courseClass   课程班级
     * @return  返回学生在该课程班级取得的成绩 + 学生自身的信息
     */
    Student findStudentPlus(@Param("studentData") Student student, @Param("courseClass") CourseClass courseClass);

    /**
     * 将所有学生信息加入db
     * @param studentList 学生列表
     */
    void addAll(@Param("stuList") List<Student> studentList);

    /**
     * 此方法返回所有的学生信息
     * @return 所有的学生信息
     */
    List<Student> findAll();

    /**
     * 返回给定range的学生信息
     * @param start 起始index
     * @param offset    数量
     * @return  分页学生信息
     */
    List<Student> findAllWithRange(@Param("start") int start, @Param("offset") int offset);

    /**
     * 返回学生总数
     * @return 学生数量
     */
    int findStudentCount();

    /**
     * 删除学生表所有记录
     * @return 影响行数
     */
    int deleteAll();

    /**
     * 查询所有学生排名信息
     * @return 排名信息
     */
    List<RankDTO> findStudentRankingList();

    /**
     * 查询学生排名信息
     * @param courseId 课程信息，可为null,表示查询总排名
     * @param studentId 学生id
     * @return 学生排名信息
     */
    RankDTO findStudentRanking(@Param("studentId") String studentId, @Param("courseId") String courseId);
}
