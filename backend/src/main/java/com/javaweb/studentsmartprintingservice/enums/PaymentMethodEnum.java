package com.javaweb.studentsmartprintingservice.enums;

public enum PaymentMethodEnum {
    CASH("Tiền mặt"),
    BANKING("Chuyển khoản ngân hàng"),
    E_WALLET ("Ví điện tử");

    private String method;

    PaymentMethodEnum(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
