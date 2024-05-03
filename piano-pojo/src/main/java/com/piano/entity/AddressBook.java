package com.piano.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String consignee;

    private String phone;

    private String province;

    private String city;

    private String street;
    private String unit;

    private String postalCode;

    private Integer isDefault;
}
