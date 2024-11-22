package com.javaweb.studentsmartprintingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javaweb.studentsmartprintingservice.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Table(name = "paper_log")
@Entity
@Getter
@Setter
public class PaperLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod = PaymentMethodEnum.CASH;

    @Column(name = "datetime", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime datetime;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    private StudentEntity student;
}
