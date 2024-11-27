package com.javaweb.studentsmartprintingservice.model.dto;

import com.javaweb.studentsmartprintingservice.enums.PaymentMethodEnum;
import com.javaweb.studentsmartprintingservice.enums.StatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaperLogDTO {
    private Long id;
    @NotNull(message = "Student ID is required")
    private Long studentId;
    @NotNull(message = "Payment method is required")
    private PaymentMethodEnum paymentMethod = PaymentMethodEnum.CASH;
    @Min(value = 1, message = "Paper quantity must be greater than 0.")
    private Long quantity;
    private StatusEnum status = StatusEnum.PENDING;
    private String message;
}
