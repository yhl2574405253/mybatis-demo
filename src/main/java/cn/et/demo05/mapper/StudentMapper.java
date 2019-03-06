package cn.et.demo05.mapper;

import cn.et.demo05.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> studentList(@Param("id") String id);

}
