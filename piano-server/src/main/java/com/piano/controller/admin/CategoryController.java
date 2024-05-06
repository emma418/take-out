package com.piano.controller.admin;

import com.piano.dto.CategoryDTO;
import com.piano.result.Result;
import com.piano.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
