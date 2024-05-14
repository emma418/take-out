package com.piano.mapper;

import com.github.pagehelper.Page;
import com.piano.annotation.AutoFill;
import com.piano.dto.IngredientPageQueryDTO;
import com.piano.entity.Ingredient;
import com.piano.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IngredientMapper {

    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into ingredient (name, category_id, status, create_time, create_user, update_time, update_user) " +
            "values (#{name}, #{categoryId}, #{status}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser})")
    void insert(Ingredient ingredient);

    Page<Ingredient> ingredientQuery(IngredientPageQueryDTO ingredientPageQueryDTO);

    @AutoFill(value = OperationType.UPDATE)
    void update(Ingredient ingredient);

    @Delete("delete from ingredient where id = #{id}")
    void delete(Long id);
}
