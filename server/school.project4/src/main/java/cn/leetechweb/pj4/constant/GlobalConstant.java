package cn.leetechweb.pj4.constant;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.constant
 * Time : 2020/6/2 0:18
 *
 * @author : Victor
 */

public class GlobalConstant {
    /**
     * 用于分割随机名字
     */
    public static final String NAME_SEPARATOR = ";";

    /**
     * 老师通用前缀
     */
    public static final String TEACHER_PREFIX = "CQU_TEACHER_";

    /**
     * 学生通用前缀
     */
    public static final String STUDENT_PREFIX = "CQU_STUDENT_";

    /**
     * 课程班级通用前缀
     */
    public static final String COURSE_CLASS_PREFIX = "CQU_CC_";

    /**
     * 课程通用前缀
     */
    public static final String COURSE_PREFIX = "CQU_COURSE_";

    /**
     * 教学班级最低学生人数
     */
    public static final int MIN_STUDENT_COUNT = 40;

    /**
     * 每门课程的最少班级数
     */
    public static final int MIN_COURSE_CLASS_COUNT = 3;

    /**
     * 取得课程名称正则
     */
    public static final String FETCH_COURSE_NAME_REGEX = GlobalConstant.COURSE_PREFIX + "(\\w+)";

    /**
     * Department列名
     */
    public static final String DEPT_COLUMN_DEPT_NAME = "dept_name";

    /**
     * JSON Result tag
     */
    public static final String JSON_RESULT = "application/json;charset=utf-8";

    /**
     * 默认学生总人数
     */
    public static final int STUDENT_TOTAL_COUNT = 120;

    /**
     * 课程分布前缀
     */
    public static final String COURSE_PHASE_PREFIX = "P_C";
}