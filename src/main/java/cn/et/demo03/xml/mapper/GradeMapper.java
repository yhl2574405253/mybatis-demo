package cn.et.demo03.xml.mapper;

import cn.et.demo03.xml.model.Grade;
import org.apache.ibatis.annotations.Param;

public interface GradeMapper {
    /**
     * 演示多对一的接口
     * 通过班级id查找班级详细信息
     * @param id
     * @return
     */
    Grade gradeById(@Param("id") String id);

    /**
     * 演示一对多的接口
     * @param id
     * @return
     */
    Grade oneToMany(@Param("id") String id);

}
