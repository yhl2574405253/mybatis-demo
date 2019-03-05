package cn.et.demo01.mapper;

import cn.et.demo01.model.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodMapper {
    /**
     * 添加
     * @param food
     * @return
     */
    int insertFood(@Param("food") Food food);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteFood(@Param("id") String id);

    /**
     * 修改
     * @param food
     * @return
     */
    int updateFood(@Param("food") Food food);

    /**
     * 查询所有的
     * @return
     */
    List<Food> foodList();

    /**
     * 根据条件查询
     * @param food
     * @return
     */
    List<Food> selectFood(@Param("food") Food food);
}
