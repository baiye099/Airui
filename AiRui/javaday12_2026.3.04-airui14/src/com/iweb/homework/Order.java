package com.iweb.homework;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//创建Order类 （注意，序列化接口和序列号版本的问题）
public class Order implements Serializable {
    private static final long serialVersionUID=-1L;
    private String order;
    private String UID;
    private BigDecimal transactionAmount;
    private LocalDateTime tradingHours;
    private PaymentMethod paymentMethod;
    private  OrderStatus orderStatus;

    public Order() {
    }

    public Order(String order, String UID, BigDecimal transactionAmount, LocalDateTime tradingHours, PaymentMethod paymentMethod, OrderStatus orderStatus) {
        this.order = order;
        this.UID = UID;
        this.transactionAmount = transactionAmount;
        this.tradingHours = tradingHours;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order='" + order + '\'' +
                ", UID='" + UID + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", tradingHours=" + tradingHours +
                ", paymenMethod=" + paymentMethod +
                ", orderStatus=" + orderStatus +
                '}';
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDateTime getTradingHours() {
        return tradingHours;
    }

    public void setTradingHours(LocalDateTime tradingHours) {
        this.tradingHours = tradingHours;
    }

    public PaymentMethod getPaymenMethod() {
        return paymentMethod;
    }

    public void setPaymenMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
