package com.javaweb.studentsmartprintingservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpsoDTO {
    private Long id;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    private String email;
    private String phone;
    private Boolean isActive = true;
    private Long systemConfigId;
}
