package cn.et.demo02.mapper;

import cn.et.demo02.model.Food;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FoodMapper {
    /**
     * 添加
     * @param food
     * @return
     */
    @Insert("insert into food values(#{food.id},#{food.name},#{food.price})")
    int insertFood(@Param("food") Food food);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from food where id=#{id}")
    int deleteFood(@Param("id") String id);

    /**
     * 修改
     * @param food
     * @return
     */
    @Update("update food set name = #{food.name}, price = #{food.price} where id=#{food.id}")
    int updateFood(@Param("food") Food food);

    /**
     * 查询所有的
     * @return
     */
    @Select("select * from food")
    List<Food> foodList();

    /**
     * 根据条件查询
     * @param food
     * @return
     */
    @Select("SELECT * FROM food where name LIKE concat('%','${food.name}','%') and price LIKE concat('%','${food.price}','%')")
    List<Food> selectFood(@Param("food") Food food);
}
