package com.example.democommerce1.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerAddress implements Serializable {

    /**
     * 收货地址id
     */
    private Integer customerAddressId;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 城市名称
     */
    private String cityName;

    // 其他信息暂时省略。。。
}
