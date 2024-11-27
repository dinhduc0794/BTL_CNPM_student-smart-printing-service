package com.javaweb.studentsmartprintingservice.enums;

public enum StatusEnum {
    PENDING("Chờ xử lý"),
    PRINTED("Đã in"),
    CANCELLED("Đã hủy");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
