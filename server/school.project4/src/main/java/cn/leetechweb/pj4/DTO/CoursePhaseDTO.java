package cn.leetechweb.pj4.DTO;

import cn.leetechweb.pj4.domain.Course;

import java.util.List;
import java.util.Map;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.DTO
 * Time : 2020/6/5 10:35
 *
 * @author : Victor
 */

public class CoursePhaseDTO {
    /**
     * 课程信息
     */
    private Course course;
    /**
     * 成绩阶段统计
     *
     */
    private List<Integer> distributionList;

    public List<Integer> getDistributionList() {
        return distributionList;
    }

    public CoursePhaseDTO setDistributionList(List<Integer> distributionList) {
        this.distributionList = distributionList;
        return this;
    }

    public Course getCourse() {
        return course;
    }

    public CoursePhaseDTO setCourse(Course course) {
        this.course = course;
        return this;
    }

    @Override
    public String toString() {
        return "CoursePhaseDTO{" +
                "course=" + course +
                ", distributionList=" + distributionList +
                '}';
    }
}
