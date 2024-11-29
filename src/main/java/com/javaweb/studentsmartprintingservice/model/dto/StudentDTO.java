package com.javaweb.studentsmartprintingservice.model.dto;

import com.javaweb.studentsmartprintingservice.enums.FacultyEnum;
import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Please retype your password")
    private String retypePassword;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    private String email;
    private String phone;
    private Boolean isActive = true;
    @NotNull(message = "Faculty is required")
    private FacultyEnum faculty;
    private Long paperQuantity = 100L;
    private Boolean isColor = false;
    private PageSizeEnum pageSize = PageSizeEnum.A4;
    private Boolean is2Side = true;
}
