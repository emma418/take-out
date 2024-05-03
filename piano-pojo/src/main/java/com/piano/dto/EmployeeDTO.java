package com.piano.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {

    private Long id;

    private String fullName;

    private String username;

    private String phone;

    private String gender;


}
