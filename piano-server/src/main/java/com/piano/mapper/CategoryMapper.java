package com.piano.mapper;

import com.github.pagehelper.Page;
import com.piano.dto.CategoryPageQueryDTO;
import com.piano.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category (type, name, status, create_time, update_time, create_user, update_user) " +
            "values(#{type}, #{name}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    Page<Category> categoryQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(Category category);
}
