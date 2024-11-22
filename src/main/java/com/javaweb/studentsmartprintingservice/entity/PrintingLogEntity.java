package com.javaweb.studentsmartprintingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import com.javaweb.studentsmartprintingservice.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Table(name = "printing_log")
@Entity
@Getter
@Setter
public class PrintingLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod = PaymentMethodEnum.CASH;

    @Column(name = "datetime", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime datetime;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "number_of_copies", nullable = false)
    private Long numberOfCopies = 1L;

    @Column(name = "document_path", nullable = false)
    private String documentPath = "./document/document1.pdf";

    @Column(name = "color", nullable = false)
    private Boolean isColor = false;

    @Column(name = "page_size", nullable = false)
    @Enumerated(EnumType.STRING)
    private PageSizeEnum pageSize;

    @Column(name = "is_2_side", nullable = false)
    private Boolean is2Side;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    private StudentEntity student;

    //quan he n-1 printer
    @ManyToOne
    @JoinColumn(name = "printer_id", nullable = false)
    @JsonBackReference
    private PrinterEntity printer;
}
