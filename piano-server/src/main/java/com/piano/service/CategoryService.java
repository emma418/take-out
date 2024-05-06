package com.piano.service;

import com.piano.dto.CategoryDTO;
import com.piano.dto.CategoryPageQueryDTO;
import com.piano.result.PageResult;

public interface CategoryService {
    void addCategory(CategoryDTO categoryDTO);

    PageResult categoryQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void updateStatus(Integer status, Long id);

    void updateCategory(CategoryDTO categoryDTO);
}
