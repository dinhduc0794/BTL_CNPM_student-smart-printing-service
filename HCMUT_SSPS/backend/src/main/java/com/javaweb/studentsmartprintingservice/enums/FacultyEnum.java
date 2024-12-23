package com.javaweb.studentsmartprintingservice.enums;

public enum FacultyEnum {
    CSE("Khoa Khoa học và Kỹ thuật Máy tính"),
    EE("Khoa Điện - Điện tử"),
    ME("Khoa Cơ khí"),
    ENV("Khoa Môi trường và Tài nguyên"),
    CE("Khoa Kỹ thuật Giao thông"),
    CIV("Khoa Kỹ thuật Xây dựng"),
    CHE("Khoa Kỹ thuật Hóa học"),
    MSE("Khoa Công nghệ Vật liệu"),
    GEO("Khoa Kỹ thuật Địa chất và Dầu khí"),
    APP("Khoa Khoa học ứng dụng"),
    IM("Khoa Quản lí công nghiệp");

    private String faculty;

    FacultyEnum(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }
}
