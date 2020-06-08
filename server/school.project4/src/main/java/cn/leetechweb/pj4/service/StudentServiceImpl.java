package cn.leetechweb.pj4.service;

import cn.leetechweb.pj4.DTO.RankDTO;
import cn.leetechweb.pj4.constant.GlobalConstant;
import cn.leetechweb.pj4.dao.StudentDao;
import cn.leetechweb.pj4.domain.Student;
import cn.leetechweb.pj4.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.service
 * Time : 2020/6/3 23:03
 *
 * @author : Victor
 */
@Service
public class StudentServiceImpl implements BaseService<Student> {
    private StudentDao studentDao;
    private RandomUtils randomUtils;
    @Autowired
    private void setRandomUtils(RandomUtils randomUtils){
        this.randomUtils = randomUtils;
    }
    @Autowired
    private void setStudentDao(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    public boolean add(Student data) {
        try{
            studentDao.add(data);
        }catch (Exception e){
            //Duplicate key exception
            return false;
        }
        return true;
    }

    @Override
    public Student delete(Student data) {
        Student beforeDeleted = find(data);
        if(beforeDeleted != null){
            studentDao.delete(data);
            return beforeDeleted;
        }
        return null;
    }

    @Override
    public Student find(Student data) {
        return studentDao.find(data);
    }

    @Override
    public Student update(Student data) {
        studentDao.update(data);
        return studentDao.find(data);
    }

    /**
     * 以GlobalConstant.STUDENT_TOTAL_COUNT为初始化人数初始化学生信息表
     * @return 初始化是否成功
     */
    public boolean addRandomStudents(){
        return addRandomStudents(GlobalConstant.STUDENT_TOTAL_COUNT);
    }

    /**
     * 此方法随机生成学生列表
     * 此方法不会生成学生成绩或者其他任何与课程相关的信息
     * @param studentCount 学生数量
     * @return 是否随机生成成功
     */
    public boolean addRandomStudents(int studentCount){
        try {
            List<Student> studentList = Stream.generate(() -> randomUtils.getStudent()).
                    limit(studentCount).
                    collect(Collectors.toList());
            studentDao.addAll(studentList);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Student> findAll(){
        return studentDao.findAll();
    }

    public List<Student> findAll(int start, int count){
        return studentDao.findAllWithRange(start, count);
    }

    public int deleteAll(){
        return studentDao.deleteAll();
    }

    public List<RankDTO> findStudentRankingList(){
        return studentDao.findStudentRankingList();
    }

    public RankDTO findStudentRanking(String studentId, String courseId){
        return studentDao.findStudentRanking(studentId, courseId);
    }
}
