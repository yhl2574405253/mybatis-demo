package cn.et.demo03.annotation.model;

import java.util.ArrayList;
import java.util.List;

public class Grade {
    private String id;
    private String name;
    private List<Student> list =new ArrayList<Student>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }
}
