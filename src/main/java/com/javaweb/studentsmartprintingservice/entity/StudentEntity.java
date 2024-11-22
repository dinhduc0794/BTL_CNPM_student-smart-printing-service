package com.javaweb.studentsmartprintingservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javaweb.studentsmartprintingservice.enums.FacultyEnum;
import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "student")
@Entity
@Getter
@Setter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "faculty", nullable = false)
    @Enumerated(EnumType.STRING)
    private FacultyEnum faculty;

    @Column(name = "paper_quantity", nullable = false)
    private Long paperQuantity = 5L;

    @Column(name = "is_color", nullable = false)
    private Boolean isColor = false;

    @Column(name = "page_size", nullable = false)
    @Enumerated(EnumType.STRING)
    private PageSizeEnum pageSize = PageSizeEnum.A4;

    @Column(name = "is_2_side", nullable = false)
    private Boolean is2Side = true;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private List<PaperLogEntity> paperLogs = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private List<PrintingLogEntity> printingLogs = new ArrayList<>();
}
