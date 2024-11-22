package com.javaweb.studentsmartprintingservice.enums;

public enum PageSizeEnum {
    A1("A1"),
    A2("A2"),
    A3("A3"),
    A4("A4");

    private String size;

    PageSizeEnum(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
