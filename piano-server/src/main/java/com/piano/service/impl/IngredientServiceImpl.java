package com.piano.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.piano.constant.MessageConstant;
import com.piano.constant.StatusConstant;
import com.piano.dto.IngredientDTO;
import com.piano.dto.IngredientPageQueryDTO;
import com.piano.entity.Ingredient;
import com.piano.exception.DeletionNotAllowedException;
import com.piano.mapper.DishIngredientMapper;
import com.piano.mapper.IngredientMapper;
import com.piano.result.PageResult;
import com.piano.service.IngredientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientMapper ingredientMapper;
    @Autowired
    private DishIngredientMapper dishIngredientMapper;


    @Override
    public void addIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        BeanUtils.copyProperties(ingredientDTO, ingredient);

        ingredient.setStatus(StatusConstant.DISABLE);
        ingredientMapper.insert(ingredient);
    }

    @Override
    public PageResult ingredientQuery(IngredientPageQueryDTO ingredientPageQueryDTO) {
        PageHelper.startPage(ingredientPageQueryDTO.getPage(), ingredientPageQueryDTO.getPageSize());

        Page<Ingredient> page = ingredientMapper.ingredientQuery(ingredientPageQueryDTO);

        List<Ingredient> result = page.getResult();

        return new PageResult(page.getTotal(), result);
    }

    @Override
    public void update(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        BeanUtils.copyProperties(ingredientDTO, ingredient);

        ingredientMapper.update(ingredient);
    }

    @Override
    public void updateStatus(Integer status, Long id) {
        Ingredient ingredient = Ingredient.builder().id(id).status(status).build();
        ingredientMapper.update(ingredient);
    }

    @Override
    public void delete(Long id) {
        Integer count = dishIngredientMapper.countByIngredientId(id);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.INGREDIENT_BE_RELATED_BY_DISH);
        }
        ingredientMapper.delete(id);
    }
}
