package cn.et.demo04.annotation.mapper;

import cn.et.demo04.annotation.model.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface StudentMapper {

    /**
     * 方案一 用字符串的方式拼接sql返回
     * @return
     */
    @Results({
        @Result(column = "sid",property = "id"),
        @Result(column = "sname",property = "name"),
        @Result(column = "sage",property = "age"),
        @Result(column = "ssex",property = "sex")
    })
    @SelectProvider(type =StudentProvider.class ,method = "getStudent")
    List<Student> getStudent(@Param("name") String name, @Param("age") String age, @Param("sex") String sex);


    /**
     * 方案二 通过SQL这个类文成动态拼接
     * @return
     */
    @Results({
            @Result(column = "sid",property = "id"),
            @Result(column = "sname",property = "name"),
            @Result(column = "sage",property = "age"),
            @Result(column = "ssex",property = "sex")
    })
    @SelectProvider(type =StudentProvider.class ,method = "sqlStudent")
    List<Student> sqlStudent(@Param("name") String name, @Param("age") String age, @Param("sex") String sex);

}
