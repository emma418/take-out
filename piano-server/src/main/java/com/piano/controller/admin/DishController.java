package com.piano.controller.admin;

import com.piano.dto.DishDTO;
import com.piano.entity.Dish;
import com.piano.result.Result;
import com.piano.service.DishService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    @ApiOperation("add a dish")
    public Result addDish(@RequestBody DishDTO dishDTO) {
        log.info("addDish: {}", dishDTO);

        dishService.addDish(dishDTO);

        return Result.success();
    }



}
