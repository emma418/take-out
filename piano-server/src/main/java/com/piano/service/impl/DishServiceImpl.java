package com.piano.service.impl;

import com.piano.dto.DishDTO;
import com.piano.entity.Dish;
import com.piano.entity.Ingredient;
import com.piano.mapper.DishMapper;
import com.piano.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        List<Ingredient> ingredients = dishDTO.getIngredients();
        Long dishId = dish.getId();

        if (ingredients != null && ingredients.size() > 0) {

        }

    }
}
