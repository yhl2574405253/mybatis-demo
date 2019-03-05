package cn.et.demo03.xml.controller;

import cn.et.demo03.xml.mapper.GradeMapper;
import cn.et.demo03.xml.mapper.StudentMapper;
import cn.et.demo03.xml.model.Grade;
import cn.et.demo03.xml.model.Student;
import cn.et.tools.DBTools;

import java.io.IOException;

public class TestController {
    public static void main(String[] args) throws IOException {
//      多对一的例子
        Student student = ManyToOne();
        System.out.println(student.getName()+"===="+student.getGrade().getName());

//      一对多的例子
//        Grade grade =OneToMany();
//        System.out.println("班级名称："+grade.getName());
//        for (int i=0; i<grade.getList().size(); i++){
//            System.out.println("学生姓名:"+grade.getList().get(i).getName());
//        }

//        鉴别器的例子
//        Student test = test();
//        System.out.println(test);

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
     * 鉴别器的例子
     * @return
     * @throws IOException
     */
    public static Student test() throws IOException {
        StudentMapper studentMapper = DBTools.getSession().getMapper(StudentMapper.class);
        Student student  =studentMapper.ManyToOneById("22");
        return student;
    }

    /**
     * 一对多的例子
     * @return
     * @throws IOException
     */
    public static Grade OneToMany() throws IOException {
        GradeMapper gradeMapper = DBTools.getSession().getMapper(GradeMapper.class);
        Grade grade =gradeMapper.oneToMany("1");
        return grade;
    }
}
