package cn.leetechweb.pj4.service;

import cn.leetechweb.pj4.constant.GlobalConstant;
import cn.leetechweb.pj4.dao.CourseClassDao;
import cn.leetechweb.pj4.dao.CourseDao;
import cn.leetechweb.pj4.dao.StudentDao;
import cn.leetechweb.pj4.domain.Course;
import cn.leetechweb.pj4.domain.CourseClass;
import cn.leetechweb.pj4.domain.Grade;
import cn.leetechweb.pj4.domain.Student;
import cn.leetechweb.pj4.utils.AssertUtils;
import cn.leetechweb.pj4.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.service
 * Time : 2020/6/3 23:12
 *
 * todo base methods
 * @author : Victor
 */
@Service
public class CourseClassServiceImpl implements BaseService<CourseClass> {
    private CourseClassDao courseClassDao;
    private StudentDao studentDao;
    private CourseDao courseDao;
    private RandomUtils randomUtils;
    private static final Pattern PATTERN = Pattern.compile(GlobalConstant.FETCH_COURSE_NAME_REGEX);
    @Autowired
    private void setCourseDao(CourseDao courseDao){
        this.courseDao = courseDao;
    }
    @Autowired
    private void setRandomUtils(RandomUtils randomUtils){
        this.randomUtils = randomUtils;
    }
    @Autowired
    private void setStudentDao(StudentDao studentDao){
        this.studentDao = studentDao;
    }
    @Autowired
    private void setCourseClassDao(CourseClassDao courseClassDao){
        this.courseClassDao = courseClassDao;
    }
    @Override
    public boolean add(CourseClass data) {
        return false;
    }

    @Override
    public CourseClass delete(CourseClass data) {
        return null;
    }

    @Override
    public CourseClass find(CourseClass data) {
        return courseClassDao.find(data);
    }

    @Override
    public CourseClass update(CourseClass data) {
        return null;
    }

    /**
     * 新增Student表中每一位学生的成绩
     * @return 添加状态
     */
    public boolean addRandomStudentAndGrades(){
        //当前学生选择的课程在list上的下标
        AtomicInteger stuIndex = new AtomicInteger();
        try{
            //查询所有课程
            List<CourseClass> courseClassList = courseClassDao.findAll();
            //所有学生信息
            List<Student> studentList = studentDao.findAll();
            //学生总数
            final int stuCount = studentList.size();
            //学生人数必须大于20
            AssertUtils.assertMinListLength(studentList, GlobalConstant.MIN_STUDENT_COUNT);
            courseClassList.forEach(courseClass -> {
                List<Student> participants = new ArrayList<>();
                //对于每一门课程，选取40名学生参与，并且立即生成成绩
                for(int i = 0; i < GlobalConstant.MIN_STUDENT_COUNT; i++){
                    Student student = studentList.get(getNext(stuIndex, stuCount));
                    Grade courseGrade = randomUtils.getGrade();
                    student.setCourseClassGrade(courseGrade);
                    participants.add(student);
                }
                courseClassDao.addStudentGradeList(participants, courseClass);
            });
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 新增课程班级信息(随机)
     * @param courseClassCount 每一门课程的最少班级数量
     * @return 成功状态
     */
    public boolean addRandomCourseClass(int courseClassCount){
        //还没加入数据库的课程数量
        int tempCourseCount;
        LocalDate current = LocalDate.now();
        //找出所有课程信息
        List<Course> courseList = courseDao.findAll();
        //待新增的课程班级列表
        List<CourseClass> courseClassList = new ArrayList<>();
        for(Course course : courseList){
            tempCourseCount = 0;
            for(int i = 0; i < courseClassCount; i++) {
                Matcher matcher = PATTERN.matcher(course.getId());
                if(matcher.find()) {
                    //取出课程名称，例如CS_1
                    String courseName = matcher.group(1);
                    String courseClassId = GlobalConstant.COURSE_CLASS_PREFIX + courseName + "_"
                            + (courseClassDao.findSameCourseClassCount(courseName) + tempCourseCount++);
                    CourseClass randomCourseClass = CourseClass.valueOf(courseClassId, course.getId(), current);
                    courseClassList.add(randomCourseClass);
                }else{
                    throw new RuntimeException("Course name exception");
                }
            }
        }
        courseClassDao.addAll(courseClassList);
        return true;
    }

    /**
     * 按照默认的人数添加课程班级信息
     * @return 添加状态
     */
    public boolean addRandomCourseClass(){
        return addRandomCourseClass(GlobalConstant.MIN_COURSE_CLASS_COUNT);
    }


    /**
     * 返回所有课程班级信息
     * @return 所有的课程班级信息
     */
    public List<CourseClass> findAll(){
        return courseClassDao.findAll();
    }

    /**
     * @param integer AtomicInteger实体
     * @param upperBound    上限
     * @return  返回AtomicInteger的取余结果
     */
    private int getNext(AtomicInteger integer, int upperBound){
        return integer.getAndIncrement() % upperBound;
    }

    public int deleteAll() {
        return courseClassDao.deleteAll();
    }

    /**
     * @return 只返回课程班级的大概信息，包括名称和id
     */
    public List<CourseClass> findMinClassCourseInfo(){
        return courseClassDao.findMinClassCourseInfo();
    }
}
