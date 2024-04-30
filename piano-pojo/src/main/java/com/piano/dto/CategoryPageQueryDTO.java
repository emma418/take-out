package com.piano.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

    private int page;

    // size of a page
    private int pageSize;

    // category name
    private String name;

    // 1: dish category  2: combo category
    private Integer type;

}
