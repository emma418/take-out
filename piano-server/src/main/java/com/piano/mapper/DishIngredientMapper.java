package com.piano.mapper;

import com.piano.annotation.AutoFill;
import com.piano.entity.DishIngredient;
import com.piano.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishIngredientMapper {

    @Select("select count(*) from dish_ingredient where ingredient_id = #{id}")
    Integer countByIngredientId(Long id);

    @Insert("insert into dish_ingredient (dish_id, ingredient_id, price, create_time, create_user, update_time, update_user)" +
            "values (#{dishId}, #{ingredientId}, #{price}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insert(DishIngredient dishIngredient);
}
