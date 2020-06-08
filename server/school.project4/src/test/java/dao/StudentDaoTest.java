package dao;

import cn.leetechweb.pj4.dao.StudentDao;
import cn.leetechweb.pj4.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Project : school.project4
 * Package : dao
 * Time : 2020/6/2 17:18
 *
 * @author : Victor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StudentDaoTest {
    private StudentDao studentDao;
    @Autowired
    private void setStudentDao(StudentDao studentDao){
        this.studentDao = studentDao;
    }
    @Test
    public void testFind(){
        String id = "20184925";
        System.out.println(studentDao.find(new Student().setId(id)));
    }
}
