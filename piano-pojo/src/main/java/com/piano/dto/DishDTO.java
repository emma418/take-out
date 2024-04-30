package com.piano.dto;

import com.piano.entity.DishFlavor;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {

    private Long id;
    // dish name
    private String name;
    // dish category id
    private Long categoryId;
    // price
    private BigDecimal price;
    // dish image
    private String image;
    // dish desciption
    private String description;
    // 0: close 1: open
    private Integer status;
    // flavor
    private List<DishFlavor> flavors = new ArrayList<>();

}
