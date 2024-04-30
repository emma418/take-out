package com.piano.dto;

import com.piano.entity.OrderDetail;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdersDTO implements Serializable {

    private Long id;

    // order number
    private String number;

    //order status 1: payment pending 2: awaiting delivery 3: dispatched 4: completed 5: canceled
    private Integer status;

    private Long userId;

    private Long addressBookId;

    private LocalDateTime orderTime;

    private LocalDateTime checkoutTime;

    private Integer payMethod;

    private BigDecimal amount;

    private String remark;

    private String userName;

    private String phone;

    private String address;

    //收货人
    private String consignee;

    private List<OrderDetail> orderDetails;

}
