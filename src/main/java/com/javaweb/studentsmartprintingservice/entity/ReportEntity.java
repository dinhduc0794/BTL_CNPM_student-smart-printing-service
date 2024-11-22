package com.javaweb.studentsmartprintingservice.entity;

import com.javaweb.studentsmartprintingservice.enums.ReportType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Table(name = "report")
@Entity
@Getter
@Setter
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datetime", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime datetime;

    @Column(name = "type", nullable = false)
    private ReportType type;
}
