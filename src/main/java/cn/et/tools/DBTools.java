package cn.et.tools;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DBTools {
    //封装一个工厂类
    public static SqlSession getSession() throws IOException {
        //读取连接池的
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //session操作的是 指向sql语句的一个唯一标示符
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }
}
