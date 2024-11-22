package com.javaweb.studentsmartprintingservice.enums;

public enum PageSizeEnum {
    A4("A4"),
    A5("A5"),
    A6("A6");

    private String size;

    PageSizeEnum(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
