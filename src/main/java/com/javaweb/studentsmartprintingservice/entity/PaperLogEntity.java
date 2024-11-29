package com.javaweb.studentsmartprintingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javaweb.studentsmartprintingservice.enums.PaymentMethodEnum;
import com.javaweb.studentsmartprintingservice.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Table(name = "paper_log")
@Entity
@Getter
@Setter
public class PaperLogEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod = PaymentMethodEnum.CASH;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "status")
    private StatusEnum status = StatusEnum.PENDING;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    private StudentEntity student;
}
