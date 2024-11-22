package com.javaweb.studentsmartprintingservice.entity;

import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private List<String> permittedFiles;

    @Column(name = "unit_price")
    private Long unitPrice = 1000L;
}
