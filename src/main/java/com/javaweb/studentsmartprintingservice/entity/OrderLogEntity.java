package com.javaweb.studentsmartprintingservice.entity;

import com.javaweb.studentsmartprintingservice.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class OrderLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method", nullable = false)
    private PaymentMethodEnum paymentMethod = PaymentMethodEnum.CASH;

    @Column(name = "datetime", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime datetime;
}
