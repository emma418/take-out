package com.piano.controller.admin;

import com.piano.dto.IngredientDTO;
import com.piano.dto.IngredientPageQueryDTO;
import com.piano.result.PageResult;
import com.piano.result.Result;
import com.piano.service.IngredientService;
import com.piano.utils.S3Util;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/ingredient")
@Slf4j
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private S3Util getS3Util;

    @PostMapping
    @ApiOperation("add an ingredient")
    public Result addIngredient(@RequestBody IngredientDTO ingredientDTO) {
        log.info("add ingredient {}", ingredientDTO);

        ingredientService.addIngredient(ingredientDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("query ingredients")
    public Result<PageResult> ingredientQuery(IngredientPageQueryDTO ingredientPageQueryDTO) {
        log.info("ingredient query {}", ingredientPageQueryDTO);

        PageResult pageResult = ingredientService.ingredientQuery(ingredientPageQueryDTO);

        return Result.success(pageResult);
    }

    @PutMapping
    @ApiOperation("update an ingredient")
    public Result update(@RequestBody IngredientDTO ingredientDTO) {
        log.info("update ingredient {}", ingredientDTO);

        ingredientService.update(ingredientDTO);

        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("update ingredient status")
    public Result updateStatus(@PathVariable Long id, Integer status) {
        log.info("update status id {}", id);

        ingredientService.updateStatus(status, id);

        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("delete an ingredient")
    public Result delete(Long id) {
        log.info("delete ingredient {}", id);

        ingredientService.delete(id);

        return Result.success();
    }

}
