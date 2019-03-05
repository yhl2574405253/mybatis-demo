package cn.et.demo01.controller;

import cn.et.demo01.mapper.FoodMapper;
import cn.et.demo01.model.Food;
import cn.et.tools.DBTools;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FoodTest {
    public static void main(String[] args) throws IOException{
        List<Food> foods = foodList();       //查询所有的数据
//        List<Food> foods = selectFood();     //根据条件查询数据
//        List<Food> foods = updateFood();     //修改数据
//        List<Food> foods = insertFood();      //添加数据
        for (Food food1 : foods){
            System.out.println(food1.getId()+","+food1.getName()+","+food1.getPrice());
        }
    }

    /**
     * 添加
     * @return
     * @throws IOException
     */
    public static List insertFood() throws IOException {
        SqlSession sqlSession = DBTools.getSession();
        FoodMapper mapper = sqlSession.getMapper(FoodMapper.class);
        Food food = new Food();
        food.setId(String.valueOf(UUID.randomUUID()));
        food.setName("7");
        food.setPrice("7");
        mapper.insertFood(food);
        sqlSession.commit();
        List<Food> foods = foodList();
        return foods;
    }

    /**
     * 删除
     * @return
     * @throws IOException
     */
    public static List deleteFood() throws IOException{
        SqlSession sqlSession = DBTools.getSession();
        FoodMapper mapper = sqlSession.getMapper(FoodMapper.class);
        mapper.deleteFood("7");
        sqlSession.commit();
        List<Food> foods = foodList();
        return foods;
    }

    /**
     * 修改
     * @return
     * @throws IOException
     */
    public static List updateFood() throws IOException{
        SqlSession sqlSession = DBTools.getSession();
        FoodMapper mapper = sqlSession.getMapper(FoodMapper.class);
        Food food = new Food();
        food.setId("1");
        food.setName("11");
        food.setPrice("11");
        mapper.updateFood(food);
        sqlSession.commit();
        List<Food> list = mapper.foodList();
        return list;
    }

    /**
     * 查询所有数据
     * @return
     * @throws IOException
     */
    public static List<Food> foodList() throws IOException {
        FoodMapper mapper =DBTools.getSession().getMapper(FoodMapper.class);
        List<Food> list = mapper.foodList();
        return list;
    }

    /**
     * 根据条件查询
     * @return
     * @throws IOException
     */
    public static List<Food> selectFood() throws IOException {
        FoodMapper mapper =DBTools.getSession().getMapper(FoodMapper.class);
        Food food =new Food();
        food.setPrice("4");
        List<Food> list = mapper.selectFood(food);
        return list;
    }
}
