package com.javaweb.studentsmartprintingservice.enums;

public enum ReportType {
    MONTHLY_PRINTING("Monthly report of printing service"),
    MONTHLY_PAPER("Monthly report of buying paper service"),
    YEARLY_PRINTING("Yearly report of printing service"),
    YEARLY_PAPER("Yearly report of buying paper service");


    private String type;

    ReportType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
