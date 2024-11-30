package com.javaweb.studentsmartprintingservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "system_config")
@Entity
@Getter
@Setter
public class SystemConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reset_period")
    private Integer pageResetPeriod = 120;

    @Column(name = "page_quantity")
    private Integer pageQuantity = 100;

    @Column(name = "permitted_files")
    private String permittedFiles;

    @Column(name = "permitted_sizes")
    private String permittedSize;

    @Column(name = "permitted_payment_methods")
    private String permittedPaymentMethods;

    @Column(name = "unit_price")
    private Long unitPrice = 1000L;

    @OneToMany(mappedBy = "systemConfig")
    @JsonManagedReference
    private List<SpsoEntity> spsoEntities = new ArrayList<>();
}
