package com.javaweb.studentsmartprintingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

@Table(name = "printer")
@Entity
@Getter
@Setter
public class PrinterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "campus", nullable = false)
    private String campus;

    @Column(name = "name", nullable = false)
    private String building;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "is_enable", nullable = false)
    private Boolean isEctive = true;

    @Column(name = "is_scheduled", nullable = false)
    private Boolean isScheduled = false;

    @Column(name = "description")
    private String description;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "paper_a4_left")
    private Long paperA4left;

    @Column(name = "paper_a5_left")
    private Long paperA5left;

    @Column(name = "paper_a6_left")
    private Long paperA6left;

}
