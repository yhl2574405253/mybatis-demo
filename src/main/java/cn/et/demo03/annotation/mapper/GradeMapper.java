package cn.et.demo03.annotation.mapper;

import cn.et.demo03.annotation.model.Grade;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GradeMapper {
    /**
     * 演示多对一的接口
     * 通过班级id查找班级详细信息
     * @param id
     * @return
     */
    @Results({
        @Result(property = "id",column = "gid"),
        @Result(property = "name",column = "gname")
    })
    @Select("select * from grade where gid= #{id}")
    Grade gradeById(@Param("id") String id);

    /**
     * 演示一对多的接口
     * @param id
     * @return
     */
    @Results({
        @Result(property = "id",column = "gid"),
        @Result(property = "name",column = "gname"),
        @Result(property = "list",column ="gid",javaType = List.class, many = @Many(select = "cn.et.demo03.annotation.mapper.StudentMapper.studentByGid"))
    })
    @Select("select * from grade where gid =#{id}")
    Grade oneToMany(@Param("id") String id);

}
