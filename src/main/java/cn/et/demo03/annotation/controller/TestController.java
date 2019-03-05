package cn.et.demo03.annotation.controller;

import cn.et.demo03.annotation.mapper.GradeMapper;
import cn.et.demo03.annotation.mapper.StudentMapper;
import cn.et.demo03.annotation.model.Grade;
import cn.et.demo03.annotation.model.Student;
import cn.et.tools.DBTools;

import java.io.IOException;

public class TestController {
    public static void main(String[] args) throws IOException {
//      多对一的例子
        Student student = ManyToOne();
        System.out.println(student.getName());
        System.out.println(student.getGrade().getName());

//        一对多的例子
//        Grade grade =OneToMany();
//        System.out.println(grade.getName());
//        for (int i=0; i<grade.getList().size(); i++){
//            System.out.println(grade.getList().get(i).getName());
//        }
    }

    /**
     * 多对一的例子
     * @return
     * @throws IOException
     */
    public static Student ManyToOne() throws IOException {
        StudentMapper studentMapper = DBTools.getSession().getMapper(StudentMapper.class);
        Student student  =studentMapper.ManyToOneById("11");
        return student;
    }

    /**
     * 一对多的例子
     * @return
     * @throws IOException
     */
    public static Grade OneToMany() throws IOException {
        GradeMapper gradeMapper = DBTools.getSession().getMapper(GradeMapper.class);
        Grade grade =gradeMapper.oneToMany("4");
        return grade;
    }
}
