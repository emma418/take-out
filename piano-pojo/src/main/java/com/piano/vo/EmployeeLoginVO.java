package com.piano.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "employee login return data")
public class EmployeeLoginVO implements Serializable {

    @ApiModelProperty("primary key")
    private Long id;

    @ApiModelProperty("username")
    private String username;

    @ApiModelProperty("full name")
    private String fullName;

    @ApiModelProperty("jwt token")
    private String token;

}
