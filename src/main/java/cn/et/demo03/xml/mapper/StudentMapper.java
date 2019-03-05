package cn.et.demo03.xml.mapper;

import cn.et.demo03.xml.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    Student ManyToOneById(@Param("id") String id);

    List<Student> studentByGid(@Param("id") String id);

    Student test(@Param("id") String id);
}
