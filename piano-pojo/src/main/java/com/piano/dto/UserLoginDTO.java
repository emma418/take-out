package com.piano.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * user login
 */
@Data
public class UserLoginDTO implements Serializable {

    private String code;

}
