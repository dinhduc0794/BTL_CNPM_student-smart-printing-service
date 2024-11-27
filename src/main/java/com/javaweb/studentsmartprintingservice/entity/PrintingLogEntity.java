package com.javaweb.studentsmartprintingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import com.javaweb.studentsmartprintingservice.enums.PaymentMethodEnum;
import com.javaweb.studentsmartprintingservice.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Table(name = "printing_log")
@Entity
@Data
@Getter
@Setter
public class PrintingLogEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_path", nullable = false)
    private String documentPath = "./document/document1.pdf";

    @Column(name = "document_pages", nullable = false)
    private Long documentPages;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "number_of_copies", nullable = false)
    private Long numberOfCopies = 1L;

    @Column(name = "is_scheduled", nullable = false)
    private Boolean isScheduled = false;

    @Column(name = "scheduled_time")
    private ZonedDateTime scheduledTime;

    @Column(name = "note")
    private String note;

    @Column(name = "color", nullable = false)
    private Boolean isColor = false;

    @Column(name = "page_size", nullable = false)
    @Enumerated(EnumType.STRING)
    private PageSizeEnum pageSize;

    @Column(name = "is_2_side", nullable = false)
    private Boolean is2Side;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.PENDING;;

    @Column(name = "message")
    private String message;

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
