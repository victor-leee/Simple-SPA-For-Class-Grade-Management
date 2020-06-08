package cn.leetechweb.pj4.domain;

import java.io.Serializable;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.domain
 * Time : 2020/6/2 0:07
 *
 * @author : Victor
 */

public class Grade implements Serializable {
    private Integer generalGrade;
    private Integer midTermGrade;
    private Integer experimentGrade;
    private Integer finalTestGrade;
    private Integer finalGrade;

    public Integer getGeneralGrade() {
        return generalGrade;
    }

    public Grade setGeneralGrade(Integer generalGrade) {
        this.generalGrade = generalGrade;
        return this;
    }

    public Integer getMidTermGrade() {
        return midTermGrade;
    }

    public Grade setMidTermGrade(Integer midTermGrade) {
        this.midTermGrade = midTermGrade;
        return this;
    }

    public Integer getExperimentGrade() {
        return experimentGrade;
    }

    public Grade setExperimentGrade(Integer experimentGrade) {
        this.experimentGrade = experimentGrade;
        return this;
    }

    public Integer getFinalTestGrade() {
        return finalTestGrade;
    }

    public Grade setFinalTestGrade(Integer finalTestGrade) {
        this.finalTestGrade = finalTestGrade;
        return this;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public Grade setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
        return this;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "generalGrade=" + generalGrade +
                ", midTermGrade=" + midTermGrade +
                ", experimentGrade=" + experimentGrade +
                ", finalTestGrade=" + finalTestGrade +
                ", finalGrade=" + finalGrade +
                '}';
    }
}
