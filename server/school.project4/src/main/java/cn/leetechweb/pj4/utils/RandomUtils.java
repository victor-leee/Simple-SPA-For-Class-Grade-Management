package cn.leetechweb.pj4.utils;

import cn.leetechweb.pj4.constant.GlobalConstant;
import cn.leetechweb.pj4.domain.Grade;
import cn.leetechweb.pj4.domain.Student;
import cn.leetechweb.pj4.domain.Teacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.utils
 * Time : 2020/6/2 0:15
 *
 * @author : Victor
 *
 * This util class doesn't work in other cases.
 * This code fucks
 */
@Component
public class RandomUtils {
    @Value("${random.firstName}") String firstName;
    @Value("${random.lastName}") String lastName;
    @Value("${grade.general}") int generalGrade;
    @Value("${grade.midTerm}") int midTermGrade;
    @Value("${grade.experiment}") int experimentGrade;
    @Value("${grade.finalTest}") int finalTestGrade;
    private String[] firstNameList;
    private String[] lastNameList;
    private final char[] sex = {'M', 'F'};
    private final Random random;
    private final RoleFactory roleFactory = new RoleFactory();
    private boolean isInit = false;

    {
        random = new Random();
    }

    public String getFirstName(){
        if(!isInit){
            init();
        }
        /*
         * 生成姓氏
         * */
        return firstNameList[random.nextInt(firstNameList.length)];
    }

    public String getLastName(){
        /*
         * 生成名
         * */
        int nextC = random.nextInt(lastNameList.length);
        return lastNameList[nextC];
    }

    public String getCompleteName(){
        return getFirstName() + " " + getLastName();
    }

    public char getSex(){
        /*
         * 随机性别
         * */
        return sex[random.nextInt(2)];
    }

    public int getRandomInt(int bound){
        return random.nextInt(bound);
    }

    public Teacher getTeacher(){
        if(!isInit){
            init();
        }
        return roleFactory.generateTeacher();
    }

    public Student getStudent(){
        if(!isInit){
            init();
        }
        return roleFactory.generateStudent();
    }

    public int uniqueId(Object o){
        return roleFactory.uniqueId(o);
    }

    public Grade getGrade(){
        int general = random.nextInt(generalGrade);
        int mid = random.nextInt(midTermGrade);
        int experiment = random.nextInt(experimentGrade);
        int finalTest = random.nextInt(finalTestGrade);
        int finalGrade = general + mid + experiment + finalTest;
        return new Grade().setGeneralGrade(general)
                .setMidTermGrade(mid)
                .setExperimentGrade(experiment)
                .setFinalTestGrade(finalTest)
                .setFinalGrade(finalGrade);
    }

    class RoleFactory {
        public Teacher generateTeacher(){
            Teacher teacher = new Teacher();
            String fullName = getCompleteName();
            char sex = getSex();
            String teacherId = GlobalConstant.TEACHER_PREFIX + uniqueId(teacher);
            teacher.setName(fullName).setSex(sex).setId(teacherId);
            return teacher;
        }

        private int uniqueId(Object any){
            return Objects.hashCode(any);
        }

        public Student generateStudent(){
            Student student = new Student();
            char sex = getSex();
            String id = GlobalConstant.STUDENT_PREFIX + uniqueId(student);
            String name = getCompleteName();
            return student.setId(id).setName(name).setSex(sex);
        }
    }

    private void init(){
        firstNameList = firstName.split(GlobalConstant.NAME_SEPARATOR);
        lastNameList = lastName.split(GlobalConstant.NAME_SEPARATOR);
        isInit = true;
    }

}
