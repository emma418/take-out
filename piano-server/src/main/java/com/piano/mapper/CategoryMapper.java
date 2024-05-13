package com.piano.mapper;

import com.github.pagehelper.Page;
import com.piano.annotation.AutoFill;
import com.piano.dto.CategoryPageQueryDTO;
import com.piano.entity.Category;
import com.piano.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into category (type, name, status, create_time, update_time, create_user, update_user) " +
            "values(#{type}, #{name}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    Page<Category> categoryQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    @Delete("delete from category where id = #{id}")
    void delete(Long id);
}
