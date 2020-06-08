package cn.leetechweb.pj4.service;

import cn.leetechweb.pj4.dao.CourseClassDao;
import cn.leetechweb.pj4.dao.DepartmentDao;
import cn.leetechweb.pj4.dao.TeacherDao;
import cn.leetechweb.pj4.domain.CourseClass;
import cn.leetechweb.pj4.domain.Teacher;
import cn.leetechweb.pj4.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.leetechweb.pj4.constant.GlobalConstant.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.service
 * Time : 2020/6/3 23:09
 *
 * @author : Victor
 */
@Service
public class TeacherServiceImpl implements BaseService<Teacher> {
    private TeacherDao teacherDao;
    private CourseClassDao courseClassDao;
    private RandomUtils randomUtils;
    private DepartmentDao departmentDao;
    @Autowired
    private void setDepartmentDao(DepartmentDao departmentDao){
        this.departmentDao = departmentDao;
    }
    @Autowired
    private void setRandomUtils(RandomUtils randomUtils){
        this.randomUtils = randomUtils;
    }
    @Autowired
    private void setCourseClassDao(CourseClassDao courseClassDao){
        this.courseClassDao = courseClassDao;
    }
    @Autowired
    private void setTeacherDao(TeacherDao teacherDao){
        this.teacherDao = teacherDao;
    }

    @Override
    public boolean add(Teacher data) {
        try {
            teacherDao.add(data);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Teacher delete(Teacher data) {
        Teacher beforeDeleted = teacherDao.find(data);
        if(beforeDeleted != null){
            teacherDao.delete(beforeDeleted);
        }
        return beforeDeleted;
    }

    @Override
    public Teacher find(Teacher data) {
        return teacherDao.find(data);
    }

    @Override
    public Teacher update(Teacher data) {
        teacherDao.update(data);
        return teacherDao.find(data);
    }

    /**
     * 往Teacher表中新增随机教师
     * teacher数量根据教学班级数量确定
     * 调用此方法前确认教学班级已经生成
     */
    public boolean addRandomTeachers(){
        try {
            List<CourseClass> courseClassList = courseClassDao.findAll();
            List<Teacher> teacherList = new ArrayList<>();
            //对于每一个教学班级生成一个老师
            for (CourseClass courseClass : courseClassList) {
                String deptName = courseClass.getDeptName();
                String deptId = departmentDao.findByColumn(deptName, DEPT_COLUMN_DEPT_NAME).getId();
                Teacher randomTeacher = randomUtils.getTeacher().setDepartmentId(deptId);
                teacherList.add(randomTeacher);
                //设置课程班级老师
                courseClass.setTeacher(randomTeacher);
            }
            //新增teacher信息
            teacherDao.addAll(teacherList);
            //关联teacher信息
            courseClassDao.addTeacherList(courseClassList);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public int deleteAll(){
        return teacherDao.deleteAll();
    }
}
