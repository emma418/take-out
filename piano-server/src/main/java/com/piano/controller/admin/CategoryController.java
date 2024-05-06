package com.piano.controller.admin;

import com.piano.dto.CategoryDTO;
import com.piano.dto.CategoryPageQueryDTO;
import com.piano.result.PageResult;
import com.piano.result.Result;
import com.piano.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ApiOperation("add a category")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("add a category: {}", categoryDTO);

        categoryService.addCategory(categoryDTO);

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("query category")
    public Result<PageResult> categoryQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("query category parameters: {}", categoryPageQueryDTO);

        PageResult pageResult = categoryService.categoryQuery(categoryPageQueryDTO);

        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, Long id) {
        log.info("category parameters: {}, {}", status, id);

        categoryService.updateStatus(status, id);

        return Result.success();
    }

    @PutMapping
    public Result updateCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("update a category: {}", categoryDTO);

        categoryService.updateCategory(categoryDTO);

        return Result.success();
    }


    @DeleteMapping
    @ApiOperation("delete a category")
    public Result delete(Long id) {
        log.info("delete a category: {}", id);

        categoryService.delete(id);

        return Result.success();

    }


}
