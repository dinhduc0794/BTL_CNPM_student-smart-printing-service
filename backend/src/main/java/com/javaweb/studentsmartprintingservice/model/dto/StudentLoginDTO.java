package com.javaweb.studentsmartprintingservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLoginDTO {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password can not be blank")
    private String password;

}
