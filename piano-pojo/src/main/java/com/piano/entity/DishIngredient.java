package com.piano.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishIngredient {
    private Long id;
    private Long dishId;
    private Long ingredientId;
    private BigDecimal price;

}
