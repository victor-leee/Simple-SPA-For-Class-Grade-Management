package cn.leetechweb.pj4.controller;

import cn.leetechweb.pj4.domain.Result;
import cn.leetechweb.pj4.service.CourseClassServiceImpl;
import cn.leetechweb.pj4.service.StudentServiceImpl;
import cn.leetechweb.pj4.service.TeacherServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.leetechweb.pj4.constant.GlobalConstant.*;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.controller
 * Time : 2020/6/4 18:18
 *
 * @author : Victor
 */
@RestController
@RequestMapping(value = "/config", produces = JSON_RESULT)
public class ConfigController {
    private StudentServiceImpl studentService;
    private CourseClassServiceImpl courseClassService;
    private TeacherServiceImpl teacherService;
    @Autowired
    private void setStudentService(StudentServiceImpl studentService){
        this.studentService = studentService;
    }
    @Autowired
    private void setCourseClassService(CourseClassServiceImpl courseClassService){
        this.courseClassService = courseClassService;
    }
    @Autowired
    private void setTeacherService(TeacherServiceImpl teacherService){
        this.teacherService = teacherService;
    }

    /**
     * 全局初始化接口
     * 已经初始化的情况下需要请求体参数refresh=true执行重新初始化
     * @return  全局init状态
     */
    @RequestMapping(value = "/init")
    public String globalInit(@RequestParam(value = "refresh", required = false) String refresh){
        int totalInfRows;
        if(!Boolean.parseBoolean(refresh)){
            if(studentService.findAll().size() != 0){
                return JSON.toJSONString(Result.fail("Already init"));
            }
        }
        //初始化顺序: 1 学生信息 2 课程班级信息 3 教师信息
        totalInfRows = studentService.deleteAll()
                + teacherService.deleteAll()
                + courseClassService.deleteAll();
        boolean isSuccess = studentService.addRandomStudents()
                & courseClassService.addRandomCourseClass()
                & courseClassService.addRandomStudentAndGrades()
                & teacherService.addRandomTeachers();
        Result<Integer> result = isSuccess ? Result.success() : Result.fail();
        if(totalInfRows > 0){
            result.setData(totalInfRows);
        }
        return JSON.toJSONString(result);
    }
}
