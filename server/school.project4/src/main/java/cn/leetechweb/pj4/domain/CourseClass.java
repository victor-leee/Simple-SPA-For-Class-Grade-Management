package cn.leetechweb.pj4.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.domain
 * Time : 2020/6/1 23:58
 *
 * @author : Victor
 */

public class CourseClass implements Serializable {
    /**
     * 课程教学班级id
     */
    private String courseClassId;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程班级所属学院信息
     */
    private String deptName;
    private LocalDate startTime;
    private Teacher teacher;
    private List<Student> studentList;

    public String getCourseClassId() {
        return courseClassId;
    }

    public CourseClass setCourseClassId(String courseClassId) {
        this.courseClassId = courseClassId;
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public CourseClass setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public CourseClass setStartTime(LocalDate startTime) {
        this.startTime = startTime;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public CourseClass setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public CourseClass setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        return this;
    }

    public String getCourseName() {
        return courseName;
    }

    public CourseClass setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public String getDeptName() {
        return deptName;
    }

    public CourseClass setDeptName(String deptName) {
        this.deptName = deptName;
        return this;
    }

    @Override
    public String toString() {
        return "CourseClass{" +
                "courseClassId='" + courseClassId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", startTime=" + startTime +
                ", teacher=" + teacher +
                ", studentList=" + studentList +
                '}';
    }

    public static CourseClass valueOf(String courseClassId, String courseId, LocalDate startTime){
        return new CourseClass()
                .setCourseClassId(courseClassId)
                .setCourseId(courseId)
                .setStartTime(startTime);
    }

    public static CourseClass valueOf(String courseClassId){
        return new CourseClass()
                .setCourseClassId(courseClassId);
    }
}
