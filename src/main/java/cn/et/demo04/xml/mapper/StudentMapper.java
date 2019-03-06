package cn.et.demo04.xml.mapper;

import cn.et.demo04.xml.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> getStudent(@Param("name") String name,@Param("age") String age,@Param("sex") String sex);

    List<Student> queryStudentSex(@Param("sex") String sex);

    void updateStudent(@Param("id") String id,@Param("name") String name,@Param("sex") String sex,@Param("age") String age);

    List<Student> queryStudentByClasses(@Param("classesList") List classesList);
}
