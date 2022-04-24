package com.example.democommerce1.service;

import com.example.democommerce1.vo.Customer;
import com.example.democommerce1.vo.CustomerAddress;

import javax.servlet.http.HttpServletRequest;

/**
 * 模拟的方法
 */
public class SimulateService {

    // 先模拟获取登录用户
    public Customer getCurrentCustomer(HttpServletRequest request){

        /*Customer customer = new Customer();

        customer.setCustomerId(1);
        return customer;*/

        return null;
    }

    // 模拟获取用户默认收货地址
    public CustomerAddress getDefaultAddress(){

        return null;
    }

    public Integer getCityIdFromAlibaba(String ip){

        // todo 补充阿里调用方法，以及将获取的结果转换为我们系统里面的cityId

        return null;
    }
}
