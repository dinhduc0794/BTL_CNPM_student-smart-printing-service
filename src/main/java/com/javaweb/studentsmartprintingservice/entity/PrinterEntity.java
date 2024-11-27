package com.javaweb.studentsmartprintingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "printer")
@Entity
@Data
@Getter
@Setter
public class PrinterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "campus", nullable = false)
    private String campus;

    @Column(name = "building", nullable = false)
    private String building;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = true;

    @Column(name = "is_scheduled", nullable = false)
    private Boolean isScheduled = false;

    @Column(name = "description")
    private String description;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "paper_a1_left")
    private Long paperA1left;

    @Column(name = "paper_a2_left")
    private Long paperA2left;

    @Column(name = "paper_a3_left")
    private Long paperA3left;

    @Column(name = "paper_a4_left")
    private Long paperA4left;

    @OneToMany(mappedBy = "printer")
    @JsonManagedReference
    private List<PrintingLogEntity> printingLogs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "spso_id")
    @JsonBackReference
    private SpsoEntity spso;
}
