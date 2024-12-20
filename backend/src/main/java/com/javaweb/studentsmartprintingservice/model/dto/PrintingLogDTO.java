package com.javaweb.studentsmartprintingservice.model.dto;


import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import com.javaweb.studentsmartprintingservice.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class PrintingLogDTO {
    private Long id;
    @NotNull(message = "Student ID is required")
    private Long studentId;
    private Long printerId;
    @NotBlank(message = "Document name is required")
    private String documentName;
    private Long documentPages;
    @NotNull(message = "Is scheduled is required")
    private Boolean isScheduled = false;
    private ZonedDateTime scheduledTime;
    private String note;
    private Long numberOfCopies = 1L;
    private Boolean isColor = false;
    private PageSizeEnum pageSize = PageSizeEnum.A4;
    private Boolean is2Side = false;
    private StatusEnum status = StatusEnum.PENDING;
    private String message;
}
