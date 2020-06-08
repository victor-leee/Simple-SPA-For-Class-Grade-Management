package service;

import cn.leetechweb.pj4.service.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Project : school.project4
 * Package : service
 * Time : 2020/6/5 10:58
 *
 * @author : Victor
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CourseServiceTest {
    @Autowired private CourseServiceImpl courseService;
    @Test
    public void statTest(){
        String courseId = "CQU_COURSE_CE_1";
        System.out.println(courseService.findCourseStat(courseId));
    }

    @Test
    public void stat2Test(){
        String courseId = "CQU_COURSE_CE_1";
        System.out.println(courseService.findCourseStudentList(courseId));
    }
}
