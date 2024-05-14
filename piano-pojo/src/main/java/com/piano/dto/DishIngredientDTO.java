package com.piano.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishIngredientDTO {
    private Long ingredientID;
    private BigDecimal customPrice;
}
