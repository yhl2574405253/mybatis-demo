package cn.et.demo02.controller;

import cn.et.demo02.mapper.FoodMapper;
import cn.et.demo02.model.Food;
import cn.et.tools.DBTools;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FoodTest {
    public static void main(String[] args) throws Exception{
//        List<Food> foods = foodList();      //查询
//        List<Food> foods = selectFood();    //根据条件查询
//        List<Food> foods = deleteFood();    //删除
//        List<Food> foods = insertFood();    //添加
        List<Food> foods = updateFood();    //修改
        for (Food food1 : foods){
            System.out.println(food1.getId()+","+food1.getName()+","+food1.getPrice());
        }
    }

    /**
     * 查询所有数据
     * @return
     * @throws IOException
     */
    public static List<Food> foodList() throws IOException {
        FoodMapper mapper =DBTools.getSession().getMapper(FoodMapper.class);
        List list = mapper.foodList();
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
        food.setPrice("6");
        List<Food> list = mapper.selectFood(food);
        return list;
    }

    /**
     * 删除
     * @return
     * @throws IOException
     */
    public static List deleteFood() throws IOException{
        SqlSession sqlSession = DBTools.getSession();
        FoodMapper mapper = sqlSession.getMapper(FoodMapper.class);
        mapper.deleteFood("8ab9f352-0726-4d44-8423-b93e044b5741");
        sqlSession.commit();
        List<Food> foods = foodList();
        return foods;
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
        food.setName("2");
        food.setPrice("2");
        mapper.insertFood(food);
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
        food.setId("fdf9d26a-ff7e-49e8-a47a-405ac6d2703b");
        food.setName("3");
        food.setPrice("3");
        mapper.updateFood(food);
        sqlSession.commit();
        List<Food> list = mapper.foodList();
        return list;
    }
}
