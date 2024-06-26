package com.piano.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {

    private String fullName;

    private int page;

    private int pageSize;

}
