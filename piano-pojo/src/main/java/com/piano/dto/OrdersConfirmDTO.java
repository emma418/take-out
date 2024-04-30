package com.piano.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersConfirmDTO implements Serializable {

    private Long id;
    // order status 1: payment pending 2: pending order 3: accepted 4:dispatched 5: completed 6 canceled 7 refund
    private Integer status;

}
