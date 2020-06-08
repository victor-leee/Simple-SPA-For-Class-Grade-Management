package service;

import cn.leetechweb.pj4.service.TeacherServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Project : school.project4
 * Package : service
 * Time : 2020/6/4 17:41
 *
 * @author : Victor
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TeacherServiceImplTest {
    @Autowired private TeacherServiceImpl teacherService;
    @Test
    public void addTeacherTest(){
        boolean ret = teacherService.addRandomTeachers();
        System.out.println(ret);
    }
}
