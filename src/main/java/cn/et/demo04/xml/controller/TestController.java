package cn.et.demo04.xml.controller;

import cn.et.demo04.xml.mapper.StudentMapper;
import cn.et.demo04.xml.model.Student;
import cn.et.tools.DBTools;

import java.util.ArrayList;
import java.util.List;

public class TestController {
    public static void main(String[] args) throws Exception {
        List<Student> students = test1();
        for (Student student: students) {
            System.out.println(student.getName()+" == "+student.getSex()+" == "+student.getAge());
        }
    }

    private static List<Student> test1()throws Exception{
        StudentMapper mapper = DBTools.getSession().getMapper(StudentMapper.class);
        List<Student> student = mapper.getStudent("", "", "");
        return student;
    }

    private static List<Student> test2()throws Exception {
        StudentMapper mapper = DBTools.getSession().getMapper(StudentMapper.class);
        List<Student> student = mapper.queryStudentSex("");
        return student;
    }

    private static List<Student> test3()throws Exception {
        StudentMapper mapper = DBTools.getSession().getMapper(StudentMapper.class);
        mapper.updateStudent("2","小红","女","88");
        DBTools.getSession().commit();
        List<Student> student = mapper.getStudent("","","");
        return student;
    }

    private static List<Student> test4()throws Exception {
        StudentMapper mapper = DBTools.getSession().getMapper(StudentMapper.class);
        List list =new ArrayList();
        list.add("3");
        list.add("4");
        List<Student> student = mapper.queryStudentByClasses(list);
        return student;
    }


}
