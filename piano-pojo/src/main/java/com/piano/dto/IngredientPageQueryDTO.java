package com.piano.dto;

import lombok.Data;

@Data
public class IngredientPageQueryDTO {
    private int page;

    private int pageSize;

    private String name;

    private Integer categoryId;

    //status 0: disable 1: enable
    private Integer status;
}
