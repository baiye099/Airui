package com.iweb.homework;

public enum PaymentMethod {
    ALIPAY("支付宝"),
    WEICHAT_PAY("微信支付"),
    BANK_PAY("银行卡支付");

    private String PayMethod;

    PaymentMethod() {
    }

    PaymentMethod(String payMethod) {
        PayMethod = payMethod;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "PayMethod='" + PayMethod + '\'' +
                '}';
    }

    public String getPayMethod() {
        return PayMethod;
    }

    public void setPayMethod(String payMethod) {
        PayMethod = payMethod;
    }
}
