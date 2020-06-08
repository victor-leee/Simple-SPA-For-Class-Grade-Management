package cn.leetechweb.pj4.controller;

import cn.leetechweb.pj4.DTO.CoursePhaseDTO;
import cn.leetechweb.pj4.domain.CourseClass;
import cn.leetechweb.pj4.domain.Result;
import cn.leetechweb.pj4.domain.Student;
import cn.leetechweb.pj4.service.CourseClassServiceImpl;
import cn.leetechweb.pj4.service.CourseServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.leetechweb.pj4.constant.GlobalConstant.*;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.controller
 * Time : 2020/6/4 17:46
 *
 * @author : Victor
 */
@RestController
@RequestMapping(value = "/course", produces = JSON_RESULT)
public class CourseController {
    private CourseClassServiceImpl courseClassService;
    private CourseServiceImpl courseService;
    @Autowired
    private void setCourseService(CourseServiceImpl courseService){
        this.courseService = courseService;
    }
    @Autowired
    private void setCourseClassService(CourseClassServiceImpl courseClassService){
        this.courseClassService = courseClassService;
    }

    @RequestMapping(value = "/{courseId}")
    public String getCourseDetail(@PathVariable("courseId") String courseId){
        CourseClass detailedCourseClass = courseClassService.find(CourseClass.valueOf(courseId));
        if(detailedCourseClass != null){
            return JSON.toJSONString(Result.success(detailedCourseClass));
        }
        return JSON.toJSONString(Result.fail("No mapped courseClass data"));
    }

    @RequestMapping(value = "/find-all")
    public String getAllClassCourse(){
        List<CourseClass> minCourseClassInfo = courseClassService.findMinClassCourseInfo();
        return JSON.toJSONString(Result.success(minCourseClassInfo));
    }

    @RequestMapping(value = "/phase-stat/{courseId}")
    public String getCoursePhaseStat(@PathVariable("courseId") String courseId){
        CoursePhaseDTO coursePhaseDTO = courseService.findCourseStat(courseId);
        return JSON.toJSONString(
                coursePhaseDTO.getCourse() == null || coursePhaseDTO.getDistributionList() == null ?
                        Result.fail("No target") : Result.success(coursePhaseDTO)
        );
    }

    @RequestMapping(value = "/grade-stat/{courseId}")
    public String getCourseStudentGradeStat(@PathVariable("courseId") String courseId){
        List<Student> studentList = courseService.findCourseStudentList(courseId);
        if(studentList == null || studentList.size() == 0){
            return JSON.toJSONString(Result.fail("Empty result"));
        }
        return JSON.toJSONString(Result.success(studentList));
    }
}