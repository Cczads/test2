package com.example.democommerce1.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {

    /**
     * 客户id
      */
    private Integer customerId;

    /**
     * 客户名称
     */
    private String customerName;
}
