package cn.et.demo05.controller;


import cn.et.demo05.mapper.StudentMapper;
import cn.et.demo05.model.Student;
import cn.et.tools.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TestController {
    public static void main(String[] args) throws Exception {
        List<Student> students = test2();
        for (Student student: students) {
            System.out.println(student.getName()+" == "+student.getSex()+" == "+student.getAge());
        }
    }

    /**
     * 一级缓存 同一个sesion对象针对同一份数据的查询 产生的缓存
     * 第一次查询时 调用数据 获取数据后
     * 通过session设置到一级缓存中
     * 第二次查询时 通过session 一级缓存判断是否存在 相同主键的数据值 如果存在 直接返回引用 否则查询数据库
     * @throws Exception
     */
    private static List<Student> test1()throws Exception{
        StudentMapper mapper = DBTools.getSession().getMapper(StudentMapper.class);
        List<Student> students = mapper.studentList("11");
        List<Student> students1 = mapper.studentList("11");
        System.out.println(students == students1);
        return students;
    }

    /**
     * 二次缓存 同一个sessionFactory下的不同session 并且需要sqlSession1 close掉 可以共享数据,
     * @throws Exception
     */
    private static List<Student> test2()throws Exception{
        SqlSessionFactory sessionFactory = DBTools.getSessionFactory();

        SqlSession sqlSession1 = sessionFactory.openSession();
        StudentMapper mapper1 = sqlSession1.getMapper(StudentMapper.class);
        List<Student> student1 = mapper1.studentList("2");
        sqlSession1.close();

        SqlSession sqlSession2 = sessionFactory.openSession();
        StudentMapper mapper2 = sqlSession2.getMapper(StudentMapper.class);
        List<Student> student2 = mapper2.studentList("2");


        System.out.println(student1 == student2);
        return student1;
    }
}
