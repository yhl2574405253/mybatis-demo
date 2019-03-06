package cn.et.demo04.annotation.controller;

import cn.et.demo04.annotation.mapper.StudentMapper;
import cn.et.demo04.annotation.model.Student;
import cn.et.tools.DBTools;

import java.util.List;

public class TestController {
    public static void main(String[] args) throws Exception {
        List<Student> students = test2();
        for (Student student: students) {
            System.out.println(student.getName()+" == "+student.getSex()+" == "+student.getAge());
        }
    }


    private static List<Student> test1()throws Exception{
        StudentMapper mapper = DBTools.getSession().getMapper(StudentMapper.class);
        List<Student> student = mapper.getStudent("小", "40", "男");
        return student;
    }

    private static List<Student> test2()throws Exception{
        StudentMapper mapper = DBTools.getSession().getMapper(StudentMapper.class);
        List<Student> student = mapper.sqlStudent("小", "40", "男");
        return student;
    }
}
