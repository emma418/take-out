package com.piano.service.impl;

import com.piano.dto.DishDTO;
import com.piano.dto.DishIngredientDTO;
import com.piano.entity.Dish;
import com.piano.entity.DishIngredient;
import com.piano.entity.Ingredient;
import com.piano.mapper.DishIngredientMapper;
import com.piano.mapper.DishMapper;
import com.piano.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishIngredientMapper dishIngredientMapper;

    @Override
    @Transactional
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dishMapper.insert(dish);

        List<DishIngredientDTO> dishIngredientDTOS = dishDTO.getDishIngredientDTOS();
        Long dishId = dish.getId();

        if (dishIngredientDTOS.size() > 0) {
            dishIngredientDTOS.forEach(dishIngredientDTO -> {
                DishIngredient dishIngredient = new DishIngredient();
                dishIngredient.setDishId(dishId);
                dishIngredient.setIngredientId(dishIngredientDTO.getIngredientID());
                dishIngredient.setPrice(dishIngredientDTO.getCustomPrice());

                dishIngredientMapper.insert(dishIngredient);
            });
        }

    }
}
