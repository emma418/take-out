package com.piano.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class IngredientDTO implements Serializable {
    private Long id;
    private String name;
    private Long categoryId;


}
