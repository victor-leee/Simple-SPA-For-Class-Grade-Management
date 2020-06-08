package cn.leetechweb.pj4.DTO;

import cn.leetechweb.pj4.domain.Student;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.DTO
 * Time : 2020/6/5 11:38
 *
 * @author : Victor
 * 排名dto
 * student不查询grade类
 */

public class RankDTO {
    private String studentId;
    private Student student;
    private Integer totalGrade;
    private Integer rank;

    public String getStudentId() {
        return studentId;
    }

    public RankDTO setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public RankDTO setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Integer getTotalGrade() {
        return totalGrade;
    }

    public RankDTO setTotalGrade(Integer totalGrade) {
        this.totalGrade = totalGrade;
        return this;
    }

    public Integer getRank() {
        return rank;
    }

    public RankDTO setRank(Integer rank) {
        this.rank = rank;
        return this;
    }

    @Override
    public String toString() {
        return "RankDTO{" +
                "student=" + student +
                ", totalGrade=" + totalGrade +
                ", rank=" + rank +
                '}';
    }
}
