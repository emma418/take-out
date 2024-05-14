package com.piano.service;

import com.piano.dto.CategoryDTO;
import com.piano.dto.CategoryPageQueryDTO;
import com.piano.entity.Category;
import com.piano.result.PageResult;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryDTO categoryDTO);

    PageResult categoryQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void updateStatus(Integer status, Long id);

    void updateCategory(CategoryDTO categoryDTO);

    void delete(Long id);

    List<Category> list(Integer type);
}
