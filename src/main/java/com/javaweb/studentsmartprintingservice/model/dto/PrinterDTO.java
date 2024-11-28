package com.javaweb.studentsmartprintingservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrinterDTO {
    private Long id;
    @NotBlank(message = "Printer location is required")
    private String campus;
    @NotBlank(message = "Printer location is required")
    private String building;
    @NotBlank(message = "Printer location is required")
    private String room;
    private Boolean isActive = true;
    @NotNull(message = "Printer is scheduled or not is required")
    private Boolean isScheduled = false;
    private String description;
    private String model;
    private String brand;
    private Long paperA4left;
    private Long paperA5left;
    private Long paperA6left;
    private Long spsoId;
}
