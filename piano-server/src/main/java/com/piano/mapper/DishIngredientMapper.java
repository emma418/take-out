package com.piano.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishIngredientMapper {
    @Select("select count(*) from dish_ingredient where ingredient_id = #{id}")
    Integer countByIngredientId(Long id);
}
