package cn.et.demo04.annotation.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class StudentProvider {

    /**
     * 方案一 用字符串的方式拼接sql返回
     * @param map
     * @return
     */
    public String getStudent(Map map){
        String sql ="select * from student where 1=1";
        if (map.get("name") != null && !map.get("name").equals("")){
            String name ="'%"+map.get("name")+"%'";
            sql += " and sname like " +name;
        }
        if (map.get("age") != null && !map.get("age").equals("")){
            String age ="'%"+map.get("age")+"%'";
            sql += " and sage like " +age;
        }
        if (map.get("sex") != null && !map.get("sex").equals("")){
            String sex ="'%"+map.get("sex")+"%'";
            sql += " and ssex like " +sex;
        }
        return sql;
    }

    /**
     * 方案二 通过SQL这个类文成动态拼接
     * @param map
     * @return
     */
    public String sqlStudent(Map map){
        SQL sql =new SQL();
        sql.SELECT("*").FROM("student");
        if (map.get("name") != null && !map.get("name").equals("")){
            String name ="'%"+map.get("name")+"%'";
            sql.WHERE(" sname like "+name);
        }
        if (map.get("age") != null && !map.get("age").equals("")){
            String age ="'"+map.get("age")+"'";
            sql.WHERE(" sage= "+age);
        }
        if (map.get("sex") != null && !map.get("sex").equals("")){
            String sex ="'"+map.get("sex")+"'";
            sql.WHERE(" ssex="+sex);
        }
        return sql.toString();
    }
}
