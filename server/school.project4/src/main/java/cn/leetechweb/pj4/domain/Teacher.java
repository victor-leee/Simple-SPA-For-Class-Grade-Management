package cn.leetechweb.pj4.domain;

import java.io.Serializable;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.domain
 * Time : 2020/6/1 23:55
 *
 * @author : Victor
 */

public class Teacher implements Serializable {
    private String id;
    private String name;
    private char sex;
    private String departmentId;

    public String getDepartmentId() {
        return departmentId;
    }

    public Teacher setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getId() {
        return id;
    }

    public Teacher setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Teacher setName(String name) {
        this.name = name;
        return this;
    }

    public char getSex() {
        return sex;
    }

    public Teacher setSex(char sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}
