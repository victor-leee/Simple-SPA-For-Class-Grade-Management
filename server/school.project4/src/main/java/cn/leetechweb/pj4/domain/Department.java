package cn.leetechweb.pj4.domain;

import java.io.Serializable;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.domain
 * Time : 2020/6/1 23:57
 *
 * @author : Victor
 */

public class Department implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public Department setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Department setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
