package cn.et.demo03.annotation.mapper;

import cn.et.demo03.annotation.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    /**
     * 演示多对一
     * @param id
     * @return
     */
    @Results({
        @Result(property = "id",column = "sid"),
        @Result(property = "name",column = "sname"),
        @Result(property = "grade",column = "gid",one = @One(select = "cn.et.demo03.annotation.mapper.GradeMapper.gradeById"))
    })
    @Select("select * from student where sid= #{id}")
    Student ManyToOneById(@Param("id") String id);

    /**
     * 演示一对多
     * @param id
     * @return
     */
    @Results({
        @Result(property = "id",column = "sid"),
        @Result(property = "name",column = "sname")
    })
    @Select("select * from student where gid=#{id}")
    List<Student> studentByGid(@Param("id") String id);
}
