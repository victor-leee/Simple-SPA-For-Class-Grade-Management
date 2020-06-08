package service;

import cn.leetechweb.pj4.service.StudentServiceImpl;
import cn.leetechweb.pj4.utils.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Project : school.project4
 * Package : service
 * Time : 2020/6/3 23:37
 *
 * @author : Victor
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StudentServiceTest {
    @Autowired private StudentServiceImpl studentService;
    @Autowired private RandomUtils randomUtils;

    @Test
    public void addAllTest(){
        boolean ret = studentService.addRandomStudents(120);
        System.out.println(ret);
    }

    @Test
    public void selectTest1(){
        System.out.println(studentService.findAll());
    }

    @Test
    public void selectTest2(){
        System.out.println(studentService.findAll(0, 2));
    }

    @Test
    public void selectRankTest(){
        System.out.println(studentService.findStudentRankingList());
    }

    @Test
    public void stuRankTest(){
        System.out.println(studentService.findStudentRanking("CQU_STUDENT_2068584483",
        "CQU_COURSE_CE_1"));
    }
}
