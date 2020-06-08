package cn.leetechweb.pj4.service;

import cn.leetechweb.pj4.DTO.CoursePhaseDTO;
import cn.leetechweb.pj4.constant.GlobalConstant;
import cn.leetechweb.pj4.dao.CourseDao;
import cn.leetechweb.pj4.domain.Course;
import cn.leetechweb.pj4.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.service
 * Time : 2020/6/5 10:34
 *
 * @author : Victor
 */
@Service
public class CourseServiceImpl implements BaseService<CoursePhaseDTO> {
    private static final int MAX_GRADE_LOWER = 90;
    private static final int STUDENT_GRADE_GAP = 10;
    private CourseDao courseDao;
    @Autowired
    private void setCourseDao(CourseDao courseDao){
        this.courseDao = courseDao;
    }
    @Override
    public boolean add(CoursePhaseDTO data) {
        return false;
    }

    @Override
    public CoursePhaseDTO delete(CoursePhaseDTO data) {
        return null;
    }

    @Override
    public CoursePhaseDTO find(CoursePhaseDTO data) {
        return null;
    }

    @Override
    public CoursePhaseDTO update(CoursePhaseDTO data) {
        return null;
    }

    /**
     * 获取课程信息，包括成绩分布
     * @param courseId 课程id
     * @return  课程DTO
     */
    public CoursePhaseDTO findCourseStat(String courseId){
        CoursePhaseDTO coursePhaseDTO = new CoursePhaseDTO();
        Course course = courseDao.find(Course.valueOf(courseId));
        coursePhaseDTO.setCourse(course);
        List<Integer> distributionList = new ArrayList<>();
        for(int start = 0; start <= MAX_GRADE_LOWER; start += STUDENT_GRADE_GAP){
            int upperBound = start + STUDENT_GRADE_GAP;
            distributionList.add(courseDao.findStudentCountBetweenGrades(start, upperBound, courseId));
        }
        coursePhaseDTO.setDistributionList(distributionList);
        return coursePhaseDTO;
    }

    /**
     * @param courseId 课程id
     * @return  参加了该课程的学生
     */
    public List<Student> findCourseStudentList(String courseId){
        return courseDao.findCourseStudentList(courseId);
    }

}
