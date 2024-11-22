package com.javaweb.studentsmartprintingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "paper_log")
@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class PaperLogEntity extends OrderLogEntity {
    @Column(name = "quanity", nullable = false)
    private Long quanity;
}
