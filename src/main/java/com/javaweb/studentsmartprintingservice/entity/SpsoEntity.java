package com.javaweb.studentsmartprintingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "spso")
@Entity
@Data
@Getter
@Setter
public class SpsoEntity extends BaseEntity {
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

    @OneToMany(mappedBy = "spso")
    @JsonManagedReference
    private List<PrinterEntity> printers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "systemconfig_id")
    @JsonBackReference
    private SystemConfigEntity systemConfig;
}
