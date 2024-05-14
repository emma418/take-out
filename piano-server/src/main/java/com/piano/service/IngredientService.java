package com.piano.service;

import com.piano.dto.IngredientDTO;
import com.piano.dto.IngredientPageQueryDTO;
import com.piano.result.PageResult;

public interface IngredientService {
    void addIngredient(IngredientDTO ingredientDTO);

    PageResult ingredientQuery(IngredientPageQueryDTO ingredientPageQueryDTO);

    void update(IngredientDTO ingredientDTO);

    void updateStatus(Integer status, Long id);

    void delete(Long id);
}
