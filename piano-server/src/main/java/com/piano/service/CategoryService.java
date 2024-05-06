package com.piano.service;

import com.piano.dto.CategoryDTO;
import com.piano.dto.CategoryPageQueryDTO;
import com.piano.result.PageResult;

public interface CategoryService {
    void addCategory(CategoryDTO categoryDTO);

    PageResult categoryQuery(CategoryPageQueryDTO categoryPageQueryDTO);
}
