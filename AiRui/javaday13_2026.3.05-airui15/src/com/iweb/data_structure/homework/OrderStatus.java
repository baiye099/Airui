package com.iweb.data_structure.homework;

import java.io.Serializable;

public enum OrderStatus implements Serializable {

        PAID("已支付"),
        UNPAID("待支付"),
        CANCELLED("已取消"),
        REFUNDED("已退款");

        private  String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
