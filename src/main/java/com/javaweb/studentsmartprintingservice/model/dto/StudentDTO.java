package com.javaweb.studentsmartprintingservice.model.dto;

import com.javaweb.studentsmartprintingservice.enums.FacultyEnum;
import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Boolean isActive = true;
    private FacultyEnum faculty;
    private Long paperQuantity = 5L;
    private Boolean isColor = false;
    private PageSizeEnum pageSize = PageSizeEnum.A4;
    private Boolean is2Side = true;
}
