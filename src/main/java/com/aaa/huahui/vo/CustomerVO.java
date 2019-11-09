package com.aaa.huahui.vo;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class CustomerVO {
    int distinctcustomer;
    int customer;

    public int getDistinctcustomer() {
        return distinctcustomer;
    }

    public void setDistinctcustomer(int distinctcustomer) {
        this.distinctcustomer = distinctcustomer;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public CustomerVO() {
    }

    public CustomerVO(int distinctcustomer, int customer) {
        this.distinctcustomer = distinctcustomer;
        this.customer = customer;
    }
}
