package cn.leetechweb.pj4.domain;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.domain
 * Time : 2020/6/1 23:57
 *
 * @author : Victor
 */

public class Course {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public Course setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    public static Course valueOf(String courseId){
        return new Course().setId(courseId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
