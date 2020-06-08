package service;

import cn.leetechweb.pj4.constant.GlobalConstant;
import cn.leetechweb.pj4.domain.CourseClass;
import cn.leetechweb.pj4.service.CourseClassServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project : school.project4
 * Package : service
 * Time : 2020/6/4 0:13
 *
 * @author : Victor
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CourseClassServiceTest {
    @Autowired private CourseClassServiceImpl courseClassService;

    @Test
    public void findTest(){
        System.out.println(courseClassService.findAll());
    }

    @Test
    public void studentLinkCCTest(){
        boolean ret = courseClassService.addRandomStudentAndGrades();
        System.out.println(ret);
    }

    @Test
    public void regTest(){
        String s = "CQU_COURSE_CS_2";
        String pat = GlobalConstant.FETCH_COURSE_NAME_REGEX;
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(s);
        matcher.find();
        System.out.println(matcher.group(1));
    }

    @Test
    public void addCCTest(){
        boolean ret = courseClassService.addRandomCourseClass();
        System.out.println(ret);
    }

    @Test
    public void minCCTest(){
        List<CourseClass> courseClassList = courseClassService.findMinClassCourseInfo();
        System.out.println(courseClassList);
    }

}
