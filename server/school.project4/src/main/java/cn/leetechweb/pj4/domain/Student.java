package cn.leetechweb.pj4.domain;

import java.io.Serializable;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.domain
 * Time : 2020/6/1 23:54
 *
 * @author : Victor
 */

public class Student implements Serializable {
    private String id;
    private String name;
    private char sex;
    private Grade courseClassGrade;

    public Student setSex(char sex) {
        this.sex = sex;
        return this;
    }

    public String getId() {
        return id;
    }

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public char getSex() {
        return sex;
    }

    public Grade getCourseClassGrade() {
        return courseClassGrade;
    }

    public Student setCourseClassGrade(Grade courseClassGrade) {
        this.courseClassGrade = courseClassGrade;
        return this;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", courseClassGrade=" + courseClassGrade +
                '}';
    }
}
