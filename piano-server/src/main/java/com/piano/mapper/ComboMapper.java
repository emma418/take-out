package com.piano.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComboMapper {

    @Select("select count(*) from combo where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);
}
