package com.piano.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    //primary key
    private Long id;

    // 1:  dish category 2: combo category
    private Integer type;

    // category name
    private String name;

    // order
//    private Integer sort;
}
